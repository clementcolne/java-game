package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import engine.GamePainter;
import engine.MapBuilder;

import javax.imageio.ImageIO;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	private final int SCALE = 40; // @author Adèle permet d'agrandir de la même manière tous les éléments du jeu
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
		// On commence par dessiner la Map puis le reste des éléments après sinon la map cache tout
		drawMap(im);
		// On dessine le personnage
		drawCharacter(im);
	}

	/**
	 * Permet de "dessiner" le personnage dans la Map
	 * @param im BufferedImage
	 */
	public void drawCharacter(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		this.background = LoadImage("resources/bground.png");
		this.pacman = LoadImage("resources/pacman.png");
		this.treasure = LoadImage("resources/treasure.png");
		crayon.drawImage(background,0,0,getWidth(),getHeight(),null);
		crayon.drawImage(pacman,pacmanGame.getCharacterPosX()*SCALE, pacmanGame.getCharacterPosY()*SCALE,20,20,null );
		crayon.drawImage(treasure,getWidth()-40,getWidth()-40,20,20,null);
	}

	public void drawMap(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		for(int i = 0 ; i < pacmanGame.getMapBuilder().getWidth() ; i++) {
			for(int j = 0; j < pacmanGame.getMapBuilder().getHeight() ; j++) {
				// pour chaque Ground de la map
				Ground g = pacmanGame.getMapBuilder().get(i, j);
				// on colorie de la couleur définie dans la classe Ground
				crayon.setColor(g.getColor());
				// on remplis un carré
				crayon.fillRect(i * SCALE, j * SCALE, SCALE,SCALE);
			}
		}
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
