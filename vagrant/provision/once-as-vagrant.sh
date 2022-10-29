#!/usr/bin/env bash

#== Import script args ==

prompt=$(echo "$1")

#== Provision script ==

echo "Creating bash-alias 'spring' and 'angular' for vagrant user..."
    grep -qxF 'alias spring="cd /app/shopgular-backend &&"' /home/vagrant/.bashrc || echo 'alias spring="cd /app/shopgular-backend &&"' >> /home/vagrant/.bashrc
    grep -qxF 'alias angular="cd /app/shopgular-frontend &&"' /home/vagrant/.bashrc || echo 'alias angular="cd /app/shopgular-frontend &&"' >> /home/vagrant/.bashrc

echo "Enabling colorized prompt for guest console..."
    grep -qxF 'export PS1="'${prompt}' "' /home/vagrant/.bashrc || echo 'export PS1="'${prompt}' "' >> /home/vagrant/.bashrc

echo "Configuring Grafana..."
    curl 'http://admin:admin@localhost:3000/api/datasources' -X POST -H 'Content-Type: application/json;charset=UTF-8' --data-binary \
    '{"name":"Jenkins: Performance and Health Overview","type":"prometheus","url":"http://192.168.56.1:9090","access":"proxy","isDefault":true,"jsonData":{"httpMethod":"POST"}}'
