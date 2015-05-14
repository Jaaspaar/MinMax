/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.general;


import minmax.GameInterface;
import minmax.MinMaxAlg;
import utility.GUI.Painter;

/**
 *
 * @author Łukasz
 */
public abstract class CompVsComp {
    
    protected GameInterface playerGame;
    protected GameInterface opponentGame;
    public static int DEPTH = 6;
    
    public CompVsComp() {
            
    }
    
    public void initGUI() {
        Painter myPainter = new Painter(playerGame, opponentGame, DEPTH);
    }

//    public CompVsComp(GameInterface playerGame, GameInterface opponentGame) {
//        this.playerGame = playerGame;
//        this.opponentGame = opponentGame;
//    }
    
    public void beginContest() {
        while(!playerGame.isOver()) {
            System.out.println(playerGame);
            if(playerGame.isPlayerTurn()) {
                System.out.println("Ruch gracza 'player'");
                MinMaxAlg.minmax(playerGame);
                opponentGame =  opponentGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
                playerGame =  playerGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
            }
            else {
                System.out.println("Ruch gracza 'opponent'");
                MinMaxAlg.minmax(opponentGame);
                playerGame =  playerGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
                opponentGame =  opponentGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
            }
        }
        printEndState(playerGame);
    }
    
    public void beginContest(int depth) {
        while(!playerGame.isOver()) {
            System.out.println(playerGame);
            if(playerGame.isPlayerTurn()) {
                System.out.println("Ruch gracza 'player'");
                MinMaxAlg.minmax(playerGame, depth);
                opponentGame =  opponentGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
                playerGame =  playerGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
            }
            else {
                System.out.println("Ruch gracza 'opponent'");
                MinMaxAlg.minmax(opponentGame, depth);
                playerGame =  playerGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
                opponentGame =  opponentGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
            }
        }
        printEndState(playerGame);
    }
    
        public void beginContest(int depth, int alpha, int beta) {
        while(!playerGame.isOver()) {
            System.out.println(playerGame);
            if(playerGame.isPlayerTurn()) {
                System.out.println("Ruch gracza 'player'");
                MinMaxAlg.minmax(playerGame, depth, alpha, beta);
                opponentGame =  opponentGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
                playerGame =  playerGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
            }
            else {
                System.out.println("Ruch gracza 'opponent'");
                MinMaxAlg.minmax(opponentGame, depth, alpha, beta);
                playerGame =  playerGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
                opponentGame =  opponentGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
            }
        }
        printEndState(playerGame);
    }
        
    public void beginContestKiller(int depth, int alpha, int beta) {
        while(!playerGame.isOver()) {
            System.out.println(playerGame);
            if(playerGame.isPlayerTurn()) {
                System.out.println("Ruch gracza 'player'");
                MinMaxAlg.minmaxKiller(playerGame, depth, alpha, beta);
                opponentGame =  opponentGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
                playerGame =  playerGame
                .getStateFromMove(playerGame.getNextSuggestedMove());
            }
            else {
                System.out.println("Ruch gracza 'opponent'");
                MinMaxAlg.minmaxKiller(opponentGame, depth, alpha, beta);
                playerGame =  playerGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
                opponentGame =  opponentGame
                .getStateFromMove(opponentGame.getNextSuggestedMove());
            }
        }
        printEndState(playerGame);
    }
    
    private void printEndState(GameInterface playerGame) {
                System.out.println(playerGame);
        if(playerGame.isPlayerAWinner())
            System.out.println("Zwycięstwo gracza 'player'");
        else if(playerGame.isOppoentAWinner())
            System.out.println("Zwycięstwo gracza 'opponent'");
        else 
            System.out.println("Remis!");
    }
    
    
    
}

