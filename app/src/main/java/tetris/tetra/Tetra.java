/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris.tetra;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roman
 */
public class Tetra {
    
    private final List<Position> positions;
    
    private final int color;
    
    private int x;
    private int y;
    
    public Tetra(int color) {
        this.color = color;
        
        positions = new ArrayList<>();
    }
    
    public void add(int x, int y) {
        positions.add(new Position(x, y));
    }
    
    public List<Position> getAbsolutePositions() {
        List<Position> absPos = new ArrayList<>(positions.size());
        
        for (Position p : positions) {
            absPos.add(new Position(p.x + this.x, p.y + this.y));
        }
        return absPos;
    }
    
    public Tetra rotateClockwise() {
        Tetra rotated = copy();
        
        if (getPosSum() == 0) {
            return rotated;
        }
        
        for (Position p : rotated.positions) {
            int temp = -p.y;
            p.y = p.x;
            p.x = temp;
        }
        return rotated;
    }
    
    public Tetra down() {
        Tetra down = copy();
        down.y += 1;
        
        return down;
    }
    
    public Tetra left() {
        Tetra left = copy();
        left.x -=1;
        
        return left;
    }
    
    public Tetra right() {
        Tetra right = copy();
        right.x += 1;
        
        return right;
    }
    
    public Tetra copy() {
        Tetra t = new Tetra(color);
        t.x = this.x;
        t.y = this.y;
        
        for (Position pos : positions) {
            t.add(pos.x, pos.y);
        }
        
        return t;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    private int getPosSum() {
        int sum = 0;
        
        for (Position p : positions) {
            sum += p.y;
            sum -= p.x;
        }
        
        return sum;
    }

    public int getColor() {
        return color;
    }
    
}
