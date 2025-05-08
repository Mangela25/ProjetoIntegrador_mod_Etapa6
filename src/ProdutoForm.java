import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.math.BigDecimal;

public class ProdutoForm extends JFrame {
    private JTextField descricaoField, quantidadeField, precoCustoField, precoVendaField;

    public ProdutoForm() {
        setTitle("Cadastro de Produtos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2));

        // Campos
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(descricaoLabel);
        descricaoField = new JTextField();
        add(descricaoField);

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(quantidadeLabel);
        quantidadeField = new JTextField();
        add(quantidadeField);

        JLabel precoCustoLabel = new JLabel("Preço de Custo:");
        precoCustoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(precoCustoLabel);
        precoCustoField = new JTextField();
        add(precoCustoField);

        JLabel precoVendaLabel = new JLabel("Preço de Venda:");
        precoVendaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(precoVendaLabel);
        precoVendaField = new JTextField();
        add(precoVendaField);

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
        salvarButton.addActionListener(e -> salvarProduto());
        cancelarButton.addActionListener(e -> dispose());
    }

    private void salvarProduto() {
        String descricao = descricaoField.getText();
        String quantidade = quantidadeField.getText();
        String precoCusto = precoCustoField.getText();
        String precoVenda = precoVendaField.getText();

        if (descricao.isEmpty() || quantidade.isEmpty() || precoCusto.isEmpty() || precoVenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            int quantidadeInt = Integer.parseInt(quantidade);
            BigDecimal precoCustoBig = new BigDecimal(precoCusto);
            BigDecimal precoVendaBig = new BigDecimal(precoVenda);

            try (Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetointegrador_db_atividade2", "root", "1234")) {
                String sql = "INSERT INTO produtos (descricao, quantidade, preco_custo, preco_venda) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                    stmt.setString(1, descricao);
                    stmt.setInt(2, quantidadeInt);
                    stmt.setBigDecimal(3, precoCustoBig);
                    stmt.setBigDecimal(4, precoVendaBig);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                    dispose();
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para quantidade e preços.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar produto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutoForm form = new ProdutoForm();
            form.setVisible(true);
        });
    }
}

