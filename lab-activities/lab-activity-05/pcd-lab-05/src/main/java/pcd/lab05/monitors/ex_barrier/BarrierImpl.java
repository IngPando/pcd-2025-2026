package pcd.lab05.monitors.ex_barrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Barrier - to be implemented
 */
public class BarrierImpl implements Barrier {

	private int nParticipant, nArrived;
	private Lock lock;
	private Condition allArrived;

	public BarrierImpl(int nParticipants) {
		this.nParticipant = nParticipants;
		nArrived = 0;
		lock = new ReentrantLock();
		allArrived = lock.newCondition();
	}
	
	@Override
	public void hitAndWaitAll() throws InterruptedException {
		try {
			lock.lock();
			nArrived++;
			if (nArrived < nParticipant){
				while (nArrived < nParticipant){
					allArrived.await();
				}
			} else {
				allArrived.signalAll();
			}
		} finally {
			lock.unlock();
		}



	}

	
}
