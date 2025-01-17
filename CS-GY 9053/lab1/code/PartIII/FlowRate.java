public class FlowRate {

	public static double calculateFlowRate(double pressureChange, double radius, double dyanmicViscosity, double length) {

		double pi = Math.PI;
		
		// Q will be the flow rate in m3/s of water
		double result = (pressureChange * pi * Math.pow(radius, 4)) / (8 * dyanmicViscosity * length);

		// Convert Q to liters/sec
		return result * 1000;
	}
	
	public static void main(String[] args) {
		double radius = .0127;
		double length = 5;
		double eta = 8.9E-4;
		double pressureChange = 22000;
		double dyanmicViscosity = 8.9E-4;
		
		
//		double flowRate = calculateFlowRate();
		double flowRate = calculateFlowRate(pressureChange, radius, dyanmicViscosity, length);
		System.out.println("The flow rate in liters/sec is: " + flowRate);

	}
}