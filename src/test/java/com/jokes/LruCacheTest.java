package com.jokes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class LruCacheTest {
    private LruCache<Integer> lruCache;
    private Gson gson;

    @Before
    public void setup() {
        lruCache = new LruCache<>();
        lruCache.setCacheSeconds(TimeUnit.SECONDS.toMillis(3));

        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
    public void testCache() throws InterruptedException {
    }

    @Test
    public void xxx() {
    }
}