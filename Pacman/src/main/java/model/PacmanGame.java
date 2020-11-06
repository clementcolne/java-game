package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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

	private final int scale = 40;
	private PacmanCharacter pacmanCharacter;
	private MapBuilder mapBuilder;
	private boolean isFinished;

	/**
	 * constructeur avec fichier source pour le help
	 *
	 */
	public PacmanGame(String source, MapBuilder map) {
		pacmanCharacter = new PacmanCharacter(1, 1);
		mapBuilder = map;
		isFinished = false;
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
		System.out.println("Vie : "+pacmanCharacter.getLife());
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 *
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		if (!pacmanCharacter.getGhost() && !mapBuilder.get((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY()).isAccessible()) {
			this.resetPosition(pacmanCharacter);
		}
		
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
			if(mapBuilder.get((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY()).isTreasure())
				isFinished = true;

			mapBuilder.get((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY()).doEffect(pacmanCharacter);
			consumeGroundEffect((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY());
		}
	}

	/**
	 * Crée un bloc d'herbe à la position [x;y] où était l'éffet
	 * @param x position en x
	 * @param y position en y
	 * @author Clément
	 */
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
		return pacmanCharacter.getLife() == 0 || isFinished;
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
	
	/**
	 * Permet de revenir à une position qui était accessible (non bloquée)
	 * @author Raphaël
	 * @param character Pacman qui possède la liste des positions parcourues
	 */
	public void resetPosition(PacmanCharacter character) {		
		ListIterator<double[]> visitedCoordinates = character.getVisitedCoordinates();
		
		while (visitedCoordinates.hasNext()) {
			double[] coordinates = visitedCoordinates.next();
			
			if (mapBuilder.get((int)coordinates[0], (int)coordinates[1]).isAccessible()) {
				pacmanCharacter.setPosX(coordinates[0]);
				pacmanCharacter.setPosY(coordinates[1]);
			}
		}
	}
	
	/**
	 * Permet d'exécuter l'effet dont la case est la plus proche du Pacman
	 * @author Raphaël
	 * @param character Pacman sur lequel on doit exécuter l'effet
	 */
	/*public void doEffect(PacmanCharacter character) {
		double min = -1;
		Ground nearestToPacman = mapBuilder.get((int)character.getPosX(), (int)character.getPosY());
		
		for (Ground g:this.getCollidingGrounds(character)) {
			double cDist = this.calculateDistance(character, g);
			if (cDist < min || min == -1) {
				min = cDist;
				nearestToPacman = g;
			}
		}
		
		nearestToPacman.doEffect(character);
	}*/
	
	/**
	 * Permet de connaître les sols avec lesquels une position est en collision
	 * @author Raphaël
	 * @param x Position en abscisse
	 * @param y Position en ordonnée
	 * @param mapBuilder Générateur de map du jeu
	 * @return Liste de sols en collision avec le Pacman
	 */
	public static ListIterator<Ground> getCollidingGrounds(double x, double y, MapBuilder mapBuilder) {		
		List<Ground> collidingGrounds = new LinkedList<Ground>();
		
		if ((int)x == x && (int)y == y) {
			collidingGrounds.add(mapBuilder.get((int)x, (int)y));
		}
		else if ((int)x != x && (int)y == y) {
			collidingGrounds.add(mapBuilder.get((int)x, (int)y));
			collidingGrounds.add(mapBuilder.get((int)x+1, (int)y));
		}
		else if ((int)x == x && (int)y != y) {
			collidingGrounds.add(mapBuilder.get((int)x, (int)y));
			collidingGrounds.add(mapBuilder.get((int)x, (int)y+1));
		}
		else {
			collidingGrounds.add(mapBuilder.get((int)x, (int)y));
			collidingGrounds.add(mapBuilder.get((int)x+1, (int)y));
			collidingGrounds.add(mapBuilder.get((int)x, (int)y+1));
			collidingGrounds.add(mapBuilder.get((int)x+1, (int)y+1));
		}
		
		return collidingGrounds.listIterator();
	}
	
	/**
	 * Calculer la distance entre un Pacman et un sol
	 * @author Raphaël
	 * @param xOne Position en abscisse du premier point
	 * @parma yOne Position en ordonnée du premier point
	 * @param xTwo Position en abscisse du deuxième point
	 * @param yTwo Position en ordonnée du deuxième point
	 * @return Distance entre le Pacman et le sol
	 */
	public static double calculateDistance(double xOne, double yOne, double xTwo, double yTwo) {	
		double memberOne = (xTwo - xOne);
		double memberTwo = (yTwo - yOne);
		
		return Math.sqrt(memberOne*memberOne + memberTwo*memberTwo);
	}
	
	/**
	 * Retourne l'échelle (largeur x hauteur) de chaque image du jeu
	 * @author Raphaël
	 * @return Echelle de chaque image
	 */
	public int getScale() {
		return this.scale;
	}
}

