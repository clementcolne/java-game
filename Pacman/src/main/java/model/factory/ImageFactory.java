package model.factory;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import engine.Animation;

/**
 * @author Clément Colné
 * Classe singleton qui permet d'instancier une seule et unique fois les images du jeu
 */
public class ImageFactory {

	private static Map<String, Image> images = new HashMap<String, Image>();
	private Animation pacmanImage;
    private Image groundImage;

    /**
     * Constructeur de ImageFactory
     * Construit le GIF du pacman et un ground
     */
    private ImageFactory() {
        pacmanImage = loadAnimation("/Character/wraith.gif", 60);
        groundImage = loadImage("/Ground/Ground_lvl1.png");
    }

    private static ImageFactory INSTANCE = new ImageFactory();

    /**
     * Retourne l'instance unique de ImageFactory
     * @return l'instance unique de ImageFactory
     */
    public static ImageFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Retourne l'image associée au chemin en paramètre
     * @param path chemin de l'image
     * @return l'image associée au chemin en paramètre
     */
    public Image get(String path) {
        return loadImage(path);
    }

    /**
     * Retourne le GIF du Pacman
     * @return le gif du Pacman
     */
    public Animation getPacmanImage() {
        return pacmanImage;
    }

    /**
     * Retourne
     * @return
     */
    public Image getGround() {
        return groundImage;
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
            temp = ImageIO.read(new File("resources/" + path));
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
    	return new Animation("resources/" + path, fps);
    }

}