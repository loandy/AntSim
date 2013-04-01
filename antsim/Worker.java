/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antsim;

/**
 *
 * @author Andy Lo
 */
public class Worker extends Ant {
    public Worker(World w, int x, int y) {
        this.world = w;
        this.position = new Position();
        this.setPosition(x, y);

        System.out.println(this + " created at (" + this.position.x
            + ", " + this.position.y + ") in world " + this.world + ".");
    }
}
