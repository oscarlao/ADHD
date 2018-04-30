/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhd.simulator.random;

import java.util.Random;

/**
 *
 * @author Oscar Lao
 */
public class RandomUniform extends RandomFunction{
    public RandomUniform(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    private final double min, max;
    
    @Override
    public double sample() {
        Random r = new Random();
        return r.nextDouble()*(max-min) + min;
    }
}
