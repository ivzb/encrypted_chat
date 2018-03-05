package com.ivzb.semaphore._base.data._contracts.generators;

import java.util.List;

public interface BaseGenerator<T> {

    T single();
    List<T> multiple(int size);

    T instantiate();
}