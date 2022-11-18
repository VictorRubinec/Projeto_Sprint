
# Removendo dockers
sudo docker stop ContainerSql
clear
sudo docker rm ContainerSql
clear
sudo docker stop ContainerPy
clear
sudo docker rm ContainerPy
clear

# Instalando docker
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) O primeiro passo é instalar o docker..."
sleep 3
sudo apt install docker.io
sudo systemctl start docker
sudo systemctl enable docker
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Feito!!"
sleep 5
clear

# Instalar a imagem mysql no docker
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) O próximo passo é instalar o docker do mysql..."
sleep 3
sudo docker pull mysql:5.7
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Feito!!"
sleep 5
clear

# Executando o container que contem o mysql
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Com o container do sql instalado, é preciso executá-lo..."
sleep 3
sudo docker run -d -p 3306:3306 --name ContainerSql -e "MYSQL_DATABASE=banco1" -e "MYSQL_ROOT_PASSWORD=urubu100" mysql:5.7
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Feito!!"
sleep 5
clear
# Criado banco de dados
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Estou criando seu banco"
cd Projeto_Sprint/
cd Banco_de_Dados/
sudo docker cp databaseKashPlus.sql ContainerSql:docker-entrypoint-initdb.d/
# Startando o containersql no bash
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Sua senha é urubu100"
sudo docker exec -it ContainerSql mysql -u root -p
source databaseKashPlus.sql
sleep 3
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Banco criado com sucesso!!"
sleep 5
clear

# Instalar a imagem python no docker
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) O próximo passo é instalar o docker do python..."
sleep 3
sudo docker run -d -t --name ContainerPy python
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Feito!!"
sleep 5
clear

# Executando o container que contem o python
echo "$(tput setaf 10)[Kash+ Assistant]:$(tput setaf 7) Só um instante, estou instalando a api python..."
sleep 3
cd ..
# Copiando api python para o container
sudo docker cp API-Python/ ContainerPy:/
sudo docker cp ./scriptPy.sh ContainerPy:/
# Startando o containerSql no bash
sudo docker exec -it ContainerPy bash scriptPy.sh


