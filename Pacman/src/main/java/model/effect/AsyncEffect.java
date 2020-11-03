package model.effect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Cette classe permet de gérer les effets de façon à ce que chaque effet puisse être exécuté pendant que le joueur continue de se déplacer
 * @author Raphaël
 *
 */

public class AsyncEffect extends TimerTask {

	private Effect effect;
	private Class<? extends Effect> effectToDestroy;
	private long remainingTime, startTime, period;
	private boolean end = false, runned = false;
	private int executionNumber = 0;
	private static Set<Effect> effects = new HashSet<Effect>();
	private static Map<Class<? extends Effect>, AsyncEffect> tasks = new HashMap<Class<? extends Effect>, AsyncEffect>();
	
	/**
	 * Constructeur de l'effet asynchrone
	 * @author Raphaël
	 * @param eff Effet à ajouter
	 * @param etd Deux effets peuvent être incompatibles. Il est possible d'annuler un effet en indiquant l'objet de classe correspondant.
	 * @param time Temps de déroulement de l'effet (ms)
	 * @param st Temps à partir duquel l'effet doit commencer à être appliqué (ms)
	 * @param p Période représentant l'intervalle de rafraichissement de l'exécution de la tâche (ms)
	 */
	public AsyncEffect(Effect eff, Class<? extends Effect> etd, long time, long st, long p) {
		this.effect = eff;
		this.effectToDestroy = etd;
		this.remainingTime = time;
		this.startTime = st;
		this.period = p;
	}

	/**
	 * Permet d'exécuter l'effet de façon asynchrone pendant un certain temps s'il n'est pas déjà existant.
	 * Si un effet entre en conflit, il est supprimé.
	 * @author Raphaël
	 */
	@Override
	public void run() {	
		synchronized (effects) {
			if (!this.end) {
				if (tasks.containsKey(this.effectToDestroy) && !this.effectToDestroy.equals(this.effect.getClass())) {
					tasks.get(this.effectToDestroy).end();
				}
				if (effects.add(this.effect)) {
					tasks.put(this.effect.getClass(), this);
					new Timer().schedule(this, this.startTime, this.period);
					this.runned = true;
				}
				else if (effects.contains(this.effect) && this.runned) {
					this.executionNumber++;
					
					if (this.remainingTime <= 0) {
						end();
					}
					else {
						this.execute();
					}
				
					this.remainingTime -= this.period;
				}
			}				
		}
	}
	
	/**
	 * Méthode à redéfinir lors de la création de l'effet dans une classe anonyme, cette méthode doit gérer les actions sur l'objet désiré.
	 * Cette méthode est appelée au moins deux fois. 
	 * Lors du dernier appel, isEnded() renvoie true.
	 * Si un effet n entre en conflit avec un effet n+1, alors l'effet n+1 est supprimé. Il est alors possible de gérer l'action a exécuter 
	 * en corrélation grâce à isEnded().
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
	 * Permet de forcer le stoppage de la tâche associée à l'effet courant
	 * @author Raphaël
	 */
	public void end() {
		if (this.runned && !this.end) { 
			this.end = true;
			this.execute();
			
			synchronized (effects) {
				effects.remove(this.effect);
			}
			synchronized (tasks) {
				tasks.remove(this.effect.getClass());
			}
			
	        this.cancel();
			System.out.println(effect + " est fini");
		}
	}
	
	/**
	 * Permet de retourner s'il s'agit de la dernière exécution ou si la tâche a été manuellement arrêtée.
	 * @author Raphaël
	 * @return true ou false
	 */
	public boolean isEnded() {
		return this.end;
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

}
