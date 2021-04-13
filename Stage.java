package Racing;

public abstract class Stage {
    protected int lenght;
    protected String description;
    public String getDescription(){
        return description;
        }
        public abstract void go(Car c);
}
