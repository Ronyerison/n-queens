package br.ufpi.easii.queens.solve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ufpi.easii.queens.model.Board;

public class Solver {
	private Board boardInicial;
	private List<Board> solution;
	public int count;

	
	public Solver(Board inicial) {
		this.boardInicial = inicial;
		this.solution = new ArrayList<Board>();
		this.count = 0;
	}
	
	public List<Board> greedy(){
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			greedySort(front);
			actual = front.get(0).clone();
			front.remove(0);
			count++;
		}
		
		if(actual.isGoal()){
			while(actual.parent != null){
				this.solution.add(actual);
				actual = actual.parent;
			}
			solution.add(boardInicial);
			return solution;
		}
		return null;
	}
	
	public List<Board> aStar(){
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			sort(front);
			actual = front.get(0).clone();
			front.remove(0);
			count++;
		}
		
		if(actual.isGoal()){
			while(actual.parent != null){
				this.solution.add(actual);
				actual = actual.parent;
			}
			solution.add(boardInicial);
			return solution;
		}
		return null;
	}
	
	public List<Board> breadthSearch(){
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			actual = front.get(0).clone();
			front.remove(0);
			count++;
		}
		
		if(actual.isGoal()){
			while(actual.parent != null){
				this.solution.add(actual);
				actual = actual.parent;
			}
			solution.add(boardInicial);
			return solution;
		}
		return null;
	}
	
	public List<Board> depthSearch(){
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			actual = front.get(front.size()-1).clone();
			front.remove(front.size()-1);
			count++;
		}
		
		if(actual.isGoal()){
			while(actual.parent != null){
				this.solution.add(actual);
				actual = actual.parent;
			}
			solution.add(boardInicial);
			return solution;
		}
		return null;
	}
	public void sort(List<Board> boards){
		Collections.sort(boards, new Comparator<Board>() {
			@Override
			public int compare(Board o1, Board o2) {
				if(Integer.valueOf(o2.queen).compareTo(Integer.valueOf(o1.queen)) == 0){
					Integer size1 = o1.expandNeighbors().size();
					Integer size2 = o2.expandNeighbors().size();
					return size1.compareTo(size2); 
				}else{
					return Integer.valueOf(o2.queen).compareTo(Integer.valueOf(o1.queen));
				}
			}
		});
	}
	
	public void greedySort(List<Board> boards){
		Collections.sort(boards, new Comparator<Board>() {
			@Override
			public int compare(Board o1, Board o2) {
				Integer size1 = o1.expandNeighbors().size();
				Integer size2 = o2.expandNeighbors().size();
				return size1.compareTo(size2); 
			}
		});
	}
	
	public List<Board> getSolution(){
		return this.solution;
	}
	
	public Board getBoardInicial() {
		return boardInicial;
	}
}
