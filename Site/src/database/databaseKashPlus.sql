create database dbKashPlus;
use dbKashPlus;

CREATE TABLE tbEmpresa(
cnpj CHAR(14) PRIMARY KEY
,nome VARCHAR(100)
,email VARCHAR(100)
,telefoneFixo CHAR(10)
,telefoneCelular CHAR(11)
);

CREATE TABLE tbUsuario(
idUsuario INT PRIMARY KEY AUTO_INCREMENT
,fkEmpresa CHAR(14), FOREIGN KEY(fkEmpresa) REFERENCES tbEmpresa(cnpj)
,nome VARCHAR(100)
,login VARCHAR(100)
,senha VARCHAR(100)
,cargo CHAR(3)
);

CREATE TABLE tbMaquina(
serialNumber INT PRIMARY KEY
,fkEmpresa CHAR(14), FOREIGN KEY(fkEmpresa) REFERENCES tbEmpresa(cnpj)
,cep CHAR(8)
,modeloCpu VARCHAR(100)
,nuclesFisicos INT
,nuclesLogicos INT
,frequenciaMaxCpu DECIMAL(5,2)
,tempMaxCpu DECIMAL(5,2)
,ramMax DECIMAL(5,2)
,discoMax DECIMAL(7,2)
);

CREATE TABLE tbRegistro(
idRegistro INT PRIMARY KEY AUTO_INCREMENT
,fkMaquina INT, FOREIGN KEY(fkMaquina) REFERENCES tbMaquina(serialNumber)
,usoCpu DECIMAL(5,2)
,tempCpu DECIMAL(5,2)
,freqCpu DECIMAL(5,2)
,usoRam DECIMAL(5,2)
,usoDisco DECIMAL(5,2)
,dataHorario DATETIME
);
