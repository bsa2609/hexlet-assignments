package exercise;

// BEGIN
public class MinThread extends Thread {
    private final int[] numbers;
    private int minNumber;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        minNumber = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minNumber) {
                minNumber = numbers[i];
            }
        }
    }

    public int getMinNumber() {
        return minNumber;
    }
}
// END
