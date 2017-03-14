package com.prepod;

import java.util.HashMap;

public class MyHashMap<K, V> {

    private Entry[] table;
    private int size;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int capacity) {
        table = new Entry[capacity];
        HashMap<String, String> map;
    }

    public int size() {
        return this.size;
    }

    public V put(K key, V value) {
        if (null == key) putForNullKey(value);
        int index = calcIndex(key);
        if (checkEquals(index, key)) {
            table[index] = newEntry(key, value);
        } else {
            index = findEmpty(index);
            if (index < 0) return null;
            table[index] = newEntry(key, value);
            size++;
        }
        return value;
    }

    private int findEmpty(int index){
        int i = index;
        do{
            if (table[i].isDeleted() || table[i] == null) return index;
            i = (i + 1) % table.length;
        }while (i != index);
        return -1;
    }

    private Entry<K, V> newEntry(K key, V value) {
        return new Entry<>(key, value);
    }

    private boolean checkEquals(int index, K key) {
        if (table[index] == null) return false;
        return key == table[index].getKey() || key.equals(table[index].getKey());
    }

    private int calcIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    private void putForNullKey(V value) {

    }

    private void addEntry(K key, V value, int hash, int index) {

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
