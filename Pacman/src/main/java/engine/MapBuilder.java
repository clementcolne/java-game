package engine;

import model.*;
import model.Character;
import model.effect.*;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.MovingStrategy;
import model.movingStrategy.SmartMovingStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Classe qui décrit la construction de la map à partir d'un fichier texte
 * @author Clément Colné
 */
public class MapBuilder {

    private String path;
    // Tableau représentant la map du jeu
    private Ground[][] map;
    private PacmanCharacter[][] characters;
    private List<MonsterCharacter> monsters;
    private static PacmanCharacter uniqueCharacter;
    private int width, height;
    private Passage p1, p2;
    private Scanner reader;
    private int nbPassages;
    private int currentLevel;
    private int maxlevel;
    private List<String> maps;

    /**
     * @author Clément
     * Constructeur de la map
     * @param maps list des maps
     */
    public MapBuilder(List<String> maps) {
    	uniqueCharacter = null;
        this.nbPassages = 0;
        this.currentLevel = 1;
        this.maxlevel = maps.size();
        this.maps = maps;

        this.reader = new Scanner(MapBuilder.class.getClassLoader().getResourceAsStream("resources/Map/"+ maps.get(currentLevel-1)));
        
        while(reader.hasNext()) {
            String data = reader.nextLine();
            if (data.length() > this.width) {
            	this.width = data.length();
            }
            this.height++;
        }

        this.reader.close();
        
        if (this.height == 0) {
            this.height = 1;
        }
        if (this.width == 0) {
        	this.width = 1;
        }
        
        // initialisation du tableau qui contiendra les objets de la map
        this.map = new Ground[height][width];
        this.characters = new PacmanCharacter[height][width];
        this.monsters = new ArrayList<>();
        
        buildMap();

        if(nbPassages != 0 && nbPassages != 2) {
            try {
                throw new Exception("Erreur sur la création de la map : le nombre de passage est différent de 0 ou 2.");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    /**
     * @author Clément
     * Construit la map à partir du fichier texte décrivant la map
     * @return tableau contenant des objets de type Ground décrivant la map
     */
    private void buildMap() {
        this.reader = new Scanner(MapBuilder.class.getClassLoader().getResourceAsStream("resources/Map/"+maps.get(currentLevel-1)));
       
        // compteur de lignes
        int i = 0;
        while(reader.hasNext()) {
            String data = reader.nextLine();
            for(int j = 0 ; j < width ; j++) {
                // pour chaque colonne
                // complète la map
                map[i][j] = getGround(data.charAt(j), j, i);
                MonsterCharacter m = generateMonsterCharacter(data.charAt(j), j, i);
                if(m != null) {
                    monsters.add(m);
                }
                characters[i][j] = generatePacmanCharacter(data.charAt(j), j, i);
            }
            i++;
        }
        reader.close();
    }

    /**
     * Permet de récupérer un éventuel personnage associé à une case
     * @author Raphaël
     * @param c Caractère décrivant le personnage
     * @param x Position en abscisse du personnage
     * @param y Position en ordonnée du personnage
     * @return Personnage si existant, sinon null
     */
    private PacmanCharacter generatePacmanCharacter(char c, int x, int y) {
        PacmanCharacter res = null;
        if(c == '1') {
            if (uniqueCharacter == null) {
              res = new PacmanCharacter(x, y);
              uniqueCharacter = res;
            }
        }
        return res;
	  }

    /**
     * Permet de récupérer un éventuel personnage associé à une case
     * @author Raphaël
     * @param c Caractère décrivant le personnage
     * @param x Position en abscisse du personnage
     * @param y Position en ordonnée du personnage
     * @return Personnage si existant, sinon null
     */
    private MonsterCharacter generateMonsterCharacter(char c, int x, int y) {
        MonsterCharacter res = null;
        if(c == '2') {
            res = new MonsterCharacter(x, y,this.currentLevel);
        }
        return res;
    }

    /**
     * Retourne l'itérateur sur la liste de monstres
     * @return l'itérateur sur la liste de monstres
     */
    public Iterator<MonsterCharacter> getIterator() {
        return new CustomIterator<MonsterCharacter>(monsters);
    }

    /**
     * Met à jour la map
     * @author Adham
     */
    public void updateMap(PacmanCharacter pc){
        this.clearAllMonsters(monsters);
        this.p1 = null;
        this.buildMap();
        pc.setCoordinates(this.updatePacmanPosX(),this.updatePacmanPosY());
        pc.resetLife();

    }

    /**
     * Met à jour la position en abscisse du pacman
     * @author Adham
     * @return position X
     */
    public int updatePacmanPosX(){
        int pos = 0;
        this.reader = new Scanner(MapBuilder.class.getClassLoader().getResourceAsStream("resources/Map/"+ maps.get(currentLevel-1)));

        while(reader.hasNext()) {
            String data = reader.nextLine();
            for(int j = 0 ; j < width ; j++) {
                if(data.charAt(j) == '1') {
                    pos= j;
                }
            }
        }
        reader.close();

        return pos;
    }

    /**
     * Met à jour la position en ordonnée du pacman
     * @author Adham
     * @return position Y
     */
    public int updatePacmanPosY(){
        int pos = 0;
        this.reader = new Scanner(MapBuilder.class.getClassLoader().getResourceAsStream("resources/Map/"+ maps.get(currentLevel-1)));

        int i = 0;
        while(reader.hasNext()) {
            String data = reader.nextLine();
            for(int j = 0 ; j < width ; j++) {
                if(data.charAt(j) == '1') {
                    pos= i;
                }
            }
            i++;
        }
        reader.close();

        return pos;
    }

    /**
     * Retourne le nombre de monstres présents sur la map
     * @return int le nombre de monstres présents sur la map
     */
    public int getNbMonsters() {
        return monsters.size();
    }

	/**
     * @author Clément
     * Retourne l'objet de type Ground correspondant au caractère en paramètre
     * @param c caractère qui décrit l'objet
     * @return objet de type Ground
     */
    private Ground getGround(char c, int x, int y) {
        Ground res;
        switch (c) {
            case '-':
                // wall
                res = new Wall(x, y);
                break;
            case 'm':
                // magic
                res = new Magic(x, y, new EffectMagic());
                break;
            case 'b':
            	// bow
            	res = new Magic(x,y, new Bow());
            	break;
            case 'g':
                // ghost
                res = new Magic(x,y, new Ghost());
                break;
            case '>':
            	// speed
            	res = new Magic(x,y, new Speed());
            	break;
            case 't':
                // trap
                res = new Trap(x, y, new EffectTrap());
                break;
            case '<':
            	// slow
            	res = new Trap(x,y, new Slow());
            	break;
            case 'x':
            	// stop
            	res = new Trap(x,y, new Stop());
            	break;
            case '~':
            	// stun
            	res = new Trap(x,y, new Stun());
            	break;
            case 'p':
                // passage
                res = new Passage(x, y);
                setPassages(res);
                break;
            case 'k':
                // treasure
                res = new End(x, y);
                break;
            case '^':
            	res = new Ground(x, y);
				break;
            default:
                // par défault, la case est un sol
                res = new Ground(x, y);
                break;
        }
        return res;
    }

    /**
     * Permet d'associer un sol à un passage
     * @author Clément
     * @param p Passage à associer au sol (si le premier est déjà existant, le deuxième est lié au premier)
     */
    private void setPassages(Ground p) {
        if(p1 == null) {
            p1 = (Passage)p;
        }else{
            p2 = (Passage)p;
            p1.setLinkedPassage(p2);
            p2.setLinkedPassage(p1);
        }
    }

    /**
     * Retourne la case à la position x;y
     * @author Clément
     * @param x position en x
     * @param y position en y
     * @return case à la position x;y
     */
    public Ground get(int x, int y) {
    	if (y < height && x < width && y >= 0 && x >= 0 && map[y][x] != null) {
    		return map[y][x];
    	}
    	return new Wall(x, y);
    }
    
    /**
     * Retourne le personnage éventuel associé à une case
     * @author Raphaël
     * @param x Position en abscisse dans le tableau de personnages
     * @param y Position en abscisse dans le tableau de personnages
     * @return Pacman si associé, null sinon
     */
    public PacmanCharacter getPacmanCharacter(int x, int y) {
    	if (y < height && x < width && y >= 0 && x >= 0 && uniqueCharacter != null) {
    		if (uniqueCharacter.getPosX() == x && uniqueCharacter.getPosY() == y) {
    			return uniqueCharacter;
    		}
    	}
    	return null;
    }

    /**
     * Retourne le joueur actuel s'il existe
     * @author Raphaël
     * @return Pacman s'il existe, null sinon
     */
    public PacmanCharacter getPacmanCharacter() {
    	return uniqueCharacter;
    }

    /**
     * @author Clément
     * Retourne le monstre situé à la position [x,y] si il existe, null sinon
     * @param x position en x
     * @param y position en y
     * @return le monstre situé à la position x, y si il existe, null sinon
     */
    public MonsterCharacter getMonster(int x, int y) {
        MonsterCharacter monster = null;
        for(MonsterCharacter m : monsters) {
            if(m.getPosX() == x && m.getPosY() == y) {
                monster = m;
            }
        }
        return monster;
    }

    /**
     * Retourne un monstre dans la liste des monstres actuellement présents
     * @author Raphaël
     * @param index Numéro du monstre dans la liste
     * @return Monstre associé à la position index
     */
    public MonsterCharacter getMonster(int index) {
    	return monsters.get(index);
    }

    /**
     * Retourne un personnage quelconque à l'intérieur du jeu à la position (x,y) s'il existe
     * @author Raphaël
     * @param x Position en abscisse du personnage
     * @param y Position en ordonnée du personnage
     * @return Personnage situé à la position (x, y) s'il existe, null sinon
     */
    public Character getCharacter(int x, int y) {
    	Character character = this.getPacmanCharacter(x, y);

    	return character != null ? character : this.getMonster(x, y);
    }

    /**
     * @author Clément
     * Retourne la position en X du monstre à l'index
     * @param index index du monstre dans la liste
     * @return la position en X du monstre à l'index
     */
    public double getMonsterPosX(int index) {
        return monsters.get(index).getPosX();
    }

    /**
     * @author Clément
     * Retourne la position en X du monstre à l'index
     * @param index index du monstre dans la liste
     * @return la position en X du monstre à l'index
     */
    public double getMonsterPosY(int index) {
        return monsters.get(index).getPosY();
    }

    /**
     * Change le Ground [x;y] par le nouveau Ground en paramètre
     * @param x position en x
     * @param y position en y
     * @param g nouveau Ground
     */
    public void set(int x, int y, Ground g) {
        map[y][x] = g;
    }

    /**
     * @author Clément
     * Retourne la largeur de la map
     * @return la largeur de la map
     */
    public int getWidth() {
        return width;
    }

    /**
     * @author Clément
     * Retourne la hauteur de la map
     * @return la hauteur de la map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retourne le nb de niveau
     * @author Adham
     */
    public int getLevel() {
        return currentLevel;
    }

    /**
     * Retourne le nb de niveaux dans le jeu
     * @author Adham
     */
    public int getMaxlevel() {
        return maxlevel;
    }

    /**
     * Augmente le niveau par 1
     * @author Adham
     */
    public void LevelUp() {
        this.currentLevel += 1;
        maps.add("map"+currentLevel+".txt");
    }

    /**
     * @author Clément
     * Retourne la map au format texte
     * @return toString de la map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(map == null) {
            sb.append("Map vide");
        }else{
            for (int i = 0; i < map.length; i++) {
                sb.append("[");
                for (int j = 0; j < map[i].length; j++) {
                    sb.append(map[i][j].toString());
                }
                sb.append("]\n");
            }
        }
        return sb.toString();
    }

    /**
     * Retire le monstre donné de la carte
     * @param m monstre à faire disparaître
     */
    public void removeMonster(MonsterCharacter m) {
        monsters.remove(m);
    }

    /**
     * Supprimer tous les monstres
     * @author Adham		
     * @param monsters
     */
    public void clearAllMonsters(List<MonsterCharacter> monsters){
        monsters.clear();
    }

    /**
     * Ajouter un fichier map à la liste des niveaux
     * @author Adham
     * @param levels
     */
    public void addLevels(String[] levels){
        for (int i = 1;i<levels.length;i++){
            levels[i] = "map"+ i +".txt";
            System.out.println(levels[i]);
        }
    }
}