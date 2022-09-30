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
serialNumber VARCHAR(30) PRIMARY KEY
,fkEmpresa CHAR(14), FOREIGN KEY(fkEmpresa) REFERENCES tbEmpresa(cnpj)
,nome VARCHAR(100)
,cep CHAR(8)
);

CREATE TABLE tbComponente(
idComponente INT PRIMARY KEY AUTO_INCREMENT
,fkMaquina VARCHAR(30), FOREIGN KEY(fkMaquina) REFERENCES tbMaquina(serialNumber)
,tipo VARCHAR(100)
,metrica VARCHAR(100)
)
;

CREATE TABLE tbRegistro(
idRegistro INT PRIMARY KEY AUTO_INCREMENT
,fkComponente INT, FOREIGN KEY(fkComponente) REFERENCES tbComponente(idComponente)
,registro VARCHAR(50)
,dataHora DATETIME
);

-- Criando Views --

CREATE VIEW vwMaquina AS
    SELECT 
        tbEmpresa.nome AS 'Empresa'
        ,tbMaquina.nome AS 'Máquina'
        ,serialNumber AS 'Número Serial'
        ,cep AS 'Local (CEP)'
        ,tipo AS 'Componente'
    FROM
        tbEmpresa
            JOIN
        tbMaquina ON cnpj = fkEmpresa
			JOIN
		tbComponente ON fkMaquina = serialNumber
;
        
CREATE VIEW vwConsumo AS
    SELECT 
		idRegistro AS 'ID'
        ,tbMaquina.nome AS 'Máquina'
        ,serialNumber AS 'Número Serial'
        ,tipo AS 'Componente'
        ,registro AS 'Registro'
        ,dataHora AS 'Horário'
    FROM
        tbMaquina
            JOIN
		tbComponente ON fkMaquina = serialNumber
			JOIN
		tbRegistro ON fkComponente = idComponente
;

CREATE VIEW vwFuncionario AS
    SELECT 
		tbEmpresa.nome AS 'Máquina'
		,tbUsuario.nome AS 'Funcionario'
        ,cargo AS 'Cargo'
        ,login AS 'Login'
    FROM
        tbEmpresa
            JOIN
        tbUsuario ON cnpj = fkEmpresa
;