package com.ivzb.encrypted_chat._base.data.generators;

import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.config.MockConfig;

import java.util.Locale;
import java.util.Random;

import io.bloco.faker.Faker;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public class DefaultGeneratorConfig implements BaseGeneratorConfig {

    private static BaseGeneratorConfig sInstance;

    private final Random mRandom;
    private final Faker mFaker;

    private DefaultGeneratorConfig(Random random, Faker faker) {
        mRandom = checkNotNull(random);
        mFaker = checkNotNull(faker);
    }

    public static void initialize(Random random, Faker faker) {
        sInstance = new DefaultGeneratorConfig(random, faker);
    }

    public static BaseGeneratorConfig getInstance() {
        return checkNotNull(sInstance);
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public String getId() {
        return String.format(Locale.getDefault(),
                "%d-%d-%d",
                mRandom.nextLong(),
                mRandom.nextLong(),
                mRandom.nextLong());
    }

    @Override
    public String getEmail() {
        return MockConfig.Email;
    }

    @Override
    public String getPassword() {
        return MockConfig.Password;
    }

    @Override
    public String getAuthenticationToken() {
        return MockConfig.Token;
    }

    @Override
    public String getWord() {
        return mFaker.lorem.word();
    }

    @Override
    public String getSentence() {
        return mFaker.lorem.sentence(5);
    }

    @Override
    public int getNumber() {
        return mRandom.nextInt();
    }

    @Override
    public int getNumber(int bound) {
        return Math.max(mRandom.nextInt(bound), 1);
    }

    @Override
    public boolean getBoolean() {
        return mRandom.nextBoolean();
    }

    @Override
    public <T extends Enum<T>> T getEnum(T[] types) {
        int selected = mRandom.nextInt(types.length);
        return types[selected];
    }
}