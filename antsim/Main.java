/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antsim;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Andy Lo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("START OF PROGRAM");
        System.out.println("------------------------------\n");

        World world = new World(30, 30);
        ArrayList<Worker> antList = new ArrayList<>();
        Random randomGenerator = new Random();

        int random = randomGenerator.nextInt(3) + 1;
        System.out.println("Creating " + random + " worker(s).\n");
        for(int i = 0; i < random; i++) {
            antList.add(new Worker(world, randomGenerator.nextInt(30),
                randomGenerator.nextInt(30)));
        }

        for(Ant ant : antList) {
            random = randomGenerator.nextInt(8);
            ant.move(random);
        }

        System.out.println("\n------------------------------");
        System.out.println("END OF PROGRAM");
    }
}
