import java.util.HashMap;
import java.util.Map;

class DatabaseMemento {
    Map<String, String> map = new HashMap<>();

    public DatabaseMemento(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getState() {
        return this.map;
    }
}

class Database {
    Map<String, String> map = new HashMap<>();

    public void create(String str1, String str2) {
        map.put(str1, str2);
        System.out.println("Data Created Successfully");
    }

    public void update(String str1, String str2) {
        map.put(str1, str2);
        System.out.println("Data Updated Successfully");
    }

    public void delete(String str1, String str2) {
        if (map.containsKey(str1)) {
            map.remove(str1);
            System.out.println("Data Deleted Successfully");
        } else {
            System.out.println("No Records Found");
        }
    }

    public DatabaseMemento createMemento() {
        System.out.println("Memento Created Successfully");
        return new DatabaseMemento(new HashMap<>(map));
    }

    public void restoreMemento(DatabaseMemento memento) {
        this.map = new HashMap<>(memento.getState());
        System.out.println("Memento Restored Successfully");
    }

    public void printMap()
    {
        System.out.println("Users Data");
        for(Map.Entry<String, String> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
    }
}

class DatabaseManager {
    DatabaseMemento backup;

    public DatabaseManager() {
        this.backup = null;
    }

    public void beginTransaction(Database d) {
        System.out.println("Transaction Begin.....");

        if (backup != null) {
            backup = null;
        }
        this.backup = d.createMemento();
    }

    public void commitTransaction() {
        System.out.println("Commit Transaction");

        if (backup != null) {
            backup = null;
        }

        System.out.println("Transaction Committed Successfully");
    }

    public void rollbackTransaction(Database d) {
        System.out.println("RollBack Transaction");

        if (backup != null) {
            d.restoreMemento(backup);

            backup = null;
        }

        System.out.println("Trasaction Rollback Successfully Backup Restore");
    }
}

class Memento
{
    public static void main(String[] args) {
        Database db = new Database();
        DatabaseManager dm = new DatabaseManager();

        dm.beginTransaction(db);
        db.create("Jayesh", "Kfintech");
        db.create("Sunil", "SRE");

        dm.commitTransaction();

        db.printMap();

        dm.beginTransaction(db);
        db.create("Vignesh", "TeamLead");
        db.create("Hardik", "SSE");

        db.printMap();

        System.out.println("Error Occured ");

        dm.rollbackTransaction(db);

        db.printMap();


    }
}