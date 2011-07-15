/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public abstract class Entity
{
    protected int energy;
    protected int[] position = new int[2];

    public Entity()
    {
        // BEGIN DEBUG CODE
        System.out.println("Entity " + this + " created.");
        // END DEBUG CODE
    }

    public abstract int[] getPosition();
    public abstract int getEnergy();
    public abstract void setPosition(int x, int y);
}
