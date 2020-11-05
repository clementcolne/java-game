package model;

import model.factory.ImageFactory;

import java.awt.*;

/**
 * Classe représentant le tresor
 * @author Adham
 *
 */
public class End extends Ground {

    public End(int x, int y) {
        super(x, y);
        this.name = 'k';
        this.color  = Color.PINK;
        this.image = ImageFactory.getInstance().get("Extra/treasure40x40.png");
    }

    @Override
    public boolean isTreasure(){
        return true ;
    }

    @Override
    public void doEffect(PacmanCharacter pacmanCharacter) {
        System.out.println("You Won !");
    }
}
