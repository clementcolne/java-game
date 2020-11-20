package engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Adham
 * Classe singleton qui permet d'instancier les fonts du jeu
 */

public class FontFactory {

    private FontFactory(){}
    
    private static Map<String, Font> fonts = new HashMap<String, Font>();

    private static FontFactory INSTANCE = new FontFactory();

    public static FontFactory getInstance() {
        return INSTANCE;
    }

    public Font loadFont(String path,float size){
        Font temp  = null;
        
        if ((temp = (fonts.get(path))) != null) {
        	return temp;
        }
        
        try {
        	InputStream is = FontFactory.class.getClassLoader().getResourceAsStream("resources/"+path);
            temp = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(temp);   
            fonts.put(path, temp);
        } catch (IOException | FontFormatException e) {
            System.out.println("Error loading font");
        }

        return fonts.get(path);
    }
}
