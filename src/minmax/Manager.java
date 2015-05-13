/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

import minmax.games.gomoku.GoMoku;
import minmax.games.gomoku.GoMokuContest;
import minmax.games.tictactoe.TicTacToeContest;
import minmax.games.tictactoe.TicTacToe;
import minmax.games.tictactoe.TicTacToeCompVsComp;
import minmax.games.tictactoe.TicTacToeMove;
import utility.GUI.Painter;

/**
 *
 * @author Łukasz
 */
public class Manager {

    public Manager() {
        //testGoMoku();
        //TicTacToeContest contest = new TicTacToeContest();
        //contest.beginContestAB();
        //TicTacToeCompVsComp contest = new TicTacToeCompVsComp();
        //contest.beginContest();
        //GoMokuContest contest = new GoMokuContest();
        //contest.beginContest();
        
        //GoMoku game = new GoMoku();
        TicTacToe game = new TicTacToe(false, 1, 2);
        new Painter(game);
        
    }
    
    private void test() {
        TicTacToe gameTicTacToe = new TicTacToe();
        gameTicTacToe = (TicTacToe) gameTicTacToe
                .getStateFromMove(new TicTacToeMove(2, 0, 1))
                .getStateFromMove(new TicTacToeMove(0, 0, 2))
                .getStateFromMove(new TicTacToeMove(1, 0, 1))
                .getStateFromMove(new TicTacToeMove(0, 2, 2))
                .getStateFromMove(new TicTacToeMove(1, 1, 1))
                .getStateFromMove(new TicTacToeMove(1, 2, 2));
        System.out.println(gameTicTacToe);
        System.out.println("isOver: "+gameTicTacToe.isOver());
        System.out.println("isPlayerAWinner: "+gameTicTacToe.isPlayerAWinner());
        
        MinMaxAlg.minmax(gameTicTacToe);
        System.out.println("nextSuggestedMove: "+gameTicTacToe.getNextSuggestedMove());
        gameTicTacToe = (TicTacToe) gameTicTacToe
                .getStateFromMove(gameTicTacToe.getNextSuggestedMove());
        System.out.println(gameTicTacToe);

    }
    
    private void testGoMoku() {
        GoMoku game = new GoMoku();
        game = (GoMoku) game
                .getStateFromMove(new TicTacToeMove(2, 0, 1))
                .getStateFromMove(new TicTacToeMove(1, 0, 2))
                .getStateFromMove(new TicTacToeMove(0, 1, 1))
                .getStateFromMove(new TicTacToeMove(1, 1, 2))
                .getStateFromMove(new TicTacToeMove(0, 2, 1))
                .getStateFromMove(new TicTacToeMove(1, 2, 2))
                .getStateFromMove(new TicTacToeMove(0, 3, 1))
                .getStateFromMove(new TicTacToeMove(1, 3, 2));
                //.getStateFromMove(new TicTacToeMove(0, 4, 1));
        System.out.println(game);
        MinMaxAlg.minmax(game, 3, Integer.MIN_VALUE, Integer.MAX_VALUE);
        game = (GoMoku) game.getStateFromMove(game.getNextSuggestedMove());
        System.out.println(game);
        System.out.println("isOver: "+game.isOver());
    }
    
}
