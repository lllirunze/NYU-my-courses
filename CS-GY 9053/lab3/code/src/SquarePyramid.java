
import java.lang.Math;

public class SquarePyramid {
	
	private static int nextId = 1;	
	private int id;
	private double side;
	private double height;
	
	public SquarePyramid() {
		this.id = nextId;
		nextId++;
		this.side = 0.0;
		this.height = 0.0;
	}
	
	public SquarePyramid(double side, double height) {
		this.id = nextId;
		nextId++;
		this.side = side;
		this.height = height;
	}
	
	public double getSide() {
		return this.side;
	}
	
	public void setSide(double side) {
		this.side = side;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getVolume() {
		// The Volume of a Pyramid is given by (1/3)*(a^2)*H
		double volume = (1.0/3.0) * Math.pow(this.side, 2) * this.height;
		return volume;
	}
	
	public double getSurfaceArea() {
		// l is known at the "slant" and is given by sqrt(h^2+r^2)
		double slant = Math.sqrt(Math.pow(this.height, 2) + Math.pow((this.side/2.0), 2));
		// The surface area of a square pyramid is given by  a^2+ 2a*sqrt(a^2/4+H^2)
		double surface = Math.pow(this.side, 2) + 2.0 * this.side * slant;
		return surface;
	}
	
	public int getID() {
		return this.id;
	}
	
}
