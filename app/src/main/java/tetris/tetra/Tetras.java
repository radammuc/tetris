/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.tetra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author roman
 */
public class Tetras {
    
    private final List<Tetra> tetras = new ArrayList<>(7);
    
    public Tetras() {
        init();
    }
    
    public Tetra getTetra(int pos) {
        return tetras.get(pos);
    }
    
    private void init() {
        Tetra i = new Tetra(7);
        i.add(-1, 0);
        i.add(1, 0);
        i.add(2, 0);
        i.add(0, 0);

        Tetra j = new Tetra(1);
        j.add(0, 1);
        j.add(1, 0);
        j.add(2, 0);
        j.add(0, 0);

        Tetra l = new Tetra(2);
        l.add(0, 1);
        l.add(-1, 0);
        l.add(-2, 0);
        l.add(0, 0);

        Tetra o = new Tetra(3);
        o.add(0, 1);
        o.add(1, 0);
        o.add(1, 1);
        o.add(0, 0);

        Tetra s = new Tetra(4);
        s.add(-1, 0);
        s.add(0, 1);
        s.add(1, 1);
        s.add(0, 0);

        Tetra t = new Tetra(5);
        t.add(1, 0);
        t.add(-1, 0);
        t.add(0, 1);
        t.add(0, 0);

        Tetra z = new Tetra(6);
        z.add(1, 0);
        z.add(0, 1);
        z.add(-1, 1);
        z.add(0, 0);
        
        

        tetras.add(i);
        tetras.add(j);
        tetras.add(l);
        tetras.add(o);
        tetras.add(s);
        tetras.add(t);
        tetras.add(z);
        
        Collections.shuffle(tetras);
        
        
    }


    
}
