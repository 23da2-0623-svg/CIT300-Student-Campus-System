import java.util.LinkedList;

class HashNode {
    String key;
    Student value;

    public HashNode(String key, Student value) {
        this.key = key;
        this.value = value;
    }
}

public class StudentHashTable {
    private static final int TABLE_SIZE = 10;
    private LinkedList<HashNode>[] table;

    @SuppressWarnings("unchecked")
    public StudentHashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode()) % TABLE_SIZE;
    }

    public void put(String key, Student value) {
        int index = getHash(key);
        for (HashNode node : table[index]) {
            if (node.key.equalsIgnoreCase(key)) {
                node.value = value;
                return;
            }
        }
        table[index].add(new HashNode(key, value));
    }

    public Student get(String key) {
        int index = getHash(key);
        for (HashNode node : table[index]) {
            if (node.key.equalsIgnoreCase(key)) {
                return node.value;
            }
        }
        return null; // Not found
    }
}