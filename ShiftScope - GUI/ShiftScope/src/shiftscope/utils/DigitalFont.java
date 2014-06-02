package shiftscope.utils;


import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigitalFont {

    private InputStream stream;
    private Font baseFont;
    private Font driveFont;
    private int size;
    public DigitalFont(){ 
        try {
            loadFont();
        } catch (IOException ex) {
            Logger.getLogger(DigitalFont.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {
            Logger.getLogger(DigitalFont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public final void loadFont() throws FontFormatException, IOException {
        stream = this.getClass().getResourceAsStream("DS-DIGI.TTF");
        baseFont= Font.createFont(Font.TRUETYPE_FONT, stream);
        driveFont = baseFont.deriveFont(Font.PLAIN, size);
    }
    
    public Font getFont(){
        return driveFont;
    }
    
    public void setFontSize(int size){
        this.size = size;
        driveFont = baseFont.deriveFont(Font.PLAIN, size);
    }
    
    public void setBold(boolean bold){
        if (bold) {
            driveFont = baseFont.deriveFont(Font.BOLD, size);
        }
    }
    
    
    
}
