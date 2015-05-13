/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.tictactoe;

import minmax.games.tictactoe.TicTacToeMove;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import minmax.GameInterface;
import minmax.Move;

/**
 *
 * @author Łukasz
 */
public class TicTacToe implements GameInterface {
    
    private int[][] boardArray;
    
    private final boolean playerTurn;
    
    private final int playerSymbolId;
    
    private final int opponentSymbolId;
    
    private final TicTacToeMove lastMove;
    
    private TicTacToeMove nextSuggestedMove;
    
    private int moveCount;
    
    private final char[] symbols = {'+','X', 'O'};

    private enum States {IN_PROGRESS, DRAW, PLAYER_WON, OPPONENT_WON;}
    
    private States gameState;
    
    public TicTacToe() {
        this.boardArray = new int[3][3];
        this.playerTurn = true;
        this.playerSymbolId = 1;
        this.opponentSymbolId = 2;
        this.lastMove =  new TicTacToeMove(0, 0, 0);
        this.nextSuggestedMove = new TicTacToeMove(0, 0, 0);
        this.moveCount = 0;
        this.gameState = States.IN_PROGRESS;
    }
    
    public TicTacToe(boolean playerStarts, int playerSymbolId, int opponentSymbolId ) {
        this.boardArray = new int[3][3];
        this.playerTurn = playerStarts;
        this.playerSymbolId = playerSymbolId;
        this.opponentSymbolId = opponentSymbolId;
        this.lastMove = new TicTacToeMove(0, 0, 0);
        this.nextSuggestedMove = new TicTacToeMove(0, 0, 0);
        this.moveCount = 0;
        this.gameState = States.IN_PROGRESS;
    }

    public TicTacToe(int [][] boardArray, boolean playerTurn, int playerSymbolId,
            int opponentSymbolId, TicTacToeMove lastMove, int moveCount, 
            TicTacToeMove nextSuggestedMove) {
        this.boardArray = boardArray;
        this.playerTurn = playerTurn;
        this.playerSymbolId = playerSymbolId;
        this.lastMove = lastMove;
        this.opponentSymbolId = opponentSymbolId;
        this.moveCount = moveCount;
        this.nextSuggestedMove = nextSuggestedMove;
        this.gameState = States.IN_PROGRESS;
    }

    @Override
    public GameInterface getStateFromMove(Move move) {
        int[][] nextBoard = getDeepCopyOfBoard();
        nextBoard[((TicTacToeMove)move).getRow()][((TicTacToeMove)move).getColumn()]
                = ((TicTacToeMove)move).getSymbolId();
        return new TicTacToe(nextBoard, !playerTurn, playerSymbolId, 
                opponentSymbolId, (TicTacToeMove)move, (moveCount+1), nextSuggestedMove);
    }

    @Override
    public List<Move> getAllAvaibleMoves() {
        List movesList = new ArrayList(9);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(boardArray[i][j] == 0)
                    movesList.add(new TicTacToeMove(i, j, playerTurn ? 
                            playerSymbolId : opponentSymbolId));
        return movesList;
    }

    @Override
    public boolean isOver() {
        if(gameState == States.IN_PROGRESS) {
            //if(getAllAvaibleMoves() == null)
                //gameState
            checkState();
        }
            
        return gameState != States.IN_PROGRESS;
    }    

    @Override
    public boolean isPlayerTurn() {
        return playerTurn;
    }
    
    @Override
    public boolean isPlayerAWinner() {
        return gameState == States.PLAYER_WON;
    }

    @Override
    public boolean isOppoentAWinner() {
        return gameState == States.OPPONENT_WON;
    }

    @Override
    public boolean isDraw() {
        return gameState == States.DRAW;
    }
    
     @Override
    public Move getNextSuggestedMove() {
        return nextSuggestedMove;
    }
    
    @Override
    public void setNextSuggestedMove(Move move) {
        nextSuggestedMove = (TicTacToeMove) move;
    }

    private void checkState() {

        int n = 3;
        int x = lastMove.getRow();
        int y = lastMove.getColumn();
        int s = lastMove.getSymbolId();
        for(int i = 0; i < n; i++){
    		if(boardArray[x][i] != s)
    			break;
    		if(i == n-1){
    			 //reporting win for s
                         if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    		}
    	}

    	//check row
    	for(int i = 0; i < n; i++){
    		if(boardArray[i][y] != s)
    			break;
    		if(i == n-1){
    			if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    		}
    	}

    	//check diag
    	if(x == y){
    		//we're on a diagonal
    		for(int i = 0; i < n; i++){
    			if(boardArray[i][i] != s)
    				break;
    			if(i == n-1){
                                if(s == playerSymbolId)
                                     gameState = States.PLAYER_WON;
                                 else if(s == opponentSymbolId)
                                     gameState = States.OPPONENT_WON;
                                }
    		}
    	}

            //check anti diag (thanks rampion)
    	for(int i = 0;i<n;i++){
    		if(boardArray[i][(n-1)-i] != s)
    			break;
    		if(i == n-1){
    			if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    		}
    	}

    	//check draw
    	if(moveCount >= (Math.pow(3, 2) )){
    		gameState = States.DRAW;
    	}
    }
    
    private int [][] getDeepCopyOfBoard() {
        int[][] copy = new int[boardArray.length][boardArray.length];
        for(int i = 0; i < boardArray.length; i++)
            for(int j = 0; j < boardArray[i].length; j++)
                copy[i][j] = boardArray[i][j];
        return copy;
    }

    public int getPlayerSymbolId() {
        return playerSymbolId;
    }

    public int getOpponentSymbolId() {
        return opponentSymbolId;
    }
    
    @Override
    public int getBoardSize() {
       return boardArray.length;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i= 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                try {
                    sb.append(symbols[boardArray[i][j]]).append("|");
                } catch(ArrayIndexOutOfBoundsException ex) {
                    return "Wrong symbol ID";
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
}
