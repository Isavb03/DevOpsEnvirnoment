FROM jenkins/jenkins:lts-jdk17

USER root

# Instala Docker CLI y curl en una sola capa, y limpia cache
RUN apt-get update \
 && apt-get install -y --no-install-recommends \
      docker.io \
      curl \
 && rm -rf /var/lib/apt/lists/*

# Descarga, valida y instala kubectl en /usr/local/bin
RUN K8S_VER="$(curl -L -s https://dl.k8s.io/release/stable.txt)" \
 && curl -LO "https://dl.k8s.io/release/${K8S_VER}/bin/linux/amd64/kubectl" \
 && install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl \
 && rm kubectl

RUN apt-get update && apt-get install -y gettext

# Install Node.js
RUN curl -fsSL https://deb.nodesource.com/setup_16.x | bash - && \
    apt-get install -y nodejs && \
    node -v && npm -v

# Añade el usuario jenkins al grupo docker
RUN usermod -aG docker jenkins

USER jenkins


