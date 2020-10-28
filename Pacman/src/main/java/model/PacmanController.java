package model;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import engine.Cmd;
import engine.GameController;

import static java.awt.event.KeyEvent.VK_DOWN;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		//affichageCommande();
		return this.commandeEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		// si on appuie sur 'q',commande joueur est gauche
			case 'l':
			case 'L':
			case 37 :
				this.commandeEnCours = Cmd.LEFT;
				break;

			case 'r':
			case 'R':
			case 39 :
				this.commandeEnCours = Cmd.RIGHT;
				break;

			case 'u':
			case 'U':
			case 38 :
				this.commandeEnCours = Cmd.UP;
				break;

			case 'd':
			case 'D':
			case 40:
				this.commandeEnCours = Cmd.DOWN;
				break;

			case 's':
			case 'S':
				// Stop, le personnage ne bouge pas
				this.commandeEnCours = Cmd.IDLE;
				break;
		}

	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}