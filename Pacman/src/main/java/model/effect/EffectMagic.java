package model.effect;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.PacmanCharacter;

/**
 * Déclenche un effet au hasard parmi les effets magiques connus
 * @author Raphaël
 **/
public class EffectMagic extends Effect {
    private static List<EffectMagic> magicEffects = new LinkedList<EffectMagic>(Arrays.asList(new EffectMagic[] {
    		new Ghost(),
    		new Speed(),
    		new Bow(),
    }));
    
    /**
     * Constructeur de EffectMagic (case effet magique tirée au hasard)
     * @author Raphaël
     */
    public EffectMagic() {
    	this.color = Color.BLUE;
    }
  
    /**
     * Exécute un effet magique au hasard
     * @author Raphaël
     * @param character Personnage affecté par le piège
     */
    @Override
    public void doEffect(PacmanCharacter pacmanCharacter) {
        EffectMagic temp = magicEffects.get((int)(Math.random()*magicEffects.size()));
    	temp.doEffect(pacmanCharacter);
        System.out.println(temp);
    }
    
  
}
