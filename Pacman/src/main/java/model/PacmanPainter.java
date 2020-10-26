package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import engine.GamePainter;

import javax.imageio.ImageIO;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class PacmanPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 100;
	protected static final int HEIGHT = 100;
	private final int SCALE = 20; //@author Adèle permet d'agrandir de la même manière tous les éléments du jeu
	private PacmanGame pacmanGame;
	private Image background, pacman, treasure;

	/**
	 * appelle constructeur parent
	 *
	 * @param game le jeutest a afficher
	 */
	public PacmanPainter(PacmanGame game) {
		pacmanGame = game;
		background = null;
		pacman = null;
		treasure = null;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		this.background = LoadImage("resources/bground.png");
		this.pacman = LoadImage("resources/pacman.png");
		this.treasure = LoadImage("resources/treasure.png");
		crayon.drawImage(background,0,0,getWidth(),getHeight(),null);
		crayon.drawImage(pacman,pacmanGame.getCharacterPosX()*SCALE, pacmanGame.getCharacterPosY()*SCALE,20,20,null );
		crayon.drawImage(treasure,getWidth()-40,getWidth()-40,20,20,null);
	}

	@Override
	public int getWidth() {
		return pacmanGame.getWidth()* SCALE;
	}

	@Override
	public int getHeight() {
		return pacmanGame.getHeight()* SCALE;
	}

	/**
	 * @author Adham
	 * Obtient l'image
	 * @param path repertoire de l'image
	 * @return l'image
	 */
	public Image LoadImage(String path){
		Image temp = null;
		try{
			temp = ImageIO.read(new File(path));
		}
		catch(Exception e){
			System.out.println("Error loading Image" + e.getMessage());
		}
		return temp;
	}


	/**
	 * @author Adèle
	 * @return l'échelle du jeu
	 */
	public int getScale() {
		return SCALE;
	}
}
