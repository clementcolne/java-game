package engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Adham
 * Classe singleton qui permet d'instancier les fonts du jeu
 */

public class FontFactory {

    private FontFactory(){}

    private static FontFactory INSTANCE = new FontFactory();

    public static FontFactory getInstance() {
        return INSTANCE;
    }

    public Font LoadFont(String path,float size){
        Font temp  = null;
        try {
            temp = Font.createFont(Font.TRUETYPE_FONT,new File("resources/"+path)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("resources/"+path)));
        } catch (IOException | FontFormatException e) {
            System.out.println("Error loading font");
        }

        return temp;
    }
}
