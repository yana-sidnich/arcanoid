
package game.level.parsers;

import java.util.HashMap;
import java.util.Map;

import game.fill.ColorFill;
import game.fill.ImageFill;

public class BlocksDefinitionReader {
   private static final String SEPERATOR = ":";

   interface BlockCreatorFunc {
      void act(FileBlockCreator fbc, String data) throws Exception;
   }
   
   public static BlocksFromSymbolsFactory fromReader(java.io.BufferedReader reader) {
      BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();

      Map<String, BlockCreatorFunc> commands = new HashMap<>();

      commands.put("symbol" + SEPERATOR, new BlockCreatorFunc() {
            public void act(FileBlockCreator fbc, String data) throws Exception {
               if (data.length() != 1) {
                  throw new Exception("invalid symbol - length is not 1");
               }
               fbc.setKey(data.charAt(0));
            }
         });

      commands.put("width"  + SEPERATOR, new BlockCreatorFunc() {
         public void act(FileBlockCreator fbc, String data) throws Exception {
            fbc.setWidth(Integer.parseInt(data));
         }
      });

      commands.put("fill"  + SEPERATOR, new BlockCreatorFunc() {
         public void act(FileBlockCreator fbc, String data) throws Exception {
            if (data.startsWith("fill:(") && data.endsWith(")")) {
               fbc.setFill(new ImageFill(data.substring("fill:(".length(), data.length() - 1)));
            
            }         

            else if (data.startsWith("color:(") && data.endsWith(")")) {
               fbc.setFill(new ColorFill(ColorsParser.parse(
                              data.substring("color:(".length(), data.length() - 1))));
            }
            else {
               throw new Exception("invalid fill");
            }         
            
         }
      });
      
      commands.put("stroke" + SEPERATOR, new BlockCreatorFunc() {
         public void act(FileBlockCreator fbc, String data) throws Exception {
            if (data.startsWith("color:(") && data.endsWith(")")) {
               fbc.setStroke(ColorsParser.parse(
                              data.substring("color:(".length(), data.length() - 1)));
            }
            else {
               throw new Exception("invalid fill");
            }    
         }
      });
      
      try {
         String line;
         FileBlockCreator defaultCreator = new FileBlockCreator();
         boolean defaultFound = false;
         while ((line = reader.readLine()) != null) {
            // this regex represents blank chaercters
            String[] elements = line.split("\\s+");
            boolean insert = true;
            FileBlockCreator current = new FileBlockCreator(defaultCreator);
            if (elements[0] == "default") {
               if ( defaultFound == true) {
                  throw new Exception("invalid default - exists more then once");
               }
               insert = false;
               current = defaultCreator;
               defaultFound = true;
            }
            if (elements[0] == "") {
               continue;
            }
            
            for (int i = 1; i < elements.length; ++i) {
               for (Pair<String,BlockCreatorFunc> f : commands)
               if ()
            }
            
         }

      }
      catch (Exception ex) {

      }

      return factory;
   }


}