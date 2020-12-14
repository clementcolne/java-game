package model.movingStrategy;

import engine.MapBuilder;
import model.Character;

/**
 * Cette classe décrit la stratégie permettant aux personnages monstres d'avoir la capacité fantôme
 * avec un déplacement dit intelligent
 * @author Clément Colné
 */
public class SmartMovingStrategy extends MovingStrategy {
    /**
     * Constructeur permettant d'indiquer qui est concerné par la stratégie
     * @param c Character
     * @author Clément
     */
    public SmartMovingStrategy(Character c) {
        super(c);
        type = "smart";
    }

    /**
     * Vérifie si le joueur peut se déplacer dans la direction demandée.
     * Si le monstre est sur la même case que le pacman, il ne bouge pas.
     * @author Clément
     * @param x Position en x à ajouter au Pacman
     * @param y Position en y à ajouter au Pacman
     * @param mapBuilder Générateur de la carte
     */
    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
        // si on est sur le pacman, on ne bouge pas
        if(mapBuilder.getPacmanCharacter().getPosX() == character.getPosX() && mapBuilder.getPacmanCharacter().getPosY() == character.getPosY()) {
            return false;
        }
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
