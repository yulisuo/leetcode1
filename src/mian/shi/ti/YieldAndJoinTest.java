package mian.shi.ti;

public class YieldAndJoinTest {
    public static void main(String[] args) {
//        yieldTest();
//        noJoinTest();
        joinTest();
    }

    private static void yieldTest() {
        YieldRunnable runnable1 = new YieldRunnable();
        YieldRunnable runnable2 = new YieldRunnable();
        Thread thread1 = new Thread(runnable1, "线程1");
        Thread thread2 = new Thread(runnable2, "线程2");

        System.out.println("开始执行");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runnable1.isRunning = false;
        runnable2.isRunning = false;
    }

    private static void noJoinTest() {
        JoinRunnable runnable1 = new JoinRunnable();
        Thread thread1 = new Thread(runnable1, "线程1");

        System.out.println("主线程开始执行！");
        thread1.start();

        System.out.println("主线程执行结束！");
    }

    private static void joinTest() {
        JoinRunnable runnable1 = new JoinRunnable();
        Thread thread1 = new Thread(runnable1, "线程1");

        System.out.println("主线程开始执行！");
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行结束！");
    }
}

class YieldRunnable implements Runnable {

    public volatile boolean isRunning = true;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始执行！");
        int i = 0;
        while (isRunning) {
            if (name.equals("线程2")) {
                System.out.println("\t\t\t\t" + name + "执行了[" + i + "]次");
            } else {
                System.out.println(name + "执行了[" + i + "]次");
            }
            i++;
            //注意，yield是静态方法
            Thread.yield();
        }
        System.out.println(name + "执行结束！");
    }
}

class JoinRunnable implements Runnable {
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始执行！");

        for(int i = 1; i < 6; i++) {
            System.out.println(name + "执行了[" + i + "]次");
        }
    }
}