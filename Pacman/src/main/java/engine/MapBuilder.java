package engine;

import model.*;
import model.Character;
import model.effect.Bow;
import model.effect.EffectMagic;
import model.effect.EffectTrap;
import model.effect.Ghost;
import model.effect.Slow;
import model.effect.Speed;
import model.effect.Stop;
import model.effect.Stun;

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
    private MonsterCharacter[][] monsters;
    private static Character uniqueCharacter;
    private int width;
    private int height;
    private Passage p1;
    private Passage p2;
    private Scanner reader;
    private int nbMonsters;

    /**
     * @author Clément
     * Constructeur de la map
     * @param path chemin vers le fichier texte décrivant la map
     */
    public MapBuilder(String path) {
    	uniqueCharacter = null;
        this.path = path;
        this.nbMonsters = 0;
        
        this.reader = new Scanner(MapBuilder.class.getClassLoader().getResourceAsStream("resources/Map/"+ path));
        
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
        this.monsters = new MonsterCharacter[height][width];
        
        buildMap();
    }

    /**
     * @author Clément
     * Construit la map à partir du fichier texte décrivant la map
     * @return tableau contenant des objets de type Ground décrivant la map
     */
    private void buildMap() {
        this.reader = new Scanner(MapBuilder.class.getClassLoader().getResourceAsStream("resources/Map/"+ path));
       
        // compteur de lignes
        int i = 0;
        while(reader.hasNext()) {
            String data = reader.nextLine();
            for(int j = 0 ; j < width ; j++) {
                // pour chaque colonne
                // complète la map
                map[i][j] = getGround(data.charAt(j), j, i);
                monsters[i][j] = GenerateMonsterCharacter(data.charAt(j), j, i);
                characters[i][j] = GeneratePacmanCharacter(data.charAt(j), j, i);
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
    private PacmanCharacter GeneratePacmanCharacter(char c, int x, int y) {
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
    private MonsterCharacter GenerateMonsterCharacter(char c, int x, int y) {
        MonsterCharacter res = null;
        if(c == '2') {
            res = new MonsterCharacter(x, y);
            this.nbMonsters++;
        }
        return res;
    }

    /**
     * Retourne le nombre de monstres présents sur la map
     * @return int le nombre de monstres présents sur la map
     */
    public int getNbMonsters() {
        return nbMonsters;
    }

	/**
     * @author Clément
     * Retourne l'objet de type Ground correspondant au caractère en paramètre
     * @param c caractère qui décrit l'objet
     * @return objet de type Ground
     */
    private Ground getGround(char c, int x, int y) {
        Ground res = null;
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
     * @param x Position en abscisse dans le tableau de personnages
     * @param y Position en abscisse dans le tableau de personnages
     * @return Pacman si associé, null sinon
     */
    public PacmanCharacter getCharacter(int x, int y) {
    	if (y < height && x < width && y >= 0 && x >= 0 && characters[y][x] != null) {
    		return characters[y][x];
    	}
    	return null;
    }

    public MonsterCharacter getMonster(int x, int y) {
        if (y < height && x < width && y >= 0 && x >= 0 && monsters[y][x] != null) {
            return monsters[y][x];
        }
        return null;
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
}