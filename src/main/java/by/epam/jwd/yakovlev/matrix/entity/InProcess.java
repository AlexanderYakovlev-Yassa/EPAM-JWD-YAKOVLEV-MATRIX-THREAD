package by.epam.jwd.yakovlev.matrix.entity;

public class InProcess implements State {

	private static final State INSTANCE = new InProcess();

	private final String status = "inProcess";

	private InProcess() {
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
	}

	@Override
	public void release(Matrix matrix) {

		matrix.setState(Free.getInstance());
	}

}
