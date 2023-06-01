import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.AffineTransform;
/*
 * @authors Nicky Patterson
 * @desc A class used to create and visualise KochSnowflakes in a jFrame
 */
public class doubleTesting extends JFrame {
    static double hardx1 = 20.0;
    static double hardx2 = 280.0;
    static double hardx3 = 280;
    static double hardx4 = 150;
    static double hardx5 = 150;
    static double hardx6 = 20;
    static double hardy1 = 280;
    static double hardy2 = 280;
    static double hardy3 = 280;
    static double hardy4 = 20;
    static double hardy5 = 20;
    static double hardy6 = 280;
    static double[][] pointTrack;
    static int track = 0;
    static int order = 0;
    private double scale = 1.0;
    private double panX = 0.0;
    private double panY = 0.0;
    private JButton increaseButton;
    private JButton decreaseButton;
    private JButton reDraw;
    private JButton changeColour;
    private Color colour = Color.black;
    private boolean notZoom = true;
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    /*
     * Method used to instantialize the Frame for drawing and button placement
     */
    public doubleTesting() {
        setTitle("KochSnowflake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        String popupBox = JOptionPane.showInputDialog("Enter the depth of recursion");
        if (popupBox == null) {
            order = 0;
        } else {
            order = Integer.parseInt(popupBox);
            order--;
            if (order < 0) order = 0;
        }

        increaseButton = new JButton("Zoom In");
        decreaseButton = new JButton("Zoom Out");
        reDraw = new JButton("ReDraw?");
        changeColour = new JButton("Change Line Colour");
        /*
         * Method calls for adding buttons that zoom, create new snowflakes, and change colours
         */
        increaseButton.addActionListener(e -> {
            notZoom = false;
            setScale(scale + 0.1);
        });

        decreaseButton.addActionListener(e -> {
            notZoom = false;
            setScale(scale - 0.1);
        });
        reDraw.addActionListener(e -> {
            notZoom = true;
            newOrder();
        });
        changeColour.addActionListener(e -> {
            notZoom = false;
            newColour();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(increaseButton);
        buttonPanel.add(decreaseButton);
        buttonPanel.add(reDraw);
        buttonPanel.add(changeColour);

        add(buttonPanel, BorderLayout.NORTH);
        /*
         * Panel used for drawing snowflake onto to allow for panning after zooming in or out
         */
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                AffineTransform panTransform = AffineTransform.getTranslateInstance(panX, panY);
                g2d.transform(panTransform);

                AffineTransform scaleTransform = AffineTransform.getScaleInstance(scale, scale);
                g2d.transform(scaleTransform);

                int lines = (int) (3 * Math.pow(4, order));
                pointTrack = new double[lines][4];

                double timer = 0;

                timer = System.currentTimeMillis() / 1000;
                storeSnow(order, hardx1, hardy1, hardx2, hardy2);
                storeSnow(order, hardx3, hardy3, hardx4, hardy4);
                storeSnow(order, hardx5, hardy5, hardx6, hardy6);
                track = 0;

                double centerX = getWidth() / 2;
                double centerY = getHeight() / 2;

                double xOffset = centerX - (hardx1 + hardx2 + hardx3 + hardx4 + hardx5 + hardx6) / 6;
                double yOffset = centerY - (hardy1 + hardy2 + hardy3 + hardy4 + hardy5 + hardy6) / 6;

                Path2D path = new Path2D.Double();

                path.moveTo(pointTrack[0][0] + xOffset, pointTrack[0][1] + yOffset);
                path.lineTo(pointTrack[0][2] + xOffset, pointTrack[0][3] + yOffset);
                for (int i = 1; i < pointTrack.length; i++) {
                    path.lineTo(pointTrack[i][0] + xOffset, pointTrack[i][1] + yOffset);
                    path.lineTo(pointTrack[i][2] + xOffset, pointTrack[i][3] + yOffset);
                }
                path.closePath();
                g2d.setColor(colour);
                g2d.setStroke(new BasicStroke(0.5f));

                g2d.draw(path);
                /*
                 * Testing method for getting times to draw, uncomment to see in terminal
                 */
                /*if (notZoom)
                    System.out.println("Time to draw: " + ((System.currentTimeMillis() / 1000) - timer) + " Seconds!");
                */
                }
        };
        /*
         * Mouse listeners for allowing panning of zoomed in or out snowflakes
         */
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMouseX = e.getX();
                lastMouseY = e.getY();
            }
        });

        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                doubleTesting.this.mouseDragged(e);
            }
        });

        add(drawingPanel, BorderLayout.CENTER);
    }
    /*
     * Method used to set scale factor when zooming in or out on the snow flake
     * @input scale factor to apply to the snowflake, determined in button use
     */
    public void setScale(double scale) {
        this.scale = scale;
        repaint();
    }
    /*
     * method used to create new snowflakes with different orders to the original Snow flake created
     * defaults to previous order if closed or 0 if order is less than 0
     */
    public void newOrder() {
        String popupBox = JOptionPane.showInputDialog("Enter the depth of recursion");
        setScale(1.0);
        if (popupBox == null) {
            order = order;
        } else {
            order = Integer.parseInt(popupBox);
            order--;
            if (order < 0) order = 0;
        }
        repaint();
    }
    /*
     * Method used to change the current colour of the snowflake
     * Has range of colours in the rainbow, minus purple
     * Defaults to black if input matches no colour options
     */
    public void newColour() {
        String popupBox = JOptionPane.showInputDialog("Enter the new line colour");

        if (popupBox == null) {
            colour = Color.black;
        } else {
            popupBox = popupBox.toLowerCase();
            if (popupBox.contains("red")) {
                colour = Color.red;
            } else if (popupBox.contains("blue")) {
                colour = Color.blue;
            } else if (popupBox.contains("green")) {
                colour = Color.green;
            } else if (popupBox.contains("yellow")) {
                colour = Color.yellow;
            } else if (popupBox.contains("black")) {
                colour = Color.black;
            } else if (popupBox.contains("white")) {
                colour = Color.white;
            } else if (popupBox.contains("orange")) {
                colour = Color.orange;
            } else if (popupBox.contains("pink")) {
                colour = Color.pink;
            } else {
                colour = Color.BLACK;
            }
        }
        repaint();
    }
    /*
     * Method used to show mouse dragged events used in panning
     * @input e mouse being dragged
     * @desc updates the panX variables with new distance travlled during dragging 
     */
    public void mouseDragged(MouseEvent e) {
        if (!notZoom) {
            int dx = e.getX() - lastMouseX;
            int dy = e.getY() - lastMouseY;

            panX += dx;
            panY += dy;

            lastMouseX = e.getX();
            lastMouseY = e.getY();

            repaint();
        }
    }
    /*
     * main method that creates a new instance of the doubletesting creator called snow flake and sets it to visable 
     */
    public static void main(String[] args) {
        doubleTesting snowFlake = new doubleTesting();
        snowFlake.setVisible(true);
    }
    /*
     * Method used to store the points as doubles within a 2d array called point track
     * @input order the order for which the snowflake will be drawn
     * @x1 the original x coordinate to start the line
     * @y1 the oringal y coordinate to start the line
     * @x5 the orinal x coordinate to end the line
     * @y5 the orinal y coordinate to end the line
     * @desc this method is called recursively when order doesent equal 0 in order to create the lines during drawing
     */
    private void storeSnow(int order, double x1, double y1, double x5, double y5) {
        if (order == 0) {
            pointTrack[track][0] = x1;
            pointTrack[track][1] = y1;
            pointTrack[track][2] = x5;
            pointTrack[track][3] = y5;
            track++;
        } else {
            double xdiff;
            double ydiff;
            double x2;
            double y2;
            double x3;
            double y3;
            double x4;
            double y4;

            xdiff = x5 - x1;
            ydiff = y5 - y1;
            x2 = x1 + xdiff / 3;
            y2 = y1 + ydiff / 3;
            x3 = (0.5 * (x1 + x5) + Math.sqrt(3) * (y1 - y5) / 6);
            y3 = (0.5 * (y1 + y5) + Math.sqrt(3) * (x5 - x1) / 6);
            x4 = x1 + 2 * xdiff / 3;
            y4 = y1 + 2 * ydiff / 3;
            storeSnow(order - 1, x1, y1, x2, y2);
            storeSnow(order - 1, x2, y2, x3, y3);
            storeSnow(order - 1, x3, y3, x4, y4);
            storeSnow(order - 1, x4, y4, x5, y5);
        }
    }
}
