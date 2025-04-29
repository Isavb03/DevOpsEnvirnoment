# Usa la imagen oficial de Jenkins con JDK17
FROM jenkins/jenkins:lts-jdk17

# Cambia a root para instalar paquetes
USER root

# Instala el cliente Docker y limpia la cache
RUN apt-get update \
    && apt-get install -y docker.io \
    && rm -rf /var/lib/apt/lists/*

# Vuelve al usuario jenkins
USER jenkins
