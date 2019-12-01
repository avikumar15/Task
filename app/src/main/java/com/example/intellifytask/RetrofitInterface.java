package com.example.intellifytask;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitInterface {

    //   @Headers("Cookie: token=****; path=/api/; domain=.services.intellify.in;")
    @GET("api/attendance")
    Call<StudentObjectClass> getAttendanceData(@Query("for") String fory, @Query("student_id") int student_id, @Header("Cookie") String Cookie);
}