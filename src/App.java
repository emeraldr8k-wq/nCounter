import java.util.Scanner;

public class App {
    private static final Object lock = new Object();
    private static int currentThread = 0;
    private static int n;
    private static int totalThread;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите кол во потоков");
        totalThread = sc.nextInt();
//        System.out.println("Введите кол во повторений");
//        n = sc.nextInt();

//        for (int i = 0; i < n; i++) {
            for (int j = 0; j < totalThread; j++) {
                final int myNumber = j;

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            synchronized (lock) {

                                if (currentThread != myNumber) {
                                    try {
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }

                                }  else {
                                    System.out.println("Поток " + myNumber);
//                                    n -= currentThread >= n ?(currentThread / n): 0;
                                    currentThread = (currentThread + 1) % totalThread;

                                }


                                lock.notifyAll();

                            }
                        }
                    }
                });

                thread.start();
            }

//        }


    }
}