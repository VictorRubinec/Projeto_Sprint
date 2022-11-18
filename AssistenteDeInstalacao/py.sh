wget https://packages.microsoft.com/debian/11/prod/pool/main/m/msodbcsql17/msodbcsql17_17.10.1.1-1_amd64.deb

apt update
apt upgrade -y

clear

apt install ./msodbcsql17_17.10.1.1-1_amd64.deb -y

clear

cd API-Python

bash bibliotecas.sh

apt update
apt upgrade -y