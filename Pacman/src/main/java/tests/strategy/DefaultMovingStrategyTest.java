package tests.strategy;

import model.PacmanCharacter;
import model.effect.AsyncEffect;
import model.effect.Effect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adèle Barbier
 * 15/12/2020
 **/
class DefaultMovingStrategyTest {
    private PacmanCharacter pacman;

    @BeforeEach
    void setUp() {
        pacman = new PacmanCharacter(5, 5);
    }

    //init
    @Test
    void init() {
        //La position du pacman devrait être de 5
        assertEquals(5, pacman.getPosX());
    }

    //mooveRight

    //Right
    @Test
    void mooveRight() {
        pacman.getMovingStrategy().setFactorX(1);
        pacman.mooveRight();
        //La position du pacman devrait être de 6
        assertEquals(6, pacman.getPosX());
    }

    @Test
    void moove2Right() {
        pacman.getMovingStrategy().setFactorX(1);
        pacman.mooveRight();
        pacman.mooveRight();
        //La position du pacman devrait être de 7
        assertEquals(7, pacman.getPosX());
    }

    //Boundary
    @Test
    void moove10Right() {
        pacman.getMovingStrategy().setFactorX(1);
        for(int i=0; i<5; i++){
            pacman.mooveRight();
        }
        //La position du pacman devrait être de 10
        assertEquals(10, pacman.getPosX());
    }

    //mooveLeft

    //Right
    @Test
    void mooveLeft() {
        pacman.getMovingStrategy().setFactorX(-1);
        pacman.mooveLeft();
        //La position du pacman devrait être de 4
        assertEquals(4, pacman.getPosX());
    }

    @Test
    void moove2Left() {
        pacman.getMovingStrategy().setFactorX(-1);
        pacman.mooveLeft();
        pacman.mooveLeft();
        //La position du pacman devrait être de 3
        assertEquals(3, pacman.getPosX());
    }

    //Boundary
    @Test
    void moove10Left() {
        pacman.getMovingStrategy().setFactorX(-1);
        for(int i=0; i<5; i++){
            pacman.mooveLeft();
        }
        //La position du pacman devrait être de 10
        assertEquals(0, pacman.getPosX());
    }

    //mooveUp

    //Right
    @Test
    void mooveUp() {
        pacman.getMovingStrategy().setFactorY(-1);
        pacman.mooveUp();
        //La position du pacman devrait être de 4
        assertEquals(4, pacman.getPosY());
    }

    @Test
    void moove2Up() {
        pacman.getMovingStrategy().setFactorY(-1);
        pacman.mooveUp();
        pacman.mooveUp();
        //La position du pacman devrait être de 3
        assertEquals(3, pacman.getPosY());
    }

    //Boundary
    @Test
    void moove10Up() {
        pacman.getMovingStrategy().setFactorY(-1);
        for(int i=0; i<5; i++){
            pacman.mooveUp();
        }
        //La position du pacman devrait être de 10
        assertEquals(0, pacman.getPosY());
    }

    //Right
    @Test
    void mooveDown() {
        pacman.getMovingStrategy().setFactorY(1);
        pacman.mooveDown();
        //La position du pacman devrait être de 6
        assertEquals(6, pacman.getPosY());
    }

    @Test
    void moove2Down() {
        pacman.getMovingStrategy().setFactorY(1);
        pacman.mooveDown();
        pacman.mooveDown();
        //La position du pacman devrait être de 7
        assertEquals(7, pacman.getPosY());
    }

    //Boundary
    @Test
    void moove10Down() {
        pacman.getMovingStrategy().setFactorY(1);
        for(int i=0; i<5; i++){
            pacman.mooveDown();
        }
        //La position du pacman devrait être de 10
        assertEquals(10, pacman.getPosY());
    }
}