public class Main {
    public static final int TIMEOUT = 1000;

    public static void main(String[] args) throws InterruptedException {
        ATS ats = new ATS();

        //Группа операторов
        ThreadGroup callCenter = new ThreadGroup("Call Center");

        for (int i = 0; i < 3; i++) {
            new Thread(null, ats::answerCall, "Оператор " + (i + 1)).start();
        }

        //Входящие вызовы
        for (int i = 0; i < 60; i++) {
            new Thread(null, ats::newCAll).start();
            Thread.sleep(TIMEOUT);
        }
    }
}