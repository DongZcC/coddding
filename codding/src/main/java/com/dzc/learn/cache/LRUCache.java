package com.dzc.learn.cache;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final int DEFAULT_SIZE = 1000;

    private static final float DEFAULT_LOAD_FACTOR = .75f;

    private final Lock lock = new ReentrantLock();

    private volatile int maxCapacity;

    public LRUCache(int capacity) {
        super(16, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = capacity;
    }

    public LRUCache() {
        this(DEFAULT_SIZE);
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key ,value);
        } finally {
            lock.unlock();
        }
    }


    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        try {
            lock.lock();
            return super.remove(key);
        } finally {
            lock.unlock();
        }
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(5);
        cache.put("1", "1");
        cache.put("2", "1");
        cache.put("3", "1");
        cache.put("4", "1");
        cache.put("5", "1");
        cache.get("1");
        cache.put("6", "1");

        System.out.println(cache);
    }

}
