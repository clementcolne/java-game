package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Clément Colné
 */
public class MapBuilder {

    private String path;
    private char[][] map;
    private int width;
    private int length;

    /**
     * @author Clément
     * Constructeur de la map
     * @param path chemin vers le fichier texte décrivant la map
     * @param width largeur de la map
     * @param length longueur de la map
     */
    public MapBuilder(String path, int width, int length) {
        this.path = path;
        this.width = width;
        this.length = length;
    }

    /**
     * @author Clément
     * Construit la map à partir du fichier texte décrivant la map
     * @return tableau contenant des objets de type Ground décrivant la map
     */
    public char[][] buildMap() {
        // initialisation du tableau qui contiendra les objets de la map
        this.map = new char[width][length];

        // lecture du fichier
        try {
            File file = new File("src/main/java/ressources/" + path);
            Scanner reader = new Scanner(file);
            // compteur de lignes
            int i = 0;
            while(i < length) {
                String data = reader.nextLine();
                for(int j = 0 ; j < data.length() ; j++) {
                    // complète la map
                    map[i][j] = getGround(data.charAt(j));
                }
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas été trouvé");
            e.printStackTrace();
        }

        return map;
    }

    /**
     * @author Clément
     * Retourne l'objet de type Ground correspondant au caractère en paramètre
     * @param c caractère qui décrit l'objet
     * @return objet de type Ground
     */
    private char getGround(char c) {
        char res = ' ';
        switch (c) {
            case 'w':
                // wall
                res = 'w';
                // res = new Wall();
                break;
            case 'e':
                // end
                res = 'e';
                // res = new End();
                break;
            case 'm':
                // magic
                res = 'm';
                // res = new Magic();
                break;
            case 't':
                // trap
                res = 't';
                // res = new Trap();
                break;
            case 'p':
                // passage
                res = 'p';
                // res = new Passage();
                break;
            default:
                // par défault, la case est un sol
                res = 'g';
                // res = new Ground();
                break;
        }
        return res;
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
                    sb.append(map[i][j]);
                }
                sb.append("]\n");
            }
        }
        return sb.toString();
    }

}
