/**
 * 
 */
package org.ankit.threads.samples;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author yz09
 *
 */
public class ThreadPoolExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// spawnSingleThread();
		// spawnMultipleFixedThreads();
		// spawnMultipleCachedThreads();
		// spawnSingleScheduledThread();
		// checkDiffInScheduleExecutorAndThreadPool();
		checkDiffAtFixedRateAndWithFixedDelay();
	}

	private static void spawnSingleThread() {
		/*
		 * Creates an Executor that uses a single worker thread operating off an
		 * unbounded queue. (Note however that if this single thread terminates
		 * due to a failure during execution prior to shutdown, a new one will
		 * take its place if needed to execute subsequent tasks.) Tasks are
		 * guaranteed to execute sequentially, and no more than one task will be
		 * active at any given time. Unlike the otherwise equivalent
		 * newFixedThreadPool(1) the returned executor is guaranteed not to be
		 * reconfigurable to use additional threads.
		 */
		Executor myExecutor = Executors.newSingleThreadExecutor();
		myExecutor.execute(() -> {

			System.out.println(
					"My Thread Running" + Thread.currentThread().getName());
			for (int i = 100; i < 200; i++) {
				System.out.println("In My own Thread.." + i);
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					System.out.println("Exception..."
							+ Thread.currentThread().isInterrupted());
				}
			}

		});

		for (int i = 0; i < 100; i++) {
			System.out.println("In Main Thread.." + i);
			if (i == 50) {
				ExecutorService service = (ExecutorService) myExecutor;
				service.shutdownNow();
			}
		}

	}

	private static void spawnMultipleFixedThreads() {
		/*
		 * Creates a thread pool that reuses a fixed number of threads operating
		 * off a shared unbounded queue. At any point, at most nThreads threads
		 * will be active processing tasks. If additional tasks are submitted
		 * when all threads are active, they will wait in the queue until a
		 * thread is available. If any thread terminates due to a failure during
		 * execution prior to shutdown, a new one will take its place if needed
		 * to execute subsequent tasks. The threads in the pool will exist until
		 * it is explicitly shutdown.
		 */
		Executor myExecutor = Executors.newFixedThreadPool(2);
		MyLock lock = new MyLock();
		myExecutor.execute(new EvenPrintThread(100, lock));
		myExecutor.execute(new OddPrintThread(100, lock));
		ExecutorService service = (ExecutorService) myExecutor;
		service.shutdown();

	}

	private static void spawnMultipleCachedThreads() {
		/*
		 * Creates a thread pool that creates new threads as needed, but will
		 * reuse previously constructed threads when they are available. These
		 * pools will typically improve the performance of programs that execute
		 * many short-lived asynchronous tasks. Calls to execute will reuse
		 * previously constructed threads if available. If no existing thread is
		 * available, a new thread will be created and added to the pool.
		 * Threads that have not been used for sixty seconds are terminated and
		 * removed from the cache. Thus, a pool that remains idle for long
		 * enough will not consume any resources. Note that pools with similar
		 * properties but different details (for example, timeout parameters)
		 * may be created using ThreadPoolExecutor constructors.
		 */
		ExecutorService myExecutor = Executors.newCachedThreadPool();
		List<Future<String>> returnList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			System.out.println("submitting task.." + i);
			Future<String> result = myExecutor.submit(() -> {
				try {
					System.out.println("Inter status..for task"
							+ Thread.currentThread().getId()
							+ Thread.currentThread().isInterrupted());
					System.out
							.println("Thread.." + Thread.currentThread().getId()
									+ "/....going to sleep");

					MILLISECONDS.sleep(2000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
					System.out.println(
							"Inter caught" + Thread.currentThread().getId()
									+ "...status.." + Thread.interrupted());
				}
				return "I am callable for" + Thread.currentThread().getId();
			});
			returnList.add(result);
		}

		System.out.println("going to shutdown pool..");
		myExecutor.shutdownNow();
		System.out.println("pool shutdown....");

		try {
			for (int i = 3; i < 5; i++) {
				System.out.println("submitting task.." + i);
				Future<String> result = myExecutor.submit(() -> {
					try {
						System.out.println("Inter status..for task.."
								+ Thread.currentThread().getId()
								+ Thread.currentThread().isInterrupted());
						System.out.println(
								"Thread.." + Thread.currentThread().getId()
										+ "/....going to sleep");
						MILLISECONDS.sleep(2000);
					} catch (InterruptedException ex) {
						System.out.println("Inter caught"
								+ Thread.currentThread().getId() + "...status.."
								+ Thread.currentThread().isInterrupted());
					}
					return "I am callable for" + Thread.currentThread().getId();
				});
				returnList.add(result);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println("going to get result..");
		returnList.forEach((item) -> {
			try {
				System.out.println("Result.." + item.get());
			} catch (ExecutionException e) {
				e.printStackTrace();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

	}

	private static void checkDiffAtFixedRateAndWithFixedDelay() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

		executor.scheduleAtFixedRate(() -> {
			System.out.println("My Thread running");
			for (int i = 0; i < 4; i++) {
				System.out.println(i);
				try {
					MILLISECONDS.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} , 5, 1, TimeUnit.SECONDS);

		/*
		 * executor.scheduleWithFixedDelay(() -> { System.out.println(
		 * "My Thread running"); for (int i = 0; i < 4; i++) {
		 * System.out.println(i); try { MILLISECONDS.sleep(2000); } catch
		 * (Exception e) { e.printStackTrace(); } } } , 5, 10,
		 * TimeUnit.SECONDS);
		 */
	}

	private static void checkDiffInScheduleExecutorAndThreadPool() {

		/*
		 * in this case once 1st thread finish then only second will execute as
		 * we have only 1 worker thread ,so for second thread delay does not
		 * matter.
		 */
		// ScheduledExecutorService executor =
		// Executors.newSingleThreadScheduledExecutor();

		/*
		 * In this case the second thread will execute after 15 second while
		 * first thraed is being run, as we have 2 pool size executor
		 */
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

		executor.schedule(() -> {
			System.out.println("Started running...1");
			for (int i = 0; i < 1000000; i++) {
				System.out.println(i);
				try {
					MILLISECONDS.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} , 10, TimeUnit.SECONDS);

		executor.schedule(() -> {
			System.out.println("I am another thread");
		} , 12, TimeUnit.SECONDS);

		executor.schedule(() -> {
			System.out.println("I am another thread 2");
		} , 15, TimeUnit.SECONDS);

		executor.shutdown();

	}

	private static void spawnSingleScheduledThread() {

		ScheduledExecutorService executor =
				Executors.newSingleThreadScheduledExecutor();

		/*
		 * ScheduledExecutorService executor =
		 * Executors.newScheduledThreadPool(2);
		 */
		/*
		 * executor.schedule(() -> { System.out.println("Beep.Once.."); } , 1,
		 * TimeUnit.SECONDS);
		 * 
		 * executor.scheduleAtFixedRate(() -> { System.out.println("Beep..."); }
		 * , 10, 10, TimeUnit.SECONDS);
		 */

		final ScheduledFuture<?> handle = executor.schedule(() -> {
			System.out.println(
					"You have not done any activity..so logging you out..");
		} , 120, TimeUnit.SECONDS);

		executor.schedule(() -> {
			System.out.println("Activity done...so cacnelling timer");
			// handle.cancel(true);
			System.out.println("Scheduling a 2 min timer again...");
			executor.schedule(() -> {
				System.out.println(
						"You have not done any activity..so logging you out..");
			} , 120, TimeUnit.SECONDS);
		} , 130, TimeUnit.SECONDS);

		try {
			executor.awaitTermination(250, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Ineruppt while awaitTermination");
		}

		System.out.println("Shutdown starts..");
		executor.shutdown();
		System.out.println("Shutdown completes..");

	}

}

class MyLock {
	private boolean flag;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}

class EvenPrintThread implements Runnable {

	private int numberLimit;
	private MyLock lock;

	public EvenPrintThread(int numberLimit, MyLock lock) {
		this.lock = lock;
		this.numberLimit = numberLimit;
	}

	@Override
	public void run() {
		int counter = 0;
		while (counter < numberLimit) {
			synchronized (lock) {
				while (lock.isFlag()) {
					try {
						lock.wait();
					} catch (InterruptedException ex) {

					}
				}

				if (counter % 2 == 0) {
					System.out.println("EvenPrintThread.." + counter);
				}
				lock.setFlag(true);
				counter++;
				lock.notifyAll();
			}
		}
	}

}

class OddPrintThread implements Runnable {

	private int numberLimit;
	private MyLock lock;

	public OddPrintThread(int numberLimit, MyLock lock) {
		this.numberLimit = numberLimit;
		this.lock = lock;
	}

	@Override
	public void run() {
		int counter = 0;
		while (counter < numberLimit) {
			synchronized (lock) {
				while (!lock.isFlag()) {
					try {
						lock.wait();
					} catch (InterruptedException ex) {

					}
				}

				if (counter % 2 != 0) {
					System.out.println("OddPrintThread..." + counter);
				}
				lock.setFlag(false);
				counter++;
				lock.notifyAll();
			}
		}
	}

}