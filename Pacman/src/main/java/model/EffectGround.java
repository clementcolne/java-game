package model;

import model.effect.Effect;

public class EffectGround extends Ground {

	private Effect effect;

	/**
	 * Constructeur de EffectCase (pour les cases magiques et les pièges
	 * @param x Abscisse de la case
	 * @param y Ordonnée de la case
	 * @param eff Effet concernant la case
	 */
	public EffectGround(int x, int y, Effect eff) {
		super(x, y);
		this.effect = eff;
		this.color = eff.getColor();
	}


	/**
	 * Permet d'exécuter l'effet associée à la case actuelle
	 * @author Raphaël
	 * @param character Pacman auquel doit être appliqué l'effet
	 */
	public void doEffect(PacmanCharacter character) {
		this.effect.doEffect(character);
	}
	
	/**
	 * Permet d'indiquer que la case est une case d'effet
	 * @author Raphaël
	 * @return true
	 */
	@Override
	public boolean isEffect() {
		return true;
	}

}
