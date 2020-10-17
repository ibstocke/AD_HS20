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
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Circle {

    final private int diameter;
    final private int xPosition;
    private int yPosition;
    final private Color color;
    private boolean isAlive;

    final private double gravity = 10.0 / 10000.0; //x pixel*s^(-2)
    private long timeTick;

    /**
     * Create a new circle at default position with default color.
     */
    public Circle() {
        this(68, 230, 90, Color.blue);
    }

    public Circle(int x, int y) {
        this(getRandomNumber(20, 150), x, y, getRandomColor());
    }

    /**
     * Create a new circle at specific position and color.
     *
     * @param diameter diameter of the circle.
     * @param xPosition x position of the circle.
     * @param yPosition y position of the circle.
     * @param color color of the circle.
     */
    public Circle(int diameter, int xPosition, int yPosition, Color color) {
        this.diameter = diameter;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        this.timeTick = 0;
    }

    /**
     * Returns the x-coordinate of the circle.
     *
     * @return x-coordinate of the circle.
     */
    public int getX() {
        return this.xPosition;
    }

    /**
     * Returns y-coordinate of the circle.
     *
     * @return y-coordinate of the circle.
     */
    public int getY() {
        return yPosition;
    }

    public int getDiameter() {
        return this.diameter;
    }

    public Color getColor() {
        return this.color;
    }

    public void setAlive() {
        this.isAlive = true;
    }

    public void setDeath() {
        this.isAlive = false;
    }

    public void nextLiveCycle() {
        if (this.isAlive) {
            this.timeTick++;
            this.yPosition += (int) (0.5 * this.gravity * this.timeTick * this.timeTick);
        }
    }

    public Shape getShape() {
        return new Ellipse2D.Double(this.xPosition, this.yPosition, this.diameter, this.diameter);
    }

    private static int getRandomNumber(int lowerNumber, int higherNumber) {
        return (int) (Math.random() * (higherNumber - lowerNumber));
    }

    private static Color getRandomColor() {
        int R = (int) (Math.random() * (255));
        int G = (int) (Math.random() * (255));
        int B = (int) (Math.random() * (255));
        return new Color(R, G, B);
    }
}
