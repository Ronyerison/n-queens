package br.ufpi.easii.queens.model;

import java.util.ArrayList;
import java.util.List;


public class Board {
	public int[][] solution;
	public int queen;
	public Board parent;

	public Board(int[][] board, Board parent, int queen) {
		solution = board;
		this.parent = parent;
		this.queen = queen;
	}
	
	public Board(Board clone) {
		this.queen = clone.queen;
		this.solution = new int[clone.solution.length][]; 
		for (int j = 0; j < clone.solution.length; j++) {
			this.solution[j] = clone.solution[j].clone();
		}
		this.parent = clone.parent;
	}

	public Board clone() {
		return new Board(this);
	}

	public boolean isGoal(){
		if(this.queen == solution.length){
			return true;
		}
		return false;
	}
	
	public void solve(int N) {
		if(placeQueens(0, N)){
			//print the result
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(" " + solution[i][j]);
				}
				System.out.println();
			}
		}else{
			System.out.println("NO SOLUTION EXISTS");
		}
	}

	public boolean placeQueens(int queen, int N) {
		// will place the Queens one at a time, for column wise
		if(queen==N){
			//if we are here that means we have solved the problem
			return true;
		}
		for (int row = 0; row < N; row++) {
			// check if queen can be placed row,col
			if (canPlace(solution, row, queen)) {
				// place the queen
				solution[row][queen] = 1;
				// solve  for next queen
				if(placeQueens(queen+1, N)){
					return true;
				}
				//if we are here that means above placement didn't work
				//BACKTRACK
				solution[row][queen]=0;
			}
		}
		//if we are here that means we haven't found solution
		return false;

	}

	public List<Board> expandNeighbors(){
		List<Board> neighbors = new ArrayList<Board>();
		for (int i = 0; i < solution.length; i++) {
			int[][] board = new int[this.solution.length][]; 
			for (int j = 0; j < board.length; j++) {
				board[j] = this.solution[j].clone();
			}
			if (canPlace(board, queen, i)) {
				board[queen][i] = 1;
				neighbors.add(new Board(board, this, this.queen+1));
			}
		}
		return neighbors;
	}
	
	public boolean canPlace(int[][] matrix, int row, int column) {
		
		//verifica coluna
		for (int i = 0; i < row; i++) {
			if (matrix[i][column] == 1) {
				return false;
			}
		}
		
		//verifica diagonal principal
		for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
			if (matrix[i][j] == 1) {
				return false;
			}
		}

		//verifica diagonal secundaria
		for (int i = row, j = column; i >= 0 && j < matrix.length; i--, j++) {
			if (matrix[i][j] == 1) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[i].length; j++) {
				sb.append(solution[i][j] + " ");
				if(i == 0 && j == solution.length-1){
					sb.append("queen=" + queen);
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	

}