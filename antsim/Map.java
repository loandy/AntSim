/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class Map
{
    private int[] dimensions = new int[2];
    private Entity[][] grid;

    public Map(int xSize, int ySize)
    {
        setDimensions(xSize, ySize);
        grid = new Entity[xSize][ySize];
    }

    public int getWidth()
    {
        return this.dimensions[0];
    }

    public int getHeight()
    {
        return this.dimensions[1];
    }

    public int[] getDimensions()
    {
        return this.dimensions;
    }

    public Entity getCellContent(int x, int y)
    {
        return this.grid[x][y];
    }

    public void setDimensions(int x, int y)
    {
        this.dimensions[0] = x;
        this.dimensions[1] = y;
    }

    public boolean associateCell(Entity e)
    {
        if(grid[e.getPosition()[0]][e.getPosition()[1]] != null)
        {
            // BEGIN DEBUG CODE
            System.out.println("Cell is already occupied by object " +
                grid[e.getPosition()[0]][e.getPosition()[1]] + ".");
            System.out.println("Cannot associate object " + e + " with this cell.\n");
            // END DEBUG CODE
            return false;
        }
        else
        {
            // BEGIN DEBUG CODE
            grid[e.getPosition()[0]][e.getPosition()[1]] = e;
            System.out.println(e + " placed at (" + e.getPosition()[0] + ", " +
                e.getPosition()[1] + ").\n");
            // END DEBUG CODE
            return true;
        }
    }

    public void disassociateCell(Entity e)
    {
        grid[e.getPosition()[0]][e.getPosition()[1]] = null;

        // BEGIN DEBUG CODE
        System.out.println(e + " removed from (" + e.getPosition()[0] + ", " +
            e.getPosition()[1] + ").\n");
        // END DEBUG CODE
    }
}

class Cell {
    private int[] coordinate = new int[2];
    private Entity item;

    public int[] getCoordinate()
    {
        return this.coordinate;
    }

    public Entity getItem()
    {
        return this.item;
    }

    public void setCoordinate(int x, int y)
    {
        this.coordinate[0] = x;
        this.coordinate[1] = y;
    }

    public void setItem(Entity e)
    {
        this.item = e;
    }
}