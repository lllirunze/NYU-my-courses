
public class EstimatePi {
	
	public static double x = 0.0;
	
	public static int estimatePi() {
		int iterations = 0;
//		double sum = Math.PI * Math.PI / 6.0;
//		while (Math.abs(sum - x) >= 0.0001) {
		while (Math.abs(Math.PI - Math.sqrt(6 * x)) >= 0.0001) {
			iterations++;
			x = x + 1.0/(iterations * iterations);
		}
		
		return iterations;
	}
	
	public static void main(String[] args) {
		int y = estimatePi();
//		System.out.println("Pi is estimated as " + x + " after " + y + " iterations");
		System.out.println("Pi is estimated as " + Math.sqrt(6 * x) + " after " + y + " iterations");
	}
	
}
