/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhd.simulator;

import java.util.Random;

/**
 *
 * @author Oscar Lao
 */
public class AdmixPopulation extends Event{
    public AdmixPopulation(Population sourceA, Population sourceB, Population admixed, double fsourceA)
    {
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.admixed = admixed;
        this.fsourceA = fsourceA;
    }
    
    private final Population sourceA, sourceB, admixed;
    private double fsourceA;
    
    /**
     * Update the non-admixed population
     */
    public void update() throws Exception
    {
        admixed.setValue(sourceA.getValue()*fsourceA + (1-fsourceA)*sourceB.getValue());
    }

    /**
     * Get the proportion of admixture of sourceA
     * @return 
     */
    public double getFsourceA() {
        return fsourceA;
    }
}
