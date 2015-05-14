/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import minmax.GameInterface;
import minmax.games.tictactoe.TicTacToeMove;

/**
 *
 * @author Łukasz
 */
public class MyCompVsCompJFrame extends JFrame implements ActionListener{
    
    MyJPanel myJPanel;
    
    GameInterface playerGame;
    
    GameInterface opponentGame;
    
    int depth;
    
    public MyCompVsCompJFrame(int width, int height, GameInterface playerGame,
            GameInterface opponentGame, int depth) {
        
        super("Let's play a game!");
        setSize(width, height);
        this.getContentPane().setPreferredSize(new Dimension(width, height));
        pack();
        setLocation(200, 10);
        this.playerGame = playerGame;
        this.opponentGame = opponentGame;
        this.depth = depth;
        
        Timer timer;
        timer = new Timer(1000, this);
        
        myJPanel = new MyJPanel(playerGame.getBoardSize());   
        add(myJPanel); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        timer.start();
 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
              
       if(!playerGame.isOver()) {
                        
                        if(!playerGame.isOver()) {
                            System.out.println("minmax in progres player..");
                            minmax.MinMaxAlg.minmax(playerGame, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            System.out.println("minmax done");
                            myJPanel.setRectangleColor(playerGame.getNextSuggestedMove().getRow(),
                                    playerGame.getNextSuggestedMove().getColumn(), Color.RED);
                            opponentGame =  playerGame.getStateFromMove(playerGame.getNextSuggestedMove());
                            playerGame =  playerGame.getStateFromMove(playerGame.getNextSuggestedMove());
                        }
                        else {
                            printEndText(playerGame);
                        }
                    
                        if(!opponentGame.isOver()) {
                            System.out.println("minmax in progres opponent..");
                            minmax.MinMaxAlg.minmax(opponentGame, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            System.out.println("minmax done");
                            myJPanel.setRectangleColor(opponentGame.getNextSuggestedMove().getRow(),
                                    opponentGame.getNextSuggestedMove().getColumn(), Color.GREEN);
                            playerGame =  opponentGame.getStateFromMove(opponentGame.getNextSuggestedMove());
                            opponentGame =  opponentGame.getStateFromMove(opponentGame.getNextSuggestedMove());
                        }
                        else {
                            printEndText(playerGame);
                        }
        }
        myJPanel.repaint();
    }
    
    private void printEndText(GameInterface playerGame) {
                if(playerGame.isPlayerAWinner())
                    System.out.println("Zwycięstwo gracza 'player'");
                else if(playerGame.isOppoentAWinner())
                    System.out.println("Zwycięstwo gracza 'opponent'");
                else 
                    System.out.println("Remis!");
            }
    
}
