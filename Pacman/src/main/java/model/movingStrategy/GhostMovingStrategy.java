package model.movingStrategy;

import engine.MapBuilder;
import model.Character;

/**
 * @author Adèle Barbier
 * 05/11/2020
 **/
public class GhostMovingStrategy extends MovingStrategy{

    /**
     * Constructeur permettant d'indiquer qui est concerné par la stratégie
     *
     * @param pc Pacman
     * @author Raphaël
     */
    public GhostMovingStrategy(Character c) {
        super(c);
        type = "ghost";
    }

    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
        this.setFactorX(x);
        this.setFactorY(y);
        this.mapBuilder = mapBuilder;
        return this.canBypassGround(x,y);
    }

    @Override
    public boolean canBypassGround(double x, double y) {
        double posX = this.character.getPosX();
        double posY = this.character.getPosY();

        boolean insideXArea = posX + x >= 0 && ((posX + x) >= 0 ? Math.ceil(posX + x) < this.mapBuilder.getWidth() : false);
        boolean insideYArea = posY + y >= 0 && ((posY + y) >= 0 ? Math.ceil(posY + y) < this.mapBuilder.getHeight() : false);

        if (insideXArea && insideYArea) {
            return true;
        }
        else {
            if (posX + x < 0) {
                character.setPosX(0);
            }
            else if (Math.ceil(posX + x) >= mapBuilder.getWidth()) {
                character.setPosX(mapBuilder.getWidth()-1);
            }
            else if (posY + y < 0) {
                character.setPosY(0);
            }
            else if (Math.ceil(posY + y) >= mapBuilder.getHeight()) {
                character.setPosY(mapBuilder.getHeight()-1);
            }
            return false;
        }
    }


}