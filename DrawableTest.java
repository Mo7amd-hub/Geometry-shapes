package oopProject;
import java.util.*;
import javax.swing.SwingUtilities;

public class DrawableTest {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
            new DrawableGUI().setVisible(true);
        });
		
		if (args.length <= 3 || args.length % 2 == 0) {
            System.out.println("[input format should be like:\n numberOfShapes typeOfShape1 value1 typeOfShape2 value2 ..etc]\n and array size must be at least 2");
            return;
        }
		
		int size = Integer.parseInt(args[0]);
        if (size < 2) {
            System.out.println("The array size must be at least 2");
            return;
        }
        
        Drawable[] input = new Drawable[size];
        int index = 0;

        for (int i = 1; i < args.length; i += 2) {
            String type = args[i];
            double value = Double.parseDouble(args[i + 1]);
            if(value<=0) {
            	System.out.println("the value should be positive");
            	return;
            }

            if (type.equalsIgnoreCase("circle")) {
                input[index++] = new Circle(value);
            }
             else if (type.equalsIgnoreCase("cube")) {
                input[index++] = new Cube(value);
            } else {
                System.out.println("Unknown type: " + type);
                return;
            }

            if (index == size) {
                break;
            }
        }

        double totalArea = 0;
        for (Drawable drawable : input) {
            Shape shape = (Shape) drawable;
            String caption=shape.howToDraw()+"\n"+shape.toString();
            System.out.println(caption);
            totalArea += shape.getArea();
        }
        System.out.println("Total area of all Drawable objects: " + totalArea);
	}

}
