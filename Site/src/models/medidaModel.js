var database = require("../database/config");

function buscarUltimasMedidasCpu(serialNumber, limite_linhas) {
    
    var instrucaoSql = ''
    
    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `SELECT top ${limite_linhas}
                        Registro, 
                        CONVERT(varchar, Horario, 108) as momento_grafico
                    FROM vwConsumo
                    WHERE NumeroSerial = '${serialNumber}' AND Componente = 'cpu'
                    ORDER BY idRegistro DESC`;
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `SELECT 
                        Registro, 
                        DATE_FORMAT(Horario,'%H:%i:%s') as momento_grafico
                    FROM vwConsumo
                    WHERE NumeroSerial = '${serialNumber}' AND Componente = 'cpu'
                    ORDER BY ID DESC LIMIT ${limite_linhas}`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }
    
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarUltimasMedidasRam(serialNumber, limite_linhas) {
    
    var instrucaoSql = ''
    
    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `SELECT top ${limite_linhas}
                        Registro, 
                        CONVERT(varchar, Horario, 108) as momento_grafico
                    FROM vwConsumo
                    WHERE NumeroSerial = '${serialNumber}' AND Componente = 'cpu'
                    ORDER BY idRegistro DESC`;
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `SELECT 
                        Registro, 
                        DATE_FORMAT(Horario,'%H:%i:%s') as momento_grafico
                    FROM vwConsumo
                    WHERE NumeroSerial = '${serialNumber}' AND Componente = 'ram'
                    ORDER BY ID DESC LIMIT ${limite_linhas}`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }
    
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarUltimasMedidasDisco(serialNumber) {
    
    var instrucaoSql = ''
    
    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = ``;
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `SELECT Registro from vwConsumo WHERE componente = 'disco' and NumeroSerial = '${serialNumber}' ORDER BY ID DESC LIMIT 1`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }
    
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarMaxDisco(serialNumber) {
    
    var instrucaoSql = ''
    
    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = ``;
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `SELECT valorMaximo from vwMaquina WHERE componente = 'disco' and NumeroSerial = '${serialNumber}'`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }
    
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}


function buscarMedidasEmTempoRealCpu(serialNumber) {
    
    instrucaoSql = ''
    
    if (process.env.AMBIENTE_PROCESSO == "producao") {       
        instrucaoSql = `SELECT top 1
                        Registro, 
                        CONVERT(varchar, dataHoraRgt, 108) as momento_grafico, 
                        fkSensor 
                        from registro where fkSensor = '${serialNumber}' and fkTipo = 1
                    order by idRegistro desc`;
        
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `SELECT 
                        Registro, 
                        DATE_FORMAT(Horario,'%H:%i:%s') as momento_grafico 
                        from vwConsumo where NumeroSerial = '${serialNumber}' and Componente = 'cpu'
                    ORDER BY ID DESC LIMIT 1`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarMedidasEmTempoRealRam(serialNumber) {
    
    instrucaoSql = ''
    
    if (process.env.AMBIENTE_PROCESSO == "producao") {       
        instrucaoSql = `SELECT top 1
                        Registro, 
                        CONVERT(varchar, dataHoraRgt, 108) as momento_grafico, 
                        fkSensor 
                        from registro where fkSensor = '${serialNumber}' and fkTipo = 1
                    order by idRegistro desc`;
        
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `SELECT 
                        Registro, 
                        DATE_FORMAT(Horario,'%H:%i:%s') as momento_grafico 
                        from vwConsumo where NumeroSerial = '${serialNumber}' and Componente = 'ram'
                    ORDER BY ID DESC LIMIT 1`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}


module.exports = {
    buscarUltimasMedidasCpu,
    buscarUltimasMedidasRam,
    buscarMedidasEmTempoRealCpu,
    buscarMedidasEmTempoRealRam,
    buscarUltimasMedidasDisco,
    buscarMaxDisco
}
