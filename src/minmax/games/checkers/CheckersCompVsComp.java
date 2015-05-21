/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.checkers;

import minmax.games.general.CompVsComp;

/**
 *
 * @author Łukasz
 */
public class CheckersCompVsComp extends CompVsComp {
    
    public CheckersCompVsComp() {
        playerGame = new Checkers(true, 1, 2);
        opponentGame = new Checkers(false, 2, 1);
    }
    
}
