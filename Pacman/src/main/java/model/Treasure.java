package model;

import java.awt.*;

/**
 * Classe représentant le tresor
 * @author Adham
 *
 */
public class Treasure extends Ground {

    public Treasure(int x, int y) {
        super(x, y);
        this.name = 'k';
        this.color  = Color.PINK;
    }

    public Treasure(){
        this.path = "Extra/treasure.png";
    }

    @Override
    public boolean isTreasure(){
        return true ;
    }
}
