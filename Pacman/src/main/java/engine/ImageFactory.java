package engine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Clément Colné
 * Classe singleton qui permet d'instancier une seule et unique fois les images du jeu
 */
public class ImageFactory {

	private static Map<String, Image> images = new HashMap<String, Image>();

    /**
     * Constructeur de ImageFactory
     * Construit le GIF du pacman et un ground
     */
    private ImageFactory() {}

    private static ImageFactory INSTANCE = new ImageFactory();

    /**
     * Retourne l'instance unique de ImageFactory
     * @return l'instance unique de ImageFactory
     */
    public static ImageFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Obtient l'image
     * @author Adham
     * @param path repertoire de l'image
     * @return l'image
     */
    public Image loadImage(String path){
        Image temp;
        
        if ((temp = (images.get(path))) != null) {
        	return temp;
        }
        
        try{
            temp = ImageIO.read(ImageFactory.class.getClassLoader().getResourceAsStream("resources/" + path));
            images.put(path, temp);
        }
        catch(Exception e){
            System.err.println("Error loading Image" + e.getMessage());
        }
        
        return images.get(path);
    }
    
    /**
     * Créé une nouvelle animation à partir d'une image GIF (ou autre, cela fonctionne également)
     * @author Raphaël
     * @param path Chemin vers le GIF (peut également être un chemin vers une image normale sans animation)
     * @param fps Nombre d'images par seconde de l'animation
     * @return Animation
     */
    public Animation loadAnimation(String path, int fps) {
    	Animation anim = null;
    	
    	try {
    		anim = new Animation("resources/" + path, fps);
    	} catch (ProjectException e) {
    		e.getStackTrace();
    	}
    	
    	return anim;
    }

}