package com.example.triviajson;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QuestionAdapter.OnQuestionClicked {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<Question> questions;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpURLConnection httpURLConnection;
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        volleyRequest(10);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        //  QuestionResponseAsyncTask task = new QuestionResponseAsyncTask(this);
        //   task.execute("http://jservice.io/api/random");
    }

    @Override
    public void questionClicked(String url) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("PassingUrls", url);
        startActivity(intent);


    }


    public void volleyRequest(int count) {

        //1.setup url
        String baseURL = "http://jservice.io/api/random";
        String query = "?count=" + count;
        String url = baseURL + query;


        //2. declare requestqueue Array instance and init it with volley.newrequestqueue()
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //3. declare jsonarrayrequest or jasonArrayrequest (depends on your .json file structure)
        //{} is Array,  [] is array
        //then init it with new json

        JsonArrayRequest request = new JsonArrayRequest(
                url, //param 1: the url string
                new Response.Listener<JSONArray>() {  //param 2: success listener
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: length is " + response.length());

                        //option 1

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                // Get the array of result from the response
                                JSONArray jsonArray = (JSONArray) response.getJSONArray(Integer.parseInt("results"));

// This creates the type of data we are expecting back from the json
                                Type listType = new TypeToken<ArrayList<Question>>() {
                                }.getType();
// Gson converts the json to the type we specified above
                                questions = new Gson().fromJson(jsonArray.toString(), listType);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage());

                    }
                }
        );
        //4. pass the request Array from step 3 into the requestqueue Array from step 2.
        requestQueue.add(request);


    }
}
