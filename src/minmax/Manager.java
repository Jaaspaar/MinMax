/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

import minmax.games.checkers.Checkers;
import minmax.games.checkers.CheckersCompVsComp;
import minmax.games.general.CompVsComp;
import minmax.games.gomoku.GoMoku;
import minmax.games.gomoku.GoMokuCompVsComp;
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
    
    public static int LAB_ITERATIONS = 1;
    public static int DEPTH = 10;

    public Manager() {
        GoMoku game = new GoMoku(false, 1, 2);
        GoMoku game2 = new GoMoku(false, 2, 1);
        new Painter(game, game2, 2);
        
        
//        Checkers game = new Checkers();
//        System.out.println(game);
//        MinMaxAlg.minmax(game);
//        game = (Checkers) game.getStateFromMove(game.getNextSuggestedMove());
//        System.out.println(game);
        
//        CheckersCompVsComp comp = new CheckersCompVsComp();
//        comp.beginContest(13, Integer.MIN_VALUE, Integer.MAX_VALUE);
//        LabTesting();
        
        
    }
    
    private void LabTesting() {
        int MMsum = 0;
//        for(int i = 0; i < LAB_ITERATIONS; i++) {
//            CompVsComp contest = new CheckersCompVsComp();
//            long startTime = System.currentTimeMillis();
//            contest.beginContest(DEPTH);
//            MMsum += System.currentTimeMillis() - startTime;
//        }

        int ABsum = 0;
        for(int i = 0; i < LAB_ITERATIONS; i++) {
            CompVsComp contest = new CheckersCompVsComp();
            long startTime = System.currentTimeMillis();
            contest.beginContest(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
            ABsum += System.currentTimeMillis() - startTime;
        }
        
        int KillerSum = 0;
//        for(int i = 0; i < LAB_ITERATIONS; i++) {
//            CompVsComp contest = new GoMokuCompVsComp();
//            long startTime = System.currentTimeMillis();
//            contest.beginContestKiller(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
//            KillerSum += System.currentTimeMillis() - startTime;
//        }
        System.out.println("avg dutation time min-max: "+MMsum/LAB_ITERATIONS);
        System.out.println("avg dutation time alpha-beta: "+ABsum/LAB_ITERATIONS);
        System.out.println("avg dutation time alpha-beta killer: "+KillerSum/LAB_ITERATIONS);
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
