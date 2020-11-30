package tests;

import model.MonsterCharacter;
import model.PacmanCharacter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adèle Barbier
 * 29/11/2020
 **/
class CharacterTest {

    private PacmanCharacter pacmanCharacter;
    private MonsterCharacter monsterCharacter;

    @BeforeAll
    static void setUpAll(){
        System.out.println("=== Début test ===");
    }

    @BeforeEach
    void setUp() {
        pacmanCharacter = new PacmanCharacter(5.0, 5.0);
        monsterCharacter = new MonsterCharacter(5.0, 5.0);
    }

    //setDamage

    //right

    @Test
    void setDamage1_right() {
        pacmanCharacter.setDamage(1);
        assertEquals(9, pacmanCharacter.getLife());
    }

    @Test
    void setDamage2_right() {
        pacmanCharacter.setDamage(2);
        assertEquals(8, pacmanCharacter.getLife());
    }

    //boundary

    @Test
    void setDamage1_boundary() {
        pacmanCharacter.setDamage(10);
        assertEquals(0, pacmanCharacter.getLife());
    }

    @Test
    void setDamage2_boundary() {
        pacmanCharacter.setDamage(20);
        assertEquals(0, pacmanCharacter.getLife());
    }

    //attack

    //right
    @Test
    void attack1_right() {
        pacmanCharacter.attack(monsterCharacter);
        assertEquals(2, monsterCharacter.getLife());
    }

    //right
    @Test
    void attack2_right() {
        pacmanCharacter.attack(monsterCharacter);
        pacmanCharacter.attack(monsterCharacter);
        assertEquals(1, monsterCharacter.getLife());
    }

    //boundary
    @Test
    void attack1_boundary() {
        for(int i=0; i<3; i++){
            pacmanCharacter.attack(monsterCharacter);
        }
        assertEquals(0, monsterCharacter.getLife());
    }

    @Test
    void attack2_boundary() {
        for(int i=0; i<20; i++){
            pacmanCharacter.attack(monsterCharacter);
        }
        assertEquals(0, monsterCharacter.getLife());
    }

    @AfterAll
    static void tearDownAll(){
        System.out.println("=== Fin test ===");
    }
}