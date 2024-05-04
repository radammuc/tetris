/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.List;
import tetris.tetra.Position;
import tetris.tetra.Tetra;

public class GameArray {
    
    private final int[][] array = new int[22][10];
    
    public void clear() {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = 0;
            }
        }
    }
    
    public void insert(Tetra tetra) {
        for (Position pos : tetra.getAbsolutePositions()) {
            array[pos.y][pos.x] = tetra.getColor();
        }
    }

    public void remove(Tetra tetra) {
        for (Position pos : tetra.getAbsolutePositions()) {
            array[pos.y][pos.x] = 0;
        }
    }

    public boolean canMove(Tetra tetra, Tetra next) {

        remove(tetra);

        boolean canMove = canMove(next.getAbsolutePositions());

        insert(tetra);

        return canMove;
    }

    public boolean canMove(List<Position> positions) {

        boolean canMove = true;
        for (Position pos : positions) {
            int nx = pos.x;
            int ny = pos.y;

            if (nx < 0 || nx > 9) {
                canMove = false;
            } else if (ny < 0 || ny > 21) {
                canMove = false;
            } else if (array[ny][nx] != 0) {
                canMove = false;
            }
        }

        return canMove;
    }

    public int collapseRows() {
        
        int collapsedRows = 0;
        
        // collapsing von "oben"
        for (int i = 2; i < 22; i++) {
            boolean c = checkCollapsable(i);
            if (c) {
                collapseRow(i);
                //update();
                ++collapsedRows;
            }
        }
        
        return collapsedRows;
    }
    
    private void collapseRow(int row) {

        for (int i = row; i > 2; i--) {
            System.arraycopy(array[i - 1], 0, array[i], 0, 10);
        }
        
        for (int i = 1; i < 9; i++) {
            array[2][i] = 0;
        }
    }
    
    private boolean checkCollapsable(int row) {
        int[] theRow = array[row];
        
        for (int i = 0; i < 10; i++) {
            if (theRow[i] == 0) {
                return false;
            }
        }
        return true;
    }


    public int getCellColor(int x, int y) {
        return array[x][y];
    }
    
}
