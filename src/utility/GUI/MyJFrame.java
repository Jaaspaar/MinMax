/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import minmax.GameInterface;
import minmax.games.gomoku.GoMoku;
import minmax.games.tictactoe.TicTacToe;
import minmax.games.tictactoe.TicTacToeMove;

/**
 *
 * @author Łukasz
 */
public class MyJFrame extends JFrame {
    
    
    MyJPanel myJPanel;
    
    GameInterface gameInit;
    
    public MyJFrame(int width, int height, GameInterface game) {
        
        super("Let's play a game!");
        
        setSize(width, height);
        
        this.getContentPane().setPreferredSize(new Dimension(width, height));
        
        pack();
        
        setLocation(200, 10);
        
        this.gameInit = game;
        
        myJPanel = new MyJPanel(gameInit.getBoardSize());
        
        add(myJPanel);
        
        myJPanel.addMouseListener(new MouseListener() {
            
            GameInterface game = gameInit;

            @Override
            public void mouseClicked(MouseEvent e) {
                if(!game.isOver()) {
                    MyRectangle rec = myJPanel.checkCollision(e.getX(), e.getY());
                    if(rec != null && !rec.isOccupied()) {
                        
                        myJPanel.setRectangleColor(rec, Color.blue);
                    
                        game = game.getStateFromMove(
                                new TicTacToeMove(rec.getBoardRow(), rec.getBoardColumn(),
                                game.getOpponentSymbolId()));
                    
                        if(!game.isOver()) {
                            System.out.println("minmax in progres..");
                            minmax.MinMaxAlg.minmaxKiller(game, 4, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            System.out.println("minmax done");
                            myJPanel.setRectangleColor(game.getNextSuggestedMove().getRow(),
                                    game.getNextSuggestedMove().getColumn(), Color.GREEN);
                            game =  game.getStateFromMove(game.getNextSuggestedMove());
                        }
                        else {
                            printEndText();
                        }
                    }
                }
                else {
                    printEndText();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
            private void printEndText() {
                if(game.isPlayerAWinner())
                    System.out.println("Zwycięstwo gracza 'player'");
                else if(game.isOppoentAWinner())
                    System.out.println("Zwycięstwo gracza 'opponent'");
                else 
                    System.out.println("Remis!");
            }


        });
        
        //pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        
    }
    
    public void setBoard(int[][] board) {
        
        myJPanel.repaint();
    }
}
