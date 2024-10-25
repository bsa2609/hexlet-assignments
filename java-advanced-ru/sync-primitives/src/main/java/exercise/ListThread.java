package exercise;

// BEGIN
public class ListThread extends Thread {
    private final SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    private int getRandom() {
        return (int) Math.round(Math.random() * 1000000);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            safetyList.add(getRandom());
        }
    }
}
// END
