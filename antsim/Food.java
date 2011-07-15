/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class Food extends Entity {
    public Food(Map map, int initX, int initY, int initEnergy)
    {
        this.position[0] = initX;
        this.position[1] = initY;
        this.energy = initEnergy;

        map.associateCell(this);
    }

    public int[] getPosition()
    {
        return position;
    }

    public int getEnergy()
    {
        return this.energy;
    }

    public void setPosition(int x, int y)
    {
        this.position[0] = x;
        this.position[1] = y;
    }
}
