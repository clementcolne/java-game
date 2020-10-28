package tests;

import model.PacmanCharacter;
import model.effect.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EffectMagicTest {

	private PacmanCharacter pacman;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        pacman = new PacmanCharacter(5, 5);
    }
    

    @org.junit.jupiter.api.Test
    void doEffect() throws InterruptedException {
    	int i = 100000;
    	
    	while (i>0) {
    		new Ghost().doEffect(pacman);
    		new Speed().doEffect(pacman);
    		new Bow().doEffect(pacman);
    		assertEquals(3, AsyncEffect.getEffects().size(), "Un effet ne doit pas être lancé plus d'une fois !");
    		assertTrue(pacman.getGhost(), "Le Pacman doit désormais être un fantôme");
        	assertEquals(3, pacman.getSpeed(), "Le Pacman doit désormais voir sa vitesse incrémentée de 1");
        	assertEquals(3, pacman.getRange(), "Le Pacman doit désormais voir sa portée des attaques incrémentée de 1");
    		i--;
    	}    
    	
    	Thread.sleep(5000);
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertFalse(pacman.getGhost(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(2, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(2, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    	
    	i = 100000;
    	
    	while (i>0) {
    		int[][] taskDuration = new int[3][2];
    		
    		for (int obj = 0; obj < 3; obj++) {
    			taskDuration[obj][0] = (int)(Math.random()*25)+1;
        		taskDuration[obj][1] = taskDuration[obj][0]  - (int)Math.random()*(taskDuration[obj][0]-25);
    		}
    		
    		for (int obj = 0; obj < 3; obj++) {
    			new AsyncEffect(new Ghost(), taskDuration[0][0], 0, taskDuration[0][1]) {
    				public void execute() {
    					pacman.setGhost(this.getExecutionNumber() == 1);
    				}
    			}.run();
    			
    			new AsyncEffect(new Speed(), taskDuration[1][0], 0, taskDuration[1][1]) {
    				public void execute() {
    					pacman.setSpeed(this.getExecutionNumber() == 1 ? 3 : 2);
    				}
    			}.run();
    			
    			new AsyncEffect(new Bow(), taskDuration[2][0], 0, taskDuration[2][1]) {
    				public void execute() {
    					pacman.setRange(this.getExecutionNumber() == 1 ? 3 : 2);
    				}
    			}.run();
    			
    		}
    		
    		assertTrue(AsyncEffect.getEffects().size() >= 1 && AsyncEffect.getEffects().size() <= 3, "Un effet ne doit pas être lancé plus d'une fois !");
    		i--;
    	}
    	
    	Thread.sleep(200);
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertFalse(pacman.getGhost(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(2, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(2, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    	
    	
    	i = 100000;
    	
    	while (i>0) {
    		new EffectMagic().doEffect(pacman);
    		new EffectMagic().doEffect(pacman);
    		new EffectMagic().doEffect(pacman);
    		
    		assertTrue(AsyncEffect.getEffects().size() >= 1, "Un effet ne doit pas être lancé plus d'une fois !");
        	i--;
    	}  
    	
    	assertEquals(3, AsyncEffect.getEffects().size(), "Un effet ne doit pas être lancé plus d'une fois !");
		assertTrue(pacman.getGhost(), "Le Pacman doit désormais être un fantôme");
    	assertEquals(3, pacman.getSpeed(), "Le Pacman doit désormais voir sa vitesse incrémentée de 1");
    	assertEquals(3, pacman.getRange(), "Le Pacman doit désormais voir sa portée des attaques incrémentée de 1");
    	
    	Thread.sleep(5000);
    	assertEquals(0, AsyncEffect.getEffects().size(), "Tous les effets du Pacman doivent être inactifs");
    	assertFalse(pacman.getGhost(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(2, pacman.getSpeed(), "Tous les effets du Pacman doivent être inactifs");
    	assertEquals(2, pacman.getRange(), "Tous les effets du Pacman doivent être inactifs");
    	
    }
}