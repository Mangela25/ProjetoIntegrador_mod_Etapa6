import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ClienteForm extends JFrame {
    private JTextField nomeField, rgField, cpfField, enderecoField;

    public ClienteForm() {
        setTitle("Cadastro de Clientes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2));

        // Adicionar campos
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(nomeLabel);
        nomeField = new JTextField();
        add(nomeField);

        JLabel rgLabel = new JLabel("RG:");
        rgLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(rgLabel);
        rgField = new JTextField();
        add(rgField);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(cpfLabel);
        cpfField = new JTextField();
        add(cpfField);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(enderecoLabel);
        enderecoField = new JTextField();
        add(enderecoField);

        // Botões
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.BOLD, 18));
        salvarButton.setBackground(new Color(171, 71, 188));
        salvarButton.setForeground(Color.WHITE);
        add(salvarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(new Font("Arial", Font.BOLD, 18));
        cancelarButton.setBackground(new Color(171, 71, 188));
        cancelarButton.setForeground(Color.WHITE);
        add(cancelarButton);

        // Ações - botões
        salvarButton.addActionListener(e -> salvarCliente());
        cancelarButton.addActionListener(e -> dispose());
    }

    private void salvarCliente() {
        String nome = nomeField.getText().trim();
        String rg = rgField.getText().trim();
        String cpf = cpfField.getText().trim();
        String endereco = enderecoField.getText().trim();

        // Validação dos campos obrigatórios
        if (nome.isEmpty() || rg.isEmpty() || cpf.isEmpty() || endereco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        // Validação do CPF (11 dígitos ou com pontos e traço)
        if (!cpf.matches("\\d{11}") && !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "CPF inválido! Use 11 dígitos ou no formato XXX.XXX.XXX-XX.");
            return;
        }

        // Validação do RG (7 a 15 dígitos numéricos)
        if (!rg.matches("\\d{7,15}")) {
            JOptionPane.showMessageDialog(this, "RG inválido! Deve conter de 7 a 15 números.");
            return;
        }

        try (Connection conexao = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/projetointegrador_db_atividade2", "root", "1234")) {

            String sql = "INSERT INTO clientes (nome, rg, cpf, endereco) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, rg);
                stmt.setString(3, cpf);
                stmt.setString(4, endereco);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
                dispose();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteForm form = new ClienteForm();
            form.setVisible(true);
        });
    }
}



