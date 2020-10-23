package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {

	private PacmanCharacter pacmanCharacter;
	private int width = 10;
	private int length = 10;

	/**
	 * constructeur avec fichier source pour le help
	 *
	 */
	public PacmanGame(String source) {
		pacmanCharacter = new PacmanCharacter(0, 0);
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
				if(pacmanCharacter.getPosX()-1 >= 0)
					pacmanCharacter.mooveLeft();
				break;
			case RIGHT:
				if(pacmanCharacter.getPosX() + 1 <= width) {
					pacmanCharacter.mooveRight();
				}
				break;
			case UP:
				if (pacmanCharacter.getPosY() - 1 >= 0) {
					pacmanCharacter.mooveUp();
				}
				break;
			case DOWN:
				if(pacmanCharacter.getPosY() + 1 <= length) {
					pacmanCharacter.mooveDown();
				}
				break;
			default:
				break;
		}
		printGame(commande);
	}

	/**
	 *
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
		// le jeu n'est jamais fini
		return false;
	}

}
