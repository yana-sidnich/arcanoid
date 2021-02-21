//ID: 323537779

package game;

import java.util.List;

import biuoop.KeyboardSensor;
import game.animation.GameLevel;
import game.animation.GameOver;
import game.animation.KeyPressStoppableAnimation;
import game.animation.WinScreen;
import game.level.LevelInformation;

/**
 * this class represents a full game flow, runs the levels given in the runLevels function.
 */
public class GameFlow {

   private KeyboardSensor keyboardSensor;
   private AnimationRunner animationRunner;
   private Counter gameScore;

   /**
    * Constructor of the GameFlow animation.
    * @param ar AnimationRunner to run the animation
    * @param ks Keyboard Sensor
    */
   public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
      this.keyboardSensor = ks;
      this.animationRunner = ar;
      gameScore = new Counter();
   }
   /**
    * run the levels in levels until failure / victory.
    * @param levels level info of the levels to run
    */
   public void runLevels(List<LevelInformation> levels) {
      boolean win = true;
      for (LevelInformation levelInfo : levels) {

         // create initialize and run the current level
         GameLevel level = new GameLevel(levelInfo,
               this.animationRunner, this.keyboardSensor,
               this.gameScore);

         level.initialize();
         level.run();

         // check if level ended on victory. if not, break
         if (!level.win()) {
            win = false;
            break;
         }
      }

      // check if victorious, if win then show Winscreen, else show lose screen.
      if (win) {
         this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
               KeyboardSensor.SPACE_KEY,
               new WinScreen(this.gameScore)));
      } else {
         this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
               KeyboardSensor.SPACE_KEY,
               new GameOver(this.gameScore)));
      }
   }

   /**
    * Getter of the score.
    * @return game score
    */
   public Counter getScore() {
      return this.gameScore;
   }
}