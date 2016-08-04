import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by User on 30.07.2016.
 */
public class BookMap {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Map<Integer,Book> map= new HashMap<Integer,Book>();
        map.put(10001,new Book("Tolstoy","Voyna i mir",25));
        map.put(10002,new Book("Drayzer","Stoik",18));
        map.put(10003,new Book("Drayzer","Titan",15));
        Set<Map.Entry<Integer,Book>> me=map.entrySet();
        for (Map.Entry<Integer,Book> m:me){
            System.out.print(m.getKey() + " - " + m.getValue());
        }
        System.out.println();
        Set<Integer> keys=map.keySet();
        for (Integer i:keys){
            System.out.print(i + " - " + map.get(i));
        }

        //получение значения private поля!
        Book book=new Book("Vasa", "hello", 56);
        Class c=book.getClass();
        Field price=c.getDeclaredField("price");
        price.setAccessible(true);
        int priceInt=(Integer)price.getInt(book);
        System.out.println(priceInt);
    }
}
