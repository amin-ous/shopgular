#!/usr/bin/env bash

#== Provision script ==

echo "Enabling root user..."
    sudo su

echo "Setting Docker permissions..."
    chmod 666 /var/run/docker.sock

echo "Starting backend containers..."
    cd /app/shopgular-backend && docker compose start

echo "Starting frontend containers..."
    cd /app/shopgular-frontend && docker compose start
