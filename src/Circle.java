import java.awt.*;

public class Circle extends Shape{
    private int radius;

    public Circle(int xPosition, int yPosition, Color shapeColor, int radius) {
        super(xPosition, yPosition, shapeColor);
        this.radius = radius;
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(getShapeColor()); // Set the color of the circle
        // Draw the circle at (xPosition, yPosition) with the specified radius
        g.fillOval(getxPosition() - radius, getyPosition() - radius, 2 * radius, 2 * radius);
    }

    @Override
    public void moveShape() {
        this.setxPosition(this.getxPosition()+ this.getxSpeed());
        this.setyPosition(this.getyPosition()+ this.getySpeed());


    }

    @Override
    public int getHeight() {
        return 2 * radius; // Diameter of the circle
    }

    @Override
    public int getWidth() {
        return 2 * radius; // Diameter of the circle
    }


}