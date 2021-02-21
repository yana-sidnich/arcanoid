// ID: 323537779
package game.level.parsers;

import java.awt.Color;

import game.Block;
import game.fill.ColorFill;
import game.fill.Fill;

public class FileBlockCreator implements BlockCreator {
    
    int height;
    int width;
    Color stroke;
    Character key;
    Fill fill;    
    public FileBlockCreator() {
        this.height = 0;
        this.width = 0;
        this.stroke = Color.black;
        this.fill = null;
        this.key = null;
    } 
    public FileBlockCreator(FileBlockCreator other) {
        this.height = other.height;
        this.width = other.width;
        this.stroke = other.stroke;
        this.key = 0;
        this.fill = other.fill;
    }

    public Color getStroke() {
        return this.stroke;
    }
    public void setStroke(Color c) {
        this.stroke = c;
    }

    public int getHeight() {
        return this.height;
    }
    public void setHeight(int h) {
        this.height = h;
    }

    public int getWidth() {
        return this.width;
    }
    public void setWidth(int w) {
        this.width = w;
    }

    public Fill getFill() {
        return this.fill;
    }
    public void setFill(Fill f) {
        this.fill = f;
    }

    
    public int getKey() {
        return this.key;
    }
    public void setKey(Character k) {
        this.key = k;
    }

    public Block create(int xpos, int ypos) {
        return null;
    }
}