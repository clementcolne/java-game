package tests;

import model.PacmanCharacter;
import model.effect.*;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.GhostMovingStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class EffectMagicTest {
	/**
	 * Note : les tests effectués ci-dessous sont relatifs à la puissance de la machine sur laquelle ils sont effectuées en raison de la méthode de test utilisée pour JUnit. 
	 * Si une erreur se produit, il faut essayer d'augmenter la valeur dans les Thread.sleep.
	 */
	
	private PacmanCharacter pacman;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        pacman = new PacmanCharacter(5, 5);
    }
    
    /**
     * Lancement des effets standard avec des Thread en masse
     * @throws InterruptedException
     */
    @org.junit.jupiter.api.Test
    void testDoEffectRight() throws InterruptedException {
    	int i = 10000;
    	
    	while (i>0) {
    		new Ghost().doEffect(pacman);
    		new Speed().doEffect(pacman);
    		new Bow().doEffect(pacman);

    		assertTrue(AsyncEffect.getEffects().size() <= 3, "Un effet ne doit pas être lancé plus d'une fois !");
    		i--;
    	}    

		assertEquals(new GhostMovingStrategy(pacman), pacman.getMovingStrategy(), "Le Pacman doit désormais être un fantôme");
    	assertEquals(2, pacman.getSpeed(), "Le Pacman doit désormais voir sa vitesse incrémentée de 1");
    	assertEquals(2, pacman.getRange(), "Le Pacman doit désormais voir sa portée des attaques incrémentée de 1");
    	
    	Thread.sleep(6000);
    	
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(new DefaultMovingStrategy(pacman), pacman.getMovingStrategy(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    }
    
    /**
     * Lancement des effets avec une vitesse aléatoire en masse
     * @throws InterruptedException
     */
    @org.junit.jupiter.api.Test
    void testDoEffectRandomSpeed() throws InterruptedException {
    	int i = 10000;
    	
    	while (i>0) {
    		int[][] taskDuration = new int[5][2];
    		
    		for (int obj = 0; obj < 5; obj++) {
    			taskDuration[obj][0] = (int)(Math.random()*25)+1;
        		taskDuration[obj][1] = taskDuration[obj][0]  - (int) (Math.random() * (taskDuration[obj][0] - 25));
    		}
    		
    		for (int obj = 0; obj < 3; obj++) {
    			new AsyncEffect(new Ghost(), Effect.class, taskDuration[0][0], 0, taskDuration[0][1]) {
    				public void execute() {
    					pacman.setMovingStrategy(!this.isEnded() ? new GhostMovingStrategy(pacman) : new DefaultMovingStrategy(pacman));
    				}
    			}.run();
    			
    			new AsyncEffect(new Ghost(), Ghost.class, taskDuration[1][0], 0, taskDuration[1][1]) {
    				public void execute() {
    					pacman.setMovingStrategy(!this.isEnded() ? new GhostMovingStrategy(pacman) : new DefaultMovingStrategy(pacman));
    				}
    			}.run();
    			
    			new AsyncEffect(new Ghost(), null, taskDuration[2][0], 0, taskDuration[2][1]) {
    				public void execute() {
    					pacman.setMovingStrategy(!this.isEnded() ? new GhostMovingStrategy(pacman) : new DefaultMovingStrategy(pacman));
    				}
    			}.run();
    			
    			new AsyncEffect(new Speed(), Effect.class, taskDuration[3][0], 0, taskDuration[3][1]) {
    				public void execute() {
    					pacman.setSpeed(!this.isEnded() ? 2 : 1);
    				}
    			}.run();
    			
    			new AsyncEffect(new Bow(), Effect.class, taskDuration[4][0], 0, taskDuration[4][1]) {
    				public void execute() {
    					pacman.setRange(this.getExecutionNumber() == 1 ? 2 : 1);
    				}
    			}.run();
    			
    		}
    		
    		assertTrue(AsyncEffect.getEffects().size() <= 3, "Un effet ne doit pas être lancé plus d'une fois !");
    		i--;
    	}
    	
    	Thread.sleep(200);
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(new DefaultMovingStrategy(pacman), pacman.getMovingStrategy(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    }
    
    /**
     * Lancement des effets de façon aléatoire en masse
     * @throws InterruptedException
     */
    @org.junit.jupiter.api.Test
    void testDoEffectRandomEffect() throws InterruptedException {
    	int i = 10000;
    	
    	while (i>0) {
    		new EffectMagic().doEffect(pacman);
    		new EffectMagic().doEffect(pacman);
    		new EffectMagic().doEffect(pacman);
    		
    		assertTrue(AsyncEffect.getEffects().size() >= 1 && AsyncEffect.getEffects().size() <= 3, "Un effet ne doit pas être lancé plus d'une fois !");
        	i--;
    	}  
    	
    	assertEquals(3, AsyncEffect.getEffects().size(), "Un effet ne doit pas être lancé plus d'une fois !");
    	assertEquals(new GhostMovingStrategy(pacman), pacman.getMovingStrategy(), "Le Pacman doit désormais être un fantôme");
    	assertEquals(2, pacman.getSpeed(), "Le Pacman doit désormais voir sa vitesse incrémentée de 1");
    	assertEquals(2, pacman.getRange(), "Le Pacman doit désormais voir sa portée des attaques incrémentée de 1");
    	
    	Thread.sleep(6000);
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(new DefaultMovingStrategy(pacman), pacman.getMovingStrategy(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    	
    }
    
    /**
     * Lancement des effets en masse sans invoquer les classes concrètes
     * @throws InterruptedException
     */
    @org.junit.jupiter.api.Test
    void testDoEffectCrossCheck() throws InterruptedException {
    	int i = 10000;
    	
    	while (i>0) {
    		new AsyncEffect(new Ghost(), Effect.class, 5000, 0, 5000) {
				public void execute() {
					pacman.setMovingStrategy(!this.isEnded() ? new GhostMovingStrategy(pacman) : new DefaultMovingStrategy(pacman));
				}
			}.run();
			
			new AsyncEffect(new Bow(), Effect.class, 3000, 0, 3000) {
				public void execute() {
					pacman.setRange(!this.isEnded() ? 2 : 1);
				}
			}.run();
			
			new AsyncEffect(new Speed(), Slow.class, 10000, 0, 5000) {
				public void execute() {
					pacman.setSpeed(!this.isEnded() ? 3 : 2);
				}
			}.run();
			
			new AsyncEffect(new Speed(), Slow.class, 10000, 0, 5000) {
				public void execute() {
					pacman.setSpeed(!this.isEnded() ? 2 : 1);
				}
			}.run();
			
			new AsyncEffect(new Slow(), Speed.class, 10000, 20, 5000) {
				public void execute() {
					pacman.setSpeed(!this.isEnded() ? 0.5: 1);
				}
			}.run();
    		
    		assertTrue(AsyncEffect.getEffects().size() <= 3, "Un effet ne doit pas être lancé plus d'une fois et Speed doit être remplacé par Slow !");
        	i--;
    	} 
		
		Thread.sleep(120);
    	
		assertEquals(new GhostMovingStrategy(pacman), pacman.getMovingStrategy(), "Le Pacman doit désormais être un fantôme");
    	assertEquals(0.5, pacman.getSpeed(), "L'effet Slow doit pas remplacer l'effet Speed");
    	assertEquals(2, pacman.getRange(), "Le Pacman doit désormais voir sa portée des attaques incrémentée de 1");
    	
    	Thread.sleep(6000);
    	assertEquals(1, AsyncEffect.getEffects().size(), "Seul l'effet Slow doit être actif puisqu'il supprime Speed");
    	assertEquals(new DefaultMovingStrategy(pacman), pacman.getMovingStrategy(), "Seul l'effet Slow doit être actif puisqu'il supprime Speed");
    	assertEquals(0.5, pacman.getSpeed(), "Seul l'effet Slow doit être actif puisqu'il supprime Speed");
    	assertEquals(1, pacman.getRange(), "Seul l'effet Slow doit être actif puisqu'il supprime Speed");
    	
    	Thread.sleep(6000);
    	assertEquals(0, AsyncEffect.getEffects().size(), "10 secondes ont été dépassées, plus aucun effet ne doit être actif");
    	assertEquals(1, pacman.getSpeed(), "10 secondes ont été dépassées, plus aucun effet ne doit être actif");

    }
}