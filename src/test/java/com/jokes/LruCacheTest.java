package com.jokes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class LruCacheTest {
    private LruCache<Integer> lruCache;
    private Gson gson;

    @Before
    public void setup() {
        lruCache = new LruCache<>();
        lruCache.setCacheMillis(TimeUnit.SECONDS.toMillis(3));

        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
    public void testCache() throws InterruptedException {
        lruCache.put("key1", CacheableObject.asCacheableObject(1));
        Assert.assertEquals(1, lruCache.size());

        Thread.sleep(1000);

        lruCache.put("key2", CacheableObject.asCacheableObject(2));
        Assert.assertEquals(2, lruCache.size());

        Thread.sleep(1000);

        lruCache.put("key3", CacheableObject.asCacheableObject(3));
        Assert.assertEquals(3, lruCache.size());

        Thread.sleep(1000);

        lruCache.put("key4", CacheableObject.asCacheableObject(4));
        Assert.assertEquals(3, lruCache.size());

        Thread.sleep(1000);

        lruCache.put("key5", CacheableObject.asCacheableObject(5));
        Assert.assertEquals(3, lruCache.size());

        Thread.sleep(1000);

        lruCache.put("key6", CacheableObject.asCacheableObject(6));
        Assert.assertEquals(3, lruCache.size());
    }
}