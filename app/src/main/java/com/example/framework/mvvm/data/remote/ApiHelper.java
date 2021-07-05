package com.example.framework.mvvm.data.remote;

import com.example.framework.mvvm.data.model.api.BlogResponse;
import com.example.framework.mvvm.data.model.api.LoginRequest;
import com.example.framework.mvvm.data.model.api.LoginResponse;
import com.example.framework.mvvm.data.model.api.LogoutResponse;
import com.example.framework.mvvm.data.model.api.OpenSourceResponse;
import io.reactivex.Single;


public interface ApiHelper {

    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    ApiHeader getApiHeader();

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();
}
