package model;

import java.awt.*;

import engine.ImageFactory;
import model.effect.AsyncEffect;
import model.effect.Effect;

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
        this.image = ImageFactory.getInstance().loadImage("Extra/treasure40x40.png");
    }

    @Override
    public boolean isTreasure(){
        return true ;
    }

    @Override
    public void doEffect(PacmanCharacter pacmanCharacter) {
        AsyncEffect.end(Effect.class);
    }
    
    /**
	 * Permet d'indiquer que le sol End effectue une action ayant une répercussion sur d'autres objets
	 * @author Raphaël
	 * @return false pour un une case de fin
	 */
    @Override
	public boolean hasEmptyBehavior() {
		return false;
	}
}
