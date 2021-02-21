// ID: 323537779
package game;

/**
 * this class represents a counter (it count things (; ), it has an increase and decrease methods.
 */
public class Counter {

    private int count;

    /**
     * Constructor of the Counter class.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     * @param number number to increase number by.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     * @param number number to decrease number by.
     * @param number
     */
    public void decrease(int number) {
        this.count -= number;

    }

    /**
     * get current count.
     * @return count
     */
    public int getValue() {
         return count;
    }

    /**
     * Set count's value.
     * @param value count's new value
     */
    public void setValue(int value) {
        this.count = value;
   }
   

}