package Racing;



public class Road extends Stage{
    public Road(int length){
        this.lenght = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап " + description);
            Thread.sleep(lenght / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап " + description);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
