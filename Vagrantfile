require 'yaml'

config = './vagrant/config/vagrant-local.yml'

domains = {
  backend:  'localhost:8089',
  frontend: 'localhost:4200'
}

# read config
options = YAML.load_file config

# vagrant configurate
Vagrant.configure(2) do |config|

  # select the box
  config.vm.box = "centos/7"

  # should we ask about box updates?
  config.vm.box_check_update = true

  # upgrade linux kernel
  config.vbguest.installer_options = { allow_kernel_upgrade: true }

  # machine configurate
  config.vm.provider 'virtualbox' do |vb|

    # machine cpus count
    vb.cpus = options['cpus']

    # machine memory size
    vb.memory = options['memory']

    # machine name (for VirtualBox UI)
    vb.name = options['machine_name']

  end

  # machine name (for vagrant console)
  config.vm.define options['machine_name']

  # machine name (for guest machine console)
  config.vm.hostname = options['machine_name']

  # network settings
  config.vm.network 'private_network', ip: options['ip']
  config.vm.network "forwarded_port", guest: 4200, host: 4200 ### Nginx ###
  config.vm.network "forwarded_port", guest: 8080, host: 8080 ### Jenkins ###
  config.vm.network "forwarded_port", guest: 8081, host: 8081 ### Nexus ###
  config.vm.network "forwarded_port", guest: 8089, host: 8089 ### Tomcat ###
  config.vm.network "forwarded_port", guest: 9000, host: 9000 ### SonarQube ###

  # sync: folder 'shopgular' (host machine) -> folder '/app' (guest machine)
  config.vm.synced_folder './', '/app', owner: 'vagrant', group: 'vagrant'

  # disable folder '/vagrant' (guest machine)
  config.vm.synced_folder '.', '/vagrant', disabled: true

  # hosts settings (host machine)
  config.vm.provision :hostmanager
  config.hostmanager.enabled = true
  config.hostmanager.manage_host = true
  config.hostmanager.ignore_private_ip = false
  config.hostmanager.include_offline = true
  config.hostmanager.aliases = domains.values

  # provisioners
  config.vm.provision 'shell', path: './vagrant/provision/once-as-root.sh', args: [options['timezone']]
  config.vm.provision 'shell', path: './vagrant/provision/once-as-vagrant.sh', privileged: false, args: [options['prompt']]
  config.vm.provision 'shell', path: './vagrant/provision/always-as-root.sh', run: 'always'

  # post-install message (vagrant console)
  config.vm.post_up_message = "Backend URL: http://#{domains[:backend]}\nFrontend URL: http://#{domains[:frontend]}"

end
