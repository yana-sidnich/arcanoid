// ID: 323537779
package game.level.parsers;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

public class ColorsParser {
    // black, blue, cyan, gray, lightGray, green, orange, pink, red, white, yellow
    private static final Map<String, Color> colors;
    static {
        colors = new HashMap<String, Color>();
        colors.put("black", Color.black);
        colors.put("blue", Color.blue);
        colors.put("cyan", Color.cyan);
        colors.put("gray", Color.gray);
        colors.put("lightGray", Color.lightGray);
        colors.put("green", Color.green);
        colors.put("orange", Color.orange);
        colors.put("pink", Color.pink);
        colors.put("red", Color.red);
        colors.put("white", Color.white);
        colors.put("yellow", Color.yellow);
    };

    // parse color definition and return the specified color.
    public static Color parse(String toParse) throws Exception {
        if (toParse.charAt(0) != '(' && toParse.charAt(toParse.length() - 1) != ')') {
            throw new RuntimeException("format is unknown - 1");
        }
        String str = toParse.substring(1, toParse.length() - 1);

        if (colors.containsKey(str)) {
            return colors.get(str);
        }
        // if we reach here we should get an RGB format
        if (! str.startsWith("RGB")) {
            throw new RuntimeException("format is unknown - 2");
        }

        str = str.substring("RGB".length());
        if (str.charAt(0) != '(' && str.charAt(str.length() - 1) != ')') {
            throw new RuntimeException("format is unknown - 3");
        }

        str = str.substring(1, toParse.length() - 1);

        String[] splitted = str.split(",");
        if (splitted.length != 3) {
            throw new RuntimeException("format is unknown - 4");
        }
        
        int r = Integer.parseInt(splitted[0]);
        int g = Integer.parseInt(splitted[1]);
        int b = Integer.parseInt(splitted[2]);

        if (! (integarValueValid(r) && integarValueValid(g) && integarValueValid(b))) {
            throw new RuntimeException("format is unknown - 5");

        }

        return new Color(r, g, b);
    }

    static private boolean integarValueValid(int n) {
        return n >= 0 && n <= 255;
    }
 }