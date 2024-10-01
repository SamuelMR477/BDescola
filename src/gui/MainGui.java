package gui;

import custom.ErrorLabel;
import custom.TextFieldCustom;
import util.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MainGui extends JFrame{
    private TextFieldCustom nameField, ageField, addressField, cellphoneNumberField,
    firstSemesterGradeField, secondSemesterGradeField;
    private ErrorLabel nameErrorLabel, ageErrorLabel, addressErrorLabel, cellphoneErrorLabel,
    firstSemesterGradeErrorLabel, secondSemesterGradeErrorLabel;

    public MainGui() {
        super("Escola Corleone");
        setSize(CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout());
        URL imagePath = getClass().getResource(CommonConstants.ICON_IMAGE_PATH);
        if (imagePath != null) {
            Image icon = Toolkit.getDefaultToolkit().getImage(imagePath);
            setIconImage(icon);
        }
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // Label principal
        JLabel mainLabel = new JLabel("O que você deseja?");
        mainLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        mainLabel.setFont(CommonConstants.TEXT_FONT);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(CommonConstants.PRIMARY_COLOR);

        JButton visualizarAlunos = new JButton("        Visualizar Alunos       ");
        visualizarAlunos.setFont(CommonConstants.TEXT_FONT);
        visualizarAlunos.setForeground(CommonConstants.SECONDARY_COLOR);
        visualizarAlunos.setBorder(BorderFactory.createLineBorder(CommonConstants.BUTTON_COLOR, 10));
        visualizarAlunos.setBackground(CommonConstants.BUTTON_COLOR);
        visualizarAlunos.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizarAlunos.setPreferredSize(CommonConstants.MAIN_BUTTON_SIZE);
        visualizarAlunos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        visualizarAlunos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ViewStudentsGui().setVisible(true);
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

        JButton visualizarProfessores = new JButton("    Visualizar Professores   ");
        visualizarProfessores.setFont(CommonConstants.TEXT_FONT);
        visualizarProfessores.setForeground(CommonConstants.SECONDARY_COLOR);
        visualizarProfessores.setBorder(BorderFactory.createLineBorder(CommonConstants.BUTTON_COLOR, 10));
        visualizarProfessores.setBackground(CommonConstants.BUTTON_COLOR);
        visualizarProfessores.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizarProfessores.setPreferredSize(CommonConstants.MAIN_BUTTON_SIZE);
        visualizarProfessores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        visualizarProfessores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ViewRegisteredTeachersGui().setVisible(true);
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

        JButton sair = new JButton("                  Sair                  ");
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
                new LoginGui().setVisible(true);
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

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 75)));
        buttonPanel.add(mainLabel);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        buttonPanel.add(visualizarAlunos);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        buttonPanel.add(visualizarProfessores);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        buttonPanel.add(sair);

        getContentPane().add(buttonPanel);
    }
}
