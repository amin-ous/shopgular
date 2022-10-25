#!/usr/bin/env bash

#== Provision script ==

echo "Enabling root user..."
    sudo su

echo "Setting Docker permissions..."
    chmod 666 /var/run/docker.sock
