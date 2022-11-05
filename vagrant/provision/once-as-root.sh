#!/usr/bin/env bash

#== Import script args ==

timezone=$(echo "$1")

#== Provision script ==

echo "Enabling root user..."
    sudo su

echo "Configuring timezone..."
    timedatectl set-timezone ${timezone}

echo "Updating OS software..."
    yum -y update && yum -y upgrade

echo "Installing additional software..."
    yum -y install epel-release git java-11-openjdk-devel nano wget yum-utils

echo "Installing Maven..."
    cd /usr/tmp && wget -nc http://mirrors.ibiblio.org/apache/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
    cd /usr/share && tar xf /usr/tmp/apache-maven-3.8.6-bin.tar.gz --transform 's/apache-maven-3.8.6/maven/'

echo "Configuring Maven..."
    username=$(sed -n '/^      <username>repouser<\/username>/=' "/usr/share/maven/conf/settings.xml")
    if [ ! -z "${username}" ]; then
        sed -i "$(expr ${username} - 3)s/.*/     |-->/" "/usr/share/maven/conf/settings.xml"
        sed -i "$(expr ${username} + 0)s/.*/      <username>admin<\/username>/" "/usr/share/maven/conf/settings.xml"
        sed -i "$(expr ${username} + 1)s/.*/      <password>shopgular<\/password>/" "/usr/share/maven/conf/settings.xml"
        sed -i "$(expr ${username} + 3)d" "/usr/share/maven/conf/settings.xml"
    fi

echo "Configuring environment variables..."
    grep -qxF 'export JAVA_HOME=/usr/lib/jvm/jre-11-openjdk' /etc/profile || echo 'export JAVA_HOME=/usr/lib/jvm/jre-11-openjdk' >> /etc/profile
    grep -qxF 'export JRE_HOME=/usr/lib/jvm/jre' /etc/profile || echo 'export JRE_HOME=/usr/lib/jvm/jre' >> /etc/profile
    grep -qxF 'export MAVEN_HOME=/usr/share/maven' /etc/profile || echo 'export MAVEN_HOME=/usr/share/maven' >> /etc/profile
    grep -qxF 'export PATH=$MAVEN_HOME/bin:$PATH' /etc/profile || echo 'export PATH=$MAVEN_HOME/bin:$PATH' >> /etc/profile

echo "Installing Jenkins..."
    curl --silent --location http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo | tee /etc/yum.repos.d/jenkins.repo
    rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key
    yum -y install jenkins

echo "Starting Jenkins..."
    systemctl start jenkins && systemctl enable jenkins

echo "Configuring Jenkins..."
    if [[ ! -e /var/lib/jenkins/hudson.tasks.Maven.xml ]]; then
        touch /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "<?xml version='1.1' encoding='UTF-8'?>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "<hudson.tasks.Maven_-DescriptorImpl>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "  <installations>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "    <hudson.tasks.Maven_-MavenInstallation>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "      <name>MAVEN_HOME</name>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "      <home>/usr/share/maven</home>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "      <properties/>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "    </hudson.tasks.Maven_-MavenInstallation>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "  </installations>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
        echo "</hudson.tasks.Maven_-DescriptorImpl>" >> /var/lib/jenkins/hudson.tasks.Maven.xml
    fi
    jdk=$(sed -n '/^  <jdks\/>/=' "/var/lib/jenkins/config.xml")
    if [ ! -z "${jdk}" ]; then
        sed -i "${jdk}s/.*/  <jdks>/" "/var/lib/jenkins/config.xml"
        sed -i "$(expr ${jdk} + 1)i\    <jdk>" "/var/lib/jenkins/config.xml"
        sed -i "$(expr ${jdk} + 2)i\      <name>JAVA_HOME</name>" "/var/lib/jenkins/config.xml"
        sed -i "$(expr ${jdk} + 3)i\      <home>/usr/lib/jvm/jre-11-openjdk</home>" "/var/lib/jenkins/config.xml"
        sed -i "$(expr ${jdk} + 4)i\      <properties/>" "/var/lib/jenkins/config.xml"
        sed -i "$(expr ${jdk} + 5)i\    </jdk>" "/var/lib/jenkins/config.xml"
        sed -i "$(expr ${jdk} + 6)i\  </jdks>" "/var/lib/jenkins/config.xml"
    fi

echo "Installing Jenkins plugins..."
    cd /usr/tmp && wget -nc "http://localhost:8080/jnlpJars/jenkins-cli.jar"
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin cloudbees-folder
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin antisamy-markup-formatter
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin build-timeout
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin credentials-binding
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin timestamper
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin ws-cleanup
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin ant
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin gradle
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin workflow-aggregator
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin github-branch-source
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin pipeline-github-lib
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin pipeline-stage-view
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin git
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin ssh-slaves
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin matrix-auth
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin pam-auth
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin ldap
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin email-ext
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin mailer
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin maven-plugin
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin nexus-jenkins-plugin
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin sonargraph-integration
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin sonar
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin docker-workflow
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin prometheus
    java -jar jenkins-cli.jar -s http://localhost:8080/ -auth admin:$(cat /var/lib/jenkins/secrets/initialAdminPassword) install-plugin cloudbees-disk-usage-simple
    systemctl restart jenkins

echo "Installing Docker Engine..."
    yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
    yum install -y docker-ce docker-ce-cli containerd.io docker-compose-plugin

echo "Starting Docker..."
    systemctl start docker && systemctl enable docker

echo "Configuring Prometheus..."
    if [[ ! -e /etc/prometheus/prometheus.yml ]]; then
        mkdir -p /etc/prometheus
        touch /etc/prometheus/prometheus.yml
        echo "global:" >> /etc/prometheus/prometheus.yml
        echo "  scrape_interval: 15s" >> /etc/prometheus/prometheus.yml
        echo "  evaluation_interval: 15s" >> /etc/prometheus/prometheus.yml
        echo "alerting:" >> /etc/prometheus/prometheus.yml
        echo "  alertmanagers:" >> /etc/prometheus/prometheus.yml
        echo "    - static_configs:" >> /etc/prometheus/prometheus.yml
        echo "      - targets:" >> /etc/prometheus/prometheus.yml
        echo "scrape_configs:" >> /etc/prometheus/prometheus.yml
        echo "  - job_name: 'prometheus'" >> /etc/prometheus/prometheus.yml
        echo "    static_configs:" >> /etc/prometheus/prometheus.yml
        echo "      - targets: ['localhost:9090']" >> /etc/prometheus/prometheus.yml
        echo "  - job_name: 'jenkins'" >> /etc/prometheus/prometheus.yml
        echo "    metrics_path: /prometheus" >> /etc/prometheus/prometheus.yml
        echo "    static_configs:" >> /etc/prometheus/prometheus.yml
        echo "      - targets: ['192.168.56.1:8080']" >> /etc/prometheus/prometheus.yml
    fi

echo "Starting backend containers..."
    if [ ! "$(cd /app/shopgular-backend && docker compose ps -a)" ]; then
        docker compose build && docker compose up -d && docker restart portainer > /dev/null 2>&1
    fi

echo "Installing Nginx..."
    yum -y install nginx

echo "Starting Nginx..."
    systemctl start nginx && systemctl enable nginx

echo "Starting frontend containers..."
    if [ ! "$(cd /app/shopgular-frontend && docker compose ps -a)" ]; then
        docker compose build && docker compose up -d
    fi
