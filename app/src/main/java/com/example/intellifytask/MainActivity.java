package com.example.intellifytask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    StudentObjectClass studentObject;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    EditText stud_id;
    Button fetch;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.DataRecycler);
        stud_id = (EditText) findViewById(R.id.stud_id);
        fetch = (Button) findViewById(R.id.fetch);

        studentObject = new StudentObjectClass();
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter(new StudentObjectClass());
        recyclerView.setAdapter(recyclerAdapter);
    }

    // to fetch data by API call
    private void fetchData(final int num) {
        Toast.makeText(this, "Fetching...", Toast.LENGTH_LONG).show();

        // declaring a client with a cookie header to test if it works. currently commented.

        /*OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        System.out.println("interceptor called");
                        final Request authorized = original.newBuilder()
                                .addHeader("Cookies", "token=*****") //; path=/api/; domain=.services.intellify.in;")
                                .build();

                        return chain.proceed(authorized);
                    }
                })
                .build();*/

        // declaring another client with cookie header. currently commented.

        /*OkHttpClient client2 = new OkHttpClient().newBuilder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                final ArrayList<Cookie> oneCookie = new ArrayList<>(1);
                oneCookie.add(createNonPersistentCookie());
                return oneCookie;
            }

            private Cookie createNonPersistentCookie() {
                System.out.println("called");
                return new Cookie.Builder()
                        .domain("services.intellify.in")
                        .path("/api/")
                        .name("token")
                        .value("***;")
                        .httpOnly()
                        .secure()
                        .build();
            }
        }).build();*/


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://services.intellify.in/")
                .addConverterFactory(GsonConverterFactory.create())
                //        .client(client)           commented
                //        .client(client2)          commented
                .build();


        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<StudentObjectClass> call = retrofitInterface.getAttendanceData("AllClassAttendance", num, "token=****; path=/api/; domain=.services.intellify.in;");

        call.enqueue(new Callback<StudentObjectClass>() {
            @Override
            public void onResponse(Call<StudentObjectClass> call, Response<StudentObjectClass> response) {
                if (!response.isSuccessful()) {
                    Log.e("Fail", "Server Error.");
                    dummyToTestRecyclerView(num);
                    return;
                }

                try {
                    StudentObjectClass DataList = response.body();
                    Log.d("Message", "The message is - " + DataList.getMessage());

                    for (int i = 0; i < DataList.getAttendance().size(); i++) {
                        studentObject.setMessage(DataList.getMessage());
                        studentObject.setAttendance(DataList.getAttendance().get(i).getClassName(), DataList.getAttendance().get(i).getTotalLectures(), DataList.getAttendance().get(i).getPresent(), DataList.getAttendance().get(i).getAbsent(), DataList.getAttendance().get(i).getSick(), DataList.getAttendance().get(i).getLate());
                    }
                    updateText();
                } catch (Exception e) {
                    Log.e("Fetching Exception", e.getMessage().toString());
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<StudentObjectClass> call, Throwable t) {
                System.out.println("Failed to fetch");
                Log.e("Fail", t.toString());
                dummyToTestRecyclerView(num);
            }
        });

    }

    // called if the request fails.
    private void dummyToTestRecyclerView(int k) {
        for (int i = 0; i < k; i++) {
            studentObject.setMessage("Dummy" + i);
            studentObject.setAttendance("Dummy" + (i + 1), i + 1, i + 1, i + 1, i + 1, i + 1);
        }
        Log.d("dummy", "Dummy data inserted");
        updateText();
    }

    // updates the recyclerview's item with the data.
    private void updateText() {
        recyclerAdapter = new RecyclerAdapter(studentObject);
        recyclerView.setAdapter(recyclerAdapter);

        Log.d("tag", "Text updated");
        studentObject = new StudentObjectClass();                       // to make sure the object becomes empty again.
    }

    // called when button is clicked
    public void Clicked(View view) {
        if (stud_id.getText().toString().equals("")) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        } else {
            int k = Integer.parseInt(stud_id.getText().toString());
            fetchData(k);
        }

    }
}