/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.gomoku;

import minmax.GameInterface;
import minmax.games.general.CompVsComp;
import minmax.games.tictactoe.TicTacToe;

/**
 *
 * @author Łukasz
 */
public class GoMokuCompVsComp extends CompVsComp{

    public GoMokuCompVsComp() {
        playerGame = new GoMoku(true, 1, 2);
        opponentGame = new GoMoku(false, 2, 1);
        //initGUI();
    }
    
}
