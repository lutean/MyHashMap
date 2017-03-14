package com.prepod;

public class MyHashMap<K, V> {

    private Entry<K, V>[] table;
    private int size;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int capacity) {
        table = new Entry[capacity];
    }

    public int size() {
        return this.size;
    }

    public V put(K key, V value) {
        int index = calcIndex(key);
        if (checkEquals(index, key)) {
            addEntry(key, value, index);
        } else {
            index = findEmpty(index, key);
            if (index < 0) return null;
            addEntry(key, value, index);
            size++;
        }
        return value;
    }

    public V get(K key){
        return find(calcIndex(key), key);
    }

    private V find(int index, K key){
        int i = index;
        do{
            if (table[i] == null) return null;
            if (checkEquals(i, key) && !table[i].isDeleted()) return table[i].getValue();
            i = (i + 1) % table.length;
        }while (i != index);
        return null;
    }

    private int findEmpty(int index, K key){
        int i = index;
        do{
            if (table[i] == null || table[i].isDeleted() || checkEquals(i, key)) return i;
            i = (i + 1) % table.length;
        }while (i != index);
        return -1;
    }

    private boolean checkEquals(int index, K key) {
        if (table[index] == null) return false;
        return key == table[index].getKey() || key != null && key.equals(table[index].getKey());
    }

    private int calcIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % table.length);
    }

    private void addEntry(K key, V value, int index) {
        table[index] = new Entry<>(key, value);
    }


    public static class Entry<K, V> {

        private K key;
        private V value;
        private boolean deleted;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }


}
