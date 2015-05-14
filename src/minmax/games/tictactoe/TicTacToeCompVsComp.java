/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.tictactoe;

import minmax.MinMaxAlg;
import minmax.games.general.CompVsComp;

/**
 *
 * @author Łukasz
 */
public class TicTacToeCompVsComp extends CompVsComp{
    
//    TicTacToe playerGame;
//    TicTacToe opponentGame;

    public TicTacToeCompVsComp() {
        this.playerGame = new TicTacToe(true, 1, 2);
        this.opponentGame = new TicTacToe(false, 2, 1);
    }
    
//    public void beginContest() {
//        while(!playerGame.isOver()) {
//            System.out.println(playerGame);
//            if(playerGame.isPlayerTurn()) {
//                System.out.println("Ruch gracza 'player'");
//                MinMaxAlg.minmax(playerGame);
//                opponentGame = (TicTacToe) opponentGame
//                .getStateFromMove(playerGame.getNextSuggestedMove());
//                playerGame = (TicTacToe) playerGame
//                .getStateFromMove(playerGame.getNextSuggestedMove());
//            }
//            else {
//                System.out.println("Ruch gracza 'opponent'");
//                MinMaxAlg.minmax(opponentGame);
//                playerGame = (TicTacToe) playerGame
//                .getStateFromMove(opponentGame.getNextSuggestedMove());
//                opponentGame = (TicTacToe) opponentGame
//                .getStateFromMove(opponentGame.getNextSuggestedMove());
//            }
//        }
//        System.out.println(playerGame);
//        if(playerGame.isPlayerAWinner())
//            System.out.println("Zwycięstwo gracza 'player'");
//        else if(playerGame.isOppoentAWinner())
//            System.out.println("Zwycięstwo gracza 'opponent'");
//        else 
//            System.out.println("Remis!");
//    }
    
    
    
}
