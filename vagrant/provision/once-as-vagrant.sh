#!/usr/bin/env bash

#== Import script args ==

prompt=$(echo "$1")

#== Provision script ==

echo "Creating bash-alias 'spring' and 'angular' for vagrant user..."
    grep -qxF 'alias spring="cd /app/shopgular-backend &&"' /home/vagrant/.bashrc || echo 'alias spring="cd /app/shopgular-backend &&"' >> /home/vagrant/.bashrc
    grep -qxF 'alias angular="cd /app/shopgular-frontend &&"' /home/vagrant/.bashrc || echo 'alias angular="cd /app/shopgular-frontend &&"' >> /home/vagrant/.bashrc

echo "Enabling colorized prompt for guest console..."
    grep -qxF 'export PS1="'${prompt}' "' /home/vagrant/.bashrc || echo 'export PS1="'${prompt}' "' >> /home/vagrant/.bashrc
