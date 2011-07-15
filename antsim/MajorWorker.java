/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class MajorWorker extends Worker implements MajorWorkerBehavior
{
    private static long count;

    public MajorWorker(Map map, int initX, int initY, int initEnergy)
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

    public void majorWorkerBehavior(Map map)
    {}
}
