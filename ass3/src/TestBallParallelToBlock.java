

// ID: 323537779
import java.awt.Color;
/**
 * This class will be used to run the game for this assigment.
 * It will contain the main function that will create run the game.
 *
 */
class TestBallParallelToBlock {
    /**
     * The main function that will run the game.
     * @param args ignored
     */
    public static void main(String[] args) {
        Game game = new Game();
        int ballsStartPointX = 400;
        int ballsStartPointY = 500;
        Color ballColor = Color.cyan;

        Block b = new Block(new Rectangle(new Point(400,200),10,10),Color.black);
        GameEnvironment ge = new GameEnvironment();
        ge.addCollidable(b);

        // Create ball1 and set its speed, then add it to the game.
        Ball ball1 = new Ball(new Point(ballsStartPointX , ballsStartPointY), 10, ballColor);
        Velocity v1 = Velocity.fromAngleAndSpeed(0, 20);
        ball1.setVelocity(v1);
       // ball1.setGameEnvironment(game.);
//        ball1.addToGame(this);

        // Create ball2 and set its speed, then add it to the game.
        //ball2.addToGame(this);

        game.initialize();
        game.run();
    }
}