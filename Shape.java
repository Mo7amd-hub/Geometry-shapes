package oopProject;

public abstract class Shape implements Drawable {
	private java.util.Date dateCreated ;
	private String color;
	
	public Shape(String color) {
        this.color=color;
    }
	
	public String getColor() {
		return color;
	}

	public Shape() {
		super();
	}

	public java.util.Date getDateCreated() {
		return dateCreated;
	}
	
	public abstract double getArea();
    public abstract double getPerimeter();

	@Override
	public String howToDraw() {
		return "draw shape with color:"+color;
	}
	
}
