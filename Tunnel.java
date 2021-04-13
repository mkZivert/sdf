package Racing;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage{
    final static Semaphore semaphore = new Semaphore(Racing.CARS_COUNT/2);
    public Tunnel(){
        this.lenght = 80;
        this.description = "Тоннель " + lenght + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовиться к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап " + description);
                Thread.sleep(lenght / c.getSpeed() * 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(c.getName() + " закончил этап " + description);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
