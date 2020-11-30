package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import engine.*;
import model.movingStrategy.GhostMovingStrategy;

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
	// TODO : utiliser la map de mapbuilder.
	private List<MonsterCharacter> monstersList;
	private MapBuilder mapBuilder;
	private boolean isFinished;
	private Ground executedEffect;
	private int monsterMooveCounter = 0;

	/**
	 * constructeur avec fichier source pour le help
	 *
	 */
	public PacmanGame(String source, MapBuilder map) {
		mapBuilder = map;
		// création du pacman
		this.pacmanCharacter = new PacmanCharacter(-1, -1);
		// création d'un monstre
		this.monstersList = new ArrayList<>();

		int cptIndex = 0;
		for (int x = 0; x < mapBuilder.getWidth(); x++) {
			for (int y = 0; y < mapBuilder.getHeight(); y++) {
				PacmanCharacter character = mapBuilder.getCharacter(x, y);
				MonsterCharacter monster = mapBuilder.getMonster(x, y);
				if (character != null) {
					this.pacmanCharacter = character;
				}
				if(monster != null) {
					this.monstersList.add(monster);
					cptIndex++;
				}
			}
		}
		isFinished = false;
		executedEffect = new Ground(0, 0);
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
		if (!pacmanCharacter.getMovingStrategyType().equals("ghost") && !mapBuilder.get((int)pacmanCharacter.getPosX(), (int)pacmanCharacter.getPosY()).isAccessible()) {
			this.resetPosition();
		}
		
		this.executedEffect = getNearestEffectiveGround(this.pacmanCharacter.getPosX(), this.pacmanCharacter.getPosY());

		boolean move = false;
		switch(commande) {

				//Déplacements

			case LEFT:
				if(move = canMoove(pacmanCharacter, -pacmanCharacter.getSpeed(), 0)) {
					pacmanCharacter.mooveLeft();
				}
				break;
			case RIGHT:
				if(move = canMoove(pacmanCharacter, pacmanCharacter.getSpeed(), 0)) {
					pacmanCharacter.mooveRight();
				}
				break;
			case UP:
				if(move = canMoove(pacmanCharacter, 0, -pacmanCharacter.getSpeed())) {
					pacmanCharacter.mooveUp();
				}
				break;
			case DOWN:
				if(move = canMoove(pacmanCharacter, 0, pacmanCharacter.getSpeed())) {
					pacmanCharacter.mooveDown();
				}
				break;

				//Attaques

			case ATTACKUP:
				attackMonster(0, -1);
				break;
			case ATTACKDOWN:
				attackMonster(0, 1);
				break;
			case ATTACKRIGHT:
				attackMonster(1, 0);
				break;
			case ATTACKLEFT:
				attackMonster(-1, 0);
				break;
			default:
				move = false;
				break;
		}
		this.doEffect(move, commande);
		//mooveMonster();
	}

	/**
	 * Le personnage lance une attaque sur un monstre si celui-ci se trouve à la position visée
	 * Si le monstre n'a plus de point de vie, il est retiré de la carte
	 * @author Adèle
	 * @param x direction horinzontale de l'attaque
	 * @param y direction verticale de l'attaque
	 */
	private void attackMonster(int x, int y) {
		MonsterCharacter m=null;
		int i=1;
		while(m==null && i<= pacmanCharacter.getRange()){
			m = mapBuilder.getMonster((int)pacmanCharacter.getPosX()+ x * i, (int)pacmanCharacter.getPosY()+ y * i);
			System.out.println(m);
			i++;
		}
		if(m!=null){
			pacmanCharacter.attack(m);
			if(m.getLife()<=0){
				mapBuilder.removeMonster(m);
				monstersList.remove(m);
			}
		}
	}

	/**
	 * @author Clément
	 * Fait se déplacer chaque monstre toutes les 1.200ms dans une direction aléatoire
	 */
	public void mooveMonster() {
		// les monstres bougent une 1 fois toutes les 10x(120ms)
		if(monsterMooveCounter == 5) {
			for(MonsterCharacter m : monstersList) {
				Random rand = new Random(); //instance of random class
				int way = rand.nextInt(4);
				switch (way) {
					case 0:
						if (canMoove(m, 0, -m.getSpeed())) {
							m.mooveUp();
						}
						break;
					case 1:
						if (canMoove(m, 0, m.getSpeed())) {
							m.mooveDown();
						}
						break;
					case 2:
						if (canMoove(m, m.getSpeed(), 0)) {
							m.mooveRight();
						}
						break;
					case 3:
						if (canMoove(m, -m.getSpeed(), 0)) {
							m.mooveLeft();
						}
						break;
					default:
						break;
				}
			}
			monsterMooveCounter = 0;
		}else{
			monsterMooveCounter++;
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
			mapBuilder.set(x, y, new Ground(x, y));
		}
	}

	/**
	 * Vérifie que le personnage peut se déplacer vers la case demandée
	 * @param x case demandée en abscisse (-1 : gauche, 0 : sur place, 1 : droite)
	 * @param y case demandée en ordonnées (-1 : haut, 0 : sur place, 1 : bas)
	 * @return vrai si le personnage peut accéder à la case, faux sinon
	 * @author Clément
	 */
	public boolean canMoove(Character c, double x, double y) {
		return c.canMoove(x, y, mapBuilder);
	}

	/**
	 * Affiche l'état du personnage dans le terminal
	 * @deprecated
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
	 * Retourne le nombre de monstres présents dans la map
	 * @return int le nombre de monstres présents dans la map
	 */
	public int getNbMonsters() {
		return mapBuilder.getNbMonsters();
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
	 * @return la position horizontal du personnage
	 * @author Adèle
	 */
	public double getMonsterPosX(int index){
		return monstersList.get(index).getPosX();
	}

	/**
	 * @return la position vertical du personnage
	 * @author Adèle
	 */
	public double getMonsterPosY(int index){
		return monstersList.get(index).getPosY();
	}

	/**
	 * Retourne le mapBuilder
	 * @return le mapBuilder
	 */
	public MapBuilder getMapBuilder() {
		return mapBuilder;
	}
	
	/**
	 * Permet de revenir à une position qui était accessible dans le jeu (non bloquée)
	 * @author Raphaël
	 */
	public void resetPosition() {		
		Iterator<int[]> visitedCoordinates = this.pacmanCharacter.getVisitedCoordinates();
		
		while (visitedCoordinates.hasNext()) {
			int[] coordinates = visitedCoordinates.next();

			if (mapBuilder.get((int)coordinates[0], (int)coordinates[1]).isAccessible()) {
				this.pacmanCharacter.setPosX(coordinates[0]);
				this.pacmanCharacter.setPosY(coordinates[1]);
			}
		}
	}
	
	/**
	 * Retourner le sol le plus proche d'une position n'ayant pas un comportement vide
	 * @author Raphaël
	 * @param x Position en abscisse
	 * @param y Position en ordonnée
	 * @return Sol le plus proche n'ayant pas un comportement vide
	 */
	public Ground getNearestEffectiveGround(double x, double y) {
		double min = -1;

		Ground nearestGround = this.mapBuilder.get((int)x, (int)y);
		Iterator<Ground> nearestGrounds = getCollidingGrounds(x, y, this.mapBuilder);
		
		while (nearestGrounds.hasNext()) {
			Ground collidingGround = nearestGrounds.next();
			
			if (collidingGround == null || collidingGround.hasEmptyBehavior()) {
				continue;
			}
			
			double cDist = calculateDistance(x, y, collidingGround.getPosX(), collidingGround.getPosY());
			if (cDist < min || min == -1) {
				min = cDist;
				nearestGround = collidingGround;
			}
		}
		
		return nearestGround;
	}
	
	/**
	 * Permet de connaître les sols avec lesquels une position est en collision
	 * @author Raphaël
	 * @param x Position en abscisse
	 * @param y Position en ordonnée
	 * @param mapBuilder Générateur de map du jeu
	 * @return Itérateur sur les sols en collision
	 */
	public static Iterator<Ground> getCollidingGrounds(double x, double y, MapBuilder mapBuilder) {		
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
		
		return new CustomIterator<Ground>(collidingGrounds);
	}
	
	public static boolean isBlocked(double x, double y, MapBuilder mapBuilder) {
		int[][] rotations = new int[][]{
				{(int)x, (int)y-1},
				{(int)x+1, (int)y},
				{(int)x, (int)y+1},
				{(int)x-1, (int)y}
		};
		for (int i = 0; i < 3; i++) {
			if (mapBuilder.get(rotations[i][0], rotations[i][1]).isAccessible()) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Calculer la distance entre deux points
	 * @author Raphaël
	 * @param xOne Position en abscisse du premier point
	 * @parma yOne Position en ordonnée du premier point
	 * @param xTwo Position en abscisse du deuxième point
	 * @param yTwo Position en ordonnée du deuxième point
	 * @return Distance entre les deux points
	 */
	public static double calculateDistance(double xOne, double yOne, double xTwo, double yTwo) {	
		double memberOne = (xTwo - xOne);
		double memberTwo = (yTwo - yOne);
		
		return Math.sqrt(memberOne*memberOne + memberTwo*memberTwo);
	}
	
	/**
	 * Permet d'exécuter l'effet en collision le plus proche avec le Pacman 
	 * @author Raphaël
	 */
	public void doEffect(boolean move, Cmd cmd) {
		Ground nearest = getNearestEffectiveGround(this.pacmanCharacter.getPosX(), this.pacmanCharacter.getPosY());

		if (nearest.isTreasure()) {
			nearest.setImage(ImageFactory.getInstance().loadImage("Extra/treasureOpen40x40.png"));
			this.isFinished = true;
		}
		
		if (!nearest.equals(this.executedEffect) || (isBlocked(this.pacmanCharacter.getPosX(), this.pacmanCharacter.getPosX(), mapBuilder) && cmd != Cmd.IDLE) || (!move && !this.executedEffect.isPassage())) {
			nearest.doEffect(this.pacmanCharacter);
			this.consumeGroundEffect(nearest.getPosX(), nearest.getPosY());
		}
	}
	
	/**
	 * Retourne l'échelle (largeur x hauteur) de chaque image du jeu
	 * @author Raphaël
	 * @return Echelle de chaque image
	 */
	public int getScale() {
		return this.scale;
	}

	/**
	 * @author Clément
	 * Retourne le PacmanCharacter
	 * @return le PacmanCharacter
	 */
	public PacmanCharacter getCharacter() {
		return this.pacmanCharacter;
	}

	/**
	 * @author Clément
	 * Retourne le monstre à l'index donné en paramètre
	 * @param index index dans la liste
	 * @return le monstre à l'index donné en paramètre
	 */
	public MonsterCharacter getMonsterCharacter(int index) {
		return monstersList.get(index);
	}
}

