/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antsim;

/**
 *
 * @author Andy Lo
 */
public abstract class Ant extends Entity {
    protected int facing;

    /**
     * Calculates the new position coordinate for movement in the indicated
     * direction.
     *
     * @param int direction
     */
    private Position calculateNewPosition(int direction) {
        if(!(direction >= 0 && direction <= 7)) {
            return null;
        }

        Position newPosition = new Position();

        double angle  = (Math.PI / 4.0) * direction;
        double sine   = 0.0;
        double cosine = 0.0;

        // Corrections were needed for the trigonometric function values at
        // specific angles (i.e. quarter angles).
        if(angle == Math.PI / 2.0 || angle == Math.PI * 3.0 / 4.0) {
            sine = 1.0;
            cosine = 0.0;
        } else if(angle == 0.0 || angle == Math.PI) {
            sine = 0.0;
            cosine = 1.0;
        } else {
            sine = Math.sin(angle);
            cosine = Math.cos(angle);
        }

        // Calculate new position.
        if(cosine > 0.0 && cosine < 1.0) {
            newPosition.x = (int)(this.position.x + cosine * (1.0/cosine));
        } else {
            newPosition.x = (int)(this.position.x + cosine);
        }

        if(sine > 0.0 && sine < 1.0) {
            newPosition.y = (int)(this.position.y + sine * (1.0/sine));
        } else {
            newPosition.y = (int)(this.position.y + sine);
        }

        return newPosition;
    }

    /**
     *
     */
    private void setFacing(int direction)
    {
        if(direction >= 0 && direction <= 7) {
            this.facing = direction;

           // BEGIN DEBUG CODE
            System.out.print(this + " is now facing ");

            switch(direction)
            {
                case 0:
                    System.out.print("east.");
                    break;
                case 1:
                    System.out.print("northeast.");
                    break;
                case 2:
                    System.out.print("north.");
                    break;
                case 3:
                    System.out.print("northwest.");
                    break;
                case 4:
                    System.out.print("west.");
                    break;
                case 5:
                    System.out.print("southwest.");
                    break;
                case 6:
                    System.out.print("south.");
                    break;
                case 7:
                    System.out.print("southeast.");
                    break;
            }
            // END DEBUG CODE
        }
    }

    /**
     * This function interpolates movement on a 2D grid.
     *
     * @param int direction
     */
    public void move(int direction)
    {
        this.setFacing(direction);

        Position newPosition = new Position();
        newPosition = this.calculateNewPosition(direction);

        // BEGIN DEBUG CODE
        System.out.println(this + " moving toward (" + newPosition.x +
            ", " + newPosition.y + ").");
        // END DEBUG CODE

        Entity content = this.world.getCell(newPosition);
        // Collision detection.
        if(content != null) {
            System.out.println("Path blocked by " + content + ".");
        } else if(newPosition.x < 0 || newPosition.y < 0) {
            System.out.println("Path blocked by boundary at (" + newPosition.x +
                ", " + newPosition.y + ").");
        } else {
            this.world.resetCell(this.position);

            this.position.x = newPosition.x;
            this.position.y = newPosition.y;

            if(this.world.setCell(this.position, this)) {
                System.out.println(this + " successfully moved to (" +
                this.position.x + ", " + this.position.y + ").");
            }
        }
    }
}
