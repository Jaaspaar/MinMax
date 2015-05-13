/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

import java.util.List;

/**
 *
 * @author Łukasz
 */
public interface GameInterface {
    
    public GameInterface getStateFromMove(Move move);
    
    public List<Move> getAllAvaibleMoves();
    
    public boolean isPlayerTurn();
    
    public boolean isOver();
    
    public boolean isPlayerAWinner();
    
    public boolean isOppoentAWinner();
    
    public boolean isDraw();
    
    public Move getNextSuggestedMove();
    
    public void setNextSuggestedMove(Move move);
    
    public int getOpponentSymbolId();
    
    public int getBoardSize();
    
}
