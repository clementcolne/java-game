package model.factory;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * @author Clément Colné
 * Classe singleton qui permet d'instancier une seule et unique fois les images du jeu
 */
public class ImageFactory {

    private Image pacmanImage;
    private Image groundImage;

    /**
     * Constructeur de ImageFactory
     * Construit le GIF du pacman et un ground
     */
    private ImageFactory() {
        // utilisation de toolkit afin de charger un gif
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        pacmanImage = toolkit.getImage("resources/Character/wraith.gif");
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
    public Image getPacmanImage() {
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
        Image temp = null;
        try{
            temp = ImageIO.read(new File("resources/" + path));
        }
        catch(Exception e){
            System.out.println("Error loading Image" + e.getMessage());
        }
        return temp;
    }

}
