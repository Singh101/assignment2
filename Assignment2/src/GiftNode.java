// Instructor-provided support class.
// You should not modify this code!

/**
 * Each GiftNode object represents a single node in a linked
 * list for white elephant gift exchange.
 */
public class GiftNode {
    public String name;   // this person's name
    public String gift;   // their chosen gift (null if none)
    public GiftNode next; // next node in list (null if none)

    /**
     * Constructs a new node to store the given name and no
     * next node.
     */
    public GiftNode(String name) {
        this(name, null);
    }

    /**
     * Constructs a new node to store the given name and a
     * reference to the given next node.
     */
    public GiftNode(String name, GiftNode next) {
        this.name = name;
        this.next = next;
        constructorCount++;
    }

    // code below this point is for MSCI 240 grading only
    private static int constructorCount = 0;

    public static void resetCount() {
        constructorCount = 0;
    }

    public static int getCount() {
        return constructorCount;
    }
}

