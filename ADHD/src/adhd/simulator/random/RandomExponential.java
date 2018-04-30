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
public class RandomExponential extends RandomFunction{
    public RandomExponential(double lammda)
    {
        this.lammda = lammda;
    }
    
    private final double lammda;

    @Override
    public double sample() {
        Random r = new Random();
        double v = -Math.log(1-r.nextDouble())/lammda;
        return v;       
    } 
}
