package PartI;

import java.util.Stack;

public class BalancedParentheses {

	public static boolean isBalanced(String inString) {
//		return false;
		Stack<Character> stack = new Stack<>();
		
		for (int i=0; i<inString.length(); i++) {
			char c = inString.charAt(i);
			if (c == '(') {
				stack.push(c);
			}
			else if (c == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				else {
					stack.pop();
				}
			}
		}
		
		return stack.isEmpty();
	}
	
	public static void main(String[] args) {
		boolean result = isBalanced("(()()()())");
		System.out.println(result);
		result = isBalanced("(((())))");
		System.out.println(result);
		result = isBalanced("((((((())");
		System.out.println(result);
	}
}
