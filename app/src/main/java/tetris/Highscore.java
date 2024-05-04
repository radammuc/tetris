/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.Date;

public class Highscore implements Comparable<Highscore> {

    String name;
    int score;
    int rows;
    Date date;
    
    int startDelay;
    int finalDelay;
    int level;

    Highscore(String name, int score, int rows, Date date, int startDelay,
              int finalDelay, int level) {
        this.name = name;
        this.score = score;
        this.rows = rows;
        this.date = date;
        this.startDelay = startDelay;
        this.finalDelay = finalDelay;
        this.level = level;
    }

    @Override
    public int compareTo(Highscore o) {
        return o.score - score;
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%d,%d,%d,%d,%d", name, score, 
                date.getTime(), rows, startDelay, finalDelay, level);
    }
}
