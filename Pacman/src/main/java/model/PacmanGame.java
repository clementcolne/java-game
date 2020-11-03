package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import engine.MapBuilder;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {

	private PacmanCharacter pacmanCharacter;
	private MapBuilder mapBuilder;

	/**
	 * constructeur avec fichier source pour le help
	 *
	 */
	public PacmanGame(String source, MapBuilder map) {
		pacmanCharacter = new PacmanCharacter(1, 1);
		mapBuilder = map;
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 *
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		boolean canMove = false;
		switch(commande) {
			case LEFT:
				if(canMove = canMoove(-pacmanCharacter.getSpeed(), 0)) {
					pacmanCharacter.mooveLeft();
				}
				break;
			case RIGHT:
				if(canMove = canMoove(pacmanCharacter.getSpeed(), 0)) {
					pacmanCharacter.mooveRight();
				}
				break;
			case UP:
				if(canMove = canMoove(0, -pacmanCharacter.getSpeed())) {
					pacmanCharacter.mooveUp();
				}
				break;
			case DOWN:
				if(canMove = canMoove(0, pacmanCharacter.getSpeed())) {
					pacmanCharacter.mooveDown();
				}
				break;
			default:
				break;
		}

		if (canMove) {
			mapBuilder.get((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY()).doEffect(pacmanCharacter);
			consumeGroundEffect((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY());
		}
	}

	public void consumeGroundEffect(int x, int y) {
		if(mapBuilder.get(x, y).isEffect()) {
			mapBuilder.set((int) pacmanCharacter.getPosX(), (int) pacmanCharacter.getPosY(), new Ground((int) pacmanCharacter.getPosX(), (int) pacmanCharacter.getPosY()));
		}
	}

	/**
	 * Vérifie que le personnage peut se déplacer vers la case demandée
	 * @param x case demandée en abscisse (-1 : gauche, 0 : sur place, 1 : droite)
	 * @param y case demandée en ordonnées (-1 : haut, 0 : sur place, 1 : bas)
	 * @return vrai si le personnage peut accéder à la case, faux sinon
	 * @author Clément
	 */
	public boolean canMoove(double x, double y) {
		return pacmanCharacter.canMoove(x, y, mapBuilder);
	}


	/**
	 * Vérifie si la case où se trouve le personnage est une case passage.
	 * Si c'est le cas, le personnage est téléporté vers la case passage
	 * correspondante.
	 * @author Clément
	 */
	/*public void checkPassage() {
		if(mapBuilder.get((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY()).isPassage()) {
			Passage p = (Passage)mapBuilder.get((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY());
			pacmanCharacter.setPosX(p.getLinkedPassage().getPosX());
			pacmanCharacter.setPosY(p.getLinkedPassage().getPosY());
		}
	}*/

	/**
	 * Affiche l'état du personnage dans le terminal
	 * @author Adèle
	 */
	public void printGame(Cmd commande) {
		if(commande != Cmd.IDLE) {
			System.out.println(pacmanCharacter.toString());
			System.out.println(commande);
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu se termine si le personnage n'a plus de point de vie
		return pacmanCharacter.getLife() == 0;
	}

	/**
	 * @return la largeur du plateau de jeu
	 * @author Adèle
	 */
	public int getWidth() {
		return mapBuilder.getWidth();
	}

	/**
	 * @return la hauteur du plateau de jeu
	 * @author Adèle
	 */
	public int getHeight() {
		return mapBuilder.getHeight();
	}

	/**
	 * @return la position horizontal du personnage
	 * @author Adèle
	 */
	public double getCharacterPosX(){
		return pacmanCharacter.getPosX();
	}

	/**
	 * @return la position vertical du personnage
	 * @author Adèle
	 */
	public double getCharacterPosY(){
		return pacmanCharacter.getPosY();
	}

	/**
	 * Retourne le mapBuilder
	 * @return le mapBuilder
	 */
	public MapBuilder getMapBuilder() {
		return mapBuilder;
	}
}
