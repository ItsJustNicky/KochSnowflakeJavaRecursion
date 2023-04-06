

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

/*
 * Add menu 
 * add tracker
 * change to set arrays
 * 
 */

public class ArrayVersion extends Canvas{
    static int pageSize = 400;
    static int order = 0;
    static double scale=0;
    static int [][] pointTrack;
    static int hardx1=20;
    static int hardx2=280;

    static int hardx3=280;
    static int hardx4=150;

    static int hardx5=150;
    static int hardx6=20;

    static int hardy1=280;
    static int hardy2=280;

    static int hardy3=280;
    static int hardy4=20;

    static int hardy5=20;
    static int hardy6=280;
    static boolean zoomDraw=false;
    JButton draw = new JButton("Draw again?");
    JButton zoomIn = new JButton("Zoom In");
    JButton zoomOut = new JButton("Zoom out");
    JFrame frame;

    int track=0;
    public static void main(String[] args) {
        ArrayVersion callMethod = new ArrayVersion();
        callMethod.create();
    }
    public ArrayVersion(){ 
    }
    public void create(){

        String popupBox = JOptionPane.showInputDialog("Enter the depth of recursion");
        order = Integer.parseInt(popupBox);
        scale=1;
        order--;
        int lines = (int) (3*Math.pow(4,order));
        pointTrack = new int[lines][4];

        frame = new JFrame("SnowFlake");
        JPanel panel = new JPanel(new GridLayout(1,2));
        JPanel panel2 = new JPanel(new GridLayout(3,1));

        panel2.setSize(40,40);
        draw.setSize(new Dimension(40, 40));
        zoomIn.setSize(new Dimension(40, 40));
        zoomOut.setSize(new Dimension(40, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        Canvas canvas = new ArrayVersion();
        canvas.setSize(700, 700);
        panel.add(canvas);
        panel2.add(draw);
        panel2.add(zoomIn);
        panel2.add(zoomOut);
        panel.add(panel2);
        frame.add(panel);
        
        ActionListener listener = new buttonListener();

        zoomIn.addActionListener(listener);
        zoomOut.addActionListener(listener);
        draw.addActionListener(listener);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
      

    }
    public void create(double inScale,int inOrder,boolean booling){
        zoomDraw=booling;
        order = inOrder;

        scale=inScale;

        int lines = (int) (3*Math.pow(4,order));
        pointTrack = new int[lines][4];

        frame = new JFrame("SnowFlake");
        JPanel panel = new JPanel(new GridLayout(1,2));
        JPanel panel2 = new JPanel(new GridLayout(3,1));

        panel2.setSize(40,40);
        draw.setSize(new Dimension(40, 40));
        zoomIn.setSize(new Dimension(40, 40));
        zoomOut.setSize(new Dimension(40, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        Canvas canvas = new ArrayVersion();
        canvas.setSize(700, 700);
        panel.add(canvas);
        panel2.add(draw);
        panel2.add(zoomIn);
        panel2.add(zoomOut);
        panel.add(panel2);
        frame.add(panel);
        
        ActionListener listener = new buttonListener();

        zoomIn.addActionListener(listener);
        zoomOut.addActionListener(listener);
        draw.addActionListener(listener);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
      

    }


    public void paint(Graphics g) {
        hardx1 = scaleInt(hardx1,scale);
        hardx2 =scaleInt(hardx2,scale);
        hardx3= scaleInt(hardx3,scale);
        hardx4=scaleInt(hardx4,scale);
        hardx5=scaleInt(hardx5,scale);
        hardx6=scaleInt(hardx6,scale);
        hardy1=scaleInt(hardy1,scale);
        hardy2=scaleInt(hardy2,scale);
        hardy3=scaleInt(hardy3,scale);
        hardy4=scaleInt(hardy4,scale);
        hardy5=scaleInt(hardy5,scale);
        hardy6=scaleInt(hardy6,scale);
        double timer=0;
        g.setColor(Color.black);
        g.translate(100, 100);
        timer=System.currentTimeMillis()/1000;

        storeSnow(order,hardx1,hardy1,hardx2,hardy2);
        storeSnow(order,hardx3,hardy3,hardx4,hardy4);
        storeSnow(order,hardx5,hardy5,hardx6,hardy6);
        drawSnow(g);
        if(zoomDraw==false){
        JOptionPane.showMessageDialog(null,"Time to draw: "+ ((System.currentTimeMillis()/1000)-timer)+" Seconds!");
        }
    }
    public int scaleInt(int in,double scale){
        int out = (int) (in * scale);
        return out;
    }


    private void storeSnow(int order, int x1, int y1, int x5,int y5){
        if (order == 0){
            pointTrack[track][0] = (x1);
            pointTrack[track][1] = (y1);
            pointTrack[track][2] = (x5);
            pointTrack[track][3] = (y5);
            track++;
        }
        else{
            int xdiff;
            int ydiff;
            int x2;
            int y2;
            int x3; 
            int y3;
            int x4;
            int y4;

                xdiff = x5 - x1;
                ydiff = y5 - y1;
                x2 = x1 + xdiff / 3;
                y2 = y1 + ydiff / 3;
                x3 = (int) (0.5 * (x1+x5) + Math.sqrt(3) * (y1-y5)/6);
                y3 = (int) (0.5 * (y1+y5) + Math.sqrt(3) * (x5-x1)/6);
                x4 = x1 + 2 * xdiff /3;
                y4 = y1 + 2 * ydiff /3;
                storeSnow (order-1, x1, y1, x2, y2);
                storeSnow (order-1, x2, y2, x3, y3);
                storeSnow (order-1, x3, y3, x4, y4);
                storeSnow (order-1, x4, y4, x5, y5);
            }
    }
    private void drawSnow (Graphics g){
        for(int i=0;i<pointTrack.length;i++){
                g.drawLine(pointTrack[i][0], pointTrack[i][1], pointTrack[i][2], pointTrack[i][3]);

        }
            
        }
        private class buttonListener implements ActionListener{
            
                
            
        public void actionPerformed(ActionEvent e){
            if(e.getSource()== zoomIn){
                    ArrayVersion recall = new ArrayVersion();
                    recall.create(2,order,true);
                    frame.dispose();
            }else if(e.getSource()==zoomOut){
                ArrayVersion recall = new ArrayVersion();
                    recall.create(0.5,order,true);
                    frame.dispose();
            }else if(e.getSource()==draw){
                ArrayVersion recall = new ArrayVersion();
                String popupBox = JOptionPane.showInputDialog("Enter the depth of recursion");
                int newOrder = Integer.parseInt(popupBox);
                recall.create(1,newOrder,false);
                frame.dispose();

                
            }
        }     
}
}


