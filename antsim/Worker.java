/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

import java.util.ArrayList;

/**
 *
 * @author loandy
 */
public abstract class Worker extends Ant implements WorkerBehavior
{
    public void workerBehavior(Map map) {
        // Behavioral event flags.
        boolean foundFood = true;
        boolean attacked = true;

        ArrayList<Entity> senseResult = this.sense(map);

        System.out.print("\n");
        for(Entity e : senseResult)
        {
            if (e instanceof Food)
            {
                // BEGIN DEBUG CODE
                System.out.println("Ant " + this + " has found food.");
                // END DEBUG CODE
                this.grab(map, e);
                this.sense(map);
                this.returnToColony(map);
            }
        }
        System.out.print("\n");
    }

    public void returnToColony(Map map)
    {
        boolean atColony = false;
        // BEGIN DEBUG CODE
        System.out.println("Ant " + this + " is returning to colony.");
        // END DEBUG CODE
    }
}
