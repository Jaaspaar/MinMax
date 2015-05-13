/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.GUI;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Łukasz
 */
public class MyRectangle extends Rectangle {
    
    private Color color;
    
    private int symbolID;
    
    private final int boardRow;
    
    private final int boardColumn;
    
    private boolean occupied;
    
    
    public MyRectangle(int x, int y, int width, int height, int boardRow,
            int boardColumn) {
        super(x, y, width, height);
        color = Color.BLACK;
        this.boardRow = boardRow;
        this.boardColumn = boardColumn;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }


    public int getBoardRow() {
        return boardRow;
    }

    public int getBoardColumn() {
        return boardColumn;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSymbolID() {
        return symbolID;
    }

    public void setSymbolID(int symbolID) {
        this.symbolID = symbolID;
    }
    
}
