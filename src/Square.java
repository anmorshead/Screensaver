import java.awt.*;

public class Square extends Shape{
    private int width;
    private int height;

    public Square(int xPosition, int yPosition, Color shapeColor, int width, int height) {
        super(xPosition, yPosition, shapeColor);
        this.width = width;
        this.height = height;
    }


    @Override
    public void drawShape(Graphics g) {
        g.setColor(getShapeColor()); // Set the color of the rectangle
        // Draw the rectangle at (xPosition, yPosition) with the specified width and height
        g.fillRect(this.getxPosition() , this.getyPosition(), width, height);
    }

    @Override
    public void moveShape() {
        this.setxPosition(this.getxPosition()+ this.getxSpeed());
        this.setyPosition(this.getyPosition()+ this.getySpeed());
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}