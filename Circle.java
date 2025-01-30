package oopProject;

public class Circle extends Shape {
	private double raduis;

	public Circle(double r) {
		this.raduis=r;
	}

	public Circle(String color,double r) {
		super(color);
		this.raduis=r;
	}
	
	public void setRaduis(double raduis) {
		this.raduis = raduis;
	}
	
	public double getRaduis() {
		return raduis;
	}

	@Override
	public double getArea() {
		return Math.PI*raduis;
	}

	@Override
	public double getPerimeter() {
		return 2*Math.PI*raduis;
	}

	@Override
	public String toString() {
		return "Circle [raduis=" + raduis + "]";
	}

	@Override
	public String howToDraw() {
		return "draw a circle with raduis: "+raduis;
	}
	
	
}
