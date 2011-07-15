/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antsim;

/**
 *
 * @author loandy
 */
public interface Mandibles
{
    public void grab(Map map, Entity item);
    public void drop(Map map);
    public void consume();
}
