package by.epam.jwd.yakovlev.matrix.entity;

public class Matrix {

	private static final Matrix INSTANCE = new Matrix();

	private int[][] matrix;
	private State state = Free.getInstance();

	private Matrix() {
	}

	public static Matrix getInstance() {
		return INSTANCE;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
