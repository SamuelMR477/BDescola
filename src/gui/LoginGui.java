package gui;

import custom.PasswordFieldCustom;
import custom.TextFieldCustom;
import resources.db.JDBC;
import util.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class LoginGui extends JFrame implements ActionListener {
    private TextFieldCustom usernameField;
    private PasswordFieldCustom passwordField;

    public LoginGui(){
        super("Escola Corleone");
        setSize(util.CommonConstants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(null);
        URL imagePath = getClass().getResource(CommonConstants.ICON_IMAGE_PATH);
        Image icon = Toolkit.getDefaultToolkit().getImage(imagePath);
        setIconImage(icon);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);

        addGuiComponent();
    }

    private void addGuiComponent(){
        // Login image
        JLabel loginImage = util.CustomTools.loadImage(CommonConstants.LOGIN_IMAGE_PATH);
        loginImage.setBounds(
                (CommonConstants.FRAME_SIZE.width - loginImage.getPreferredSize().width)/2,
                4,
                CommonConstants.LOGIN_IMAGE_SIZE.width, CommonConstants.LOGIN_IMAGE_SIZE.height
        );

        // Campo do Nome de Usuário
        usernameField = new TextFieldCustom("Insira o Nome de Usuário:", 30);
        usernameField.setMargin(CommonConstants.FIELDBUTTON_MARGIN);
        usernameField.setBorder(BorderFactory.createLineBorder(CommonConstants.SECONDARY_COLOR, 10));
        usernameField.setFont(CommonConstants.TEXT_FONT);
        usernameField.setBounds(
                50,
                loginImage.getY() + 275,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );

        // Campo da senha
        passwordField = new PasswordFieldCustom("Insira a Senha:", 35);
        passwordField.setMargin(CommonConstants.FIELDBUTTON_MARGIN);
        passwordField.setBorder(BorderFactory.createLineBorder(CommonConstants.SECONDARY_COLOR, 10));
        passwordField.setFont(CommonConstants.TEXT_FONT);
        passwordField.setBounds(
                50,
                usernameField.getY() + 70,
                CommonConstants.TEXTFIELD_SIZE.width, CommonConstants.TEXTFIELD_SIZE.height
        );

        // Login -> Pagina principal
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(CommonConstants.BUTTON_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(null);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(
                50,
                passwordField.getY() + 70,
                CommonConstants.BUTTON_SIZE.width, CommonConstants.BUTTON_SIZE.height
        );
        loginButton.addActionListener(this);


        // Adicionar ao frame
        getContentPane().add(loginImage);
        getContentPane().add(usernameField);
        getContentPane().add(passwordField);
        getContentPane().add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Login")) {
            // criando caixa de dialogo
            JDialog resultDialog = new JDialog();
            resultDialog.setPreferredSize(CommonConstants.RESULT_DIALOG_SIZE);
            URL imagePath = getClass().getResource(CommonConstants.ICON_IMAGE_PATH);
            Image icon = Toolkit.getDefaultToolkit().getImage(imagePath);
            resultDialog.setIconImage(icon);
            resultDialog.pack();
            resultDialog.setLocationRelativeTo(this);
            resultDialog.setModal(true);

            // Criando Label (Display Result)
            JLabel resultLabel = new JLabel();
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
            resultDialog.add(resultLabel);

            // Devolvendo as credenciais inseridas
            String username = usernameField.getText();
            String password = passwordField.getText();

            if(JDBC.validarLogin(username, password) == true){
                resultLabel.setText("Logou com sucesso!");

                resultDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                        new MainGui().setVisible(true);
                    }
                });
            }else if(JDBC.checarUsuario(username) == false){
                    resultLabel.setText("Usuário não existe!");
            }else{
                resultLabel.setText("Usuário e senha não Batem!");
            }

            resultDialog.setVisible(true);
        }
    }
}
