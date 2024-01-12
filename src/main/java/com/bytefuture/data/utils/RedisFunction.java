package com.bytefuture.data.utils;

public interface RedisFunction<Q,T> {
    public T call(Q q);
}
