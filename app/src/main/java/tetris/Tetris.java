/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.text.DateFormat;
import java.util.Date;
import tetris.tetra.Tetras;
import tetris.tetra.Tetra;
import tetris.gui.TetrisGui;
import java.util.logging.Level;
import java.util.logging.Logger;
import tetris.gui.ITetris;

public class Tetris implements ITetris {
    
    private final int DELAY_CHANGE_PER_LEVEL = 5;

    private final int START_DELAY = 500;
    
    int delay;

    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        tetris.start();
    }

    private final GameArray array = new GameArray();
    
    private final Tetras tetras = new Tetras();
    
    private final Highscores highscores = new Highscores();
    
    private final Rnd random = Rnd.instance();
    

    private ITetrisGui gui;
    
    private boolean pause;
    
    private int score;
    
    private int rows;
    
    private int nextRandom;
    
    private Tetra current;
    

    public void start() {
        
        highscores.load();
        
        do {
            array.clear();
            score = 0;
            delay = START_DELAY;
    
            gui = new TetrisGui(this, array);

            run();

            gui.close();
            
            if (highscores.isTopScore(score)) {
                System.out.println("new top score");
            }
            highscores.addScore("Roman", score, rows, new Date(), START_DELAY, delay, DELAY_CHANGE_PER_LEVEL);
            
            highscores.save();
            
        } while (gui.showGameOverMessage(score));
        
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
        
        for (Highscore hs : highscores.getTopScores()) {
            System.out.println(hs.score + " " + hs.name 
                    + " " + hs.rows + " " + df.format(hs.date)
                    + " " + hs.startDelay + " " + hs.finalDelay
                    + " " + hs.level);
        }
    }

    public void run() {

        while (true) {

            if (!canNewTetraMove()) {
                break;
            }

            boolean canMove;

            do {
                delay(delay);

                canMove = checkInsert(Movement.Down);
            } while (canMove);

            int collapsedRows = array.collapseRows();

            score += getSummedRows(collapsedRows) * 100;
            rows += collapsedRows;

            gui.showScore(score);

            if (delay > 50 && collapsedRows > 0) {
                delay -= DELAY_CHANGE_PER_LEVEL;
            }
        }
    }
    
    @Override
    public boolean checkInsert(Movement movement) {

        Tetra next;

        switch (movement) {
            case Rotate:
                next = current.rotateClockwise();
                break;
            case Left:
                next = current.left();
                break;
            case Right:
                next = current.right();
                break;
            case Down:
            default:
                next = current.down();
        }
      
        boolean canMove = array.canMove(current, next);

        checkPause();
                
        if (canMove) {
            array.remove(current);
            current = next;
            array.insert(current);
            gui.updateView();
        }
        
        return canMove;
    }
    
    @Override
    public void pause() {
        pause = !pause;
    }

    private boolean canNewTetraMove() {
        
        current = tetras.getTetra(nextRandom).copy();
        current.setPosition(4, 0);

        nextRandom = random.nextInt(7);
        Tetra nextTetra = tetras.getTetra(nextRandom);
        gui.setNextTetra(nextTetra);
        
        return array.canMove(current.getAbsolutePositions());
    }

    private int getSummedRows(int rows) {
        int sum = 0;
        for (int i = 1; i <= rows; i++) {
            sum += i;
        }
        return sum;
    }

    private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(TetrisGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkPause() {
        while (pause) {
            delay(10);
        }
    }

}
