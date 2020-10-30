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
		pacmanCharacter = new PacmanCharacter(13, 1);
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
				if(pacmanCharacter.getPosX() - 1 >= 0 && mapBuilder.get(pacmanCharacter.getPosX() - 1, pacmanCharacter.getPosY()).isAccessible())
					pacmanCharacter.mooveLeft();
				break;
			case RIGHT:
				if(pacmanCharacter.getPosX() + 1 < mapBuilder.getWidth() && mapBuilder.get(pacmanCharacter.getPosX() + 1, pacmanCharacter.getPosY()).isAccessible()) {
					pacmanCharacter.mooveRight();
				}
				break;
			case UP:
				if (pacmanCharacter.getPosY() - 1 >= 0 && mapBuilder.get(pacmanCharacter.getPosX(), pacmanCharacter.getPosY() - 1).isAccessible()) {
					pacmanCharacter.mooveUp();
				}
				break;
			case DOWN:
				if(pacmanCharacter.getPosY() + 1 < mapBuilder.getHeight() && mapBuilder.get(pacmanCharacter.getPosX(), pacmanCharacter.getPosY() + 1).isAccessible()) {
					pacmanCharacter.mooveDown();
				}
				break;
			default:
				break;
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

	public PacmanCharacter getCharacter() {
		return pacmanCharacter;
	}
}
