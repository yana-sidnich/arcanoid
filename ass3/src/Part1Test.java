
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
// import java.util.List;
import java.util.Random;


public class Part1Test {
    public static void main(String[] args) {
        // Test1();
        Test2();
    }

    public static void Test1() {
        GUI gui = new GUI("Test1", 800, 800);
        Sleeper sleeper = new Sleeper();
        GameEnvironment gameEnv = new GameEnvironment();

        // Rectangle upperBound = new Rectangle(new Point(0, 0), 800, 20);
        // Rectangle leftBound =  new Rectangle(new Point(0, 20), 20, 760);
        // Rectangle lowerBound = new Rectangle(new Point(0, 780), 800, 20);
        // Rectangle rightBound = new Rectangle(new Point(780, 20), 20, 760);
        Random r = new Random();
        ArrayList<Rectangle>  recArray = new ArrayList<Rectangle>();
        recArray.add(new Rectangle(new Point(0, 0), 800, 20));
        recArray.add(new Rectangle(new Point(0, 20), 20, 760));
        recArray.add(new Rectangle(new Point(0, 780), 800, 20));
        recArray.add(new Rectangle(new Point(780, 20), 20, 760));

        for (int i = 0; i < 10; ++i) {
            recArray.add(new Rectangle(new Point(100 + 40 * i, 100 + 20 * i), 40, 20));
            // recArray.add(new Rectangle(new Point(600 - 20 * i, 100 + 50 * i), 20, 40));
        }

        recArray.add(new Rectangle(new Point(200, 500), 30, 200));
        recArray.add(new Rectangle(new Point(600, 600), 150, 40));
        recArray.add(new Rectangle(new Point(600, 200), 20, 200));

        Color[] colorArray = new Color[recArray.size()];
        for (int i = 0; i < colorArray.length; ++i) {
            colorArray[i] = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
        }

        for (int i = 0; i < colorArray.length; ++i) {
            gameEnv.addCollidable(new Block(recArray.get(i), colorArray[i]));
        }

        ArrayList<Ball> ballArray = new ArrayList<Ball>();

        ballArray.add(new Ball(new Point(201, 199), 7, Color.BLACK));
        ballArray.add(new Ball(new Point(599, 200), 7, Color.BLACK));
        ballArray.add(new Ball(new Point(199, 601), 7, Color.BLACK));
        ballArray.add(new Ball(new Point(599, 601), 7, Color.BLACK));
        for (Ball b : ballArray) {
            Velocity v = new Velocity(20 * r.nextDouble(), 20 * r.nextDouble());
            b.setVelocity(v);
            b.setGameEnvironment(gameEnv);
        }

        ballArray.add(new Ball(new Point(120, 140), 7, Color.BLACK));
        ballArray.get(ballArray.size() - 1).setVelocity(new Velocity(5, -5));
        ballArray.get(ballArray.size() - 1).setGameEnvironment(gameEnv);

        //add more blocks

        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            for (int i = 0; i < colorArray.length; ++i) {
                recArray.get(i).drawOn(surface, colorArray[i]);
            }
            for (Ball b : ballArray) {
                b.drawOn(surface);
                b.moveOneStep();
            }

            gui.show(surface);
            sleeper.sleepFor(50);  // Wait for 50 milliseconds.
        }
    }

    public static void Test2() {
        Random r = new Random();
        GUI gui = new GUI("Test1", 800, 800);
        Sleeper sleeper = new Sleeper();
        GameEnvironment gameEnv = new GameEnvironment();

        ArrayList<Rectangle>  recArray = new ArrayList<Rectangle>();
        recArray.add(new Rectangle(new Point(100, 100), 30, 30));
        recArray.add(new Rectangle(new Point(130, 100), 30, 30));

        Color[] colorArray = new Color[recArray.size()];
        for (int i = 0; i < colorArray.length; ++i) {
            colorArray[i] = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
        }
        ArrayList<Ball> ballArray = new ArrayList<Ball>();

        for (int i = 0; i < colorArray.length; ++i) {
            gameEnv.addCollidable(new Block(recArray.get(i), colorArray[i]));
        }

        ballArray.add(new Ball(new Point(90, 60), 7, Color.BLACK));
        ballArray.add(new Ball(new Point(90, 170), 7, Color.BLACK));
        ballArray.add(new Ball(new Point(170, 170), 7, Color.BLACK));
        ballArray.add(new Ball(new Point(170, 60), 7, Color.BLACK));

        int speed = 10;
        ballArray.get(0).setVelocity(new Velocity(speed, speed));
        ballArray.get(0).setGameEnvironment(gameEnv);
        ballArray.get(1).setVelocity(new Velocity(speed, -speed));
        ballArray.get(1).setGameEnvironment(gameEnv);
        ballArray.get(2).setVelocity(new Velocity(-speed, -speed));
        ballArray.get(2).setGameEnvironment(gameEnv);
        ballArray.get(3).setVelocity(new Velocity(-speed, speed));
        ballArray.get(3).setGameEnvironment(gameEnv);


        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            for (int i = 0; i < colorArray.length; ++i) {
                recArray.get(i).drawOn(surface, colorArray[i]);
            }
            for (Ball b : ballArray) {
                b.drawOn(surface);
                b.moveOneStep();
            }

            gui.show(surface);
            sleeper.sleepFor(500);  // Wait for 50 milliseconds.
        }

    }
}