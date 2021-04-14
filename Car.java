package Racing;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;



public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static boolean WinnerFound;
    private CyclicBarrier cyclicBarrier;
    private CountDownLatch cdl;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cyclicBarrier, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        this.cyclicBarrier = cyclicBarrier;
        this.cdl = cdl;
        CARS_COUNT++;
        this.name = "Участник " + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        cdl.countDown();
        Winner(this);
    }

    private static synchronized void Winner(Car c){
        if(!WinnerFound){
            System.out.println(c.name + " выиграл!");
            WinnerFound = true;
        }
    }
}

