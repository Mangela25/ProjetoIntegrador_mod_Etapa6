

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/projetointegrador_db_atividade2";  // URL do banco
        String usuario = "root";  // Usuário do banco de dados
        String senha = "123456";    // Senha do banco de dados

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            if (conexao != null) {
                System.out.println("Conexão bem-sucedida com o banco de dados!");
            } else {
                System.out.println("Falha na conexão com o banco de dados.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }
}
