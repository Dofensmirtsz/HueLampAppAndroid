package com.avans.huelampapp.data.remote;

import com.avans.huelampapp.data.model.DeviceBody;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.data.model.SimpleState;
import com.avans.huelampapp.data.model.SuccessResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HueService {

    String API = "api/";

    /*
        API documentation says "app_name#username"
     */
    @POST(API)
    Call<SuccessResponse[]> registerDevice(@Body DeviceBody devicetype);

    @GET(API + "{username}/lights")
    Call<Map<String, Light>> getLights(@Path(value = "username") String username);

    @GET(API + "{username}/lights/{id}")
    Call<Light> getLight(@Path(value = "username") String username, @Path(value = "id") String id);

    @PUT(API + "{username}/lights/{id}/state")
    Call<String> manageLightState(@Path(value = "username") String username, @Path(value = "id") String id, @Body SimpleState state);

    @GET(API + "{username}/lights/{id}/state")
    Call<String> getLightState(@Path(value = "username") String username, @Path(value = "id") String id);


}
