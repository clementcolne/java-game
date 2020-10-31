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
		switch(commande) {
			case LEFT:
				if(canMoove(-1, 0)) {
					pacmanCharacter.mooveLeft();
					checkPassage();
				}
				break;
			case RIGHT:
				if(canMoove(1, 0)) {
					pacmanCharacter.mooveRight();
					checkPassage();
				}
				break;
			case UP:
				if(canMoove(0, -1)) {
					pacmanCharacter.mooveUp();
					checkPassage();
				}
				break;
			case DOWN:
				if(canMoove(0, 1)) {
					pacmanCharacter.mooveDown();
					checkPassage();
				}
				break;
			default:
				break;
		}
	}

	/**
	 * Vérifie que le personnage peut se déplacer vers la case demandée
	 * @param x case demandée en abscisse (-1 : gauche, 0 : sur place, 1 : droite)
	 * @param y case demandée en ordonnées (-1 : haut, 0 : sur place, 1 : bas)
	 * @return vrai si le personnage peut accéder à la case, faux sinon
	 * @author Clément
	 */
	public boolean canMoove(int x, int y) {
		return pacmanCharacter.getPosX() + x < mapBuilder.getWidth() && pacmanCharacter.getPosY() + y < mapBuilder.getHeight() && mapBuilder.get(pacmanCharacter.getPosX() + x, pacmanCharacter.getPosY() + y).isAccessible();
	}

	/**
	 * Vérifie si la case où se trouve le personnage est une case passage.
	 * Si c'est le cas, le personnage est téléporté vers la case passage
	 * correspondante.
	 * @author Clément
	 */
	public void checkPassage() {
		if(mapBuilder.get(pacmanCharacter.getPosX(), pacmanCharacter.getPosY()).isPassage()) {
			Passage p = (Passage)mapBuilder.get(pacmanCharacter.getPosX(), pacmanCharacter.getPosY());
			pacmanCharacter.setPosX(p.getLinkedPassage().getPosX());
			pacmanCharacter.setPosY(p.getLinkedPassage().getPosY());
		}
	}

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
	public int getCharacterPosX(){
		return pacmanCharacter.getPosX();
	}

	/**
	 * @return la position vertical du personnage
	 * @author Adèle
	 */
	public int getCharacterPosY(){
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
