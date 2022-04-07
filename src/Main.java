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
            for (int value : arrayInt()) {
                result.add(value);
            }
        });

        Thread secondThread = new Thread(() -> {
            for (int value : arrayInt()) {
                result.add(value);
            }
        });

        Thread thirdThread = new Thread(() -> {
            for (int value : arrayInt()) {
                result.add(value);
            }
        });

        firstThread.start();
        secondThread.start();
        thirdThread.start();

        try {
            firstThread.join();
            secondThread.join();
            thirdThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Общая сумма: " + result.sum());
    }
}
