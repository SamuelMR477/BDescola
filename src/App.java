import javax.swing.*;

import gui.LoginGui;
import gui.ViewStudentsGui;
import gui.ViewRegisteredTeachersGui;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new LoginGui().setVisible(true);
                //new MainGui().setVisible(true);
                //new ViewRegisteredTeachersGui().setVisible(true);
                new ViewStudentsGui().setVisible(true);
            }
        });
    }
}
