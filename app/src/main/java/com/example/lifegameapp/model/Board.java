package com.example.lifegameapp.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    int width;
    int height;
    HashMap<Integer, Tuple> coordPos;

    boolean[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = createBoard();
        this.coordPos = getMapCoordinates();
    }

    public HashMap<Integer, Tuple> getCoordPos() {
        return coordPos;
    }

    public Board() {
        this.width = 20;
        this.height = 20;
        this.board = createBoard();
        this.coordPos = getMapCoordinates();
    }

    public boolean[][] createBoard(){
        return new boolean[width][height];
    }
    //toDO boundaries
    public void updateWorld(){

        boolean[][] newMatrix = copyMatrix(board);
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                if(board[i][j]) {
                    int countN = checkNeighbours(i, j);
                    if (countN < 2 | countN > 3) {
                        newMatrix[i][j] = false;
                    }
                }
                else{
                    int n = checkNeighbours(i,j);
                    if(n==3) newMatrix[i][j]=true;
                }

            }
        }

        board = newMatrix;
    }

    private boolean[][] copyMatrix(boolean[][] board) {
        boolean[][] matrix = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                matrix[i][j] = board[i][j];
            }
        }
        return matrix;
    }


    public int checkNeighbours(int x, int y){
        int counter = 0;
        for (int i = -1; i < 2 ; i++) {
            for (int j = -1; j < 2 ; j++) {
                if(i==0 & j==0) continue;
                if(checkBounds(x, y, i, j)) {
                    if (board[x + i][y + j]) counter++;
                }
            }
        }
        return counter;



    }

    private boolean checkBounds(int x, int y, int i, int j) {
        int expI = Math.abs(i);
        int expJ = Math.abs(j);
        if(x+expI>=width | y+expJ>=height) return false;
        return !(x - expI < 0 | y - expJ < 0);
    }

    //TODO init board

    public void initWorld(ArrayList<Tuple> initPositions){

        for (Tuple tuple: initPositions
             ) {
            int i = tuple.getX();
            int j = tuple.getY();
            board[i][j] = true;
        }
    }

    public void printBoard(){

        System.out.println();
        System.out.println();
        int i, j;
        for(i=0; i<width; i++)
        {
            for(j=0; j<height; j++)
            {
                if(board[i][j])
                    System.out.print("+");
                else
                    System.out.print("-");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private boolean decide(int i, int j) {
        int neighbors = 0;
        if(j > 0) {
            if(board[i][j-1])
                neighbors++;
            if(i>0) if(board[i-1][j-1])
                neighbors++;
            if(i<height-1) if(board[i+1][j-1])
                neighbors++;
        }
        if(j < width-1) {
            if(board[i][j+1])
                neighbors++;
            if(i>0) if(board[i-1][j+1])
                neighbors++;
            if(i<height-1) if(board[i+1][j+1])
                neighbors++;
        }
        if(i>0) if(board[i-1][j])
            neighbors++;
        if(i<height-1) if(board[i+1][j])
            neighbors++;
        if(neighbors == 3) return true;
        if(board[i][j] && neighbors == 2) return true;
        return false;
    }


    public int[] getPositions(){
        int[] positions = new int[400];
        int count=0;
        int index = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                positions[index]= count;
                count++;
                index++;
            }
        }
        return positions;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public ArrayList<Tuple> getAliveCells(){
         ArrayList<Tuple> aliveCells = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(board[i][j]) aliveCells.add(new Tuple(i, j));
            }

        }

        return aliveCells;
    }

    public HashMap<Integer, Tuple> getMapCoordinates(){

        HashMap<Integer, Tuple> coordPos= new HashMap<>();
        int count = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                coordPos.put(count, new Tuple(i, j));
                count++;
            }
        }
        return coordPos;
    }

    public void clearBoard() {
        board = createBoard();
    }
}


