/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhd;

import adhd.model.Model;
import adhd.simulator.random.RandomTwoSides;
import adhd.simulator.random.RandomUniform;
import data_transformation.InputDataTransformation_ByColumn;
import data_transformation.InputDataTransformation_Standardize;
import network.NetworkGenerator;
import network.NetworkLoader;
import org.encog.neural.networks.BasicNetwork;
import write.WriteFile;

/**
 *
 * @author Oscar Lao
 */
public class ADHD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // Place to save the ABC output.
        WriteFile wf = new WriteFile("C:\\FU_ABC.txt");
        double[] observed = {-0.003307126, -0.002754069, -0.002260308, -0.003230037, -0.002735077, -0.003022789, -0.003036813, -0.002916256};
        int n = 5000;
        double[][] output = new double[n][1];
        double[][] input = new double[n][observed.length];
        int rep = 0;
        // Prior of the selection coefficient. Give a stronger weight to the tails of the distribution
        RandomTwoSides rts = new RandomTwoSides(-Math.pow(10, -6), Math.pow(10, -6));
        // Model simulations to be used as training
        while (rep < n) {
            try {
                //System.out.println(rep);
                Model m = new Model();
                m.run(rts);
                // Value of the simulation at each individual
                input[rep][0] = m.getElMiron().getValue();
                input[rep][1] = m.getGoyetQ116_1().getValue();
                input[rep][2] = m.getKostenski14().getValue();
                input[rep][3] = m.getLoschbour().getValue();
                input[rep][4] = m.getMalta1().getValue();
                input[rep][5] = m.getUstIshim().getValue();
                input[rep][6] = m.getVestonice16().getValue();
                input[rep][7] = m.getVillabruna().getValue();
                // Selection coefficient of this simulation
                output[rep][0] = m.getR();
                rep++;
            } catch (Exception tok) {
                System.out.println("MESSAGE " + tok.getMessage());
            }
        }

        // Transform the output so it ranges between 0 and 1 (we are using Elliot activation functions!)
        InputDataTransformation_ByColumn idto = new InputDataTransformation_ByColumn(-1);
        output = idto.transform(output);
        // Standardize each cell of the input
        InputDataTransformation_Standardize idti = new InputDataTransformation_Standardize(-1);
        input = idti.transform(input);
        // Scale also the observed data!
        observed = idti.transform(observed);

        // Run 10 DL networks
        NetworkLoader[] nl = new NetworkLoader[10];
        NetworkGenerator ng = new NetworkGenerator();

        for (int r = 0; r < nl.length; r++) {

            long startTime = System.currentTimeMillis();

            BasicNetwork bn = ng.runANN(0.0125, startTime, input, output, 5, 10000, 1);

            nl[r] = new NetworkLoader(bn);
        }

        double obs = 0;

        for (int r = 0; r < nl.length; r++) {
            obs += nl[r].predict(observed)[0];
        }

        obs /= nl.length;

        // Observed predictions with the real data
        System.out.println("OBSERVED " + obs);

        rep = 0;

        // New simulations!
        
        RandomUniform ru = new RandomUniform(-Math.pow(10, -6), Math.pow(10, -6));

        while (rep < 1000000) {
//            try {
            //System.out.println(rep);
            Model m = new Model();
            m.run(ru);
            double[] v = new double[observed.length];
            v[0] = m.getElMiron().getValue();
            v[1] = m.getGoyetQ116_1().getValue();
            v[2] = m.getKostenski14().getValue();
            v[3] = m.getLoschbour().getValue();
            v[4] = m.getMalta1().getValue();
            v[5] = m.getUstIshim().getValue();
            v[6] = m.getVestonice16().getValue();
            v[7] = m.getVillabruna().getValue();
            v = idti.transform(v);
            double sim = 0;
            for (int r = 0; r < nl.length; r++) {
                sim += nl[r].predict(v)[0];
            }
            wf.println(m.getR() + " " + sim / nl.length);
            rep++;
        }
        wf.close();
        ng.shutdownEncogInstance();
    }
}
