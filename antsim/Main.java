/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class Main
{
    public static void main(String[] args) {
        System.out.println("START OF PROGRAM");
        System.out.println("------------------------------\n");

        Map map1 = new Map(30, 30);
        
        MinorWorker worker1 = new MinorWorker(map1, map1.getWidth() / 2,
            map1.getHeight() / 2, 300);

        Food apple = new Food(map1, map1.getWidth() / 2 + 1, map1.getHeight() / 2, 150);

        listEntities();

        worker1.sense(map1);
        worker1.move(map1, 2);
        worker1.workerBehavior(map1);

        System.out.println("------------------------------");
        System.out.println("END OF PROGRAM");
    }

    private static void listEntities()
    {
        System.out.println("------------------------------");
        System.out.println("Queen Count: " + Queen.getCount());
        System.out.println("Drone Count: " + Drone.getCount());
        System.out.println("Minor Worker Count: " + MinorWorker.getCount());
        System.out.println("Major Worker Count: " + MajorWorker.getCount());
        System.out.println("------------------------------");
        System.out.println("Total Ants: " + Ant.getTotal() + "\n");
    }
}
