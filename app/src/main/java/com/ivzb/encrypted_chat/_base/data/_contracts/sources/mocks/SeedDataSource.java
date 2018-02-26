package com.ivzb.encrypted_chat._base.data._contracts.sources.mocks;

import java.util.List;

public interface SeedDataSource<T> {

    List<T> seed(String containerId, int size);
}