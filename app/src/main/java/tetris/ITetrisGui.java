/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import tetris.tetra.Tetra;

/**
 *
 * @author roman
 */
public interface ITetrisGui {
    
    void close();

    void showScore(int score);

    void setNextTetra(Tetra nextTetra);

    void updateView();
    
    boolean showGameOverMessage(int score);
    
}
