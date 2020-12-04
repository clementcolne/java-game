package tests;

import model.PacmanCharacter;
import model.effect.*;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.GhostMovingStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class EffectMagicTest {
	/**
	 * Note : les tests effectués ci-dessous sont relatifs à la puissance de la machine sur laquelle ils sont effectuées en raison de la méthode de test utilisée pour JUnit. 
	 * Si une erreur se produit, il faut essayer d'augmenter la valeur dans les Thread.sleep.
	 */
	
	private PacmanCharacter pacman;
	
	@BeforeAll
	static void stopPreviousTestEffects() {
		AsyncEffect.end(Effect.class);
	}

    @BeforeEach
    void setUp() {
        pacman = new PacmanCharacter(5, 5);
    }
    
    /**
     * Lancement des effets magiques de base
     * @throws Exception
     */
    @Test
    void testDoEffectRight() throws Exception {
    	int i = 10000;
    	
    	while (i>0) {
    		new Ghost().doEffect(pacman);
    		new Speed().doEffect(pacman);
    		new Bow().doEffect(pacman);

    		assertTrue(AsyncEffect.getEffects().size() <= 3, "Un effet ne doit pas être lancé plus d'une fois !");
    		i--;
    	}    

    	assertEquals(3, AsyncEffect.getEffects().size());
		assertEquals("ghost",  pacman.getMovingStrategyType(), "Le Pacman doit désormais être un fantôme");
    	assertEquals(2, pacman.getSpeed(), "Le Pacman doit désormais voir sa vitesse incrémentée de 1");
    	assertEquals(3, pacman.getRange(), "Le Pacman doit désormais voir sa portée des attaques incrémentée de 2");
    	
    	Thread.sleep(5100);
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals("default",  pacman.getMovingStrategyType(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    }
    
    /**
     * Lancement des effets magiques de base
     * @throws Exception
     */
    @Test
    void testDoEffectCrosscheck() throws Exception {
    	int i = 10000;
		Effect[] effects = new Effect[3];
		effects[0] = new Ghost();
		effects[1] = new Speed();
		effects[2] = new Bow();
    	
    	while (i>0) {    		
    		new AsyncEffect(new Ghost(), Effect.class, 1000, 0, 500) {
    			public void execute() {
    				pacman.setMovingStrategy(!this.isEnded() ? new GhostMovingStrategy(pacman) : new DefaultMovingStrategy(pacman));
    			}
    		}.run();
    			
    		new AsyncEffect(new Speed(), Effect.class, 1000, 0, 500) {
    			public void execute() {
    				pacman.setSpeed(!this.isEnded() ? 2 : 1);
    			}
    		}.run();
    			
    		new AsyncEffect(new Bow(), Effect.class, 1000, 0, 500) {
    			public void execute() {
    				pacman.setRange(!this.isEnded() ? 3 : 1);
    			}
    		}.run();
    		
    		assertTrue(AsyncEffect.getEffects().size() <= 3);
    		i--;
    	}

    	assertEquals(3, AsyncEffect.getEffects().size());
		assertEquals("ghost",  pacman.getMovingStrategyType());
    	assertEquals(2, pacman.getSpeed());
    	assertEquals(3, pacman.getRange());
    	
    	Thread.sleep(1100);
    	assertEquals(0, AsyncEffect.getEffects().size());
    	assertEquals("default",  pacman.getMovingStrategyType());
    	assertEquals(1, pacman.getSpeed());
    	assertEquals(1, pacman.getRange());
    }
    
    /**
     * Lancement des effets de façon aléatoire
     * @throws Exception
     */
    @Test
    void testDoEffectRandomRight() throws Exception {
    	int i = 10000;

    	while (i>0) {
    		new EffectMagic().doEffect(pacman);
    		new EffectMagic().doEffect(pacman);
    		new EffectMagic().doEffect(pacman);
        	assertTrue(AsyncEffect.getEffects().size() <= 3);
        	i--;
    	}
    	
    	assertEquals(3, AsyncEffect.getEffects().size());
		assertEquals("ghost",  pacman.getMovingStrategyType(), "Le Pacman doit désormais être un fantôme");
    	assertEquals(2, pacman.getSpeed(), "Le Pacman doit désormais voir sa vitesse incrémentée de 1");
    	assertEquals(3, pacman.getRange(), "Le Pacman doit désormais voir sa portée des attaques incrémentée de 2");
    	
    	Thread.sleep(5100);
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals("default",  pacman.getMovingStrategyType(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(1, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    }
}