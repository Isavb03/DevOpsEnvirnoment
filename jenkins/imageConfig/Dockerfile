FROM jenkins/jenkins:lts-jdk17
USER root

# Instala Docker CLI
RUN apt-get update \
 && apt-get install -y docker.io \
 && rm -rf /var/lib/apt/lists/*

# Añade el usuario jenkins al grupo docker
RUN usermod -aG docker jenkins

USER jenkins

