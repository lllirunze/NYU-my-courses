
import java.util.*;

public class test {
	
	public static List<String> f(List<String> strs) {
		List<String> res = new ArrayList<String>();
		if (strs.size() == 0) return res;
		String tmpString = strs.get(0);
		int count = 0;
		for (String str : strs) {
			if (str.equals(tmpString)) {
				count++;
			}
			else {
				if (count < 2) {
					res.add(tmpString);
				}
				else {
					res.add(tmpString);
					res.add(String.valueOf(count));
				}
				tmpString = str;
				count = 1;
			}
		}
		if (count < 2) {
			res.add(tmpString);
		}
		else {
			res.add(tmpString);
			res.add(String.valueOf(count));
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		
	}
}
