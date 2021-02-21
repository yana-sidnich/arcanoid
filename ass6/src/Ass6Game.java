// ID: 323537779

import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import game.AnimationRunner;
import game.animation.GameLevel;
import game.GameFlow;
import game.level.DirectHit;
import game.level.FinalFour;
import game.level.Green3;
import game.level.LevelInformation;
import game.level.WideEasy;

/**
 * This class will be used to run the game for this assigment.
 * It will contain the main function that will create run the game.
 *
 */
public class Ass6Game {


    /**
     * The main function that will run the game.
     * @param args ignored
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid GameLevel", GameLevel.GAME_GUI_WIDTH, GameLevel.GAME_GUI_HEIGHT);
        AnimationRunner am = new AnimationRunner(gui, GameLevel.GAME_FPS);
        GameFlow f = new GameFlow(am, gui.getKeyboardSensor());
        List<LevelInformation> info = parse(args);
        f.runLevels(info);
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
}