package model.effect;

import model.PacmanCharacter;

/**
 * @author Adèle, Raphaël
 * 25/10/2020 -> 28/10/2020
 **/
public abstract class Effect {
    public abstract void doEffect(PacmanCharacter character);
    
    /**
     * Permet de déterminer si un effet est égal à un autre, un effet ne faisant qu'exécuter une tâche.
     * @return true si les deux effets sont égaux, false sinon
     */
    public boolean equals(Object obj) {
    	return ((Effect)this).getClass().equals(((Effect)obj).getClass());
    }
    
    /**
     * Retourne le hash code associé uniquement à la classe de l'effet
     * @return Hash code de l'effet
     */
    public int hashCode() {
    	return this.getClass().hashCode();
    }
}