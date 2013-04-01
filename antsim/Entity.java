/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antsim;

/**
 *
 * @author Andy Lo
 */
public abstract class Entity {
    protected int energy;
    protected World world;
    protected Position position;

    public int getEnergy() {
        return this.energy;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setEnergy(int e) {
        this.energy = e;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
        this.world.setCell(this.position, this);
    }
}
