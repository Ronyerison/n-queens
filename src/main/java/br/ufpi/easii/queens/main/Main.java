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
		List<Board> solutions = solver.solv();
		System.out.println("Nós Visitados: " + solver.count);
		for (int i = solutions.size() - 1; i >= 0; i--) {
			System.out.println(solutions.get(i).toString());
		}

	}

}
