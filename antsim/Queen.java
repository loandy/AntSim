/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class Queen extends Ant implements QueenBehavior
{
    private static long count;

    public Queen(Map map, int initX, int initY, int initEnergy)
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

    public Egg layEgg(Map map)
    {
        int[] eggCoordinate = new int[2];

        Egg newEgg = new Egg(map, eggCoordinate[0], eggCoordinate[1]);
        return newEgg;
    }

    public void queenBehavior(Map map)
    {
        
    }
}
