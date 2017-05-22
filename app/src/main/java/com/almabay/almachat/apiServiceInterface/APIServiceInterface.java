package com.almabay.almachat.apiServiceInterface;

import com.almabay.almachat.pojo.login_response.ResponseLogin;
import com.almabay.almachat.pojo.UserLogin;
import com.almabay.almachat.pojo.upload_image_response.ResponseUploadImage;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


/**
 * Created by deepakr on 1/27/2016.
 */
public interface APIServiceInterface {

    @POST("almabay_oauth/authorize")
    Call<ResponseLogin> login(@Body UserLogin userLogin); // Sending JSON object in login method and response will be stoed in POJO ResponseLogin

    // /webservice/media?user_id=<user_id>&type=photo&active=1&access_token=<access_token>
    //Uploading Image to server
    @Multipart
    @POST("webservice/media/")
    Call<ResponseUploadImage> uploadImage(@Query("user_id") String userID,
                                          @Query("type") String type,
                                          @Query("active") Integer active,
                                          @Query("access_token") String accessToken,
                                          @Part("image\"; filename=\"MyPhoto.jpg\" ") RequestBody file,
                                          @Part("description") RequestBody description
    );
}
