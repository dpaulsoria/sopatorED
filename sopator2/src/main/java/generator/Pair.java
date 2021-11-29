/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

/**
 *
 * @author danny
 */
public class Pair {
    private final int X;
    private final int Y;
    
    public Pair(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}
