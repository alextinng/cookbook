package com.jokes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LruCache<T> extends LinkedHashMap<String, CacheableObject<T>> {
    // 对象缓存时间，默认1小时
    private Long cacheMillis;

    LruCache() {
        cacheMillis = TimeUnit.HOURS.toMillis(1);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<String, CacheableObject<T>> eldest) {
        Long createTime = eldest.getValue().getCreateTime();

        return System.currentTimeMillis() - createTime > cacheMillis;
    }

    public Long getCacheMillis() {
        return cacheMillis;
    }

    public void setCacheMillis(Long cacheMillis) {
        this.cacheMillis = cacheMillis;
    }
}
