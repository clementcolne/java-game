package model.movingStrategy;

import engine.MapBuilder;
import model.Character;
import model.PacmanCharacter;

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
    public GhostMovingStrategy(PacmanCharacter pc) {
        super(pc);
    }

    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
        return pacmanCharacter.getPosX() + x < mapBuilder.getWidth() && pacmanCharacter.getPosY() + y < mapBuilder.getHeight() && pacmanCharacter.getPosX() + x >=0 && pacmanCharacter.getPosY() + y >=0;
    }

    @Override
    public boolean canBypassGround(double x, double y) {
        double posX = this.pacmanCharacter.getPosX();
        double posY = this.pacmanCharacter.getPosY();

        boolean insideXArea = posX + x >= 0 && ((posX + x) >= 0 ? Math.ceil(posX + x) < this.mapBuilder.getWidth() : false);
        boolean insideYArea = posY + y >= 0 && ((posY + y) >= 0 ? Math.ceil(posY + y) < this.mapBuilder.getHeight() : false);

        if (insideXArea && insideYArea) {
            return true;
        }
        else {
            if (posX + x < 0) {
                pacmanCharacter.setPosX(0);
            }
            else if (Math.ceil(posX + x) >= mapBuilder.getWidth()) {
                pacmanCharacter.setPosX(mapBuilder.getWidth()-1);
            }
            else if (posY + y < 0) {
                pacmanCharacter.setPosY(0);
            }
            else if (Math.ceil(posY + y) >= mapBuilder.getHeight()) {
                pacmanCharacter.setPosY(mapBuilder.getHeight()-1);
            }
            return false;
        }
    }


}
