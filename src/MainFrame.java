

// Alteração de teste para primeiro commit

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Sistema Gerenciamento Empresa de Mecânica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Cor de fundo da janela


        JLabel titleLabel = new JLabel("Gerenciamento Empresa de Mecânica", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Tamanho da fonte aumentado
        titleLabel.setForeground(Color.BLACK); // Cor do título
        add(titleLabel, BorderLayout.NORTH);


        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridBagLayout()); // Usando GridBagLayout para centralizar

        // Carregar a imagem da logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Imagem2/rb_43150.png")); // Use o nome correto da imagem

       
        Image img = logoIcon.getImage();  // Obtemos a imagem do ImageIcon
        Image resizedImg = img.getScaledInstance(500, 200, Image.SCALE_SMOOTH); // Ajusta o tamanho da imagem (500x200 pixels)

        // criar imagem- 
        ImageIcon resizedLogoIcon = new ImageIcon(resizedImg);

        
        JLabel logoLabel = new JLabel(resizedLogoIcon);

        // logo centro
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.CENTER); // Centraliza a logo na tela

        // Criar um painel para o texto de créditos abaixo da logo
        JPanel creditPanel = new JPanel();
        creditPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centraliza o texto
        creditPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo igual à da janela

        
        JLabel creditLabel = new JLabel("Fonte: Freepik.com");
        creditLabel.setFont(new Font("Arial", Font.ITALIC, 14)); // Fonte itálica para créditos
        creditLabel.setForeground(Color.BLACK); // Cor do texto de crédito (preto)
        creditPanel.add(creditLabel); // Adiciona o texto no painel de créditos
        add(creditPanel, BorderLayout.SOUTH); // Adiciona o painel de créditos na parte inferior da tela

        // Criar um painel - botões navegação
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2)); // Layout de 2 colunas
        mainPanel.setBackground(new Color(230, 230, 230)); // Cor de fundo do painel de botões
        add(mainPanel, BorderLayout.SOUTH);

        // Cor 
        Color buttonColor = new Color(171, 71, 188); // Lilás

        // Botões navegação
        JButton btnLogin = new JButton("Login");
        JButton btnCadastroCliente = new JButton("Cadastro de Clientes");
        JButton btnCadastroProduto = new JButton("Cadastro de Produtos");
        JButton btnRegistroVenda = new JButton("Registro de Vendas");
        JButton btnConsultaServico = new JButton("Consulta de Serviços");

        // Estilo -  botões cor lilás e texto branco com fonte grande
        Font buttonFont = new Font("Arial", Font.BOLD, 18); //  fonte 18

        btnLogin.setBackground(buttonColor);
        btnLogin.setForeground(Color.WHITE); // Cor do texto
        btnLogin.setFont(buttonFont); // Definindo o tamanho da fonte

        btnCadastroCliente.setBackground(buttonColor);
        btnCadastroCliente.setForeground(Color.WHITE);
        btnCadastroCliente.setFont(buttonFont); // Definindo o tamanho da fonte

        btnCadastroProduto.setBackground(buttonColor);
        btnCadastroProduto.setForeground(Color.WHITE);
        btnCadastroProduto.setFont(buttonFont); // Definindo o tamanho da fonte

        btnRegistroVenda.setBackground(buttonColor);
        btnRegistroVenda.setForeground(Color.WHITE);
        btnRegistroVenda.setFont(buttonFont); // Definindo o tamanho da fonte

        btnConsultaServico.setBackground(buttonColor);
        btnConsultaServico.setForeground(Color.WHITE);
        btnConsultaServico.setFont(buttonFont); // Definindo o tamanho da fonte

        // Adicionar - botões 
        mainPanel.add(btnLogin);
        mainPanel.add(btnCadastroCliente);
        mainPanel.add(btnCadastroProduto);
        mainPanel.add(btnRegistroVenda);
        mainPanel.add(btnConsultaServico);

        // Ações - botões
        btnLogin.addActionListener(e -> new LoginFrame().setVisible(true));
        btnCadastroCliente.addActionListener(e -> new ClienteForm().setVisible(true));
        btnCadastroProduto.addActionListener(e -> new ProdutoForm().setVisible(true));
        btnRegistroVenda.addActionListener(e -> new VendaForm().setVisible(true));
        btnConsultaServico.addActionListener(e -> new ConsultaServicos().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}





