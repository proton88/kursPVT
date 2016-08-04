import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by User on 29.07.2016.
 */
public class MyMap {
    public static void main(String[] args) {
        Map<String, Item<Double>> map=new HashMap<String, Item<Double>>();
        map.put("one",new Item<>(1.0));
        map.put("two",new Item<>(2.0));
        map.put("three",new Item<>(3.0));
        map.put("four", new Item<>(4.0));
        printMyMap(map);
        System.out.println("\n");
        Set<Map.Entry<String,Item<Double>>> me = map.entrySet();
        for (Map.Entry<String, Item<Double>> mapE:me){
            System.out.println(mapE.getKey()+" - "+mapE.getValue());
        }
    }

    public static <E extends Number>void printMyMap(Map<String,Item<E>> map){
        Set<String> keys=map.keySet();
        for (String str:keys){
            System.out.println(str + " - "+map.get(str).getX());
        }
    }
}
