package model;

import java.awt.Color;

import model.effect.EffectMagic;

/**
 * Classe qui représente un sol associé à un effet magique
 * @author Raphaël
 *
 */
public class Magic extends Ground {
	
	private EffectMagic effect;

	/**
	 * Constructeur de Magic (magie)
	 * @author Raphaël
	 * @param x, position en abscisse de la case de magie
	 * @param y, position en ordonnée de la case de magie
	 */
	public Magic(int x, int y, EffectMagic eff) {
		super(x, y);
		this.name = 'm';
		this.effect = eff;
		this.color = Color.PINK;
	}
	
	/**
     * Exécute l'effet magique associé à cette case
     * @author Raphaël
     * @param pacmanCharacter Personnage affecté par l'effet magique
     */
    @Override
	public void doEffect(PacmanCharacter pacmanCharacter) {
		this.effect.doEffect(pacmanCharacter);
	}
    
    /**
     * Permet d'indiquer que la case est associée à un effet
     * @author Raphaël
     * @return true
     */
    @Override
    public boolean isEffect() {
    	return true;
    }
	
	

}
