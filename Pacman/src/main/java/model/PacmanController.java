package model;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;


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
			case 37 :
				this.commandeEnCours = Cmd.LEFT;
				break;

			case 39 :
				this.commandeEnCours = Cmd.RIGHT;
				break;

			case 38 :
				this.commandeEnCours = Cmd.UP;
				break;

			case 40:
				this.commandeEnCours = Cmd.DOWN;
				break;

			//Le personnage lance une attaque vers le bas
			case 's':
			case 'S':
				this.commandeEnCours = Cmd.ATTACKDOWN;
				break;

			//Le personnage lance une attaque vers le haut
			case 'z':
			case 'Z':
				this.commandeEnCours = Cmd.ATTACKUP;
				break;

			//Le personnage lance une attaque vers la gauche
			case 'q':
			case 'Q':
				this.commandeEnCours = Cmd.ATTACKLEFT;
				break;

			//Le personnage lance une attaque vers la droite
			case 'd':
			case 'D':
				this.commandeEnCours = Cmd.ATTACKRIGHT;
				break;

			default:
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