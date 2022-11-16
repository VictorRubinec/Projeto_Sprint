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
public class TbUsuarioCrud {
    public TbUsuario selecionar(String login, String senha) {

        Conexao con = new Conexao();
        JdbcTemplate cursor = con.getConexao();

        List<TbUsuario> validacao = cursor.query(String.format("SELECT * FROM tbUsuario WHERE login LIKE '%s' AND senha LIKE '%s'; ", login, senha), new BeanPropertyRowMapper(TbUsuario.class));
        for (TbUsuario usuario : validacao) {
            System.out.println(usuario);
            return usuario;
        }
        return null;
    }
}
