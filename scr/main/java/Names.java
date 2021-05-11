import java.lang.reflect.Array;
import java.util.*;

public class Names {

   static private HashMap<String, String> map;
    public Names() {
        map = new HashMap<>();

    }

    public synchronized void add(String login, String pass) {
        map.put(login, pass);
    }

    public synchronized String[] getNamesStrings() {
        return map.toString().split("\n");
    }

    public synchronized void delete(String name){
        map.remove(name);
    }

    public synchronized void reset() {
        map.clear();
    }

    public static synchronized HashMap<String, String> getBase(){
        return map;
    }
}
