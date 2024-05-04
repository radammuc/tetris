/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris.tetra;

public class Position {
    
    public int x;
    public int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position other) {
            return x == other.x && y == other.y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return x * 3 + y;
    }
    
    

    
}
