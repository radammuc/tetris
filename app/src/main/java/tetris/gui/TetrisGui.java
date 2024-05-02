/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import tetris.GameArray;
import tetris.ITetrisGui;
import tetris.Movement;
import tetris.tetra.Tetra;

public class TetrisGui extends JFrame implements ITetrisGui {

    private final GamePane gamePane;
    
    private final ITetris tetris;
    
    public TetrisGui(ITetris tetris, GameArray array) {

        final int SIZE = 25;
        
        this.tetris = tetris;
        
        setSize(SIZE * 20, SIZE * 20 + 24);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gamePane = new GamePane(array);
        add(gamePane);

        setResizable(false);
        setVisible(true);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("menu");
        menuBar.add(menu);
        add(menu);

        pack();

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        TetrisGui.this.tetris.checkInsert(Movement.Right);
                        break;

                    case KeyEvent.VK_LEFT:
                        TetrisGui.this.tetris.checkInsert(Movement.Left);
                        break;

                    case KeyEvent.VK_UP:
                        TetrisGui.this.tetris.checkInsert(Movement.Rotate);
                        break;

                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_SPACE:
                        TetrisGui.this.tetris.checkInsert(Movement.Down);
                        break;
                        
                    case KeyEvent.VK_P:
                        TetrisGui.this.tetris.pause();
                        break;
                }
            }
        });
    }
    
    @Override
    public boolean showGameOverMessage(int score) {
        Object[] options = {"Ja, nochmal spielen", "Beenden"};

        return JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null, 
                "Sie haben " + score + " Punkte erreicht. \nNochmal spielen?", 
                "Game over", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    @Override
    public void close() {
        setVisible(false);
        dispose();
    }

    @Override
    public void showScore(int score) {
        gamePane.setScore(score);
        repaint();
    }

    @Override
    public void setNextTetra(Tetra nextTetra) {
        gamePane.setNextTetra(nextTetra);
    }

    @Override
    public void updateView() {
        gamePane.update();
    }

}
