/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

import java.lang.Math;
import java.util.ArrayList;

/**
 *
 * @author loandy
 */
public abstract class Ant extends Entity implements Antennae, Legs, Mandibles,
    Stinger
{
    private static long total;
    protected int facing;
    protected Entity heldItem = null;
    protected PathIntegrator path = null;

    public Ant() {
        total += 1;
    }

    public static long getTotal()
    {
        return total;
    }

    public int[] getPosition()
    {
        return this.position;
    }

    public int getFacing()
    {
        return this.facing;
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

    public void setFacing(int direction)
    {
        if(direction >= 0 && direction <= 7)
        {
            this.facing = direction;

           // BEGIN DEBUG CODE
            System.out.print("Ant " + this + " is now facing ");

            switch(direction)
            {
                case 0:
                    System.out.print("east.\n");
                    break;
                case 1:
                    System.out.print("northeast.\n");
                    break;
                case 2:
                    System.out.print("north.\n");
                    break;
                case 3:
                    System.out.print("northwest.\n");
                    break;
                case 4:
                    System.out.print("west.\n");
                    break;
                case 5:
                    System.out.print("southwest.\n");
                    break;
                case 6:
                    System.out.print("south.\n");
                    break;
                case 7:
                    System.out.print("southeast.\n");
                    break;
            }
            // END DEBUG CODE
        }
        else
        {
            System.out.println("Invalid movement direction input for " + this
                + ": " + direction + ".");
            return;
        }
    }

    public ArrayList<Entity> sense(Map map)
    {
        // This flag signals that something was sensed.
        boolean foundFlag = false;

        // BEGIN DEBUG CODE
        System.out.println("Ant " + this + " is sensing.\n");
        // END DEBUG CODE

        // This array list lists all the entities that were sensed.
        ArrayList<Entity> foundList = new ArrayList<Entity>();

        int r = 5; // Sensing range;
        int initX = this.position[0];
        int initY = this.position[1];

        // BEGIN DEBUG CODE
        System.out.println("Sensing from location (" + initX + ", " + initY +
            ").\n");
        // END DEBUG CODE
    
        for(int y = initY + r; y >= initY - r; y--)
        {
            for(int x = initX - r; x <= initX + r; x++)
            {
                int dx = Math.abs(x - initX);
                int dy = Math.abs(y - initY);

                if((dx + dy) <= r)
                {
                    // BEGIN DEBUG CODE
                    // Prints out an ASCII sensory map as a graphical
                    // representation of the sensory results.
                    if(x == initX && y == initY)
                    {
                        System.out.print(" O ");
                    }
                    // END DEBUG CODE
                    else
                    {
                        if(map.getCellContent(x, y) != null)
                        {
                            foundFlag = true;
                            foundList.add(map.getCellContent(x, y));

                            // BEGIN DEBUG CODE
                            if(map.getCellContent(x, y) instanceof Ant)
                            {
                                System.out.print(" A ");
                            }
                            else if(map.getCellContent(x, y) instanceof Food)
                            {
                                System.out.print(" F ");
                            }
                            // END DEBUG CODE
                        }
                        // BEGIN DEBUG CODE
                        else
                        {
                            System.out.print(" + ");
                        }
                        // END DEBUG CODE
                    }
                }
                else
                {
                    // BEGIN DEBUG CODE
                    System.out.print("   ");
                    // END DEBUG CODE
                }
            }
            // BEGIN DEBUG CODE
            System.out.print("\n");
            // END DEBUG CODE
        }

        //BEGIN DEBUG CODE
        System.out.print("\n");

        if(foundFlag)
        {
            for(Entity e : foundList)
            {
                System.out.println("Ant " + this + " senses " + e + " at ("
                    + e.getPosition()[0] + ", " + e.getPosition()[1] + ").");
            }
        }
        else
        {
            System.out.println("Ant " + this + " found nothing.");
        }
        // END DEBUG CODE

        return foundList;
    }

    // This function interpolates movement on a 2D grid.
    public void move(Map map, int direction)
    {
        this.setFacing(direction);

        int[] directionCoordinate = new int[2];
        directionCoordinate = this.calculateDirection(direction);
        
        // BEGIN DEBUG CODE
        System.out.println("Ant " + this + " moving in toward <" +
            directionCoordinate[0] + ", " + directionCoordinate[1] + ">.");
        // END DEBUG CODE

        // Check new position for preexisting entities.
        if(map.getCellContent(directionCoordinate[0], directionCoordinate[1]) !=
            null)
        {
            System.out.println("Path blocked by " + map.getCellContent(
                directionCoordinate[0], directionCoordinate[1]) + ".\n");
        }
        else
        {
            map.disassociateCell(this);
            this.position[0] = directionCoordinate[0];
            this.position[1] = directionCoordinate[1];
            this.path.updatePath(this.position);
            map.associateCell(this);
            System.out.println(this + " successfully moved to (" +
                this.position[0] + ", " + this.position[1] + ").\n");
        }
    }
    
    public void grab(Map map, Entity item)
    {
        int[] directionCoordinate = new int[2];
        directionCoordinate = this.calculateDirection(this.facing);

        // If the ant is currently holding an item, drop it and pick up the new
        // item.
        if(this.heldItem != null)
        {
            this.drop(map);
        }

        if(map.getCellContent(directionCoordinate[0], directionCoordinate[1]) !=
            null)
        {
            this.heldItem = item;
            map.disassociateCell(item);

            // BEGIN DEBUG CODE
            System.out.println("Ant has grabbed " + item + ".");
            // END DEBUG CODE
        }
    }

    public void drop(Map map)
    {
        if(this.heldItem != null)
        {
            while(this.heldItem != null)
            {
                int[] directionCoordinate = new int[2];

                directionCoordinate = this.calculateDirection(this.facing);

                // Check new position for preexisting entities.
                if(map.getCellContent(directionCoordinate[0], directionCoordinate[1])
                    != null)
                {
                    System.out.println("Cannot drop item.\n");
                    this.setFacing(this.getFacing() + 1);
                }
                else
                {
                    this.heldItem.position[0] = directionCoordinate[0];
                    this.heldItem.position[1] = directionCoordinate[1];
                    map.associateCell(this.heldItem);
                    System.out.println(this.heldItem + " successfully dropped at (" +
                        this.heldItem.position[0] + ", " + this.heldItem.position[1] +
                        ").\n");
                    this.heldItem = null;
                }
            }
        }
    }

    public void consume()
    {
        if(this.heldItem != null && this.heldItem instanceof Food)
        {
            // BEGIN DEBUG CODE
            System.out.println("Ant " + this + " has consumed food and gained " +
                this.heldItem.getEnergy() + " energy.");
            // END DEBUG CODE

            this.energy += this.heldItem.getEnergy();
            this.heldItem = null;         
        }
        else
        {
            // BEGIN DEBUG CODE
            System.out.println("Ant " + this + " cannot consume " +
                this.heldItem + ".");
            // END DEBUG CODE
        }
    }

    public void sting()
    {

    }

    // Calculates the next grid cell in a particular direction and returns a
    // movement vector as an ordered pair.
    protected int[] calculateDirection(int direction)
    {
        if(!(direction >= 0 && direction <= 7))
        {
            System.out.println("Cannot calculate direction for " + direction + ".");
            return null;
        }

        int[] directionCoordinate = new int[2];

        double angle = (Math.PI / 4.0) * direction;
        double sine = 0.0;
        double cosine = 0.0;

        // Corrections were needed for the trigonometric function values at
        // specific angles (i.e. quarter angles).
        if(angle == Math.PI / 2.0 || angle == Math.PI * 3.0 / 4.0)
        {
            sine = 1.0;
            cosine = 0.0;
        }
        else if(angle == 0.0 || angle == Math.PI)
        {
            sine = 0.0;
            cosine = 1.0;
        }
        else
        {
            sine = Math.sin(angle);
            cosine = Math.cos(angle);
        }

        // Calculate new position.
        if(cosine > 0.0 && cosine < 1.0)
        {
            directionCoordinate[0] = (int)(this.position[0] + cosine *
                (1.0/cosine));
        }
        else
        {
            directionCoordinate[0] = (int)(this.position[0] + cosine);
        }

        if(sine > 0.0 && sine < 1.0)
        {
            directionCoordinate[1] = (int)(this.position[1] + sine *
                (1.0/sine));
        }
        else
        {
            directionCoordinate[1] = (int)(this.position[1] + sine);
        }

        return directionCoordinate;
    }

    // Calculates the next grid cell in a particular direction and returns a
    // movement vector as an ordered pair.
    protected int[] calculateDirection(int[] directionVector)
    {
        int[] directionCoordinate = new int[2];

        directionCoordinate[0] += directionVector[0];
        directionCoordinate[1] += directionVector[1];
        
        return directionCoordinate;
    }
}
