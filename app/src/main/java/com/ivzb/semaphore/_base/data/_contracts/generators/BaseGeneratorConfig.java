package com.ivzb.semaphore._base.data._contracts.generators;

import java.util.Date;
import java.util.List;

public interface BaseGeneratorConfig {

    String getId();
    String getEmail();
    String getPassword();
    String getAuthenticationToken();

    Date getDate();

    String getWord();
    List<String> getWords(int size);
    String getSentence();

    int getNumber();
    int getNumber(int bound);

    boolean getBoolean();

    <T extends Enum<T>> T getEnum(T[] types);
}