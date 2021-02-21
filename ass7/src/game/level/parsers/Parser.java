// ID: 323537779
package game.level.parsers;

public interface Parser {
    void parse(String toParse, BlockCreator creator) throws Exception;
}