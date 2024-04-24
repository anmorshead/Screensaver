
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawingBoard extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Shape> clickAddedShapes = new ArrayList<>();
    private int nextShapeIndex = 0; // Index to keep track of the next shape to return
    private int shapesAddedCount = 0; // Counter to keep track of how many shapes have been added
    private Timer timer = new Timer(10,new TimerAction());

    public DrawingBoard() {
        //make all shapes
        Circle firstCircle = new Circle(50,50,Color.RED,20);
        Circle secondCircle = new Circle(250,300,Color.CYAN,50);
        Circle thirdCircle = new Circle(750,500,Color.MAGENTA,60);
        Square firstSquare = new Square(800,50,Color.GREEN,20,20);
        Square secondSquare = new Square(150,700,Color.BLUE,30,30);
        Square thirdSquare = new Square(500,500,Color.PINK,40,40);
        Triangle firstTriangle = new Triangle(760,60,Color.YELLOW,new int[]{getX(), getX()+50, getX()}, new int[]{getY(), getY()+50, getY()+100},3);
        Triangle secondTriangle = new Triangle(30,390,Color.GRAY,new int[]{getX(), getX()+100, getX()+50}, new int[]{getY(), getY(), getY()+100},3);
        Triangle thirdTriangle = new Triangle(600,700,Color.ORANGE,new int[]{getX(), getX()+70, getX()+140}, new int[]{getY(), getY()+100, getY()},3);
        Pentagon firstPentagon = new Pentagon(760, 60, Color.WHITE, new int[]{getX(), getX()+30, getX()+40, getX()+20, getX()}, new int[]{getY(), getY()+20, getY()+30, getY()+50, getY()+50}, 5);
        Pentagon secondPentagon = new Pentagon(600, 700, Color.RED, new int[]{getX(), getX()+100, getX()+150, getX()+50, getX()}, new int[]{getY(), getY(), getY()+50, getY()+70, getY()+30}, 5);
        Pentagon thirdPentagon = new Pentagon(400, 400, Color.CYAN, new int[]{getX(), getX()+50, getX()+70, getX()+30, getX()}, new int[]{getY(), getY()+30, getY()+70, getY()+70, getY()+30}, 5);

        //add all shapes to array
        shapes.add(firstCircle);
        shapes.add(firstSquare);
        shapes.add(firstTriangle);
        shapes.add(firstPentagon);
        shapes.add(secondCircle);
        shapes.add(secondSquare);
        shapes.add(secondTriangle);
        shapes.add(secondPentagon);
        shapes.add(thirdCircle);
        shapes.add(thirdSquare);
        shapes.add(thirdTriangle);
        shapes.add(thirdPentagon);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //When mouse is clicked on panel, call method that includes timer
                clickForShape(e);
            }
        });
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //tell the jpanel to redraw

        //draw all shapes in the clickAddedShape list here
        for (Shape addedShapes : clickAddedShapes) { //for each loop to draw all shapes in arraylist
            addedShapes.drawShape(g);
        }

    }
    public Shape getNextShape() {
        if (shapesAddedCount >= shapes.size()) {
            // If all shapes have been added, return null to indicate no more shapes should be added
            return null;
        }
        // Get the next shape from the list based on the nextShapeIndex
        Shape nextShape = shapes.get(nextShapeIndex);

        nextShapeIndex++;// Increment the index for nextShape
        shapesAddedCount++;// Increment the count of shapesAdded

        return nextShape;
    }

    private class TimerAction implements ActionListener{//implement is a relationship between classes(inheriting from actionListener)
        @Override
        public void actionPerformed(ActionEvent e) {
            //whenever the timer fires a tick event, this method will be called
            moveShapes();
        }

    }
    private void clickForShape(MouseEvent e) { //e represents the mouse
        // Get the coordinates of the mouse click
        int mouseX = e.getX();
        int mouseY = e.getY();

        //get the next shape
        Shape nextShape = getNextShape();
        Graphics g = getGraphics();

        //set x and y position of nextShape to mouse click coordinates
        nextShape.setxPosition(mouseX);
        nextShape.setyPosition(mouseY);

        // Set speed
        nextShape.setxSpeed(10);
        nextShape.setySpeed(10);

        // Draw the shape at the mouse click coordinates
        nextShape.drawShape(g);

        // Add shape to the clickAddedShapes Array
        clickAddedShapes.add(nextShape);

        timer.start(); //start the timer
    }
    public void moveShapes(){
        for(Shape addedShapes: clickAddedShapes){
            addedShapes.moveShape();//moves each shape in the array
            //after moving, check each shape for collision with walls
            checkForWallHit(addedShapes);
        }
        checkForShapeCollision(); // Check for collisions between shapes
        this.repaint();//repaints screen each time (recall paintComponent method)
    }

    public void checkForWallHit(Shape currentShape){
        int xPosition = currentShape.getxPosition();
        int yPosition = currentShape.getyPosition();
        int width = currentShape.getWidth();
        int height = currentShape.getHeight();

        // Compare the position of the shape with the boundaries of the panel
        if (xPosition + width >= this.getWidth() || xPosition < 0) { //add width of shape to x position
            // Hit left or right wall
            currentShape.setxSpeed(currentShape.getxSpeed() * -1); // Reverse x speed
        }
        if (yPosition + height >= this.getHeight() || yPosition < 0) {//add width of shape to y position
            // Hit top or bottom wall
            currentShape.setySpeed(currentShape.getySpeed() * -1); // Reverse y speed
        }
    }
    public void checkForShapeCollision() {
        for (int i = 0; i < clickAddedShapes.size(); i++) {
            Shape shapeA = clickAddedShapes.get(i);//assign shapeA
            for (int j = i + 1; j < clickAddedShapes.size(); j++) {
                Shape shapeB = clickAddedShapes.get(j); //assign shapeB
                if (shapesIntersect(shapeA, shapeB)) {//if shapeA intersects shapeB
                    // Reverse direction of both shapes so they bounce off
                    shapeA.setxSpeed(shapeA.getxSpeed()*-1);
                    shapeA.setySpeed(shapeA.getySpeed()*-1);
                    shapeB.setxSpeed(shapeB.getxSpeed()*-1);
                    shapeB.setySpeed(shapeB.getySpeed()*-1);
                }
            }
        }
    }

    private boolean shapesIntersect(Shape shapeA, Shape shapeB) { //takes in assigned shapes
        // Get bounding boxes of both shapes
        Rectangle rectA = new Rectangle(shapeA.getxPosition(), shapeA.getyPosition(), shapeA.getWidth(), shapeA.getHeight());
        Rectangle rectB = new Rectangle(shapeB.getxPosition(), shapeB.getyPosition(), shapeB.getWidth(), shapeB.getHeight());

        // Check if the bounding boxes intersect
        return rectA.intersects(rectB);
    }



}