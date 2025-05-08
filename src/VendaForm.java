
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.math.BigDecimal;

public class VendaForm extends JFrame {
    private JTextField clienteField, produtoField, precoField, descontoField;

    public VendaForm() {
        setTitle("Registro de Vendas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2));

        // Adicionar campos
        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(clienteLabel);
        clienteField = new JTextField();
        add(clienteField);

        JLabel produtoLabel = new JLabel("Produto:");
        produtoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(produtoLabel);
        produtoField = new JTextField();
        add(produtoField);

        JLabel precoLabel = new JLabel("Preço:");
        precoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(precoLabel);
        precoField = new JTextField();
        add(precoField);

        JLabel descontoLabel = new JLabel("Desconto:");
        descontoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(descontoLabel);
        descontoField = new JTextField();
        add(descontoField);

        // Botões
        JButton salvarButton = new JButton("Registrar Venda");
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
        salvarButton.addActionListener(e -> registrarVenda());
        cancelarButton.addActionListener(e -> dispose());
    }

    private void registrarVenda() {
        String cliente = clienteField.getText();
        String produto = produtoField.getText();
        String preco = precoField.getText();
        String desconto = descontoField.getText();

        if (cliente.isEmpty() || produto.isEmpty() || preco.isEmpty() || desconto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetointegrador_db_atividade2", "root", "1234")) {
            String sqlCliente = "SELECT id FROM clientes WHERE nome = ?";
            PreparedStatement stmtCliente = conexao.prepareStatement(sqlCliente);
            stmtCliente.setString(1, cliente);
            ResultSet rsCliente = stmtCliente.executeQuery();
            if (!rsCliente.next()) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                return;
            }

            String sqlProduto = "SELECT id FROM produtos WHERE descricao = ?";
            PreparedStatement stmtProduto = conexao.prepareStatement(sqlProduto);
            stmtProduto.setString(1, produto);
            ResultSet rsProduto = stmtProduto.executeQuery();
            if (!rsProduto.next()) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                return;
            }

            String sql = "INSERT INTO vendas (cliente_id, produto_id, preco, desconto) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, rsCliente.getInt("id"));
                stmt.setInt(2, rsProduto.getInt("id"));
                stmt.setBigDecimal(3, new BigDecimal(preco));
                stmt.setBigDecimal(4, new BigDecimal(desconto));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!");
                dispose();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar venda: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VendaForm form = new VendaForm();
            form.setVisible(true);
        });
    }
}

