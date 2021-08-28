import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ATS {
    private Queue<Call> calls = new LinkedBlockingQueue<>();
    private Random random = new Random();
    private static final int MAX = 6000;
    private static final int MIN = 2000;
    public static final int CALL_WAITING = 4000;

    //Поступил звонок
    public void newCAll() {
        calls.offer(new Call());
        System.out.println("Поступил звонок");
    }

    //Отвечаем на звонки
    public void answerCall() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                final Call call = calls.poll();
                if (call != null) {
                    Thread.sleep(random.nextInt(MAX - MIN) + MIN);
                    System.out.printf("Оператор %s обработал вызов, кол-во необработанных вызовов %d\n", Thread.currentThread().getName(), calls.size());
                } else {
                    System.out.printf("Оператор %s может отдохнуть\n", Thread.currentThread().getName());
                    Thread.sleep(CALL_WAITING);
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}