package br.ufpi.easii.queens.main;

import java.util.List;

import br.ufpi.easii.queens.model.Board;
import br.ufpi.easii.queens.solve.Solver;

public class Main {

	public static void main(String[] args) {
		int[][] matriz = {{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};
		Board q = new Board(matriz, null, 0);
		Solver solver = new Solver(q);
		List<Board> solutions = solver.breadthSearch();
		long sum = 0;
		
		for (int i = 0; i < 10; i++) {
			solver = new Solver(q);
			long start = System.currentTimeMillis();
			solutions = solver.depthSearch();
			long end = System.currentTimeMillis();
			sum += end - start;
		}
		
		System.out.println("Tempo médio de Execução: " + (sum / 10)
				+ " milisegundos");
	    System.out.println("Max Front: " + solver.maxFront);
	    System.out.println("Número de Visitados: " + solver.count);
	    
		for (int i = solutions.size() - 1; i >= 0; i--) {
			System.out.println(solutions.get(i).toString());
		}

	}

}
