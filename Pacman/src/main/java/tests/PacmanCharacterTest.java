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
        //Right
        assert(pacman.getPosX()==5):"La coordonnée horizontale du personnage à sa création n'est pas la bonne";
        for(int i = 4; i>-1; i--){
            pacman.mooveLeft();
            assert (pacman.getPosX()==i):"La coordonnée horizontale après déplacement à gauche est incorecte (x!="+i+")";
        }
        //Boundary
        pacman.mooveLeft();
        assert(pacman.getPosX()==-1):"La coordonnée horizontale après déplacement à gauche est incorecte (x!=-1)";
    }

    @org.junit.jupiter.api.Test
    void mooveUp() {
    }

    @org.junit.jupiter.api.Test
    void mooveDown() {
        assert(pacman.getPosY() == 5): "La position du pacman devrait être de 5";
        pacman.mooveDown();
        assert(pacman.getPosY() == 4): "La position du pacman devrait être de 4";
        pacman.mooveDown();
        pacman.mooveDown();
        pacman.mooveDown();
        pacman.mooveDown();
        assert(pacman.getPosY() == 0): "La position du pacman devrait être de 0";
        pacman.mooveDown();
        assert(pacman.getPosY() == -1): "La position du pacman devrait être de -1";
    }
}