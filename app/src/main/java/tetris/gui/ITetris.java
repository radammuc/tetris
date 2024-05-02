/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.gui;

import tetris.Movement;

/**
 *
 * @author roman
 */
public interface ITetris {
    
    boolean checkInsert(Movement movement);

    public void pause();
    
}
