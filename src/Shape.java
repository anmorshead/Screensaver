import java.awt.*;

public abstract class Shape {
    private int xPosition;
    private int yPosition;
    private int xSpeed = 10;
    private int ySpeed = 10;
    private Color shapeColor;

    public Shape(int xPosition, int yPosition, Color shapeColor) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.shapeColor = shapeColor;
    }

    //methods
    public abstract void drawShape(Graphics g);
    public abstract void moveShape();
    public abstract int getHeight();
    public abstract int getWidth();



    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }
}