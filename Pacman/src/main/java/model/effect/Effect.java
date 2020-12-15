package model.effect;

import java.awt.Color;

import engine.ProjectException;
import model.PacmanCharacter;

/**
 * @author Adèle, Raphaël
 * 25/10/2020 -> 28/10/2020
 **/
public abstract class Effect {
	
	protected Color color;

	/**
	 * Permet de définir comment l'effet doit réagir
	 * @param character Pacman auquel doit être appliqué l'effet
	 * @throws ProjectException 
	 */
    public abstract void doEffect(PacmanCharacter character) throws ProjectException;
    
    /**
     * Permet d'obtenir la couleur de la case associée à l'effet
     * @author Raphaël
     * @return Couleur de l'effet, s'il s'agit d'un effet tiré au hasard, la couleur de la case tirée au hasard est retournée
     */
	public Color getColor() {
		return this.color;
	}

    /**
     * Permet de déterminer si un effet est égal à un autre, un effet ne faisant qu'exécuter une tâche.
     * @author Raphaël
     * @return true si les deux effets sont égaux, false sinon
     */
    public boolean equals(Object obj) {
    	return ((Effect)this).getClass().equals(((Effect)obj).getClass());
    }

    /**
     * Retourne le hash code associé uniquement à la classe de l'effet
     * @author Raphaël
     * @return Hash code de l'effet
     */
    public int hashCode() {
    	return this.getClass().hashCode();
    }
}