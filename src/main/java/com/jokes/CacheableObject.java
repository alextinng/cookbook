package com.jokes;

public class CacheableObject<T> {
    private final Long createTime;
    private T data;

    public CacheableObject() {
        createTime = System.currentTimeMillis();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public T getData() {
        return data;
    }

    public static <T> CacheableObject<T> asCacheableObject(T data) {
        CacheableObject<T> obj = new CacheableObject<>();
        obj.data = data;

        return obj;
    }
}
