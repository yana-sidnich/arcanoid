// ID: 323537779

import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class represents a bouncing ball animation.
 * This is a single Ball, his size b command line arguments, drawn on a surface.
 */
public class BouncingBallAnimation {
   // Consts to represent the screen's size and the ball's radius.
   private static final int GUI_WIDTH = 400;
   private static final int GUI_LENGTH = 300;
   private static final int BALL_RADIUS = 30;
   /**
    * This function creates the ball with the given arguments and draws the animation.
    * @param start start point of the Ball.
    * @param dx speed on x axis of the Ball.
    * @param dy speed on y axis of the Ball.
    */
   private static void drawAnimation(Point start, double dx, double dy) {
      // Create the GUI window.
      GUI gui = new GUI("BouncingBallAnimation", GUI_WIDTH, GUI_LENGTH);
      Sleeper sleeper = new Sleeper();
      // Create the ball and setting it's velocity.
      Ball ball = new Ball(start.getX(), start.getY(), BALL_RADIUS, Color.BLACK);
      ball.setVelocity(dx, dy);
      // Main animation loop, runs until program is closed.
      while (true) {
         DrawSurface d = gui.getDrawSurface();
         ball.drawOn(d);
         ball.moveOneStep(0, GUI_WIDTH, 0, GUI_LENGTH);
         gui.show(d);
         sleeper.sleepFor(50);  // Wait for 50 milliseconds.
      }
   }

   /**
    * Main function, parses the given arguments and calls draw animation.
    * @param args four arguments expected : x and y values of center point, speed on x axis, speed on y axis.
    * We assume that the given ball is completely inside the screen.
    */
   public static void main(String[] args) {

      if (args.length != 4) {
          System.out.println("Invalid Input: args length must at exactly 4");
          return;
      }

      int pointX = Integer.parseInt(args[0]);
      int pointY = Integer.parseInt(args[1]);
      int dx = Integer.parseInt(args[2]);
      int dy = Integer.parseInt(args[3]);
      drawAnimation(new Point(pointX, pointY), dx, dy);
   }
}