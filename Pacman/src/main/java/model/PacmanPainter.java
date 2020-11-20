package model;

import java.awt.*;
import java.awt.image.BufferedImage;

import engine.*;
import model.effect.AsyncEffect;
import model.effect.Effect;

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
		this.monster = ImageFactory.getInstance().loadAnimation("Character/Personnage2.gif", 60);
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {

		Graphics2D crayon = (Graphics2D) im.getGraphics();

		// Premièrement, on affiche toute la map
		addMapTextures(crayon);
		// Deuxièmement, on affiche les personnages
		drawCharacter(im);
		drawMonster(im);
		// Troisièmement, on affiche HUD
		drawHealthBar(crayon);
		drawHUD(crayon);
		// En fin, om affiche la message de fin
		drawEnd(crayon);
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
			this.monster.drawImage((int) (pacmanGame.getMonsterPosX(i) * pacmanGame.getScale()-5), (int) (pacmanGame.getMonsterPosY(i) * pacmanGame.getScale()-7), pacmanGame.getScale()+12, pacmanGame.getScale()+12, null, (Graphics2D) im.getGraphics());
		}
	}

	/**
	 * Ajoute les textures des mur et de sol
	 * @author Clément
	 * @param crayon Graphics2D
	 */
	public void addMapTextures(Graphics2D crayon){

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

	/**
	 * Affichage de la vie de personnage
	 * @author Adham
	 * @param crayon Graphics2D
	 */
	public void drawHealthBar(Graphics2D crayon){
		for (int i = 1; i<= pacmanGame.getCharacter().getLife();i++){
			crayon.drawImage(ImageFactory.getInstance().loadImage("Extra/vie.png"),10+(i*10),10,25, 25, null);
		}
	}

	/**
	 * Affichage nom effet en cours
	 * @author Adham
	 * @param crayon Graphics2D
	 */

	public void drawHUD(Graphics2D crayon){
		Font f = FontFactory.getInstance().loadFont("Font/Pixeboy.ttf",20f);
		FontMetrics fm = crayon.getFontMetrics(f);

		CustomIterator<Effect> effects  = AsyncEffect.getEffects();
		crayon.setColor(Color.black);
		crayon.setFont(f);

		BasicStroke bs = new BasicStroke(20);
		crayon.setStroke(bs);

		int i = 0;
		while (effects.hasNext()) {
			Effect effect = effects.next();

			int width = fm.stringWidth(effect.toString());
			int height = fm.getHeight();

			crayon.setColor(Color.black);
			crayon.fillRect(145, 5 + i * height, width + 10, height);
			crayon.setColor(Color.white);
			crayon.drawString(effect.toString(),150,20 + i * height);
			i++;
		}
	}

	/**
	 * Affichage message de fin
	 * @author Adham
	 * @param crayon Graphics2D
	 */
	public void drawEnd(Graphics2D crayon){
		if(pacmanGame.isFinished()){
			crayon.drawImage(ImageFactory.getInstance().loadImage("Extra/end.png"),0,0,null);
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