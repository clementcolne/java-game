package tests;

import engine.ProjectException;
import model.PacmanCharacter;
import model.effect.AsyncEffect;
import model.effect.Effect;
import model.effect.Slow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adèle Barbier
 * 15/12/2020
 **/
class SlowEffectTest {

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
     * Test de l'application de l'effet piège Slow
     * @throws Exception
     */
    @Test
    void testDoEffectSlowRight() throws Exception {

        new Slow().doEffect(pacman);
        assertTrue(AsyncEffect.getEffects().size() <= 1);

        assertEquals(1, AsyncEffect.getEffects().size());
        assertEquals(0.5,  pacman.getSpeed());

        Thread.sleep(5100);
        assertEquals(0, AsyncEffect.getEffects().size());
        assertEquals(1,  pacman.getSpeed());
    }

    /**
     * Test de l'application de l'effet piège Slow
     * @throws Exception
     */
    @Test
    void testDoEffectSlowCrosscheck() throws Exception {

        new AsyncEffect(new Slow(), Effect.class, 1000, 0, 1000) {
            public void execute() {
                pacman.setSpeed(!this.isEnded() ? 0.5 : 1);
            }
        }.run();

        assertTrue(AsyncEffect.getEffects().size() <= 1);

        assertEquals(1, AsyncEffect.getEffects().size());
        assertEquals(0.5,  pacman.getSpeed());

        Thread.sleep(1100);
        assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
        assertEquals(1,  pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    }

    /**
     * S'assurer que la création d'un effet avec un objet inexistant n'est pas possible
     * @author Adèle
     * @throws Exception
     */
    @Test
    void testDoEffectNullCharacter() throws Exception {
        assertThrows(ProjectException.class, () -> {
            new Slow().doEffect(null);
        });
    }
}