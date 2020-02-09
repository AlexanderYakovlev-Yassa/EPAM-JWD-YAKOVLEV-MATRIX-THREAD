package by.epam.jwd.yakovlev.matrix.entity;

public interface State {

	String getStatus();
	void block(Matrix matrix);
	void release(Matrix matrix);
}
