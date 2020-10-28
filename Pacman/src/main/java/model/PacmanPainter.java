package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	private final int SCALE = 40; // @author Adèle permet d'agrandir de la même manière tous les éléments du jeu
	private PacmanGame pacmanGame;
	private Image background, pacman, wall, ground;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game le jeutest a afficher
	 */
	public PacmanPainter(PacmanGame game) {
		pacmanGame = game;
		background = null;
		pacman = null;
		wall = null;
		ground = null;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		// On commence par dessiner la Map puis le reste des éléments après sinon la map cache tout
		drawMap(im);
		// Ajout les textures des mur et de sol
		addMapTextures(im);
		// On dessine le personnage
		drawCharacter(im);
	}

	/**
	 * Permet de "dessiner" le personnage dans la Map
	 * @param im BufferedImage
	 */
	public void drawCharacter(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		pacman = LoadImage("resources/Character/pacman.png");
		crayon.drawImage(pacman,pacmanGame.getCharacterPosX()*SCALE, pacmanGame.getCharacterPosY()*SCALE,SCALE,SCALE,null);
	}


	public void drawMap(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		for(int i = 0 ; i < pacmanGame.getMapBuilder().getWidth() ; i++) {
			for(int j = 0; j < pacmanGame.getMapBuilder().getHeight() ; j++) {
				// pour chaque Ground de la map
				Ground g = pacmanGame.getMapBuilder().get(i, j);
				// on colorie de la couleur définie dans la classe Ground
				crayon.setColor(g.getColor());
				//System.out.println(g.getColor());
				// on remplis un carré
				crayon.fillRect(i * SCALE, j * SCALE, SCALE,SCALE);
			}
		}
	}


	/**
	 * Ajoute les textures des mur et de sol
	 * @author Adham
	 * @param im BufferedImage
	 */
	public void addMapTextures(BufferedImage im){
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		wall = LoadImage("resources/Wall/wall_lvl1.png");
		ground = LoadImage("resources/Ground/ground_lvl1.png");

		for (int i = 0; i< pacmanGame.getMapBuilder().getWidth(); i++){
			for (int j = 0; j<pacmanGame.getMapBuilder().getHeight(); j++){
				 Ground g = pacmanGame.getMapBuilder().get(i,j);
				 // Si la couleur est blanche on ajoute les texture des murs
				 if (g.color == Color.WHITE){
				 	crayon.drawImage(ground,i*SCALE,j*SCALE,SCALE,SCALE,null);
				 }
				 // Si la couleur est grise on ajoute les texture de sol
				 else if (g.color == Color.DARK_GRAY){
				 	crayon.drawImage(wall,i*SCALE,j*SCALE,SCALE,SCALE,null);
				 }
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
	 * Obtient l'image
	 * @author Adham
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
