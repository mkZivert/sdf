package Racing;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Racing {

    public static final int CARS_COUNT = 4;


    public static void main(String[] args) {
        System.out.println("Важное объявление >>> Подготовка!!!");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT + 1,() ->
                System.out.println("Важное объявление >>> Гонка началась!!!"));
        CountDownLatch cdl = new CountDownLatch(CARS_COUNT);
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrier,cdl);
        }
        for (int i = 0; i < CARS_COUNT; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        try {
            cdl.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("Важное объявление >>> Гонка закончилась!!!");
        }

    }
}
