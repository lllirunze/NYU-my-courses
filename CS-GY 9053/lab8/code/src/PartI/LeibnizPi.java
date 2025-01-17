package PartI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LeibnizPi {
	
	class CalculatePiPerThread implements Runnable {
		
		private final long start;
		private final long end;
		
		public CalculatePiPerThread(long start, long end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public void run() {
			double localSum = 0.0;
			for (long i=start; i<end; i++) {
				localSum += Math.pow(-1, i) * (1.0/((2*i)+1));
			}
			synchronized (LeibnizPi.class) {
				result += localSum;
			}
		}
	
	}

	private double result;
	private int numberOfThreads;
	
	public LeibnizPi() {
		// TODO Auto-generated constructor stub
		this.numberOfThreads = 1;
	}
	
	public LeibnizPi(int numberOfThreads) {
		// TODO Auto-generated constructor stub
		this.numberOfThreads = numberOfThreads;
	}
	
//	public void calculatePi(long steps) {
//		result = 1;
//		for (long i=1;i<steps;i++) {
//			result += Math.pow(-1, i)*(1.0/((2*i)+1));
//		}
//		result *= 4;
//	}
	
	public void calculatePi(long steps) {
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		long stepsPerThread = steps / numberOfThreads;
		
		for (int i=0; i<numberOfThreads; i++) {
			long start = i * stepsPerThread;
			long end = (i+1) * stepsPerThread;
			executorService.execute(new CalculatePiPerThread(start, end));
		}
		
		executorService.shutdown();
		while (!executorService.isTerminated()) {
			
		}
		
		result *= 4;
	}
	
	public static void main(String[] args) {
		
		for (int thread=1; thread<=16; thread*=2) {
			LeibnizPi lpi = new LeibnizPi(thread);
			
			Long startTime = System.currentTimeMillis();
			lpi.calculatePi(1_000_000_000);
			Long endTime = System.currentTimeMillis();
			
//			System.out.println("Processors: " + Runtime.getRuntime().availableProcessors());
			System.out.println("Threads: " + thread);
			System.out.println("result for 1,000,000,000 steps is " + lpi.result);
			System.out.println("error: "+ Math.abs(Math.PI - lpi.result));
			System.out.println("execution time: " + (endTime - startTime) + "ms");
			System.out.println();
		}
		
	}

}
