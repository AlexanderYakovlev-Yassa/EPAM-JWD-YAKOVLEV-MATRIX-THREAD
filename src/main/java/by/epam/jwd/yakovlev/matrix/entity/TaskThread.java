package by.epam.jwd.yakovlev.matrix.entity;

import java.util.concurrent.CyclicBarrier;

public class TaskThread extends Thread {

	private int threadID;
	private int sum;
	private CyclicBarrier barier;

	public TaskThread(int id, CyclicBarrier barier) {
		super();
		this.threadID = id;
		this.barier = barier;
		this.sum = 0;
	}

	public int getThreadID() {
		return threadID;
	}

	public int getSum() {
		return sum;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
	

}
