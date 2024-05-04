/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import javax.swing.JPanel;
import tetris.GameArray;
import tetris.tetra.Position;
import tetris.tetra.Tetra;

public class GamePane extends JPanel {

    private final int SIZE = 25;

    private final GameArray array;
    
    private int score;
    private int level;
    private Tetra nextTetra;

    public GamePane(GameArray array) {
        
        this.array = array;
        setSize(SIZE * 40, SIZE * 18);

        level = 1;
    }

    @Override
    public void paint(Graphics graphics) {

        super.paint(graphics);

        Graphics2D g = (Graphics2D) graphics;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                
                int color = array.getCellColor(i+2, j);
                Color c = Colors.color[color];
                g.setColor(c);
                g.fillRect(j * SIZE, i * SIZE, SIZE, SIZE);

                g.setColor(Color.BLACK);
                g.drawRect(j * SIZE, i * SIZE, SIZE, SIZE);
            }
        }
        
        AttributedString scoreValue = new AttributedString("Points: " + score);
        scoreValue.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_DEMIBOLD);
        scoreValue.addAttribute(TextAttribute.SIZE, 14);
    
        g.drawString(scoreValue.getIterator(), SIZE * 10 + 40, 20);

        AttributedString levelValue = new AttributedString("Level: " + level);
        levelValue.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_DEMIBOLD);
        levelValue.addAttribute(TextAttribute.SIZE, 14);

        g.drawString(levelValue.getIterator(), SIZE * 10 + 40, 40);
        
        if (nextTetra != null) {
            Color c = Colors.color[nextTetra.getColor()];
            g.setColor(c);

            for (Position pos : nextTetra.getAbsolutePositions()) {
                g.fillRect(SIZE * 10 + 60 + pos.x * SIZE, 90 + pos.y * SIZE, SIZE, SIZE);
            } 
        }
    }

    public void update() {
        repaint();
    }

    public void setNextTetra(Tetra nextTetra) {
        this.nextTetra = nextTetra;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public void increaseLevel() {
        level += 1;
    }
}
