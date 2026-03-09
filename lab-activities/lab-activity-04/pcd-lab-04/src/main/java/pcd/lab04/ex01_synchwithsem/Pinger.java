package pcd.lab04.ex01_synchwithsem;

import java.util.Random;
import java.util.concurrent.Semaphore;

import static java.rmi.server.LogStream.log;

public class Pinger extends ActiveComponent {

	private Semaphore pingEventSem, pongEventSem;
	private Random rand;

	public Pinger(Semaphore pingSem, Semaphore pongSem) {
		this.pingEventSem = pingSem;
		this.pongEventSem = pongSem;
		this.rand = new Random();
	}


	protected void wasteRandomTime(long min, long max){
		try {
			double value = rand.nextDouble();
			double delay = min + value*(max-min);
			sleep((int)delay);
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				pongEventSem.acquire();
				println("ping");
				wasteRandomTime(100,500);

			} catch (InterruptedException ex) {
				log("interrupted");
			}
			finally {
				pingEventSem.release();
			}
		}
	}
}