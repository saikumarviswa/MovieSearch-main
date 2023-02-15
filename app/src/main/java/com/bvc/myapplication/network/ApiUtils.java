package com.bvc.myapplication.network;

public class ApiUtils {

    private ApiUtils() {
    }

    public static APIService getAPIService() {

        return RetrofitClient.getClient().create(APIService.class);
    }
}
