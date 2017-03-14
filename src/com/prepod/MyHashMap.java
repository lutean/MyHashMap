package com.prepod;

public class MyHashMap<K, V> {

    private Entry<K, V>[] table;
    private int size;
    private int threshold;
    private float loadFactor;
    private int capacity;

    public MyHashMap() {
        this(16, 0.75f);
    }

    public MyHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        table = new Entry[capacity];
        threshold =  (int)( capacity * loadFactor);
    }

    public int size() {
        return this.size;
    }

    public V put(K key, V value) {
        int index = calcIndex(key);
        if (checkEquals(index, key, table)) {
            putEntry(key, value, index);
        } else {
            if (isOverLoad()) resize(capacity * 2);
            index = findEmpty(index, key, table);
            if (index < 0) return null;
            putEntry(key, value, index);
            size++;
        }
        return value;
    }

    public V get(K key) {
        return find(calcIndex(key), key);
    }

    private V find(int index, K key) {
        int i = index;
        do {
            if (table[i] == null) return null;
            if (checkEquals(i, key, table) && !table[i].isDeleted()) return table[i].getValue();
            i = (i + 1) % capacity;
        } while (i != index);
        return null;
    }

    public boolean remove(K key) {
        int index = calcIndex(key);
        int i = index;
        do {
            if (table[i] == null) return false;
            if (checkEquals(i, key, table)) {
                if (table[i].isDeleted()) return false;
                table[i].setDeleted(true);
                size--;
                return true;
            }
            i = (i + 1) % capacity;
        } while (i != index);
        return false;
    }

    private int findEmpty(int index, K key, Entry[] table) {
        int i = index;
        do {
            if (table[i] == null || table[i].isDeleted() || checkEquals(i, key, table)) return i;
            i = (i + 1) % capacity;
        } while (i != index);
        return -1;
    }

    private boolean checkEquals(int index, K key, Entry[] table) {
        return table[index] != null && (key == table[index].getKey() || key != null && key.equals(table[index].getKey()));
    }

    private int calcIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % capacity);
    }

    private void putEntry(K key, V value, int index) {
        table[index] = new Entry<>(key, value);
    }

    private void resize(int newCapacity) {
        this.capacity = newCapacity;
        Entry[] newTable = new Entry[newCapacity];

        transfer(newTable);

        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(Entry[] newTable){
        for (Entry<K, V> entry : table) {
            if (entry == null) continue;
            int index = calcIndex(entry.getKey());
            if (checkEquals(index, entry.getKey(), newTable)) {
                newTable[index] = entry;
            } else {
                index = findEmpty(index, entry.getKey(), newTable);
                //if (index < 0) return null;
                newTable[index] = entry;
            }
        }
    }


    private boolean isOverLoad(){
        if (size >= threshold) return true;
        return false;
    }


    private static class Entry<K, V> {

        private K key;
        private V value;
        private boolean deleted;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }

        private void setKey(K key) {
            this.key = key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        private boolean isDeleted() {
            return deleted;
        }

        private void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }


}
