package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;

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
	private final int SCALE = 20;
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
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillOval(pacmanGame.getCharacterPosX()* SCALE, pacmanGame.getCharacterPosY()* SCALE, 10,10);
	}

	@Override
	public int getWidth() {
		return pacmanGame.getWidth()* SCALE;
	}

	@Override
	public int getHeight() {
		return pacmanGame.getHeight()* SCALE;
	}

}
