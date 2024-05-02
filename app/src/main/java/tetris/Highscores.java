/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roman
 */
public class Highscores {
    
    private static final int MAX_SCORES = 10;
    
    private static final String highscoreFile = System.getProperty("user.home") + "/tetris.hs";
    
    
    private final List<Highscore> highscores = new LinkedList<>();
    
    public void addScore(String name, int score, int rows, Date date, 
            int startDelay, int finalDelay, int level) {
        int pos = 0;
        Iterator<Highscore> it =  highscores.iterator();
        while (it.hasNext() && it.next().score >= score) {
            pos++;
        }
        
        if (pos < MAX_SCORES) {
            highscores.add(pos, new Highscore(name, score, rows, date, 
                    startDelay, finalDelay, level));
        }
        
        while (highscores.size() > MAX_SCORES) {
            highscores.remove(MAX_SCORES);
        }
    }
    
    public List<Highscore> getTopScores() {
        return highscores;
    }
    
    public boolean isTopScore(int score) {
        return highscores.size() < MAX_SCORES || highscores.get(MAX_SCORES - 1).score < score;
    }
    
    public void load() {
        File f = new File(highscoreFile);
        
        if (!f.exists()) {
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(highscoreFile))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] vals = line.split(",");
                
                String name = vals[0];
                int score = Integer.parseUnsignedInt(vals[1]);
                long date = Long.parseUnsignedLong(vals[2]);
                int rows = 0;
                if (vals.length > 3) {
                    rows = Integer.parseUnsignedInt(vals[3]);
                }
                int startDelay = 500;
                int finalDelay = 0;
                int level = 5;
                if (vals.length > 6) {
                    startDelay = Integer.parseUnsignedInt(vals[4]);
                    finalDelay = Integer.parseUnsignedInt(vals[5]);
                    level = Integer.parseUnsignedInt(vals[6]);
                }
                
                addScore(name, score, rows, new Date(date),
                        startDelay, finalDelay, level);
            }    
        } catch (IOException ex) {
            Logger.getLogger(Highscores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(highscoreFile))) {
            for (Highscore hs : getTopScores()) {
                bw.append(hs.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Highscores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
