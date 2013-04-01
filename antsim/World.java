/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antsim;

/**
 *
 * @author Andy Lo
 */
public class World {
    private int width;
    private int height;
    private Entity[][] grid;

    public World(int w, int h) {
        this.width = w;
        this.height = h;
        this.grid = new Entity[w][h];
    }

    public Entity getCell(Position p) {
        return this.grid[p.x][p.y];
    }

    public boolean setCell(Position p, Entity e) {
        Position position = e.getPosition();
        if(this.getCell(p) != null) {
            // BEGIN DEBUG CODE
            System.out.println("Position (" + p.x + ", " + p.y +
                ") is already occupied by object " +
                this.getCell(position) + ".");
            System.out.println("Cannot associate object " + e + " with this cell.");
            // END DEBUG CODE

            return false;
        } else {
            this.grid[p.x][p.y] = e;

            // BEGIN DEBUG CODE
            System.out.println(e + " placed at (" + p.x + ", " + p.y + ").");
            // END DEBUG CODE

            return true;
        }
    }

    public void resetCell(Position p) {
        // BEGIN DEBUG CODE
        System.out.println(this.grid[p.x][p.y] + " removed from (" + p.x + ", "
            + p.y + ").");
        // END DEBUG CODE

        this.grid[p.x][p.y] = null;
    }
}