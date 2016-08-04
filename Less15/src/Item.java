/**
 * Created by User on 29.07.2016.
 */
public class Item<T extends Number> {
    private T x;

    public Item(T x){
        this.x=x;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return x.toString();
    }
}
