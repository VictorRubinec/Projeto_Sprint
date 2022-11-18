#!/bin/bash

PURPLE='0;35'
NC='\033[0m' 
VERSAO=11

clear
	
echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Olá, serei seu assistente para instalação dos recursos necessários para a utilização dos serviços da kash+;"
echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Primeiro vamos clonar nosso repositório;"
sleep 2

git clone https://github.com/KASH-PLUS/Projeto_Sprint.git

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Agora vamos criar o docker com o banco do projeto;"

sudo systemctl stop docker

sudo apt install docker.io

sudo systemctl start docker

sudo systemctl enable docker

sudo docker pull mysql:5.7
clear

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Digite o nome Docker que deseja criar:"
read nomeDocker
sudo docker run -d -p 3306:3306 --name $nomeDocker -e "MYSQL_ROOT_PASSWORD=urubu100" mysql:5.7
clear

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) A senha de acesso do MySQL é 'urubu100'"
pause 3

sudo docker cp ./nomescript.sql $nomeDocker:docker-entrypoint-initdb.d/

sudo docker exec -it $nomeDocker bash

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Banco de Dados do projeto criado;"

clear

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Vamos configurar o container Python agora;"
pause 3

sudo systemctl stop docker

sudo systemctl start docker

sudo systemctl enable docker

sudo docker pull python
clear

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Digite o nome Docker que deseja criar:"
read nomeDocker
sudo docker run -d -t --name $nomeDocker python
clear

sudo docker cp ./Projeto_Sprint/API-Python $nomeDocker:/

sudo docker cp ./py.sh $nomeDocker:/
sudo docker exec -it $nomeDocker bash py.sh
clear

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Docker python configurado!"
pause 2

echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Agora sua máquina está totalmente configurada"
pause 3