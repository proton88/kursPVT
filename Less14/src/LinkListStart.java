/**
 * Created by User on 28.07.2016.
 */
public class LinkListStart {


    public static void main(String[] args) {
        LinkList<String> list = new LinkList();
        list.add("mama");
        list.add("papa");
        list.add("deda");
        System.out.println(list.size());
        int size=list.size();
        for(int i=0; i<size; i++){
            System.out.print(list.remove() + " ");
        }
    }
}
