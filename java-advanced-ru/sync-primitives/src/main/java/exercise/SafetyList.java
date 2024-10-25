package exercise;

public class SafetyList {
    // BEGIN
    private int[] items;
    private int maxCount;
    private int itemsCount;
    private final int incrementSizeCount = 16;

    public SafetyList() {
        maxCount = incrementSizeCount;
        items = new int[incrementSizeCount];
        itemsCount = 0;
    }

    public synchronized void add(int item) {
        if (itemsCount == maxCount) {
            int newMaxCount = maxCount + incrementSizeCount;
            int[] newItems = new int[newMaxCount];

            System.arraycopy(items, 0, newItems, 0, maxCount);

            items = newItems;
            maxCount = newMaxCount;
        }

        items[itemsCount] = item;
        itemsCount++;
    }

    public synchronized int get(int index) {
        if (index < itemsCount) {
            return items[index];
        } else {
            throw new RuntimeException(String.format("Index %d out of range", index));
        }
    }

    public synchronized int getSize() {
        return itemsCount;
    }
    // END
}
