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
		pacmanCharacter = new PacmanCharacter(5, 5);
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
				if(pacmanCharacter.getPosY()-1 >= 0) {
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
		// commentaire pour toi Adèle, je trouvais que print l'état du jeu
		// n'était pas de la responsabilité de evolve directement, voilà
		// pourquoi j'ai créé cette fonction mais si tu la sens mieux dans
		// evolve fais comme tu préfères
		if(commande != Cmd.IDLE) {
			// idem ici donner la position du perso je trouvais que c'était
			// de sa propre responsabilité en mode il crie "JE SUIS ICI" et
			// pas au jeu d'aller chercher où est le pacman, donc j'ai
			// Override toString, si t'es pas d'accord idem modifie
			System.out.println(pacmanCharacter.toString());
			System.out.println("Execute " + commande);
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
