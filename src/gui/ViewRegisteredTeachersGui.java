package gui;

import resources.db.JDBC;
import util.CommonConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ViewRegisteredTeachersGui extends JFrame{
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ViewRegisteredTeachersGui() {
        super("Escola Corleone");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        URL imagePath = getClass().getResource(CommonConstants.ICON_IMAGE_PATH);
        if (imagePath != null) {
            Image icon = Toolkit.getDefaultToolkit().getImage(imagePath);
            setIconImage(icon);
        }
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

        addGuiComponents();
    }

    public void addGuiComponents(){
        JLabel viewRegistered = new JLabel("Visualizando Professores Cadastrados");
        viewRegistered.setForeground(CommonConstants.SECONDARY_COLOR);
        viewRegistered.setFont(CommonConstants.TEXT_FONT);
        viewRegistered.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Criando o painel superior
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(CommonConstants.PRIMARY_COLOR);

        JButton sair = new JButton("Sair");
        sair.setFont(CommonConstants.TEXT_FONT);
        sair.setForeground(CommonConstants.SECONDARY_COLOR);
        sair.setBorder(BorderFactory.createLineBorder(CommonConstants.LOGOUT_BUTTON_COLOR, 10));
        sair.setBackground(CommonConstants.LOGOUT_BUTTON_COLOR);
        sair.setAlignmentX(Component.CENTER_ALIGNMENT);
        sair.setPreferredSize(CommonConstants.MAIN_BUTTON_SIZE);
        sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MainGui().setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        JButton updateButton = new JButton("Atualizar");
        updateButton.setBackground(CommonConstants.BUTTON_COLOR);
        updateButton.setForeground(CommonConstants.SECONDARY_COLOR);
        updateButton.setFont(CommonConstants.TEXT_FONT);
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateButton.setPreferredSize(CommonConstants.MAIN_BUTTON_SIZE);
        updateButton.setBorder(BorderFactory.createLineBorder(CommonConstants.BUTTON_COLOR, 10));
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(e -> {
            JDBC.atualizarDados("professor");
            modeloTabela.setRowCount(0);
            JDBC.carregarDadosdoJDBC(modeloTabela, "professor");
        });

        JButton addButton = new JButton("Adicionar");
        addButton.setBackground(CommonConstants.BUTTON_COLOR);
        addButton.setForeground(CommonConstants.SECONDARY_COLOR);
        addButton.setFont(CommonConstants.TEXT_FONT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setPreferredSize(CommonConstants.MAIN_BUTTON_SIZE);
        addButton.setBorder(BorderFactory.createLineBorder(CommonConstants.BUTTON_COLOR, 10));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(e -> {
            JDBC.cadastrar("professor");
            modeloTabela.setRowCount(0);
            JDBC.carregarDadosdoJDBC(modeloTabela, "professor");
        });

        JButton deleteButton = new JButton("Excluir");
        deleteButton.setBackground(CommonConstants.BUTTON_COLOR);
        deleteButton.setForeground(CommonConstants.SECONDARY_COLOR);
        deleteButton.setFont(CommonConstants.TEXT_FONT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setPreferredSize(CommonConstants.MAIN_BUTTON_SIZE);
        deleteButton.setBorder(BorderFactory.createLineBorder(CommonConstants.BUTTON_COLOR, 10));
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> {
            JDBC.excluirDados("professor");
            modeloTabela.setRowCount(0);
            JDBC.carregarDadosdoJDBC(modeloTabela, "professor");
        });


        topPanel.add(viewRegistered);
        topPanel.add(Box.createRigidArea(new Dimension(50, 0)));

        topPanel.add(addButton);
        topPanel.add(Box.createRigidArea(new Dimension(50, 0)));

        topPanel.add(updateButton);
        topPanel.add(Box.createRigidArea(new Dimension(50, 0)));

        topPanel.add(deleteButton);
        topPanel.add(Box.createRigidArea(new Dimension(100, 0)));

        topPanel.add(sair);

        modeloTabela = new DefaultTableModel();

        tabela = new JTable(modeloTabela);
        tabela.setFont(CommonConstants.TEXT_FONT);
        tabela.setBackground(CommonConstants.TERTIARY_COLOR);
        tabela.setForeground(CommonConstants.SECONDARY_COLOR);
        tabela.setRowHeight(100);
        tabela.setRowMargin(40);

        JScrollPane scrollPane = new JScrollPane(tabela);

        getContentPane().add(topPanel, BorderLayout.NORTH);
        JDBC.carregarDadosdoJDBC(modeloTabela, "professor");
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

}
