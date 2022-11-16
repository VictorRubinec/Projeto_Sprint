#!/bin/bash

PURPLE='0;35'
NC='\033[0m' 
VERSAO=11
	
echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Olá, serei seu assistente para instalação dos recursos necessários para a utilização dos serviços da kash+;"
echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Primeiro vamos instalar o Java;"
echo  "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Verificando aqui se você possui o Java instalado...;"
sleep 2

java -version
if [ $? -eq 0 ]
	then
		echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) : Você já tem o java instalado!!!"
	else
		echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7)  Opa! Não identifiquei nenhuma versão do Java instalado, mas sem problemas, irei resolver isso agora!"
		echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7)  Instalando o Java em seu computador !!"
	
		echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7)  Adicionando o repositório!"
		sleep 2
		sudo add-apt-repository ppa:webupd8team/java -y
		clear
		echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7)  Atualizando! Quase lá."
		sleep 2
		sudo apt update -y
		clear
		
		if [ $VERSAO -eq 11 ]
			then
				echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Preparando para instalar a versão 11 do Java. Confirme a instalação quando solicitado ;D"
				sudo apt install default-jre ; apt install openjdk-11-jre-headless; -y
				clear
				echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Java instalado com sucesso!"
		fi
fi

echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Agora que já instalamos o Java, vamos verificar se o Python está instalado;"

which python3
if [ $? -eq 1 ]
	then
	
	echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Instalando o Python;"

	sleep 1

	sudo apt-get install python3
	clear

	else
	
	echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) A máquina já possui o Python instalado;"

	sleep 1

fi

echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Agora que temos já garatimos que a máquina possui o Java e o Python, vamos clonar o repositório;"
sleep 2

cd 
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Vamos ver se já temos o repositório clonado;"
cd Projeto_Sprint/
if [ $? -eq 1]
	then

	git clone https://github.com/KASH-PLUS/Projeto_Sprint.git

	cd Projeto_Sprint
	echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Feito!! Agora vamos configurar o ambiente virtual para que nossa aplicação Python funcione;"
	sleep 2

	cd API-Python 
	python -m venv venv

	cd venv
	source bin/activate
	cd ..

	echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Ambiente virtual criado!!, agora vamos instalar as bibliotecas necessárias para que a aplicação Python funcione;"

	sleep 2

	python -m pip install py-cpuinfo
	python -m pip install psutil
	python -m pip install mysql-connector-python
	python -m pip install matplot
	python -m pip install dashing
	python -m pip install openpyxl

	else

	echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Você já possui o repositório no seu computador;"
	echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Gostaria de atualizar o repositório? [y/n]"
	read resposta

	if [$reposta -eq "y"]{
		cd Projeto_Sprint
		git pull
		cd
	}

fi

sleep 2

clear
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Pronto, seu computador está pronto para usar os serviços da Kash+;"

sleep 2
