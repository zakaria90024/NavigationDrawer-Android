package com.androwep.androidlearingapp.util.remote.retrofit;



import com.androwep.androidlearingapp.util.remote.model.CompileResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RemoteApiInterface {

    @POST("execute")
    Call<CompileResponse> executeCode(@Body RequestBody params);

}
