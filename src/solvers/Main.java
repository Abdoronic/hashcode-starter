package solvers;

import java.io.FileNotFoundException;

import io.Parser;
import io.ScoreCalculator;
import io.Writer;
import model.Problem;
import model.Solution;

public class Main {
	
	static int chosenInput = 0; // change number of input as needed
	
	static String[] fileNames = {};
	static String inputExtension = "", outputExtension = ".txt";

	public static void main(String[] args) throws FileNotFoundException {
		
		Problem problem = Parser.parse(fileNames[chosenInput] + inputExtension);

		Solver solver = new BasicSolver(); // change type of solver as needed
		Solution solution = solver.solve(problem);

		long score = ScoreCalculator.calculateScore(solution);
		System.out.println(score);

		Writer.writeSolution(solution, fileNames[chosenInput] + outputExtension);

	}
}
