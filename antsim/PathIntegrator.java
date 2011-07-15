/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public class PathIntegrator
{
    private int[] lastCoordinate = new int[2];
    private int[] pathVector = new int[2];

    public PathIntegrator(int[] coordinate)
    {
        for(int i = 0; i < 2; i++)
        {
            this.lastCoordinate[i] = coordinate[i];
            this.pathVector[i] = 0;
        }
    }

    public int[] getPathVector()
    {
        return this.pathVector;
    }

    public void updatePath(int[] newCoordinate)
    {
        // BEGIN DEBUG CODE
        System.out.println("Updating path integration.");
        System.out.println("Previous Coordinate: (" + this.lastCoordinate[0]
            + ", " + this.lastCoordinate[1] + ")");
        System.out.println("Next Coordinate: (" + newCoordinate[0] + ", " +
            newCoordinate[1] + ")");
        System.out.println("Vector Result: <" + (newCoordinate[0] - this.lastCoordinate[0])
                + ", " + (newCoordinate[1] - this.lastCoordinate[1]) + ">\n");
        // END DEBUG CODE

        for(int i = 0; i < 2; i++)
        {
            pathVector[i] += (newCoordinate[i] - this.lastCoordinate[i]);
            this.lastCoordinate[i] = newCoordinate[i];
        }

        // BEGIN DEBUG CODE
        System.out.println("Path integration updated.");
        System.out.println("Updated Coordinate: (" + this.lastCoordinate[0]
            + ", " + this.lastCoordinate[1] + ")");
        System.out.println("Integrated Vector: <" + this.pathVector[0] +
            ", " + this.pathVector[1] + ">\n");
        // END DEBUG CODE
    }
}
