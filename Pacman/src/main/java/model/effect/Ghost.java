package model.effect;

import engine.ProjectException;
import model.PacmanCharacter;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.GhostMovingStrategy;

/**
 * Classe représentant l'effet fantôme. Un fantôme peut traverser les murs.
 * @author Raphaël
 *
 */
public class Ghost extends EffectMagic {
	
	/**
	 * Appliquer l'effet fantôme sur le Pacman. L'effet est appliqué au début, puis retiré au bout de 5 secondes
	 * @author Raphaël
	 * @param pacmanCharacter Pacman auquel doit être appliqué l'effet
	 * @throws ProjectException Lancée si le Pacman est null
	 */
	@Override
    public void doEffect(final PacmanCharacter pacmanCharacter) throws ProjectException {	
		if (pacmanCharacter == null) {
    		throw new ProjectException("Character can't be null");
    	}
		new AsyncEffect(this, Effect.class, 5000, 0, 5000) {
			public void execute() {
				pacmanCharacter.setMovingStrategy(!this.isEnded() ? new GhostMovingStrategy(pacmanCharacter) : new DefaultMovingStrategy(pacmanCharacter));
			}
		}.run();
    }

	@Override
	public String toString() {
		return "Effet Fantome";
	}
}
