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
				// TODO : vérifier si le personnage peut aller à gauche
				pacmanCharacter.mooveLeft();
				break;
			case RIGHT:
				// TODO : vérifier si le personnage peut aller à droite
				pacmanCharacter.mooveRight();
				break;
			case UP:
				// TODO : vérifier si le personnage peut aller en haut
				pacmanCharacter.mooveUp();
				break;
			case DOWN:
				// TODO : vérifier si le personnage peut aller en bas
				pacmanCharacter.mooveDown();
				break;
			default:
					break;
		}
		// TODO : print l'état du jeu
		System.out.println("Execute "+commande);
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
