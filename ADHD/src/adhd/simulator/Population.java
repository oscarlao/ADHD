/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhd.simulator;

/**
 *
 * @author Oscar Lao
 */
public class Population {
    public Population(String name)
    {
        this.name = name;
    }

    public Population(String name, double time)
    {
        this.name = name;
        this.time = time;
    }    
    private final String name;
    private double value = Double.NaN;
    private double time = 0;

    /**
     * Set the time of the population
     * @param time 
     */
    public void setTime(double time) {
        if(time==0)
        {
            System.out.println(name);
            System.exit(0);
        }
        this.time = time;
    }
    
    /**
     * Get the value
     * @return 
     */
    public double getValue() {
        return value;
    }

    /**
     * Get the name
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value
     * @param value 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Get the time
     * @return 
     */
    public double getTime() {
        return time;
    }
}
