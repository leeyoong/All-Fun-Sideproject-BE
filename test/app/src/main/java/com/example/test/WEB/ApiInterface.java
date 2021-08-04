package com.example.test.WEB;

import com.example.test.DTO.Member.CreateMemberDto;
import com.example.test.DTO.Member.FindEmailDto;
import com.example.test.DTO.Member.FindPasswordDto;
import com.example.test.DTO.Member.ImageDto;
import com.example.test.DTO.Member.LoginDto;
import com.example.test.DTO.Member.MemberDataDto;
import com.example.test.DTO.Member.OneItemDto;
import com.example.test.Response.ResponseJson;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface ApiInterface {



    @POST("/auth/check/email")
    Call<ResponseJson> emailCheck(@Body OneItemDto Object);


    @POST("/auth/check/nickname")
    Call<ResponseJson> nickNameCheck(@Body OneItemDto Object);


    @POST("/auth/login")
    Call<MemberDataDto> login(@Body LoginDto Object);

    //sign up
    @POST("/auth/create")
    Call<ResponseJson> createMember(@Body CreateMemberDto jsonObject);

    //sign up
    @POST("/auth/findid")
    Call<ResponseJson> findID(@Body FindEmailDto Object);



    //send email
    @POST("/auth/findpw")
    Call<ResponseJson> findPW(@Body FindPasswordDto Object);



    //upload image

    //get image
    @GET("images/{apiName}")
    @Streaming
    Call<ImageDto> downloadImage(@Path("apiName") String apiName);


    @Multipart
    @POST("api주소")
    Call<ResponseJson> uploadImage(@Part MultipartBody.Part file);



}
