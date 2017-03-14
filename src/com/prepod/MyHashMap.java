package com.prepod;

public class MyHashMap<K, V> {

    private Entry<K, V>[] table;
    private int size;

    public MyHashMap(){
        this(16);
    }

    public MyHashMap(int capacity){
        table = new Entry[capacity];
    }

    public static class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value){
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
    }
    
}
