package by.epam.jwd.yakovlev.matrix.entity;

import java.util.ArrayList;
import java.util.HashSet;

public class Matrix {

	private int matrixDimension;
	private HashSet<Cell> matrix;

	public Matrix(int matrixDimension) {
		this.matrixDimension = matrixDimension;
	}

	public HashSet<Cell> getRowSet(int row){

		HashSet<Cell> res = new HashSet<>();

		for (Cell c : matrix){
			if (c.getLocation().y == row){
				res.add(c);
			}
		}
		return res;
	}

	public HashSet<Cell> getColumnSet(int column){

		HashSet<Cell> res = new HashSet<>();

		for (Cell c : matrix){
			if (c.getLocation().x == column){
				res.add(c);
			}
		}
		return res;
	}

	public int getMatrixDimension() {
		return matrixDimension;
	}
}
