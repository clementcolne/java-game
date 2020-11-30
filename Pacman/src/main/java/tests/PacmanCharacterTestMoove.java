package tests;

import model.PacmanCharacter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacmanCharacterTestMoove {

    private PacmanCharacter pacman;

    @BeforeEach
    void setUp() {
        pacman = new PacmanCharacter(5, 5);
    }

    //setPosX

    //right
    @Test
    void setPosX1_right(){
        pacman.setPosX(1);
        Iterator<int[]> it = pacman.getVisitedCoordinates();
        int[] position = it.next();
        assertEquals(5, position[0]);
        assertEquals(5, position[1]);
        int[] position1 = it.next();
        assertEquals(1, position1[0]);
        assertEquals(5, position1[1]);
    }

    @Test
    void setPosX2_right(){
        pacman.setPosX(1);
        pacman.setPosX(6);
        Iterator<int[]> it = pacman.getVisitedCoordinates();
        int[] position = it.next();
        assertEquals(5, position[0]);
        assertEquals(5, position[1]);
        int[] position1 = it.next();
        assertEquals(1, position1[0]);
        assertEquals(5, position1[1]);
        int[] position2 = it.next();
        assertEquals(6, position2[0]);
        assertEquals(5, position2[1]);
    }

    //crosscheck
    @Test
    void setPosX1_crosscheck(){
        pacman.setPosX(1);
        Iterator<int[]> it = pacman.getVisitedCoordinates();

        List<int[]> checkList = new LinkedList<>();
        checkList.add(new int[]{5,5});
        checkList.add(new int[]{1,5});

        int[] position;
        int i =0;

        while(it.hasNext()){
            position = it.next();
            assertEquals(checkList.get(i)[0], position[0]);
            assertEquals(checkList.get(i)[1], position[1]);
            i++;
        }
    }

    //crosscheck
    @Test
    void setPosX2_crosscheck(){
        pacman.setPosX(1);
        pacman.setPosX(7);
        Iterator<int[]> it = pacman.getVisitedCoordinates();

        List<int[]> checkList = new LinkedList<>();
        checkList.add(new int[]{5,5});
        checkList.add(new int[]{1,5});
        checkList.add(new int[]{7,5});

        int[] position;
        int i =0;

        while(it.hasNext()){
            position = it.next();
            assertEquals(checkList.get(i)[0], position[0]);
            assertEquals(checkList.get(i)[1], position[1]);
            i++;
        }
    }

    //setPosY

    //right
    @Test
    void setPosY1_right(){
        pacman.setPosY(1);
        Iterator<int[]> it = pacman.getVisitedCoordinates();
        int[] position = it.next();
        assertEquals(5, position[0]);
        assertEquals(5, position[1]);
        int[] position1 = it.next();
        assertEquals(5, position1[0]);
        assertEquals(1, position1[1]);
    }

    @Test
    void setPosY2_right(){
        pacman.setPosY(1);
        pacman.setPosY(6);
        Iterator<int[]> it = pacman.getVisitedCoordinates();
        int[] position = it.next();
        assertEquals(5, position[0]);
        assertEquals(5, position[1]);
        int[] position1 = it.next();
        assertEquals(5, position1[0]);
        assertEquals(1, position1[1]);
        int[] position2 = it.next();
        assertEquals(5, position2[0]);
        assertEquals(6, position2[1]);
    }

    //crosscheck
    @Test
    void setPosY1_crosscheck(){
        pacman.setPosY(1);
        Iterator<int[]> it = pacman.getVisitedCoordinates();

        List<int[]> checkList = new LinkedList<>();
        checkList.add(new int[]{5,5});
        checkList.add(new int[]{5,1});

        int[] position;
        int i =0;

        while(it.hasNext()){
            position = it.next();
            assertEquals(checkList.get(i)[0], position[0]);
            assertEquals(checkList.get(i)[1], position[1]);
            i++;
        }
    }

    //crosscheck
    @Test
    void setPosY2_crosscheck(){
        pacman.setPosY(1);
        pacman.setPosY(7);
        Iterator<int[]> it = pacman.getVisitedCoordinates();

        List<int[]> checkList = new LinkedList<>();
        checkList.add(new int[]{5,5});
        checkList.add(new int[]{5,1});
        checkList.add(new int[]{5,7});

        int[] position;
        int i =0;

        while(it.hasNext()){
            position = it.next();
            assertEquals(checkList.get(i)[0], position[0]);
            assertEquals(checkList.get(i)[1], position[1]);
            i++;
        }
    }
}