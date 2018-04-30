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
public class EdgeBetweenTwoPopulations extends Event{
    public EdgeBetweenTwoPopulations(Population source, Population descendent)
    {
        this.source = source;
        this.descendent = descendent;
    }
    
    private final Population source, descendent;
    // r = response to selection; s is the drift factor
    private double r, drift;

    /**
     * Set the drift
     * @param drift 
     */
    public void setDrift(double drift) {
        this.drift = drift;
    }

    /**
     * Set the response to selection
     * @param r 
     */
    public void setR(double r) {
        this.r = r;
    }    
    
    /**
     * Update the descendent
     */
    public void update() throws Exception
    {
        double t = (source.getTime() - descendent.getTime());
        t/=29;
        if(t<0)
        {
            throw new Exception("Cagueit " + source.getName() + " " + source.getTime() + " " + descendent.getName() + " " + descendent.getTime());
        }
        Random ra = new Random();
        descendent.setValue(ra.nextGaussian()*drift*t + r*t + source.getValue());
    }
}
