/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Łukasz
 */
public class MyJPanel extends JPanel{
    
    int[][] board;
    
    public static final int BOARD_SIZE = 500;
    
    public static final int SINGLE_GRID_SIZE = 40;
    
    private final List<MyRectangle> rectanglesList;
    
    public MyJPanel(int [][] board) {
            
        setBackground(Color.white);
        this.board = board;
        rectanglesList = new ArrayList<>(15);
        prepareScene();
    }
    
    @Override
    protected void paintComponent(Graphics grap) {
        
        super.paintComponent(grap);
        Graphics2D g2d = (Graphics2D) grap; 
        g2d.setColor(Color.black);
        drawBoard(g2d);
        g2d.setColor(Color.red);
        
    }
    
    private void prepareScene() {
     
        for(int i = 1; i < board.length+1; i++) {
            for(int j = 1; j < board.length+1; j++) {
                rectanglesList.add(new MyRectangle((SINGLE_GRID_SIZE*i)+i,
                        (SINGLE_GRID_SIZE*j)+j,
                        SINGLE_GRID_SIZE, SINGLE_GRID_SIZE, (j-1), (i-1)));
            }
        }
        
    }
    
    private void drawBoard(Graphics2D g2d) {
        
        for(MyRectangle r : rectanglesList) {
            g2d.setColor(r.getColor());
            //g2d.drawRect(r.x, r.y, r.width, r.height);
            g2d.fillRect(r.x, r.y, r.width, r.height);
        }
    }
    
    public MyRectangle checkCollision(int x, int y) {
        
        Iterator it = rectanglesList.iterator();
        while(it.hasNext()) {
            MyRectangle rec = (MyRectangle) it.next();
            if(x >= rec.x && x <= rec.x + rec.width)
                if(y >= rec.y && y <= rec.y + rec.height) {
//                    if(rec.isOccupied() == false) {
//                    rec.setColor(Color.red);
//                    repaint();
//                    rec.setOccupied(true);
//                    return rec;
//                    }
                    return rec;
                }
                    
        }
        return null;
        
    }
    
    public void setRectangleColor(MyRectangle rec, Color color) {
        if(rec.isOccupied())
            return;
        rec.setColor(color);
        rec.setOccupied(true);
        repaint();
        
    }
    
    public void setRectangleColor(int boardX, int boardY, Color color) {
        MyRectangle rec = findRectangleBasedOnBoardCoords(boardX, boardY);
        if(rec.isOccupied())
            return;
        rec.setColor(color);
        rec.setOccupied(true);
        repaint();
        
    }
    
    public MyRectangle findRectangleBasedOnBoardCoords(int boardX, int boardY) {
        for(MyRectangle rec : rectanglesList) {
            if(rec.getBoardRow() == boardX && rec.getBoardColumn() == boardY)
                return rec;
        }
        return null;
    }

}
