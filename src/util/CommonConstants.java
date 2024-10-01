package util;

import java.awt.*;

public class CommonConstants {
    // Direcionamento para os arquivos
    public static final String LOGIN_IMAGE_PATH = "../resources/images/logo.png";
    public static final String ICON_IMAGE_PATH = "../resources/images/icon.png";

    // Configurações de cor
    public static final Color PRIMARY_COLOR = new Color(150, 75, 0);
    public static final Color SECONDARY_COLOR = new Color(255, 255, 255);
    public static final Color TERTIARY_COLOR = new Color(58, 29, 0);
    public static final Color BUTTON_COLOR = new Color(0, 255, 0);
    public static final Color LOGOUT_BUTTON_COLOR = new Color(255, 0, 0);

    // Configuração do Frame
    public static final Dimension FRAME_SIZE = new Dimension(540, 600);
    public static final Dimension TEXTFIELD_SIZE = new Dimension((int) (FRAME_SIZE.width *  0.80), 50);
    public static final Font TEXT_FONT = new Font("Arial", Font.BOLD, 20);
    public static final Insets FIELDBUTTON_MARGIN = new Insets(50, 50, 50, 50);
    public static final Dimension BUTTON_SIZE = TEXTFIELD_SIZE;

    // Configuração de Login
    public static final Dimension LOGIN_IMAGE_SIZE = new Dimension(255, 262);

    // Configuração Main
    public static final Dimension MAIN_BUTTON_SIZE = new Dimension(300, 50);

    // Configuração do Result Dialog
    public static final Dimension RESULT_DIALOG_SIZE = new Dimension(300, 100);

    // credenciais do mysql
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/escoladb";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "santos123";
    public static final String DB_PROFESSORES_TABLE_NAME = "professores";
    public static final String DB_ALUNOS_TABLE_NAME = "alunos";
}
