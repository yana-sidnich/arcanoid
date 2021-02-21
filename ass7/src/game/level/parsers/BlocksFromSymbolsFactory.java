
package game.level.parsers;

import java.util.HashMap;
import java.util.Map;

import game.Block;

public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators = new HashMap<String, BlockCreator>();
    }
    
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
    
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    public void addSpacer(String key, Integer value) {
        spacerWidths.put(key, value);
    }

    public void addblockCreator(String key, BlockCreator value) {
        blockCreators.put(key, value);
    }
}