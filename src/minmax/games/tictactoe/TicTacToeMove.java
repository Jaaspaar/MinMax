/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.tictactoe;

import minmax.Move;

/**
 *
 * @author Łukasz
 */
public class TicTacToeMove implements Move{
    
    private int row;
    private int column;
    private int symbolId;

    public TicTacToeMove(int row, int column, int symbolId) {
        this.row = row;
        this.column = column;
        this.symbolId = symbolId;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int getSymbolId() {
        return symbolId;
    }
    
    @Override
    public String toString() {
        return "row: " + row + " column: " + column;
    }
    
    
    
}
