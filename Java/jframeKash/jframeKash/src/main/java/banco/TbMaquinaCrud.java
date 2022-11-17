/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author User
 */
public class TbMaquinaCrud {
    public TbMaquina selecionar(String serial) {

        ConexaoAzure con = new ConexaoAzure();
        JdbcTemplate cursor = con.getConexao();

        List<TbMaquina> validacao = cursor.query(String.format("SELECT * FROM tbMaquina WHERE serialNumber LIKE '%s';", serial), new BeanPropertyRowMapper(TbMaquina.class));
        for (TbMaquina maquina : validacao) {
            return maquina;
        }
        return null;
    }
    
    public TbMaquina selectMaquinaSerial(String serial){
        Conexao conexao = new Conexao();
        JdbcTemplate cursor = conexao.getConexao();
        List <TbMaquina> listaMaquina;
        listaMaquina = cursor.query(String.format("SELECT * FROM tbMaquina WHERE serialNumber LIKE '%s';", serial), new BeanPropertyRowMapper(TbMaquina.class));
        for (TbMaquina maquina : listaMaquina) {
            System.out.println(maquina);
            return maquina;
        }
        return null;
    }
}
