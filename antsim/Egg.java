/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class Egg extends Entity
{
    private int age;
    private int energy;

    public Egg (Map map, int initX, int initY)
    {
        this.position[0] = initX;
        this.position[1] = initY;
        this.age = 0;
        this.energy = 150;
        map.associateCell(this);
    }

    public int[] getPosition()
    {
        return this.position;
    }

    public int getEnergy()
    {
        return this.energy;
    }

    public int getAge()
    {
        return this.age;
    }

    public void addEnergy(int e)
    {
        this.energy += e;
    }

    public void addAge(int a)
    {
        this.age += a;
    }

    public void setPosition(int x, int y)
    {
        this.position[0] = x;
        this.position[0] = y;
    }
}
