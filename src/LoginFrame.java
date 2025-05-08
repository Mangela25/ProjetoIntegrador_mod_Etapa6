
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        // Campos de login
        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(emailLabel);

        JTextField emailField = new JTextField();
        add(emailField);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        // Botões
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBackground(new Color(171, 71, 188));
        loginButton.setForeground(Color.WHITE);
        add(loginButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 18));
        cancelButton.setBackground(new Color(171, 71, 188));
        cancelButton.setForeground(Color.WHITE);
        add(cancelButton);

        // Ações - botões
        loginButton.addActionListener(e -> validarLogin(emailField.getText(), new String(passwordField.getPassword())));
        cancelButton.addActionListener(e -> dispose());
    }

    private void validarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetointegrador_db_atividade2", "root", "1234");
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "E-mail ou senha inválidos.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
