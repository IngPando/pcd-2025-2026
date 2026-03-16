package pcd.lab05.monitors.ex_latch;

/*
 * Latch - to be implemented
 */
public class LatchImpl implements Latch {

	private int nCountdowns;
	private int nCounts;

	public LatchImpl(int nCountdowns) {
		this.nCountdowns = nCountdowns;
		this.nCounts = 0;
	}

	@Override
	public synchronized void countDown() {
		nCounts++;
		if (nCounts == nCountdowns){
			notifyAll();
		}
	}

	@Override
	public synchronized void await() throws InterruptedException {
		while(nCounts < nCountdowns){
			wait();
		}
	}


}
