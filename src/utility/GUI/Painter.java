/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.GUI;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JFrame;
import sun.org.mozilla.javascript.internal.Token;

/**
 *
 * @author Łukasz
 */
public class Painter {
    
    private static final int I_SCENE_WIDTH = 1000;
    
    private static final int I_SCENE_HEIGHT = 700;
    
    
    MyJFrame myJFrame;
    
    int[][] board;
    
    public Painter(int [][] board) {
        
        this.board = board;
        
        prepareScene();
    }
    
    
    private void prepareScene() {
        
        EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				myJFrame = new MyJFrame(I_SCENE_WIDTH,
                                        I_SCENE_HEIGHT, board);
			}
		});
        
    }
    
}
