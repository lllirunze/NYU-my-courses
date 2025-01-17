import java.util.Scanner;

public class CharacterFrequency {

	public static char charFrequency(String s) {
		int[] frequency = new int[26];
		char result = '\0';
		int maxFrequency = 0;
		for (int i=s.length()-1; i>=0; i--) {
			char c = s.charAt(i);
			frequency[c-'a']++;
			if (frequency[c-'a'] >= maxFrequency) {
				result = c;
				maxFrequency = frequency[c-'a'];
			}
		}
		return result;
	}
	
	public static String randomStringGenerator(int chars) {
		char[] charArray = new char[chars];
		for (int i=0;i<charArray.length;i++) {
			char c = (char)((int)(Math.random()*26) + 'a');
			charArray[i] = c;
		}
		return new String(charArray);
	}
	
	public static void main(String[] args) {
		String s = "abcdeapapqarr";
		
		// Create arbitrary strings to test out your charFrequency method.
		
//		Scanner input = new Scanner(System.in);
//    	System.out.print("Enter the length of a string: ");
//    	int n = input.nextInt();
//    	if(n <= 0) {
//    		System.out.println("The length of the string need to be greater than 0");
//    		return;
//    	}
//		String s = randomStringGenerator(n);
		
		char result = charFrequency(s);
//		System.out.println(s);
		System.out.println(result);
	}
}
