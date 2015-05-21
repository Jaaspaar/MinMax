/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.checkers;

import minmax.Move;

/**
 *
 * @author Łukasz
 */
public class CheckersMove implements Move{
    
    private final int symbolId;
    private final int currentRow;
    private final int currentColumn;
    private final int row;
    private final int column;
    private boolean opponentPawnDestroyed;
    private final int opponentRow;
    private final int opponentColumn;

    public CheckersMove(int symbolId, int currentRow, int currentColumn, int row,
            int column) {
        this.symbolId = symbolId;
        this.currentRow = currentRow;
        this.currentColumn = currentColumn;
        this.row = row;
        this.column = column;
        this.opponentPawnDestroyed = false;
        this.opponentRow = 0;
        this.opponentColumn = 0;
    }

    public CheckersMove(int symbolId, int currentRow, int currentColumn, int row,
            int column, boolean opponentDestryed, int opponentRow, int opponentColumn) {
        this.symbolId = symbolId;
        this.currentRow = currentRow;
        this.currentColumn = currentColumn;
        this.row = row;
        this.column = column;
        this.opponentPawnDestroyed = opponentDestryed;
        this.opponentRow = opponentRow;
        this.opponentColumn = opponentColumn;
    }

    @Override
    public int getSymbolId() {
        return symbolId;
    }

    @Override
    public int getRow() {
         return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public boolean isOpponentPawnDestroyed() {
        return opponentPawnDestroyed;
    }

    public int getOpponentRow() {
        return opponentRow;
    }

    public int getOpponentColumn() {
        return opponentColumn;
    }
    
    
    
    
    
}
