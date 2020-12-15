package tests;

import engine.ProjectException;
import model.PacmanCharacter;
import model.effect.AsyncEffect;
import model.effect.Effect;
import model.effect.Stun;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.RandomMovingStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
    @author Adèle Barbier
    15/12/2020
**/
    class StunEffectTest {
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
     * Test de l'application de l'effet piège Stun (une fois maximum)
     * @author Raphaël
     * @throws Exception
     */
    @Test
    void testDoEffectStunRight() throws Exception {
        new Stun().doEffect(pacman);
        assertTrue(AsyncEffect.getEffects().size() <= 1);

        assertEquals(1, AsyncEffect.getEffects().size());
        assertEquals("random",  pacman.getMovingStrategyType());

        Thread.sleep(5100);
        assertEquals(0, AsyncEffect.getEffects().size());
        assertEquals("default",  pacman.getMovingStrategyType());
    }

    /**
     * Test de l'application de l'effet piège Stun (une fois maximum)
     * @author Raphaël
     * @throws Exception
     */
    @Test
    void testDoEffectStunCrosscheck() throws Exception {
        new AsyncEffect(new Stun(), Effect.class, 500, 0, 500) {
            public void execute() {
                pacman.setMovingStrategy(!this.isEnded() ? new RandomMovingStrategy(pacman) : new DefaultMovingStrategy(pacman));
            }
        }.run();

        assertTrue(AsyncEffect.getEffects().size() <= 1);

        assertEquals(1, AsyncEffect.getEffects().size());
        assertEquals("random",  pacman.getMovingStrategyType());

        Thread.sleep(510);
        assertEquals(0, AsyncEffect.getEffects().size());
        assertEquals("default",  pacman.getMovingStrategyType());
    }

    /**
     * S'assurer que la création d'un effet avec un objet inexistant n'est pas possible
     * @author Raphaël
     * @throws Exception
     */
    @Test
    void testDoEffectNullCharacter() throws Exception {
        assertThrows(ProjectException.class, () -> {
            new Stun().doEffect(null);
        });
    }
}