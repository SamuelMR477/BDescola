package custom;

import javax.swing.*;
import java.awt.event.*;

public class TextFieldCustom extends JTextField {
    private String placeholderText;
    private boolean hasPlaceHolder;

    public boolean hasPlaceHolder(){
        return hasPlaceHolder;
    }

    public TextFieldCustom(String placeholderText, int charLimit){
        super();
        this.placeholderText = placeholderText;

        // Status do texto do PlaceHolder
        hasPlaceHolder = true;

        // limite de caracteres no campo do input
        setDocument(new LimitText(charLimit));
        setText(this.placeholderText);

        addListener();
    }

    private void addListener(){
        // Detectar click do mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(hasPlaceHolder){
                    hasPlaceHolder = false;
                    setText("");
                }
            }
        });

        // Verificando os clicks do mouse
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(hasPlaceHolder){
                    hasPlaceHolder = false;
                    setText("");
                }
            }
        });

        // Verificando se o usuario tirou o foco desse campo
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                // Verificando se está vázio
                if(getText().toString().length() <= 0){
                    hasPlaceHolder = true;
                    setText(placeholderText);
                }
            }
        });
    }
}
