package model.effect;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Cette classe permet de gérer les effets de façon à ce que chaque effet puisse être exécuté pendant que le joueur continue de se déplacer
 * @author Raphaël
 *
 */

public class AsyncEffect extends TimerTask {

	private long remainingTime, startTime, period;
	private int executionNumber = 0;
	private static HashSet<Effect> effects = new HashSet<Effect>();
	private Effect effect;
	
	/**
	 * Constructeur de l'effet asynchrone
	 * @author Raphaël
	 * @param eff Effet à ajouter
	 * @param time Temps de déroulement de l'effet (ms)
	 * @param st Temps à partir duquel l'effet doit commencer à être appliqué (ms)
	 * @param p Période représentant l'intervalle de rafraichissement de l'exécution de la tâche (ms)
	 */
	public AsyncEffect(Effect eff, long time, long st, long p) {
		this.remainingTime = time;
		this.startTime = st;
		this.period = p;
		this.effect = eff;
	}

	/**
	 * Permet d'exécuter l'effet de façon asynchrone pendant un certain temps s'il n'est pas déjà existant.
	 * @author Raphaël
	 */
	@Override
	public void run() {	
		synchronized (effects) { 
			if (AsyncEffect.effects.contains(this.effect)) {
				this.executionNumber++;
				this.execute();
				
				if (this.remainingTime <= 0) {
					AsyncEffect.effects.remove(this.effect);
		            this.cancel();
				}
	
				this.remainingTime -= this.scheduledExecutionTime();
			}
			else {
				if (AsyncEffect.effects.add(this.effect)) {
					new Timer().schedule(this, this.startTime, this.period);
				}
			}
		}
	}
	
	/**
	 * Méthode à redéfinir lors de la création de l'effet dans une classe anonyme, cette méthode doit gérer les actions sur l'objet désiré
	 * @author Raphaël
	 */
	public void execute() {};
	
	/**
	 * Retourner le temps restant de la tâche
	 * @author Raphaël
	 * @return Temps restant de la tâche
	 */
	public long getRemainingTime() {
		return this.remainingTime;
	}
	
	/**
	 * Retourne le numéro de l'exécution de la tâche
	 * @author Raphaël
	 * @return Numéro de l'exécution de la tâche
	 */
	public int getExecutionNumber() {
		return this.executionNumber;
	}
	
	/**
	 * Retourne une nouvelle liste contenant les effets actuellement en cours
	 * @author Raphaël
	 * @return Liste des effets actuellement en cours
	 */
	public static HashSet<Effect> getEffects() {
		synchronized (effects) {
			return new HashSet<Effect>(effects);
		}
	}
	
	/**
	 * Permet de supprimer manuellement un effet de la liste des effets
	 * @param eff Effet à supprimer
	 * @return true si existant, false sinon
	 */
	public static boolean removeEffect(Effect eff) {
		synchronized (effects) {
			return effects.remove(eff);
		}
	}
	
	/**
	 * Permet d'ajouter manuellement un effet à la liste des effets
	 * @param eff Effet à ajouter
	 * @return true si non existant, false sinon
	 */
	public static boolean addEffect(Effect eff ) {
		synchronized (effects) {
			return effects.add(eff);
		}
	}

}
