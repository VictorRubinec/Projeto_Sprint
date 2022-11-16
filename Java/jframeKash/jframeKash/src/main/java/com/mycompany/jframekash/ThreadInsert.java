/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jframekash;

import banco.Conexao;
import banco.TbComponente;
import banco.TbComponenteCrud;
import org.springframework.jdbc.core.JdbcTemplate;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinicius
 */
public class ThreadInsert extends Thread {

    private String serialNumber;

    TbComponenteCrud componeteCrud = new TbComponenteCrud();
    Conexao con = new Conexao();
    JdbcTemplate cursor = con.getConexao();
    Looca looca = new Looca();

    public ThreadInsert() {

    }

    @Override
    public void run() {
        getComponentes();
    }

    private void getComponentes() {
        List<TbComponente> componentes = componeteCrud.selecionar(serialNumber);

        for (TbComponente componente : componentes) {
            insertRegistro(componente.getIdComponente(), componente.getTipo());
        }
    }

    private void insertRegistro(Integer fkComponente, String tipo) {

        Processador processador = looca.getProcessador();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Volume> volumes = grupoDeDiscos.getVolumes();

        Long discoDisponivel = volumes.get(0).getDisponivel();
        Double usoCpu = processador.getUso();
        Long usoMemoria = memoria.getEmUso();

        if (tipo.equals("disco")) {
//            cursor.update("INSERT INTO tbRegistro ");
        }
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
