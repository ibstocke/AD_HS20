/*
 * Copyright 2020 Hochschule Luzern - Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.SW05.balls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas. This is a modification of the general purpose
 * Canvas, specially made for the BlueJ "shapes" example.
 *
 * @author Bruce Quig and Michael Kölling (mik)
 *
 * @version 2016.02.29
 */
public final class Canvas extends JFrame implements MouseListener {
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static final long serialVersionUID = 1534533443;

    //  ----- instance part -----
    private final JFrame frame;
    private final CanvasPane canvas;
    private Graphics2D graphic;
    private final Color backgroundColor;
    private Image canvasImage;
    private final HashMap<Circle, ShapeDescription> shapes;

    private final int canvasWith;
    private final int canvasHeight;

    private ReentrantLock lock = new ReentrantLock();

    /**
     * Create a Canvas.
     *
     * @param title title to appear in Canvas Frame.
     * @param width the desired width for the canvas.
     * @param height the desired height for the canvas.
     * @param bgColor the desired background color of the canvas.
     */
    private Canvas(String title, int width, int height, Color bgColor) {
        this.canvasWith = width;
        this.canvasHeight = height;
        this.backgroundColor = bgColor;
        this.shapes = new HashMap<>();

        canvas = new CanvasPane();
        canvas.setPreferredSize(new Dimension(width, height));

        frame = new JFrame();
        frame.setTitle(title);
        frame.setLocation(30, 30);
        frame.setContentPane(canvas);
        frame.pack();
        frame.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen when made visible. This method can also be
     * used to bring an already visible canvas to the front of other windows.
     *
     * @param visible boolean value representing the desired visibility of the canvas (true or false).
     */
    @Override
    public void setVisible(boolean visible) {
        if (graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D) canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     *
     * @param referenceObject an object to define identity for this shape.
     */
    // Note: this is a slightly backwards way of maintaining the shape
    // objects. It is carefully designed to keep the visible shape interfaces
    // in this project clean and simple for educational purposes.
    public void updateShape(Circle referenceObject) {
        shapes.put(referenceObject, new ShapeDescription(referenceObject.getShape(), referenceObject.getColor()));
        //redraw();
    }

    /**
     * Wait for a specified number of milliseconds before finishing. This provides an easy way to specify a small delay
     * which can be used when producing animations.
     *
     * @param milliseconds the number.
     */
    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // ignoring exception at the moment
        }
    }

    public synchronized void nextLifeCycle() {
        synchronized (this) {
            Iterator<Circle> itr = shapes.keySet().iterator();

            while (itr.hasNext()) {
                Circle nextCircle = itr.next();
                nextCircle.nextLiveCycle();
                if ((nextCircle.getY() + (double) nextCircle.getDiameter() / 2) >= this.canvasHeight) {
                    itr.remove();
                } else {
                    updateShape(nextCircle);
                }
            }
        }
        redraw();

    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    private void redraw() {
        erase();
        for (ShapeDescription shapeDescription : shapes.values()) {
            shapeDescription.draw(graphic);
        }
        canvas.repaint();
    }

    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase() {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        Circle c1 = new Circle(arg0.getX(), arg0.getY());
        c1.setAlive();

        synchronized (this) {
            updateShape(c1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    /**
     * Inner class CanvasPane - the actual canvas component contained in the Canvas frame. This is essentially a JPanel
     * with added capability to refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public void paint(Graphics g) {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }

    /**
     * Inner class CanvasPane - the actual canvas component contained in the Canvas frame. This is essentially a JPanel
     * with added capability to refresh the image drawn on it.
     */
    private class ShapeDescription {

        private final Shape shape;
        private final Color color;

        public ShapeDescription(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }

        public void draw(Graphics2D graphic) {
            graphic.setColor(this.color);
            graphic.fill(shape);
        }
    }

    public static void main(String[] args) {
        Canvas canvas = new Canvas("TEST", 1200, 800, Color.white);
        canvas.setVisible(true);

        for (;;) {
            canvas.nextLifeCycle();
            canvas.wait(10);
        }
    }

}
