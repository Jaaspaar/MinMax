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
import minmax.GameInterface;
import sun.org.mozilla.javascript.internal.Token;

/**
 *
 * @author Łukasz
 */
public class Painter {
    
    private static final int I_SCENE_WIDTH = 1000;
    
    private static final int I_SCENE_HEIGHT = 700;
    
    
    JFrame myJFrame;
    
    GameInterface game;
    
    public Painter(GameInterface game) {
        
        this.game = game;
        
        prepareScene();
    }
    
    public Painter(GameInterface playerGame, GameInterface opponentGame,
            int depth) {
        
        prepareSceneCompVsComp(playerGame, opponentGame, depth);
    }
    
    
    private void prepareScene() {
        
        EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				myJFrame = new MyJFrame(I_SCENE_WIDTH,
                                        I_SCENE_HEIGHT, game);
			}
		});
        
    }
    
    private void prepareSceneCompVsComp(final GameInterface pG, final GameInterface oG, 
            final int depth) {
        
        EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				myJFrame = new MyCompVsCompJFrame(I_SCENE_WIDTH,
                                        I_SCENE_HEIGHT, pG, oG, depth );
			}
		});
        
    }
    
}
