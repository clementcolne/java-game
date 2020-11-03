package tests;

import model.PacmanCharacter;
import model.effect.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EffectTrapTest {

    private PacmanCharacter pacman;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        pacman = new PacmanCharacter(5, 5);
    }

    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void doEffect() throws InterruptedException {

        for(int i =0; i<1000; i++) {
            new Slow().doEffect(pacman);
            new Stun().doEffect(pacman);
            new Stop().doEffect(pacman);
            assertEquals(3, AsyncEffect.getEffects().size(), "Un effet ne doit pas être lancé plus d'une fois !");
        }
    }

    @Test
    void slowEffect() throws InterruptedException {
        new Slow().doEffect(pacman);
        Thread.sleep(1);
        assertEquals(0.5, pacman.getSpeed(), "La vitesse du pacman n'a pas diminuée");
        Thread.sleep(5001);
        assertEquals(1, pacman.getSpeed(), "La vitesse du pacman n'est pas revenue à la normale");
    }

    @Test
    void stopEffect() throws InterruptedException {
        new Stop().doEffect(pacman);
        Thread.sleep(1);
        assertEquals(0, pacman.getSpeed(), "La vitesse du pacman n'est pas nulle");
        Thread.sleep(5001);
        assertEquals(1, pacman.getSpeed(), "La vitesse du pacman n'est pas revenue à la normale");
    }
}