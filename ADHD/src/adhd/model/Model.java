/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhd.model;

import adhd.simulator.AdmixPopulation;
import adhd.simulator.EdgeBetweenTwoPopulations;
import adhd.simulator.Event;
import adhd.simulator.Population;
import adhd.simulator.random.RandomFunction;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Oscar Lao
 */
public class Model {
    
    private final Population non_African = new Population("non_African");
    private final Population ustIshim = new Population("ustIshim", 45020);    
    private final Population X = new Population("X");
    private final Population Malta1 = new Population("Malta1", 24305);
    private final Population western_Eurasian = new Population("Western_Eurasian");
    private final Population nc1e0 = new Population("nc1e0");
    private final Population kostenski14 = new Population("Kostenski14", 37470);
    private final Population ncle2 = new Population("ncle2");
    private final Population ncle4 = new Population("ncle4");
    private final Population vestonice16 = new Population("Vestonice16", 30010);
    private final Population ndle0 = new Population("ndle0");
    private final Population n1e0 = new Population("nle0");
    private final Population ndle2 = new Population("ndle2");
    private final Population nclc1 = new Population("nclc1");
    private final Population GoyetQ116_1 = new Population("GoyetQ11-1", 34795);
    private final Population nf1f1 = new Population("nf1f1");
    private final Population Villabruna = new Population("Villabruna", 13980);
    private final Population n1e1 = new Population("n1e1");
    private final Population n1e3 = new Population("n1e3");
    private final Population n1e2 = new Population("n1e2");
    private final Population nd1d1 = new Population("nd1d1");
    private final Population ncle3 = new Population("ncle3");
    private final Population ndle3 = new Population("ndle3");
    private final Population ndle4 = new Population("ndle4");
    private final Population nle4 = new Population("nle4");
    private final Population Loschbour = new Population("Loschbour", 8050);
    private final Population ElMiron = new Population("ElMiron", 18720);
    
    // R is the response to selection.
    private double r;

    /**
     * Get the response to selection
     * @return 
     */
    public double getR() {
        return r;
    }
        
    
    /**
     * Run a simulation
     * @param ra the random function
     * @throws Exception 
     */
    public void run(RandomFunction ra) throws Exception
    {
        double gdrift = 0.0000005;
// Initialize s, r and the frequency in non_Africa
        r = ra.sample();
        ArrayList<Event> events = new ArrayList<>();
        events.add(new EdgeBetweenTwoPopulations(non_African, ustIshim));
        events.add(new EdgeBetweenTwoPopulations(non_African, X));
        events.add(new EdgeBetweenTwoPopulations(X, Malta1));
        events.add(new EdgeBetweenTwoPopulations(X, western_Eurasian));
        events.add(new EdgeBetweenTwoPopulations(western_Eurasian, nc1e0));
        events.add(new EdgeBetweenTwoPopulations(nc1e0, kostenski14));
        events.add(new EdgeBetweenTwoPopulations(nc1e0, ncle2));
        events.add(new EdgeBetweenTwoPopulations(western_Eurasian, nclc1));
        events.add(new EdgeBetweenTwoPopulations(nclc1, ndle0));
        events.add(new EdgeBetweenTwoPopulations(ndle0, GoyetQ116_1));
        events.add(new EdgeBetweenTwoPopulations(ndle0, n1e0));
        events.add(new EdgeBetweenTwoPopulations(n1e0, ndle2));        
        events.add(new EdgeBetweenTwoPopulations(nclc1, nf1f1));
        events.add(new EdgeBetweenTwoPopulations(nf1f1,Villabruna));
        events.add(new EdgeBetweenTwoPopulations(nf1f1,n1e1));
        events.add(new EdgeBetweenTwoPopulations(n1e1,n1e3));
        events.add(new EdgeBetweenTwoPopulations(n1e1,nd1d1));
        events.add(new EdgeBetweenTwoPopulations(n1e0,n1e2));
        events.add(new EdgeBetweenTwoPopulations(nd1d1,ndle3));
        events.add(new EdgeBetweenTwoPopulations(nd1d1,ncle3));
        
        Random rand = new Random();
        double fInNon_Africa = rand.nextGaussian()*0.001 -0.00302278857760104; // value UstIshm
        double time_non_Africa = 45021 + rand.nextInt(80000) + 1000;
        non_African.setValue(fInNon_Africa);
        non_African.setTime(time_non_Africa);
        X.setTime(time_non_Africa-rand.nextInt(45020-37471));
        western_Eurasian.setTime(X.getTime()-rand.nextInt(((int)X.getTime())-37471));
        nc1e0.setTime(western_Eurasian.getTime()-rand.nextInt(((int)western_Eurasian.getTime())-37471));
        ncle2.setTime(nc1e0.getTime()-rand.nextInt(((int)nc1e0.getTime())-30011));
        nclc1.setTime(western_Eurasian.getTime()-rand.nextInt(((int)western_Eurasian.getTime())-34796));
        ndle0.setTime(nclc1.getTime()-rand.nextInt(((int)nclc1.getTime())-34796));
        n1e0.setTime(ndle0.getTime()-rand.nextInt(((int)ndle0.getTime())-18721));
        ndle2.setTime(n1e0.getTime()-rand.nextInt(((int)n1e0.getTime())-18721));
        nf1f1.setTime(nclc1.getTime()-rand.nextInt(((int)nclc1.getTime())-30011));
        n1e1.setTime(nf1f1.getTime()-rand.nextInt(((int)nf1f1.getTime())-30011));
        n1e3.setTime(n1e1.getTime()-rand.nextInt(((int)n1e1.getTime())-8051));
        n1e2.setTime(n1e0.getTime()-rand.nextInt(((int)n1e0.getTime())-8051));
        nd1d1.setTime(n1e1.getTime()-rand.nextInt(((int)n1e1.getTime())-30011));
        ndle3.setTime(nd1d1.getTime()-rand.nextInt(((int)nd1d1.getTime())-18721));
        ncle3.setTime(nd1d1.getTime()-rand.nextInt(((int)nd1d1.getTime())-30011));
        Random drift = new Random();
        for(Event e:events)
        {
            ((EdgeBetweenTwoPopulations)e).setDrift(drift.nextDouble()*gdrift);
            ((EdgeBetweenTwoPopulations)e).setR(r);                        
            e.update();
        }        
        
        AdmixPopulation n1e2_n1e3_nle4 = new AdmixPopulation(n1e2, n1e3, nle4,0.15);
        double youngestTime = Math.min(n1e2.getTime(), n1e3.getTime());
        n1e2_n1e3_nle4.update();
        nle4.setTime(youngestTime-rand.nextInt(((int)youngestTime)-8050));
        events.add(n1e2_n1e3_nle4);        

        AdmixPopulation ncle2_ncle3_ncle4 = new AdmixPopulation(ncle2, ncle3, ncle4,0.9);
        youngestTime = Math.min(ncle2.getTime(), ncle3.getTime());
        ncle4.setTime(youngestTime-rand.nextInt(((int)youngestTime)-30011));
        ncle2_ncle3_ncle4.update();
        events.add(ncle2_ncle3_ncle4);

        AdmixPopulation ndle2_ndle3_ndle4 = new AdmixPopulation(ndle2, ndle3, ndle4,0.63);
        youngestTime = Math.min(ndle2.getTime(), ndle3.getTime());
        ndle2_ndle3_ndle4.update();
        ndle4.setTime(youngestTime-rand.nextInt(((int)youngestTime)-18721));
        events.add(ndle2_ndle3_ndle4);
        
        EdgeBetweenTwoPopulations nle4_Loschbour= new EdgeBetweenTwoPopulations(nle4,Loschbour);
        nle4_Loschbour.setDrift(drift.nextDouble()*gdrift);
        nle4_Loschbour.setR(r);
        nle4_Loschbour.update();
        
        EdgeBetweenTwoPopulations ncle4_vestonice16 = new EdgeBetweenTwoPopulations(ncle4, vestonice16);
        ncle4_vestonice16.setDrift(drift.nextDouble()*gdrift);
        ncle4_vestonice16.setR(r);
        ncle4_vestonice16.update();
        

        EdgeBetweenTwoPopulations ndle4_ElMiron = new EdgeBetweenTwoPopulations(ndle4, ElMiron);
        ndle4_ElMiron.setDrift(drift.nextDouble()*gdrift);
        ndle4_ElMiron.setR(r);
        ndle4_ElMiron.update();
    }

    public Population getElMiron() {
        return ElMiron;
    }

    public Population getGoyetQ116_1() {
        return GoyetQ116_1;
    }

    public Population getKostenski14() {
        return kostenski14;
    }

    public Population getLoschbour() {
        return Loschbour;
    }

    public Population getMalta1() {
        return Malta1;
    }

    public Population getUstIshim() {
        return ustIshim;
    }

    public Population getVestonice16() {
        return vestonice16;
    }

    public Population getVillabruna() {
        return Villabruna;
    }
}
