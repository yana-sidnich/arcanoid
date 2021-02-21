// ID: 323537779

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.Counter;
import game.animation.Animation;
import game.animation.GameLevel;
import game.animation.HighScoreAnimation;
import game.animation.KeyPressStoppableAnimation;
import game.animation.Menu;
import game.animation.MenuAnimation;
import game.GameFlow;
import game.level.DirectHit;
import game.level.FinalFour;
import game.level.Green3;
import game.level.LevelInformation;
import game.level.WideEasy;
import game.tasks.Task;

/**
 * This class will be used to run the game for this assigment.
 * It will contain the main function that will create run the game.
 *
 */
public class Ass6Game {

    static private boolean stop;
    /**
     * The main function that will run the game.
     * @param args ignored
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid GameLevel", GameLevel.GAME_GUI_WIDTH, GameLevel.GAME_GUI_HEIGHT);
        AnimationRunner am = new AnimationRunner(gui, GameLevel.GAME_FPS);
        stop = false;
        Counter highscore = getHighscore();
        Animation highscoreAnimation = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), 
                                            KeyboardSensor.SPACE_KEY, new HighScoreAnimation(highscore));

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", gui.getKeyboardSensor());
        menu = new MenuAnimation<>("Arkanoid", gui.getKeyboardSensor());

        menu.addSelection("s", "Play", new Task<Void>() {
            public Void run() {
                GameFlow flow = new GameFlow(am, gui.getKeyboardSensor());        
                List<LevelInformation> info = parse(args);
                flow.runLevels(info);
                highscore.setValue(highscore.getValue() >  flow.getScore().getValue() ? 
                                   highscore.getValue() :
                                   flow.getScore().getValue());

                updateHighscore(highscore);
                am.run(highscoreAnimation);
                return null;
            }
        });

        menu.addSelection("h", "High Score", new Task<Void>() {
            public Void run() {
                am.run(highscoreAnimation);
                return null;
            }
        });

        menu.addSelection("q", "Quit game", new Task<Void>() {
            public Void run() {
                stop = true;
                return null;
            }
        });

        while (!stop) {
            am.run(menu);
            menu.getStatus().run();
        }

        // this must be last (close the whole program)
        gui.close();
    }
    
    /**
     * parse the command line and create a list of levels accordingly.
     * @param args array of strings
     * @return levels according to strings
     */
    private static List<LevelInformation> parse(String[] args) {
        List<LevelInformation> info = new ArrayList<LevelInformation>();
        for (String s : args) {
            switch (s) {
                case "1":
                // level 1
                info.add(new DirectHit());
                break;
                case "2":
                // level 2
                info.add(new WideEasy());
                break;
                case "3":
                    // level 3
                    info.add(new Green3());
                case "4":
                    // level 4
                    info.add(new FinalFour());
                break;
                default:
                    // do nothing - invalid argument for level
            }

        }
        // in case no valid arguments, add default levels 1,2,3,4.
        if (info.size() == 0) {
            info.add(new DirectHit());
            info.add(new WideEasy());
            info.add(new Green3());
            info.add(new FinalFour());
        }
        return info;
    }

    static private Counter getHighscore() {
                
        Counter highscore = new Counter();
        File file = new File("highscores");
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                highscore.increase(scanner.nextInt()); 
                scanner.close();
            }
        } catch (Exception ex) {
            // nothing to do
            ;
        }
        
        return highscore;
    }

    static private void updateHighscore(Counter score) {
        
        File file = new File("highscores");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(Integer.toString(score.getValue()));
            fw.close();
            
        } catch (Exception ex) {
            // nothing to do
            ;
        }       
    }
}