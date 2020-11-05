package model.movingStrategy;

import engine.MapBuilder;
import model.Ground;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 30/10/2020
 **/
public class DefaultMovingStrategy implements MovingStrategy {

    @Override
    public void mooveUp(PacmanCharacter character) {
        character.setPosY(character.getPosY()-character.getSpeed());
    }

    @Override
    public void mooveDown(PacmanCharacter character) {
        character.setPosY(character.getPosY()+character.getSpeed());
    }

    @Override
    public void mooveRight(PacmanCharacter character) {
        character.setPosX(character.getPosX()+character.getSpeed());
    }

    @Override
    public void mooveLeft(PacmanCharacter character) {
        character.setPosX(character.getPosX()-character.getSpeed());
    }

    /**
     * Vérifie si le joueur peut se déplacer dans la direction par défaut
     * @author Raphaël
     * @param x Position en x à ajouter au Pacman
     * @param y Position en y à ajouter au Pacman
     * @param mapBuilder Générateur de la carte
     * @param pacmanCharacter Pacman sur lequel la vérification de déplacement doit être effectuée
     */
    @Override
    public boolean canMoove(double x, double y, MapBuilder mapBuilder, PacmanCharacter pacmanCharacter) {
    	int posX = (int)pacmanCharacter.getPosX();
    	int posY = (int)pacmanCharacter.getPosY();

		double factorX = Math.signum(x);
		double factorY = Math.signum(y);

		double testX = posX;
		while ((factorX > 0) ? testX < posX + x : testX > posX + x) {
			testX += factorX;
			if (!mapBuilder.get((int)testX, posY).isAccessible()) {
				pacmanCharacter.setPosX((int)testX-factorX);
				return false;
			}
		}

		double testY = posY;
		while ((factorY > 0) ? testY < posY + y : testY > posY + y) {
			testY += factorY;
			if (!mapBuilder.get(posX, (int) testY).isAccessible()) {
				pacmanCharacter.setPosY((int)testY-factorY);
				return false;
			}
    	}
    	
    	boolean isAccessible = posX + x > 0 && posY + y >= 0;
    	boolean isInsideGame = posX + x < mapBuilder.getWidth() && posY + y < mapBuilder.getHeight() && posX + x >=0 && posY + y >=0;
    	
        return isAccessible && isInsideGame;
    }
}
