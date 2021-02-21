// ID: 323537779

/**
 * This class will be used to run the game for this assigment.
 * It will contain the main function that will create run the game.
 *
 */
class Ass3Game {
    /**
     * The main function that will run the game.
     * @param args ignored
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}