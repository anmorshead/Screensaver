import java.awt.*;
import java.awt.geom.GeneralPath;

public class Triangle extends Shape{
    private int[] xPoints;
    private int[] yPoints;
    private int numPoints;

    public Triangle(int xPosition, int yPosition, Color shapeColor, int[] xPoints, int[] yPoints, int numPoints) {
        super(xPosition, yPosition, shapeColor);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.numPoints = numPoints;
    }


    @Override
    public void drawShape(Graphics g) {
        // Update the x and y coordinates of the triangle's points based on the xPosition and yPosition
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getShapeColor());

        int[] updatedXPoints = new int[xPoints.length];
        int[] updatedYPoints = new int[yPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            updatedXPoints[i] = xPoints[i] + getxPosition(); // Adjust x-coordinate by xPosition
            updatedYPoints[i] = yPoints[i] + getyPosition(); // Adjust y-coordinate by yPosition
        }

        GeneralPath path = new GeneralPath();
        path.moveTo(updatedXPoints[0], updatedYPoints[0]);
        for (int i = 1; i < updatedXPoints.length; i++) {
            path.lineTo(updatedXPoints[i], updatedYPoints[i]);
        }
        path.closePath();

        g2d.fill(path);

    }

    @Override
    public void moveShape() {
        this.setxPosition(this.getxPosition()+ this.getxSpeed());
        this.setyPosition(this.getyPosition()+ this.getySpeed());
    }

    @Override
    public int getHeight() {
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (int y : yPoints) {
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }
        return maxY - minY;
        //had to ask chat for these equations, i'm terrible at math.
    }

    @Override
    public int getWidth() {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int x : xPoints) {
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
        }
        return maxX - minX;
        //had to ask chat for these equations, i'm terrible at math.
    }
}
