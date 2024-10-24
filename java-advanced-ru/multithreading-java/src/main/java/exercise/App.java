package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        minThread.start();
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " started");

        maxThread.start();
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " started");

        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " finished");

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " finished");

        return Map.of("min", minThread.getMinNumber(), "max", maxThread.getMaxNumber());
    }
    // END
}
