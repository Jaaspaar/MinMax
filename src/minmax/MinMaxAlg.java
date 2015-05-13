/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Łukasz
 */
public class MinMaxAlg {
    
    public static int minmax(GameInterface game) {
        if(game.isOver())
            return score(game);
        
        ArrayList<Integer> scoreList = new ArrayList<>();
        ArrayList<Move> movesList;
        movesList = (ArrayList<Move>) game.getAllAvaibleMoves();
        
        for(Move m : movesList) {
            GameInterface possibleGame = game.getStateFromMove(m);
            scoreList.add(minmax(possibleGame));
        }
  
        if(game.isPlayerTurn()) {
            int maxScoreIndex = getIndexOfLargest(scoreList);//scoreList.size() - 1;
            game.setNextSuggestedMove(movesList.get(maxScoreIndex));
            return scoreList.get(maxScoreIndex);
        }
        else {
            int minScoreIndex = getIndexOfSmallest(scoreList);
            game.setNextSuggestedMove(movesList.get(minScoreIndex));
            return scoreList.get(minScoreIndex);
        }
            
    }
    
     public static int minmax(GameInterface game, int depth) {
        if(game.isOver() || depth == 0)
            return score(game);
        
        ArrayList<Integer> scoreList = new ArrayList<>();
        ArrayList<Move> movesList;
        movesList = (ArrayList<Move>) game.getAllAvaibleMoves();
        
        for(Move m : movesList) {
            GameInterface possibleGame = game.getStateFromMove(m);
            scoreList.add(minmax(possibleGame, (depth-1)));
        }
  
        if(game.isPlayerTurn()) {
            int maxScoreIndex = getIndexOfLargest(scoreList);//scoreList.size() - 1;
            game.setNextSuggestedMove(movesList.get(maxScoreIndex));
            return scoreList.get(maxScoreIndex);
        }
        else {
            int minScoreIndex = getIndexOfSmallest(scoreList);
            game.setNextSuggestedMove(movesList.get(minScoreIndex));
            return scoreList.get(minScoreIndex);
        }
            
    }
    
    public static int minmax(GameInterface game, int alpha, int beta) {
        if(game.isOver())
            return score(game);
        
        ArrayList<Move> movesList;
        movesList = (ArrayList<Move>) game.getAllAvaibleMoves();
        int moveIndex = 0;
        int i = 0;
        
        int score;
        for(Move m : movesList) {
            GameInterface possibleGame = game.getStateFromMove(m);
            score = minmax(possibleGame, alpha, beta);
            if(game.isPlayerTurn()) {
                if(score > alpha) {
                    alpha = score;
                    moveIndex = i;
                }
                if(alpha >= beta)
                    break;
            }
            else {
                if(score < beta) {
                    beta = score;
                    moveIndex = i;
                }
                if(alpha >= beta)
                    break;
            }
            i++;
        }
        
        game.setNextSuggestedMove(movesList.get(moveIndex));
        
        return game.isPlayerTurn() ? alpha : beta;
  
            
    }
    
    public static int minmax(GameInterface game,int depth, int alpha, int beta) {
        if(game.isOver() || depth == 0)
            return score(game);
        
        ArrayList<Move> movesList;
        movesList = (ArrayList<Move>) game.getAllAvaibleMoves();
        int moveIndex = 0;
        int i = 0;
        
        int score;
        for(Move m : movesList) {
            GameInterface possibleGame = game.getStateFromMove(m);
            score = minmax(possibleGame, (depth-1), alpha, beta);
            if(game.isPlayerTurn()) {
                if(score > alpha) {
                    alpha = score;
                    moveIndex = i;
                }
                if(alpha >= beta)
                    break;
            }
            else {
                if(score < beta) {
                    beta = score;
                    moveIndex = i;
                }
                if(alpha >= beta)
                    break;
            }
            i++;
        }
        
        game.setNextSuggestedMove(movesList.get(moveIndex));
        
        return game.isPlayerTurn() ? alpha : beta;
  
            
    }
    
    public static int minmax(GameInterface game,int depth,
                int recurrency, int alpha, int beta) {
        if(game.isOver() || depth == 0)
            return score(game, recurrency);
        
        ArrayList<Move> movesList;
        movesList = (ArrayList<Move>) game.getAllAvaibleMoves();
        int moveIndex = 0;
        int i = 0;
        
        int score;
        for(Move m : movesList) {
            GameInterface possibleGame = game.getStateFromMove(m);
            score = minmax(possibleGame, (depth-1), ++recurrency, alpha, beta);
            if(game.isPlayerTurn()) {
                if(score > alpha) {
                    alpha = score;
                    moveIndex = i;
                }
                if(alpha >= beta)
                    break;
            }
            else {
                if(score < beta) {
                    beta = score;
                    moveIndex = i;
                }
                if(alpha >= beta)
                    break;
            }
            i++;
        }
        
        game.setNextSuggestedMove(movesList.get(moveIndex));
        
        return game.isPlayerTurn() ? alpha : beta;
  
            
    }
    
    private static int score(GameInterface game) {
        if(game.isPlayerAWinner())
            return 10;
        else if(game.isOppoentAWinner())
            return -10;
        else
            return 0;
    }
    
    private static int score(GameInterface game, int recurrency) {
        if(game.isPlayerAWinner())
            return 10 - recurrency;
        else if(game.isOppoentAWinner())
            return recurrency -10;
        else
            return 0;
    }
    
    private static int getIndexOfLargest(List<Integer> list) {
        int indexOfLargest = 0;
        for(int i = 0; i < list.size(); i++)
            if(list.get(i) > list.get(indexOfLargest))
                indexOfLargest = i;
        return indexOfLargest;
    }
    
    private static int getIndexOfSmallest(List<Integer> list) {
        int indexOfSmallest = 0;
        for(int i = 0; i < list.size(); i++)
            if(list.get(i) < list.get(indexOfSmallest))
                indexOfSmallest = i;
        return indexOfSmallest;
    }
    
}
