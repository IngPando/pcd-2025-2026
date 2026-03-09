package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {

		// Event Semaphore start always from zero, we initialize one of the semaphore at 1 not like a mutex semaphore but to indicate
		// the one event is already occurred so the thread start if not

		Semaphore pingEventSem = new Semaphore(0,false);
		Semaphore pongEventSem = new Semaphore(1, false); // event semaphore

		new Pinger(pingEventSem,pongEventSem).start();
		new Ponger(pingEventSem,pongEventSem).start();

		//pongEventSem.release();	//if the two semaphore is initialized to zero we need to notify one event to start thread if not we are in deadlock
	}

}
