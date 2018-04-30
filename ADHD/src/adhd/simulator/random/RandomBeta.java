/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adhd.simulator.random;

/**
 *
 * @author Oscar
 */
public class RandomBeta extends RandomFunction{

    public RandomBeta(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
    }
    private final double alpha, beta;

    public double sample() {
        double X1 = RandomGamma.sampleGamma(alpha, 1);
        double X2 = RandomGamma.sampleGamma(beta, 1);
        double X = X1 / (X1 + X2);
        return X;
    }
}
