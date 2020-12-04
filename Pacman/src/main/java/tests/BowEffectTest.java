package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import engine.ProjectException;
import model.PacmanCharacter;
import model.effect.AsyncEffect;
import model.effect.Bow;
import model.effect.Effect;
import model.effect.Speed;

class BowEffectTest {

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
     * Test de l'application de l'effet magique Bow (une fois maximum)
     * @throws Exception
     */
    @Test
    void testDoEffectBowRight() throws Exception {
    	int i = 10000;
    	
    	while (i>0) {
    		new Bow().doEffect(pacman);

    		assertTrue(AsyncEffect.getEffects().size() <= 1);
    		i--;
    	}    

    	assertEquals(1, AsyncEffect.getEffects().size());
		assertEquals(3,  pacman.getRange());
    	
    	Thread.sleep(5100);
    	assertEquals(0, AsyncEffect.getEffects().size());
    	assertEquals(1,  pacman.getRange());
    }
    
    /**
     * Test de l'application de l'effet magique Bow (une fois maximum)
     * @throws Exception
     */
    @Test
    void testDoEffectBowCrosscheck() throws Exception {
    	int i = 10000;
    	
    	while (i>0) {
    		new Bow().doEffect(pacman);

    		assertTrue(AsyncEffect.getEffects().size() <= 1);
    		i--;
    	}    

    	assertEquals(1, AsyncEffect.getEffects().size());
		assertEquals(3,  pacman.getRange());
    	
    	Thread.sleep(5100);
    	assertEquals(0, AsyncEffect.getEffects().size());
    	assertEquals(1,  pacman.getRange());
    }
    
    /**
     * S'assurer que la création d'un effet avec un objet inexistant n'est pas possible
     * @author Raphaël
     * @throws Exception
     */
    @Test
    void testDoEffectNullCharacter() throws Exception {
    	assertThrows(ProjectException.class, () -> {
    		new Bow().doEffect(null);
    	});
    }

}
