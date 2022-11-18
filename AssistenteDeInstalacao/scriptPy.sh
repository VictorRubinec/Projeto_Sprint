echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Api instalada com sucesso!!"
sleep 5

# Baixando pacote para conexão com a azure
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Baixando pacote para azure"
wget https://packages.microsoft.com/debian/11/prod/pool/main/m/msodbcsql18/msodbcsql18_18.0.1.1-1_amd64.deb
apt update && apt upgrade -y
sleep 5
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Instalando pacote para azure"
apt install ./msodbcsql18_18.0.1.1-1_amd64.deb
sleep 5
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Feito!!"

# Iniciando api
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Preparando ambiente para iniciar api"
cd API-Python/
bash ./bibliotecas.sh
cd venv/
source bin/activate
cd ..
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Tudo finalizado, iniciando aplicação"
sleep 3
python main.py
