package model;

import java.awt.*;
import java.awt.image.BufferedImage;

import engine.Animation;
import engine.GamePainter;
import engine.ImageFactory;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	private PacmanGame pacmanGame;
	private Animation pacman;
	private Animation monster;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game le jeutest a afficher
	 */
	public PacmanPainter(PacmanGame game) {
		pacmanGame = game;
		this.pacman = ImageFactory.getInstance().loadAnimation("Character/wraith.gif", 60);
		this.monster = ImageFactory.getInstance().loadAnimation("Character/Personnage1.gif", 60);
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		// Premièrement, on affiche toute la map
		addMapTextures(im);
		// Deuxièmement, on affiche les personnages
		drawCharacter(im);
		drawMonster(im);
	}

	/**
	 * Permet de "dessiner" le personnage dans la Map
	 * @param im BufferedImage
	 */
	public void drawCharacter(BufferedImage im) {
		this.pacman.drawImage((int)(pacmanGame.getCharacterPosX()*pacmanGame.getScale()), (int)(pacmanGame.getCharacterPosY()*pacmanGame.getScale()), pacmanGame.getScale(), pacmanGame.getScale(), null, (Graphics2D) im.getGraphics());
	}

	public void drawMonster(BufferedImage im) {
		for(int i = 0 ; i < pacmanGame.getNbMonsters() ; i++) {
			this.monster.drawImage((int) (pacmanGame.getMonsterPosX(i) * pacmanGame.getScale()), (int) (pacmanGame.getMonsterPosY(i) * pacmanGame.getScale()), pacmanGame.getScale(), pacmanGame.getScale(), null, (Graphics2D) im.getGraphics());
		}
	}

	/**
	 * Ajoute les textures des mur et de sol
	 * @author Clément
	 * @param im BufferedImage
	 */
	public void addMapTextures(BufferedImage im){
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		for (int i = 0 ; i < pacmanGame.getMapBuilder().getWidth() ; i++){
			for (int j = 0 ; j < pacmanGame.getMapBuilder().getHeight() ; j++){
				// on commence par mettre de l'herbe partout (pour que les objets soient posés sur le sol)
				crayon.drawImage(ImageFactory.getInstance().loadImage("Ground/Ground_lvl1.png"),i*pacmanGame.getScale(),j*pacmanGame.getScale(),pacmanGame.getScale(),pacmanGame.getScale(),null);
				Ground g = pacmanGame.getMapBuilder().get(i, j);
				
				if(g.isEffect()) {
					// ici, je regarde si c'est un effet afin de dessiner la pomme en plus petit
					crayon.drawImage(g.getImage(),i*pacmanGame.getScale() + 10,j*pacmanGame.getScale() + 10,pacmanGame.getScale()/2,pacmanGame.getScale()/2,null);
				}else{
					crayon.drawImage(g.getImage(), i * pacmanGame.getScale(), j* pacmanGame.getScale(), pacmanGame.getScale(), pacmanGame.getScale(), null);
				}
			}
		}
	}

	@Override
	public int getWidth() {
		return pacmanGame.getWidth()* pacmanGame.getScale();
	}

	@Override
	public int getHeight() {
		return pacmanGame.getHeight()* pacmanGame.getScale();
	}

}

