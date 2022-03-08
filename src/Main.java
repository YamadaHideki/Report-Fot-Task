import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static LongAdder result = new LongAdder();

    public static int[] arrayInt() {
        int[] result = new int[100];
        for (int i = 0; i < 100; i++) {
            result[i] = (int) (Math.random() * 99999);
        }
        return result;
    }

    public static void main(String[] args) {

        Thread firstThread = new Thread(() -> {
            result.add(Arrays.stream(arrayInt()).sum());
        });

        Thread secondThread = new Thread(() -> {
            result.add(Arrays.stream(arrayInt()).sum());
        });

        Thread thirdThread = new Thread(() -> {
            result.add(Arrays.stream(arrayInt()).sum());
        });

        Thread mainThread = new Thread(() -> {
            try {
                firstThread.join();
                secondThread.join();
                thirdThread.join();

                System.out.println(result.sum());
            } catch (InterruptedException ignored) { }
        });

        mainThread.start();

        firstThread.start();
        secondThread.start();
        thirdThread.start();
    }
}
