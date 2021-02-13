package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.Solution;

public class Writer {

	public static void writeSolution(Solution solution, String fileName) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("output/" + fileName);

		out.close();
	}

	public static void writeSolution(Solution solution) throws FileNotFoundException {
		writeSolution(solution, "out.txt");
	}
}
