/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhd.simulator.random;

/**
 *
 * @author Oscar Lao
 */
public class RandomTwoSides extends RandomFunction{
    public RandomTwoSides(double min, double max)
    {
        this.min = min;
        this.max = max;
    }
    
    private final double min, max;

    @Override
    public double sample() {
        RandomBeta rb = new RandomBeta(0.5,0.5);        
        return rb.sample()*(max-min) + min;
    }
}
