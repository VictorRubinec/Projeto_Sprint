CREATE USER 'kashUser'@'localhost' IDENTIFIED BY 'kash';
GRANT ALL PRIVILEGES ON databaseKashPlus.* TO 'kashUser'@'localhost';

create database databaseKashPlus;
use databaseKashPlus;


CREATE TABLE Banco(
cnpj CHAR(18) PRIMARY KEY 
,nomeEmpresarial VARCHAR(100)
,emailEmpresa VARCHAR(100)
);

CREATE TABLE Usuario(
idUsuario INT PRIMARY KEY AUTO_INCREMENT
,nome VARCHAR(100)
,email VARCHAR(100) UNIQUE
,senha VARCHAR(100)
,cargo CHAR(3)
,fkBanco CHAR(18), FOREIGN KEY(fkBanco) REFERENCES Banco(cnpj)
)auto_increment = 100;

CREATE TABLE Regiao(
idRegiao INT PRIMARY KEY AUTO_INCREMENT
,nomeRegiao VARCHAR(200)
);

CREATE TABLE CaixaEletronico(
idCaixaEletronico INT PRIMARY KEY AUTO_INCREMENT
,serialNumber CHAR(12)
,localizacao VARCHAR(300)
,fkBanco CHAR(18), FOREIGN KEY(fkBanco) REFERENCES banco(cnpj)
,fkRegiao INT, FOREIGN KEY(fkRegiao) REFERENCES Regiao(idRegiao)
);

CREATE TABLE Registro(
idRegistro INT PRIMARY KEY AUTO_INCREMENT
,fkCaixaEletronico INT, FOREIGN KEY(fkCaixaEletronico) REFERENCES CaixaEletronico(idCaixaEletronico)
,dataHorario DATETIME
,consumoCPU DECIMAL(5,2)
,temperaturaCPU DECIMAL(5,2)
,consumoRAM DECIMAL(5,2)
,usoDisco DECIMAL(5,2)
);