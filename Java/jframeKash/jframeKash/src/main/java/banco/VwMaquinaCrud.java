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
public class VwMaquinaCrud {
    public VwMaquina selecionar(String numeroSerial) {

        Conexao con = new Conexao();
        JdbcTemplate cursor = con.getConexao();

        List<VwMaquina> validacao = cursor.query(String.format("SELECT * FROM vwMaquina where numeroSerial = %s", numeroSerial), new BeanPropertyRowMapper(VwMaquina.class));
        for (VwMaquina maquina : validacao) {
            System.out.println(maquina);
            return maquina;
        }
        return null;
    }
}
