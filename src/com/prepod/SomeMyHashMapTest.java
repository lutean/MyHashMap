package com.prepod;

import org.junit.Assert;
import org.junit.Test;

public class SomeMyHashMapTest {

    @Test
    public void testEmptyMap() {
        SomeMyHashMap map = new SomeMyHashMap();
        Assert.assertEquals(0, map.size());
        Assert.assertEquals(null, map.get(22));
        Assert.assertEquals(null, map.get(null));
    }

    @Test
    public void testPut() throws Exception {
        SomeMyHashMap map = new SomeMyHashMap();
        Assert.assertEquals(Long.valueOf(1L), map.put(1, 1L));
        Assert.assertEquals(Long.valueOf(0L), map.put(0, 0L));
        Assert.assertEquals(Long.valueOf(11L), map.put(11, 11L));
        Assert.assertEquals(Long.valueOf(12L), map.put(12, 12L));
        Assert.assertEquals(Long.valueOf(101L), map.put(1, 101L));
        Assert.assertEquals(Long.valueOf(0L), map.put(null, 0L));
    }

    @Test
    public void testGet() throws Exception {
        SomeMyHashMap map = new SomeMyHashMap();
        map.put(1, 10L);
        map.put(null, 1L);
        map.put(2, null);
        map.put(1, 1L);
        map.put(11,11L);
        map.put(10, 10L);
        map.put(null, 11L);
        map.put(111, 111L);
        map.put(null, 12L);
        map.put(10, 1L);

        Assert.assertEquals(Long.valueOf(1L), map.get(1));
        Assert.assertEquals(null, map.get(5));
        Assert.assertEquals(Long.valueOf(12L), map.get(null));
        Assert.assertEquals(null, map.get(2));
        Assert.assertEquals(Long.valueOf(1L), map.get(10));
    }

    @Test
    public void testRemove() throws Exception {
        SomeMyHashMap map = new SomeMyHashMap();

        map.put(1, 10L);
        map.put(3, 30L);
        map.put(4, 40L);
        map.put(8, 80L);

        Assert.assertEquals(true, map.remove(4));
        Assert.assertEquals(false, map.remove(null));
        Assert.assertEquals(false, map.remove(111));
        Assert.assertEquals(true, map.remove(1));
        Assert.assertEquals(true, map.remove(3));

        Assert.assertEquals(null, map.get(4));
        Assert.assertEquals(Long.valueOf(80L), map.get(8));
        Assert.assertEquals(false, map.remove(4));
        Assert.assertEquals(false, map.remove(1));
        Assert.assertEquals(false, map.remove(3));
        Assert.assertEquals(1, map.size());
    }

    @Test
    public void testOverload() throws Exception {

        SomeMyHashMap map = new SomeMyHashMap(5, 0.75f);

        map.put(0, 0L);
        map.put(6, 6L);
        map.put(9, 9L);
        map.put(11,11L);
        map.put(1, 10L);
        map.put(null, 1L);
        map.put(2, null);
        map.put(1, 1L);
        map.put(11,11L);
        map.put(10, 10L);
        map.put(null, 11L);
        map.put(111, 111L);
        map.put(null, 12L);
        map.put(10, 1L);

        Assert.assertEquals(Long.valueOf(11L), map.get(11));
        Assert.assertEquals(Long.valueOf(1L), map.get(10));
        Assert.assertEquals(Long.valueOf(12L), map.get(null));

    }


}