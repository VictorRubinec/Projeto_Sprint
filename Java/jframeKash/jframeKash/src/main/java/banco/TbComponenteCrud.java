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
 * @author vinicius
 */
public class TbComponenteCrud {

    public List<TbComponente> selecionar(String numeroSerial) {
        ConexaoAzure con = new ConexaoAzure();
        JdbcTemplate cursor = con.getConexao();
        
        List<TbComponente> validacao = cursor.query(String.format("SELECT * FROM tbComponente WHERE fkMaquina = '%s';", numeroSerial), new BeanPropertyRowMapper(TbComponente.class));
        return validacao;
    }
}
