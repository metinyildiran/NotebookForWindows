import java.util.ArrayList;

public class Model {

    private static ArrayList<String> notes;
    private static ArrayList<String> titles;
    private static ArrayList<String> uuids;

    public Model(){
        notes = new ArrayList<>();
        titles = new ArrayList<>();
        uuids = new ArrayList<>();
    }

    public void update(String note, String title, String uuid){
        notes.add(note);
        titles.add(title);
        uuids.add(uuid);
    }

    public static void removeAll(){
        notes.clear();
        titles.clear();
        uuids.clear();
    }

    public static ArrayList<String> getNotes() {
        return notes;
    }

    public static ArrayList<String> getTitles() {
        return titles;
    }

    public static ArrayList<String> getUuids() {
        return uuids;
    }

    public static int getSize(){
        return notes.size();
    }
}
