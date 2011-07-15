/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class Drone extends Ant implements DroneBehavior
{
    private static long count;

    public Drone(Map map, int initX, int initY, int initEnergy)
    {
        this.setPosition(initX, initY);
        this.setFacing(2);
        this.energy = initEnergy;
        count += 1;

        if(this.path == null)
        {
            this.path = new PathIntegrator(this.position);
        }

        map.associateCell(this);
    }

    public static long getCount()
    {
        return count;
    }

    public void droneBehavior(Map map)
    {

    }
}
