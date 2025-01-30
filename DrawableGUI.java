package oopProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawableGUI extends JFrame {
    
	private static final long serialVersionUID = 5578586986738099747L;
	private ArrayList<JComboBox<String>> shapeTypes;
    private ArrayList<JTextField> dimensions;
    private JSpinner sizeSpinner;
    private JPanel shapesPanel;
    private JTextArea resultArea;
    
    public DrawableGUI() {
        shapeTypes = new ArrayList<>();
        dimensions = new ArrayList<>();
        
        // إعداد النافذة الرئيسية
        setTitle("Drawable Shapes Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // لوحة إدخال عدد الأشكال
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Number of Shapes (min 2):"));
        SpinnerNumberModel model = new SpinnerNumberModel(2, 2, 10, 1);
        sizeSpinner = new JSpinner(model);
        topPanel.add(sizeSpinner);
        
        JButton createButton = new JButton("Create Fields");
        createButton.addActionListener(e -> createShapeFields());
        topPanel.add(createButton);
        
        add(topPanel, BorderLayout.NORTH);
        
        // لوحة إدخال الأشكال
        shapesPanel = new JPanel();
        shapesPanel.setLayout(new BoxLayout(shapesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(shapesPanel);
        add(scrollPane, BorderLayout.CENTER);
        
        // منطقة عرض النتائج
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);
        
        // زر الحساب
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateAreas());
        topPanel.add(calculateButton);
        
        // إنشاء الحقول الأولية
        createShapeFields();
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void createShapeFields() {
        shapesPanel.removeAll();
        shapeTypes.clear();
        dimensions.clear();
        
        int size = (Integer) sizeSpinner.getValue();
        
        for (int i = 0; i < size; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel("Shape " + (i + 1) + ":"));
            
            JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Circle", "Cube"});
            shapeTypes.add(typeCombo);
            row.add(typeCombo);
            
            row.add(new JLabel("Dimension:"));
            JTextField dimField = new JTextField(10);
            dimensions.add(dimField);
            row.add(dimField);
            
            shapesPanel.add(row);
        }
        
        pack();
        repaint();
    }
    
    private void calculateAreas() {
        try {
            int size = shapeTypes.size();
            Drawable[] shapes = new Drawable[size];
            
            // إنشاء الأشكال من المدخلات
            for (int i = 0; i < size; i++) {
                String type = (String) shapeTypes.get(i).getSelectedItem();
                double value = Double.parseDouble(dimensions.get(i).getText());
                
                if (value <= 0) {
                    throw new IllegalArgumentException("Dimension must be positive for shape " + (i + 1));
                }
                
                if (type.equals("Circle")) {
                    shapes[i] = new Circle(value);
                } else {
                    shapes[i] = new Cube(value);
                }
            }
            
            // حساب وعرض النتائج
            StringBuilder result = new StringBuilder();
            double totalArea = 0;
            
            for (int i = 0; i < shapes.length; i++) {
                Shape shape = (Shape) shapes[i];
                result.append("Shape ").append(i + 1).append(":\n");
                result.append(shape.howToDraw()).append("\n");
                result.append(shape.toString()).append("\n");
                result.append("Area: ").append(shape.getArea()).append("\n\n");
                totalArea += shape.getArea();
            }
            
            result.append("Total area of all shapes: ").append(totalArea);
            resultArea.setText(result.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for dimensions", 
                                        "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                                        "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }}
