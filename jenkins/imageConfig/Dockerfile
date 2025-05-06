FROM jenkins/jenkins:lts-jdk17
USER root

# Instala Docker CLI
RUN apt-get update \
 && apt-get install -y docker.io \
 && rm -rf /var/lib/apt/lists/*

# Instalar kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
    && chmod +x kubectl \
    && mv kubectl /usr/local/bin/

# Añade el usuario jenkins al grupo docker
RUN usermod -aG docker jenkins

USER jenkins

