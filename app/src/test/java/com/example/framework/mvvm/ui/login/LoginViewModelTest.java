

package com.example.framework.mvvm.ui.login;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.example.framework.mvvm.utils.rx.TestSchedulerProvider;
import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.data.model.api.LoginRequest;
import com.example.framework.mvvm.data.model.api.LoginResponse;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LoginViewModelTest {

    @Mock
    LoginNavigator mLoginCallback;
    @Mock
    DataManager mMockDataManager;
    private LoginViewModel mLoginViewModel;
    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mLoginViewModel = new LoginViewModel(mMockDataManager, testSchedulerProvider);
        mLoginViewModel.setNavigator(mLoginCallback);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        mLoginViewModel = null;
        mLoginCallback = null;
    }

    @Test
    public void testServerLoginSuccess() {
        String email = "dummy@gmail.com";
        String password = "password";

        LoginResponse loginResponse = new LoginResponse();

        doReturn(Single.just(loginResponse))
                .when(mMockDataManager)
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password));

        mLoginViewModel.login(email, password);
        mTestScheduler.triggerActions();

        verify(mLoginCallback).openMainActivity();
    }
}
