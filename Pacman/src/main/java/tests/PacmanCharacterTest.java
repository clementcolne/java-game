package tests;

import model.PacmanCharacter;

class PacmanCharacterTest {

    private PacmanCharacter pacman = new PacmanCharacter(5, 5);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void mooveRight() {

    }

    @org.junit.jupiter.api.Test
    void mooveLeft() {
    }

    @org.junit.jupiter.api.Test
    void mooveUp() {
    }

    @org.junit.jupiter.api.Test
    void mooveDown() {
        assert(pacman.getPosY() == 5): "La position du pacman devrait être de 5";
        pacman.mooveDown();
        assert(pacman.getPosY() == 6): "La position du pacman devrait être de 4";
        pacman.mooveDown();
        pacman.mooveDown();
        pacman.mooveDown();
        pacman.mooveDown();
        assert(pacman.getPosY() == 10): "La position du pacman devrait être de 0";
        pacman.mooveDown();
        assert(pacman.getPosY() == 11): "La position du pacman devrait être de -1";
    }
}