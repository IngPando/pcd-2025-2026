package pcd.lab05.monitors.ex_latch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Latch - to be implemented
 */
public class LatchImpl2 implements Latch {

	private int nCountdowns;
	private int nCounts;
	private Lock lock;
	private Condition allCountsDone;

	public LatchImpl2(int nCountdowns) {
		this.nCountdowns = nCountdowns;
		this.nCounts = 0;
		lock = new ReentrantLock();
		allCountsDone = lock.newCondition();
	}

	@Override
	public void countDown() {
		try{
			lock.lock();
			nCounts++;
			if (nCounts == nCountdowns){
				allCountsDone.signalAll();
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void await() throws InterruptedException {
		try{
			lock.lock();
			while(nCounts < nCountdowns){
				allCountsDone.await();
			}
		} finally {
			lock.unlock();
		}
	}


}
