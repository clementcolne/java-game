package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;
import engine.MapBuilder;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	private final int SCALE = 40; // @author Adèle permet d'agrandir de la même manière tous les éléments du jeu
	private PacmanGame pacmanGame;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game le jeutest a afficher
	 */
	public PacmanPainter(PacmanGame game) {
		pacmanGame = game;
	}

	/**
	 * methode redefinie de Afficheur retourne une image du jeu
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
		crayon.setColor(Color.blue);
		crayon.fillOval(pacmanGame.getCharacterPosX() * SCALE, pacmanGame.getCharacterPosY() * SCALE, SCALE,SCALE);
	}

	/**
	 * Permet de "dessiner" la map au sens des blocs du sol
	 * @param im BufferedImage
	 */
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
		return pacmanGame.getWidth() * SCALE;
	}

	@Override
	public int getHeight() {
		return pacmanGame.getHeight() * SCALE;
	}

	/**
	 * @author Adèle
	 * @return l'échelle du jeu
	 */
	public int getScale() {
		return SCALE;
	}
}
