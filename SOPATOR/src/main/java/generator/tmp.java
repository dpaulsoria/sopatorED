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
public class tmp {
    private int X;
    private int Y;
    private String RANDOM_WORD;
    private Pair RANDOM_DIRECTION;
    
    public tmp() {

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public String getRANDOM_WORD() {
        return RANDOM_WORD;
    }

    public Pair getRANDOM_DIRECTION() {
        return RANDOM_DIRECTION;
    }
    
    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public void setRANDOM_WORD(String rw) {
        this.RANDOM_WORD = rw;
    }

    public void setRANDOM_DIRECTION(Pair rd) {
        this.RANDOM_DIRECTION = rd;
    }

    @Override
    public String toString() {
        return getRANDOM_WORD() + ", " + getRANDOM_DIRECTION() + ", " + getX() + "x" + getY();
    }
}
