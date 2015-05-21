/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.games.checkers;

import java.util.ArrayList;
import java.util.List;
import minmax.GameInterface;
import minmax.Move;
import minmax.games.gomoku.GoMoku;
import sun.org.mozilla.javascript.internal.Token;

/**
 *
 * @author Łukasz
 */
public class Checkers  implements GameInterface {
    
    private int[][] boardArray;
    
    private final boolean playerTurn;
    
    private final int playerSymbolId;
    
    private final int opponentSymbolId;
    
    private final CheckersMove lastMove;   
    
    private CheckersMove nextSuggestedMove;
    
    private int movesCount;
    
    private final char[] symbols = {'+','X', 'O'};

    private enum States {IN_PROGRESS, DRAW, PLAYER_WON, OPPONENT_WON;}
    
    private States gameState;
    
    private static int N = 8;
    
    private int playerPawnsDestroyed;
    
    private int opponentPawnsDestroyed;
    
    public Checkers() {
        this.boardArray = new int[N][N];
        this.playerTurn = true;
        this.playerSymbolId = 1;
        this.opponentSymbolId = 2;
        this.lastMove = new CheckersMove(0, 0, 0, 0, 0);
        this.nextSuggestedMove = new CheckersMove(0, 0, 0, 0, 0);
        this.gameState = States.IN_PROGRESS;
        prepareBoard();
    }
    
    public Checkers(boolean isPlayerTurn, int playerSymbolId, int opponentSymbolId) {
        this.boardArray = new int[N][N];
        this.playerTurn = isPlayerTurn;
        this.playerSymbolId = playerSymbolId;
        this.opponentSymbolId = opponentSymbolId;
        this.lastMove = new CheckersMove(0, 0, 0, 0, 0);
        this.nextSuggestedMove = new CheckersMove(0, 0, 0, 0, 0);
        this.gameState = States.IN_PROGRESS;
        prepareBoard();
    }

    public Checkers(int[][] boardArray, boolean playerTurn, int playerSymbolId,
            int opponentSymbolId, CheckersMove lastMove, CheckersMove nextSuggestedMove,
            int moveCount, int playerPawnsDestroyed, int opponentPawnsDestroyed) {
        this.boardArray = boardArray;
        this.playerTurn = playerTurn;
        this.playerSymbolId = playerSymbolId;
        this.opponentSymbolId = opponentSymbolId;
        this.lastMove = lastMove;
        this.nextSuggestedMove = nextSuggestedMove;
        this.movesCount = moveCount;
        this.playerPawnsDestroyed = playerPawnsDestroyed;
        this.opponentPawnsDestroyed = opponentPawnsDestroyed;
        this.gameState = States.IN_PROGRESS;
    }
    
    

    @Override
    public GameInterface getStateFromMove(Move move) {
        int nextPlayerPawnsDestroyed = playerPawnsDestroyed;
        int nextOpponentPawnsDestroyed = opponentPawnsDestroyed;
        int boardCopy[][] = getDeepCopyOfBoard();
        boardCopy[((CheckersMove)move).getCurrentRow()][((CheckersMove)move).getCurrentColumn()] = 0;
        boardCopy[((CheckersMove)move).getRow()][((CheckersMove)move).getColumn()] = move.getSymbolId();
        if(((CheckersMove)move).isOpponentPawnDestroyed()) {
            boardCopy[((CheckersMove)move).getOpponentRow()][((CheckersMove)move).getOpponentColumn()] = 0;
            if(playerTurn)
                nextOpponentPawnsDestroyed++;
            else
                playerPawnsDestroyed++;
        }
        return new Checkers(boardCopy, !playerTurn, playerSymbolId, opponentSymbolId,
                lastMove, nextSuggestedMove,
        movesCount+1, nextPlayerPawnsDestroyed, nextOpponentPawnsDestroyed);
    }

    @Override
    public List<Move> getAllAvaibleMoves() {
        List<Move> movesList = new ArrayList<>(N*N);
        int currentPlayerdId = playerTurn ? playerSymbolId : opponentSymbolId;
        int otherPlayerId = playerTurn ? opponentSymbolId : playerSymbolId;
        int dir = (currentPlayerdId == 1) ? 1 : -1;
        if(dir == -1)
        {
            int z =1;
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(boardArray[i][j] == currentPlayerdId) {
                    if(i+dir < N && i+dir >= 0) {
                        if(j+1 < N) {
                            if(boardArray[i+dir][j+1] == 0)
                                movesList.add(new CheckersMove(currentPlayerdId, i, j, i+dir, j+1));
                            else if(boardArray[i+dir][j+1] == otherPlayerId
                                    && i+dir+dir < N && i+dir+dir >= 0
                                    && j+1+1 < N) {
                                if(boardArray[i+dir+dir][j+1+1] == 0)
                                     movesList.add(new CheckersMove(currentPlayerdId, i, j, i+dir, j+1+1,
                                     true, i+dir, j+1));
                            }
                        }
                        if(j-1 >= 0) {
                            if(boardArray[i+dir][j-1] == 0)
                                movesList.add(new CheckersMove(currentPlayerdId, i, j, i+dir, j-1));
                            else if(boardArray[i+dir][j-1] == otherPlayerId
                                    && i+dir+dir < N && i+dir+dir >= 0
                                    && j-1-1 >= 0) {
                                if(boardArray[i+dir+dir][j-1-1] == 0)
                                     movesList.add(new CheckersMove(currentPlayerdId, i, j, i+dir+dir, j-1-1,
                                     true, i+dir, j-1));
                            }
                        }
                    }
                }
            }
        }
        return movesList;
    }
    
     public boolean existsAnyMove() {
        int currentPlayerdId = playerTurn ? playerSymbolId : opponentSymbolId;
        int otherPlayerId = playerTurn ? opponentSymbolId : playerSymbolId;
        int dir = (currentPlayerdId == 1) ? 1 : -1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(boardArray[i][j] == currentPlayerdId) {
                    if( i+dir < N && i+dir >= 0) {
                        if(j+1 < N) {
                            if(boardArray[i+dir][j+1] == 0)
                                return true;
                            else if(boardArray[i+dir][j+1] == otherPlayerId
                                    && i+dir+dir < N && i+dir+dir >= 0
                                    && j+1+1 < N) {
                                if(boardArray[i+dir+dir][j+1+1] == 0)
                                     return true;
                            }
                        }
                        if(j-1 >= 0) {
                            if(boardArray[i+dir][j-1] == 0)
                                return true;
                            else if(boardArray[i+dir][j-1] == otherPlayerId
                                    && i+dir+dir < N && i+dir+dir >= 0
                                    && j-1-1 >= 0) {
                                if(boardArray[i+dir+dir][j-1-1] == 0)
                                     return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    @Override
    public boolean isOver() { 
        if(gameState == States.IN_PROGRESS) {
            checkState();
        }
        return gameState != States.IN_PROGRESS;
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
        nextSuggestedMove = (CheckersMove) move;
    }

    @Override
    public int getOpponentSymbolId() {
        return opponentSymbolId;
    }

    @Override
    public int getBoardSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLegalMove(Move move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Move getKiller(int depth) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setKiller(int depth, Move move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getPlayerPawnsDestroyed() {
        return playerPawnsDestroyed;
    }

    public int getOpponentPawnsDestroyed() {
        return opponentPawnsDestroyed;
    }
    
    private void checkState() {
        
        int s = playerTurn ? playerSymbolId : opponentSymbolId;
        if(!existsAnyMove()) {
            if(s == playerSymbolId)
                gameState = States.OPPONENT_WON;
            else if(s == opponentSymbolId)
                gameState = States.PLAYER_WON;
        }
        
        //if(movesCount >= 100)
          //  gameState = States.DRAW;
    }
    
    public int score() {
        if(this.isPlayerAWinner())
            return 100;
        else if(this.isOppoentAWinner())
            return -100;
        else if(playerTurn)
            return opponentPawnsDestroyed - playerPawnsDestroyed;
        else 
            return playerPawnsDestroyed - opponentPawnsDestroyed;
    }
    
    private int [][] getDeepCopyOfBoard() {
        int[][] copy = new int[boardArray.length][boardArray.length];
        for(int i = 0; i < boardArray.length; i++)
            for(int j = 0; j < boardArray[i].length; j++)
                copy[i][j] = boardArray[i][j];
        return copy;
    }
    
    private void prepareBoard() {
        for(int i = 0; i < N; i+=2) {
            boardArray[0][i] = 1;
            boardArray[1][i+1] = 1;
            boardArray[2][i] = 1;
            
            boardArray[5][i+1] = 2;
            boardArray[6][i] = 2;
            boardArray[7][i+1] = 2;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i= 0; i < N; i++) {
            sb.append(i).append(" ");
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
