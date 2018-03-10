package com.ivzb.semaphore.auth.ui;

import android.content.Context;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.LoadCallback;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.data.generators.DefaultGenerator;
import com.ivzb.semaphore._base.ui.DefaultPresenterTest;
import com.ivzb.semaphore.auth.data.AuthDataSource;
import com.ivzb.semaphore.auth.data.AuthEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AuthPresenterTest
        extends DefaultPresenterTest<AuthContract.Presenter, AuthContract.View, AuthDataSource> {

    protected static final String sAuthFailure = "Could not authenticate.";

    @Mock
    protected AuthContract.View mView;

    @Mock
    protected AuthDataSource mDataSource;

    @Override
    public AuthContract.View getView() {
        return mView;
    }

    @Override
    public AuthDataSource getDataSource() {
        return mDataSource;
    }

    @Override
    public AuthContract.Presenter initPresenter(
            Context context,
            AuthContract.View view,
            AuthDataSource dataSource) {

        return new AuthPresenter(context, view, dataSource);
    }

    private AuthEntity initEntity() {
        return new AuthEntity("email", "password");
    }

    @Test
    public void testLogin_initiallyInactiveView() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                true,
                false,
                true);

        // act
        mPresenter.login(expected);

        // assert
        verify(getView()).isActive();
    }

    @Test
    public void testLogin_successfulCallbackInactiveView() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                true,
                true,
                false);

        // act
        mPresenter.login(expected);

        // assert
        assertCallbackInactiveView(expected, Type.Login);
    }

    @Test
    public void testLogin_failureCallbackInactiveView() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                false,
                true,
                false);

        // act
        mPresenter.login(expected);

        // assert
        assertCallbackInactiveView(expected, Type.Login);
    }

    @Test
    public void testLogin_failure() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                false,
                true,
                true);

        // act
        mPresenter.login(expected);

        // assert
        assertFailureAuth(expected, Type.Login);
    }

    @Test
    public void testLogin_successful() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                true,
                true,
                true);

        // act
        mPresenter.login(expected);

        // assert
        assertSuccessfulAuth(expected, Type.Login);
    }

    @Test
    public void testRegister_initiallyInactiveView() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                true,
                false,
                true);

        // act
        mPresenter.register(expected);

        // assert
        verify(getView()).isActive();
    }

    @Test
    public void testRegister_successfulCallbackInactiveView() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                true,
                true,
                false);

        // act
        mPresenter.register(expected);

        // assert
        assertCallbackInactiveView(expected, Type.Register);
    }

    @Test
    public void testRegister_failureCallbackInactiveView() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                false,
                true,
                false);

        // act
        mPresenter.register(expected);

        // assert
        assertCallbackInactiveView(expected, Type.Register);
    }

    @Test
    public void testRegister_failure() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                false,
                true,
                true);

        // act
        mPresenter.register(expected);

        // assert
        assertFailureAuth(expected, Type.Register);
    }

    @Test
    public void testRegister_successful() {
        // arrange
        AuthEntity expected = initEntity();

        arrangeAuth(
                expected,
                true,
                true,
                true);

        // act
        mPresenter.register(expected);

        // assert
        assertSuccessfulAuth(expected, Type.Register);
    }

    private void arrangeAuth(
            final AuthEntity auth,
            final Boolean isSuccessful,
            final Boolean initiallyInactiveView,
            final Boolean callbackInactiveView) {

        when(getView().isActive()).thenReturn(initiallyInactiveView);

        Stubber answer = doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SaveCallback<String> callback = (SaveCallback<String>) invocation.getArguments()[1];

                when(getView().isActive()).thenReturn(callbackInactiveView);

                if (isSuccessful) {
                    Result<String> result = new Result<>("token here", "message here");
                    callback.onSuccess(result);
                    return null;
                }

                callback.onFailure(sAuthFailure);
                return null;
            }
        });

        answer.when(getDataSource()).login(
            eq(auth),
            isA(SaveCallback.class));

        answer.when(getDataSource()).register(
                eq(auth),
                isA(SaveCallback.class));
    }

    private enum Type {
        Login,
        Register
    }

    private void assertSuccessfulAuth(AuthEntity auth, Type type) {
        verify(getView(), times(2)).isActive();
        verify(getView()).hideErrorMessage();
        verify(getView()).showLoading(eq(true));

        switch (type) {
            case Login:
                verify(getDataSource()).login(eq(auth), isA(SaveCallback.class));
                break;
            case Register:
                verify(getDataSource()).register(eq(auth), isA(SaveCallback.class));
                break;
        }

        verify(getView()).navigateToHome();
        verify(getView()).showLoading(eq(false));
    }

    private void assertFailureAuth(AuthEntity auth, Type type) {
        verify(getView(), times(2)).isActive();
        verify(getView()).hideErrorMessage();
        verify(getView()).showLoading(eq(true));

        switch (type) {
            case Login:
                verify(getDataSource()).login(eq(auth), isA(SaveCallback.class));
                break;
            case Register:
                verify(getDataSource()).register(eq(auth), isA(SaveCallback.class));
                break;
        }

        verify(getView()).showLoading(eq(false));
        verify(getView()).setErrorMessage(anyString());
        verify(getView()).showErrorMessage(eq(true));
    }

    private void assertCallbackInactiveView(AuthEntity expected, Type type) {
        verify(getView(), times(2)).isActive();
        verify(getView()).hideErrorMessage();
        verify(getView()).showLoading(eq(true));

        switch (type) {
            case Login:
                verify(getDataSource()).login(eq(expected), isA(SaveCallback.class));
                break;
            case Register:
                verify(getDataSource()).register(eq(expected), isA(SaveCallback.class));
                break;
        }
    }
}

