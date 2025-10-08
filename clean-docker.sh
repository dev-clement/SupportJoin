#!/bin/bash

# Stop all running containers
docker stop $(docker ps -aq)

# Remove all containers
docker rm -f $(docker ps -aq)

# Remove all images
docker rmi -f $(docker images -q)

# Remove all volumes
docker volume rm $(docker volume ls -q)

# Remove all networks (except default ones)
docker network rm $(docker network ls -q)

# Optional: Clean up any remaining unused data
docker system prune -a --volumes -f
