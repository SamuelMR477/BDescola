package custom;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// Essa classe vai ser usada para limitar o número de caracteres no campo de input
public class LimitText extends PlainDocument {
    private int charLimit;

    public LimitText(int charLimit){
        this.charLimit = charLimit;
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
        if(str == null) return;

        if((super.getLength() + str.length()) <= charLimit){
            super.insertString(offs, str, a);
        }
    }
}
