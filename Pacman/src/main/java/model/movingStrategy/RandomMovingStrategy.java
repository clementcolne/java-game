package model.movingStrategy;

import engine.MapBuilder;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 30/10/2020
 **/
public class RandomMovingStrategy implements MovingStrategy{

    @Override
    public void mooveUp(PacmanCharacter character) {
        character.setPosX(character.getPosX()-character.getSpeed());
    }

    @Override
    public void mooveDown(PacmanCharacter character) {
        character.setPosX(character.getPosX()+character.getSpeed());
    }

    @Override
    public void mooveRight(PacmanCharacter character) {
        character.setPosY(character.getPosY()-character.getSpeed());
    }

    @Override
    public void mooveLeft(PacmanCharacter character) {
        character.setPosY(character.getPosY()+character.getSpeed());
    }

    /**
     * Vérifie si le joueur peut se déplacer dans la direction Random.
     * Etant donné que l'appel à canMoove est générique, les paramètres x et y sont inversés selon la direction choisie dans les méthodes moove.
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
    	int factorX = 0, factorY = 0;
    	if (x > 0) {
    		factorY = -1;
    		y = -x;
    		x = 0;
    	}
    	else if (x < 0) {
    		factorY = 1;
    		y = -x;
    		x = 0;
    	}
    	else if (y > 0) {
    		factorX = 1;
    		x = y;
    		y = 0;
    	}
    	else if (y < 0) {
    		factorX = -1;
    		x = -y;
    		y = 0;
    	}
    	
    	if (!pacmanCharacter.getGhost()) {
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
	    			pacmanCharacter.setPosY((int)(testY-factorY));
	    			return false;
	    		}
	    	}
    	}
    	
    	boolean isAccessible = (posX + x > 0 && posY + y >=0) ? true : false;
    	boolean isInsideGame = posX + x < mapBuilder.getWidth() && posY + y < mapBuilder.getHeight() && posX + x >=0 && posY + y >=0;
    	
        return isAccessible && isInsideGame;
    }
}
