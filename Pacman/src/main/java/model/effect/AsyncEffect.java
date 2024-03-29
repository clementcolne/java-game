package model.effect;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import engine.CustomIterator;
import engine.ProjectException;

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
	private static Set<Effect> effects = Collections.synchronizedSet(new HashSet<Effect>());
	private static Map<Class<? extends Effect>, AsyncEffect> tasks = Collections.synchronizedMap(new HashMap<Class<? extends Effect>, AsyncEffect>());
	private static boolean waiting = false;
	
	/**
	 * Constructeur de l'effet asynchrone
	 * @author Raphaël
	 * @param eff Effet à ajouter
	 * @param etd Deux effets peuvent être incompatibles. Il est possible d'annuler un effet en indiquant l'objet de classe correspondant.
	 * @param time Temps de déroulement de l'effet (ms)
	 * @param st Temps à partir duquel l'effet doit commencer à être appliqué (ms)
	 * @param p Période représentant l'intervalle de rafraichissement de l'exécution de la tâche (ms)
	 * @throws ProjectException Lancée en cas de paramètres fournis incorrects
	 */
	public AsyncEffect(Effect eff, Class<? extends Effect> etd, long time, long st, long p) throws ProjectException  {
		this.effect = eff;
		this.effectToDestroy = etd;
		this.remainingTime = time;
		this.startTime = st;
		this.period = p;
		
		if (eff == null) {
			throw new ProjectException("An effect can't be null");
		}
		if (this.startTime < 0) {
			throw new ProjectException("Start time of effect must at least be 0 ms");
		}
		else if (this.period <= 0) {
			throw new ProjectException("Period of effect must at least be 1 ms");
		}
		else if (this.remainingTime <= 0) {
			throw new ProjectException("Effect must at least last 1 ms");
		}
		else if (this.remainingTime < this.period) {
			throw new ProjectException("The effect must last longer than the defined period");
		}
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
					waiting = true;
					this.runned = true;
				}
				else if (effects.contains(this.effect) && this.runned) {
					waiting = false;
					this.executionNumber++;
					

					this.execute();
					if (this.remainingTime <= 0) {
						this.end();
					}
				
					this.remainingTime -= this.period;
				}
			}			
		}

		try {
			while (waiting) {
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	        waiting = false;
			this.end = true;
			this.execute();
			
			effects.remove(this.effect);
			tasks.remove(this.effect.getClass());
			
	        this.cancel();
			//System.out.println(effect + " est fini");
		}
	}
	
	
	/**
	 * Permet de terminer un effet spécifique ou tous les effets
	 * @param effClass Classe de l'effet (ou Effect.class pour tous les effets)
	 */
	public static void end(Class<? extends Effect> effClass) {
		CustomIterator<AsyncEffect> aeList = new CustomIterator<AsyncEffect>(tasks.values());
		
		synchronized (aeList) {
			while (aeList.hasNext()) {
				AsyncEffect ae = aeList.next();
				
				if (effClass.isAssignableFrom(ae.getEffect().getClass())) {
					ae.end();
				}
			}
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
	 * Retourne l'effet associé à la tâche
	 * @return Effet de la tâche
	 */
	public Effect getEffect() {
		return this.effect;
	}
	
	/**
	 * Retourne une nouvelle liste contenant les effets actuellement en cours
	 * @author Raphaël
	 * @return Liste des effets actuellement en cours
	 */
	public synchronized static CustomIterator<Effect> getEffects() {
		return new CustomIterator<Effect>(effects);
	}
}
