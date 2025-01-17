package PartIII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortFrequency {

	public static void sortByFrequency(ArrayList<Integer> ar) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : ar) {
			map.put(num, map.getOrDefault(num, 0)+1);
		}
		Collections.sort(ar, (a, b) -> {
			if (map.get(a) == map.get(b)) {
				return (a-b);
			}
			return map.get(a)-map.get(b);
		});
		return;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> ar = new ArrayList<Integer>();
		for (int i=0;i<100;i++) {
			ar.add((int)(Math.random()*10));			
		}
//		ar.add(1);
//		ar.add(2);
//		ar.add(2);
//		ar.add(1);
//		ar.add(1);
//		ar.add(1);
//		ar.add(5);
		System.out.println(ar.toString());
		sortByFrequency(ar);
		System.out.println(ar.toString());
	}
}
