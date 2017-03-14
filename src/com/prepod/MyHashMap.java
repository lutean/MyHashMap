package com.prepod;

public class MyHashMap<K, V> {

    private Entry<K, V>[] table;
    private int size;

    MyHashMap() {
        this(16);
    }

    MyHashMap(int capacity) {
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

    public V get(K key) {
        return find(calcIndex(key), key);
    }

    private V find(int index, K key) {
        int i = index;
        do {
            if (table[i] == null) return null;
            if (checkEquals(i, key) && !table[i].isDeleted()) return table[i].getValue();
            i = (i + 1) % table.length;
        } while (i != index);
        return null;
    }

    public boolean remove(K key) {
        int index = calcIndex(key);
        int i = index;
        do {
            if (table[i] == null) return false;
            if (checkEquals(i, key)) {
                if (table[i].isDeleted()) return false;
                table[i].setDeleted(true);
                size--;
                return true;
            }
            i = (i + 1) % table.length;
        } while (i != index);
        return false;
    }

    private int findEmpty(int index, K key) {
        int i = index;
        do {
            if (table[i] == null || table[i].isDeleted() || checkEquals(i, key)) return i;
            i = (i + 1) % table.length;
        } while (i != index);
        return -1;
    }

    private boolean checkEquals(int index, K key) {
        return table[index] != null && (key == table[index].getKey() || key != null && key.equals(table[index].getKey()));
    }

    private int calcIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % table.length);
    }

    private void addEntry(K key, V value, int index) {
        table[index] = new Entry<>(key, value);
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
