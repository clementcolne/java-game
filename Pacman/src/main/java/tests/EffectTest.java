package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import engine.ProjectException;
import model.PacmanCharacter;
import model.effect.AsyncEffect;
import model.effect.Effect;
import model.effect.Slow;
import model.effect.Speed;

class EffectTest {
	
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
     * Lancement d'effets incompatibles (le deuxième doit stopper le premier)
     * @throws ProjectException
     */
    @Test
    void testDoEffectIncompatibleBoundary() throws Exception {
    	int i = 100;
    	
    	while (i>0) {
    		new Speed().doEffect(pacman);
    		new Slow().doEffect(pacman);
    		
    		assertTrue(AsyncEffect.getEffects().size() <= 1);
    		i--;
    	}
    	
    	assertEquals(1, AsyncEffect.getEffects().size());
    	assertEquals(0.5, pacman.getSpeed());
    	
    	Thread.sleep(5100);
    	assertEquals(0, AsyncEffect.getEffects().size());
    	assertEquals(1, pacman.getSpeed());
    }
    
    /**
     * Lancement d'effets incompatibles (le deuxième doit stopper le premier)
     * @throws Exception
     */
    @Test
    void testDoEffectIncompatibleBoundaryCrosscheck() throws Exception {
    	int i = 100;
    	
    	while (i>0) {			
			new AsyncEffect(new Speed(), Slow.class, 10000, 0, 5000) {
				public void execute() {
					pacman.setSpeed(!this.isEnded() ? 3 : 2);
				}
			}.run();
			
			new AsyncEffect(new Slow(), Speed.class, 10000, 20, 5000) {
				public void execute() {
					pacman.setSpeed(!this.isEnded() ? 0.5: 1);
				}
			}.run();
    		
    		assertTrue(AsyncEffect.getEffects().size() <= 1);
        	i--;
    	}
    	
    	assertEquals(1, AsyncEffect.getEffects().size());
		assertEquals(1,  AsyncEffect.getEffects().size());
		assertEquals(0.5,  pacman.getSpeed());
    	
    	Thread.sleep(5100);
    	assertEquals(1, AsyncEffect.getEffects().size());
    	assertEquals(0.5, pacman.getSpeed());
    	
    	Thread.sleep(5100);
    	assertEquals(1, pacman.getSpeed());

    }
    
    /**
     * S'assurer qu'on ne peut pas créer un effet associé à aucun effet concret
     * @author Raphaël
     */
    @Test
    void testDoEffectNullBoundary() {
    	assertThrows(ProjectException.class, () -> {
	    	new AsyncEffect(null, Speed.class, 1, 0, 1) {}.run();
    	});
    }
    
    /**
     * S'assurer qu'on ne peut pas créer un effet ayant un temps de démarrage négatif
     * @author Raphaël
     */
    @Test
    void testDoEffectNegativeStartTimeBoundary() {
    	assertThrows(ProjectException.class, () -> {
	    	new AsyncEffect(null, Speed.class, 10000, -1, 5000) {}.run();
    	});
    }
    
    /**
     * S'assurer qu'on ne peut pas créer un effet ayant une période nulle
     * @author Raphaël
     */
    @Test
    void testDoEffectNullPeriodBoundary() {
    	assertThrows(ProjectException.class, () -> {
	    	new AsyncEffect(null, Speed.class, 10000, 0, 0) {}.run();
    	});
    }
    
    /**
     * S'assurer qu'on ne peut pas créer un effet ayant une période nulle
     * @author Raphaël
     */
    @Test
    void testDoEffectNegativePeriodBoundary() {
    	assertThrows(ProjectException.class, () -> {
	    	new AsyncEffect(null, Speed.class, 10000, 0, -1) {}.run();
    	});
    }
    
    /**
     * S'assurer qu'on ne peut pas créer un effet ayant une période supérieure à la durée de l'effet
     * @author Raphaël
     */
    @Test
    void testDoEffectIncorrectParametersBoundary() {
    	assertThrows(ProjectException.class, () -> {
	    	new AsyncEffect(null, Speed.class, 100, 10, 500) {}.run();
    	});
    }

}
