package PartII;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Consumer implements Runnable {

	public static final int DELAY = 2;
	
	Queue<String> in;
	Map<Character, Integer> out;
	
	public Consumer(Queue<String> in, Map<Character,Integer> out) {
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void run() {
		while (true) {

			// remove element from the queue
			// get the first character of the String you
			// got from the queue, and update the count in the map
			
//			try {
//				Thread.sleep((long) (Math.random()*DELAY));
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//				System.out.println("ending consumer");
//				break;
//			}
			
			String word = null;
			synchronized (in) {
				while (in.isEmpty()) {
					try {
						in.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						System.out.println("Consumer interrupted");
						return;
					}
				}
				word = in.poll();
				if ("END".equals(word)) {
					in.add("END");
					break;
				}
				in.notifyAll();
			}
				
			if (word != null) {
				char firstChar = word.charAt(0);
				synchronized (out) {
					out.put(firstChar, out.getOrDefault(firstChar, 0) + 1);
				}
			}
			
			try {
				Thread.sleep((long)(Math.random()*DELAY));
			} catch (InterruptedException e) {
				// TODO: handle exception
				System.out.println("Consumer sleep interrupted");
				break;
			}
		}
		
	}

}
