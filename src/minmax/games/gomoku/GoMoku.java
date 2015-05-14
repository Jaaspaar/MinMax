/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.gomoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import minmax.GameInterface;
import minmax.Move;
import minmax.games.tictactoe.TicTacToe;
import minmax.games.tictactoe.TicTacToeMove;

/**
 *
 * @author Łukasz
 */
public class GoMoku implements GameInterface{

        
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
    
    private static int N = 15;
    
    private HashMap<Integer, Move> killersMap;
    
    public GoMoku() {
        this.boardArray = new int[N][N];
        this.playerTurn = true;
        this.playerSymbolId = 1;
        this.opponentSymbolId = 2;
        this.lastMove =  new TicTacToeMove(0, 0, 0);
        this.nextSuggestedMove = new TicTacToeMove(0, 0, 0);
        this.moveCount = 0;
        this.gameState = States.IN_PROGRESS;
        killersMap = new HashMap<>();
    }
    
    public GoMoku(boolean playerStarts, int playerSymbolId, int opponentSymbolId ) {
        this.boardArray = new int[N][N];
        this.playerTurn = playerStarts;
        this.playerSymbolId = playerSymbolId;
        this.opponentSymbolId = opponentSymbolId;
        this.lastMove = new TicTacToeMove(0, 0, 0);
        this.nextSuggestedMove = new TicTacToeMove(0, 0, 0);
        this.moveCount = 0;
        this.gameState = States.IN_PROGRESS;
        killersMap = new HashMap<>();
    }

    public GoMoku(int [][] boardArray, boolean playerTurn, int playerSymbolId,
            int opponentSymbolId, TicTacToeMove lastMove, int moveCount, 
            TicTacToeMove nextSuggestedMove, HashMap killersMap) {
        this.boardArray = boardArray;
        this.playerTurn = playerTurn;
        this.playerSymbolId = playerSymbolId;
        this.lastMove = lastMove;
        this.opponentSymbolId = opponentSymbolId;
        this.moveCount = moveCount;
        this.nextSuggestedMove = nextSuggestedMove;
        this.gameState = States.IN_PROGRESS;
        this.killersMap = killersMap;
    }

    @Override
    public GameInterface getStateFromMove(Move move) {
        int[][] nextBoard = getDeepCopyOfBoard();
        nextBoard[((TicTacToeMove)move).getRow()][((TicTacToeMove)move).getColumn()]
                = ((TicTacToeMove)move).getSymbolId();
        return new GoMoku(nextBoard, !playerTurn, playerSymbolId, 
                opponentSymbolId, (TicTacToeMove)move, (moveCount+1), nextSuggestedMove,
        killersMap);
    }

    @Override
    public List<Move> getAllAvaibleMoves() {
        List movesList = new ArrayList(N*N);
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
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
    
    @Override
    public boolean isLegalMove(Move move) {
        return boardArray[move.getRow()][move.getColumn()] == 0;
    }
    
    @Override
    public Move getKiller(int depth) {
        return killersMap.get(depth);
    }
    
    @Override
    public void setKiller(int depth, Move move) {
        killersMap.put(depth, move);
    }

    private void checkState() {

        int n = N;
        int x = lastMove.getRow();
        int y = lastMove.getColumn();
        int s = lastMove.getSymbolId();
        int matchCount = 0;
        int lastMatchIndex = -1;
        for(int i = 0; i < n; i++){
    		if(boardArray[x][i] == s) {
                        if(lastMatchIndex < 0)
                            lastMatchIndex = i;
                        else if(lastMatchIndex != i - 1) {
                            matchCount = 1;
                            lastMatchIndex = i;
                            continue;
                        }
    			matchCount++;
                        lastMatchIndex = i;
                }
    		
    	}
        
        if(matchCount == 5){
    			 //reporting win for s
                         if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    	}

    	//check row
        matchCount = 0;
        lastMatchIndex = -1;
    	for(int i = 0; i < n; i++){
    		if(boardArray[i][y] == s){
                        if(lastMatchIndex < 0)
                            lastMatchIndex = i;
                        else if(lastMatchIndex != i - 1) {
                            matchCount = 1;
                            lastMatchIndex = i;
                            continue;
                        }
    			matchCount++;
                        lastMatchIndex = i;
                }
    	}
        
        if(matchCount == 5){
    			 //reporting win for s
                         if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    	}

    	//check diag
        matchCount = 0;
        lastMatchIndex = -1;
    	//if(x == y){
    		//we're on a diagonal
    		for(int i = 0; i < n; i++){
                    if(matchCount == 5)
                        break;
                    matchCount = 0;
                    lastMatchIndex = -1;
                    //for(int j = i; j < n; j++) {
                        int j = i;
                        for(int k = 0; k < n & j < n; k++) {
                            if(boardArray[j][k] == s){
                            if(lastMatchIndex < 0)
                                lastMatchIndex = j;
                            else if(lastMatchIndex != j - 1) {
                                matchCount = 1;
                                lastMatchIndex = j;
                                j++;
                                continue;
                            }
                            matchCount++;
                            lastMatchIndex = j;

                            }
                            j++;
                        }
                    //
    		}
    	//}
        if(matchCount == 5){
    			 //reporting win for s
                         if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    	}
        //check diag upper part
        matchCount = 0;
        lastMatchIndex = -1;
    	//if(x == y){
    		//we're on a diagonal
    		for(int i = 0; i < n; i++){
                    if(matchCount == 5)
                        break;
                    matchCount = 0;
                    lastMatchIndex = -1;
                    //for(int j = i; j < n; j++) {
                        int k = i;
                        for(int j = 0; k < n & j < n; j++) {
                            if(boardArray[j][k] == s){
                            if(lastMatchIndex < 0)
                                lastMatchIndex = j;
                            else if(lastMatchIndex != j - 1) {
                                matchCount = 1;
                                lastMatchIndex = j;
                                k++;
                                continue;
                            }
                            matchCount++;
                            lastMatchIndex = j;

                            }
                            k++;
                        }
                    //
    		}
    	//}
        if(matchCount == 5){
    			 //reporting win for s
                         if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    	}

            //check anti diag (thanks rampion)
        matchCount = 0;
        lastMatchIndex = -1;
    	for(int i = 0;i<n;i++){
            //for(int j = i; j < n; j++) {
                if(matchCount == 5)
                        break;
                matchCount = 0;
                lastMatchIndex = -1;
                int j = i;
                for(int k = 0; k < n & j < n; k++) {
                    if(boardArray[j][(n-1)-k] == s){
                            if(lastMatchIndex < 0)
                                lastMatchIndex = j;
                            else if(lastMatchIndex != j - 1) {
                                matchCount = 1;
                                lastMatchIndex = j;
                                j++;
                                continue;
                            }
                            matchCount++;
                            lastMatchIndex = j;
                    }
                    j++;
                }
            //}
    	}
        if(matchCount == 5){
    			 //reporting win for s
                         if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    	}
            //check anti diag, upper part
        matchCount = 0;
        lastMatchIndex = -1;
    	for(int i = 0;i<n;i++){
            //for(int j = i; j < n; j++) {
                if(matchCount == 5)
                        break;
                matchCount = 0;
                lastMatchIndex = -1;
                int k = i;
                for(int j = 0; k < n & j < n; j++) {
                    if(boardArray[j][(n-1)-k] == s){
                            if(lastMatchIndex < 0)
                                lastMatchIndex = j;
                            else if(lastMatchIndex != j - 1) {
                                matchCount = 1;
                                lastMatchIndex = j;
                                k++;
                                continue;
                            }
                            matchCount++;
                            lastMatchIndex = j;
                    }
                    k++;
                }
            //}
    	}
        if(matchCount == 5){
    			 //reporting win for s
                         if(s == playerSymbolId)
                             gameState = States.PLAYER_WON;
                         else if(s == opponentSymbolId)
                             gameState = States.OPPONENT_WON;
    	}

    	//check draw
    	if(moveCount >= ((n*n))){
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

    public int[][] getBoardArray() {
        return boardArray;
    }

    public int getPlayerSymbolId() {
        return playerSymbolId;
    }

    @Override
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
        for(int i= 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
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
