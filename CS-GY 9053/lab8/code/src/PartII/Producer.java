package PartII;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {

	public static final int DELAY = 2;
	public static final int MAX_QUEUE_SIZE = 50;
	
	Queue<String> out;
	ArrayList<String> words;
	
	public Producer(Queue<String> out) {
		this.out = out;
		words = new ArrayList<String>();
		try {
			FileReader f = new FileReader("wordlist.10000.txt");
			BufferedReader r = new BufferedReader(f);
			String inLine = r.readLine();
			while (inLine != null) {
				words.add(inLine);
				inLine = r.readLine();
			}
			Collections.shuffle(words);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}
	
	@Override
	public void run() {
//		for (int i=0;i<words.size();i++) {
//			
//			String obj = words.get(i);
//			
//			// put data on the queue and don't overflow
//			// the queue size
//			
//			if ((i+1) % 1000 == 0) {
//				System.out.println("put " + (i+1) + " elements on queue" );
//			}
//			try {
//				Thread.sleep((long) (Math.random()*DELAY));
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		for (String word : words) {
			synchronized (out) {
				while (out.size() == MAX_QUEUE_SIZE) {
					try {
						out.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						System.out.println("Producer was interrupted");
						return;
					}
				}
				out.add(word);
				out.notifyAll();
			}
			try {
				Thread.sleep((long) (Math.random()*DELAY));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		synchronized (out) {
			out.add("END");
			out.notifyAll();
		}
		
		
	}
	
}
