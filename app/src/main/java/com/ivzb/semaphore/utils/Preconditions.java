package com.ivzb.semaphore.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Preconditions {

    public static @NonNull
    <T> T checkNotNull(final T reference) {
        return checkNotNull(reference, "Reference is null.");
    }

    public static @NonNull <T> T checkNotNull(final T reference, @Nullable String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }

        return reference;
    }
}