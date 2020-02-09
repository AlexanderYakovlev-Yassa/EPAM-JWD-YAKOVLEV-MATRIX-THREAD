package by.epam.jwd.yakovlev.matrix.entity;

public class Free implements State {

	private static final State INSTANCE = new Free();

	private final String status = "free";

	private Free() {
	}

	@Override
	public String getStatus() {
		return status;
	}

	public static State getInstance() {
		return INSTANCE;
	}

	

	@Override
	public void block(Matrix matrix) {

		matrix.setState(InProcess.getInstance());
	}

	@Override
	public void release(Matrix matrix) {
		// TODO Auto-generated method stub
		
	}
}
