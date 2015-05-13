/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.tictactoe;

import java.util.Scanner;
import minmax.MinMaxAlg;
import minmax.games.tictactoe.TicTacToeMove;
import sun.org.mozilla.javascript.internal.Token;

/**
 *
 * @author Łukasz
 */
public class TicTacToeContest {
    
    TicTacToe game;

    public TicTacToeContest() {
        game = new TicTacToe();
        //game = (TicTacToe) game.getStateFromMove(new TicTacToeMove(2, 0, 1))
          //      .getStateFromMove(new TicTacToeMove(0, 0, 2));
    }
    
    public void beginContest() {
        Scanner keyboard = new Scanner(System.in);
        while(!game.isOver()) {
            System.out.println(game);
            if(game.isPlayerTurn()) {
                System.out.println("Ruch gracza 'player'");
                MinMaxAlg.minmax(game);
                game = (TicTacToe) game
                .getStateFromMove(game.getNextSuggestedMove());
            }
            else {
                System.out.println("Ruch gracza 'opponent'");
                game = (TicTacToe) game
                .getStateFromMove(new TicTacToeMove(keyboard.nextInt(),
                        keyboard.nextInt(), game.getOpponentSymbolId()));
            }
        }
        System.out.println(game);
        if(game.isPlayerAWinner())
            System.out.println("Zwycięstwo gracza 'player'");
        else if(game.isOppoentAWinner())
            System.out.println("Zwycięstwo gracza 'opponent'");
        else 
            System.out.println("Remis!");
    }
    
    public void beginContestAB() {
        Scanner keyboard = new Scanner(System.in);
        while(!game.isOver()) {
            System.out.println(game);
            if(game.isPlayerTurn()) {
                System.out.println("Ruch gracza 'player'");
                MinMaxAlg.minmax(game, Integer.MIN_VALUE, Integer.MAX_VALUE);
                game = (TicTacToe) game
                .getStateFromMove(game.getNextSuggestedMove());
            }
            else {
                System.out.println("Ruch gracza 'opponent'");
                game = (TicTacToe) game
                .getStateFromMove(new TicTacToeMove(keyboard.nextInt(),
                        keyboard.nextInt(), game.getOpponentSymbolId()));
            }
        }
        System.out.println(game);
        if(game.isPlayerAWinner())
            System.out.println("Zwycięstwo gracza 'player'");
        else if(game.isOppoentAWinner())
            System.out.println("Zwycięstwo gracza 'opponent'");
        else 
            System.out.println("Remis!");
    }
    
}
