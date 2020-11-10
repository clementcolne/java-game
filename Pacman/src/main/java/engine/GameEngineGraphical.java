package engine;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		// boucle de game
		new Timer().schedule(new TimerTask() {
			
			public void run() {
				if (!game.isFinished()) {
					// demande controle utilisateur
					Cmd c = gameController.getCommand();
					// fait evoluer le game
					game.evolve(c);
					// affiche le game
					gui.paint();
				}
				else {
					try {
						Thread.sleep(1000);
						System.exit(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, 0, 120);
		
		
	}

}
