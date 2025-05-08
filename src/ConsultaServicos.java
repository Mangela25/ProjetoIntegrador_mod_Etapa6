
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ConsultaServicos extends JFrame {

    public ConsultaServicos() {
        setTitle("Consulta de Serviços");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Adicionar campo de consulta
        JTextField servicoField = new JTextField();
        JButton consultarButton = new JButton("Consultar");

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(new JLabel("Serviço:"));
        searchPanel.add(servicoField);
        searchPanel.add(consultarButton);

        add(searchPanel, BorderLayout.NORTH);

        // Tabela para exibir os resultados
        String[] columnNames = {"Nome do Serviço", "Descrição"};
        JTable resultTable = new JTable(new Object[0][2], columnNames);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        consultarButton.addActionListener(e -> {
            String servico = servicoField.getText().trim();
            if (!servico.isEmpty()) {
                consultarServico(servico, resultTable);
            } else {
                JOptionPane.showMessageDialog(this, "Informe o serviço para consulta.");
            }
        });
    }

    private void consultarServico(String servico, JTable resultTable) {
        String sql = "SELECT nome_servico, descricao_servico FROM servicos WHERE nome_servico LIKE ?";
        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetointegrador_db_atividade2", "root", "1234");
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + servico + "%");
            ResultSet rs = stmt.executeQuery();
            resultTable.setModel(buildTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar serviço: " + e.getMessage());
        }
    }

    private static javax.swing.table.DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        java.util.Vector<String> columnNames = new java.util.Vector<>();
        java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<>();

        int columnCount = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(rs.getMetaData().getColumnLabel(i));
        }

        while (rs.next()) {
            java.util.Vector<Object> row = new java.util.Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        return new javax.swing.table.DefaultTableModel(data, columnNames);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultaServicos form = new ConsultaServicos();
            form.setVisible(true);
        });
    }
}
