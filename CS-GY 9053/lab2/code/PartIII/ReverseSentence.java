
public class ReverseSentence {

	public static String reverseSentence(String sentence) {
		String[] strs = sentence.split(" ");
		int n = strs.length;
		String tempString = strs[0].substring(0, 1).toLowerCase() + strs[0].substring(1);
		strs[0] = tempString;
		tempString = strs[n-1].substring(0, 1).toUpperCase() + strs[n-1].substring(1);
		strs[n-1] = tempString;
		for (int i=0; i<n/2; i++) {
			tempString = strs[i];
			strs[i] = strs[n-1-i];
			strs[n-1-i] = tempString;
		}
		String result = String.join(" ", strs);
		return result;
	}
	
	public static void main(String[] args) {
		String result = reverseSentence("The quick brown fox jumps over the lazy dog");
		System.out.println(result);
	}

}
