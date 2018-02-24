package com.ivzb.encrypted_chat._base.data._contracts.generators;

public interface BaseGeneratorConfig {

    String getId();
    String getEmail();
    String getPassword();
    String getAuthenticationToken();

    String getWord();
    String getSentence();

    int getNumber();
    int getNumber(int bound);

    <T extends Enum<T>> T getEnum(T[] types);
}