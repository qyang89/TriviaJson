package com.example.triviajson;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.triviajson.pojo.TriviaQuestions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    //variables on play screen
    Button btnSubmit, btnForfeit, btnCheat, btnWinner;
    EditText etAnswer;
    TextView tvTitle, tvScore, showAnswer, tvQuestion, tvCluesCount, tvHint, tvUpdatedAt, tvCreatedAt, tvId, invalidCount, tvValue, tvGameId;
    private List<TriviaQuestions> questions;
   // private List<TriviaQuestions> answers;
  //  private List<TriviaQuestions>
    int currentQuestion = 0;
    int currentAnswer=0;
    int totalValue=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        btnCheat = findViewById(R.id.btn_cheat);
        btnSubmit = findViewById(R.id.btn_submit);     //these are the button/fields that are passed
        btnForfeit = findViewById(R.id.btn_forfeit);
        etAnswer = findViewById(R.id.et_answer);
        tvQuestion = findViewById(R.id.tv_question);
        showAnswer=findViewById(R.id.show_answer);
        tvValue=findViewById(R.id.tv_value);
        tvScore=findViewById(R.id.tv_score);
        btnWinner=findViewById(R.id.btn_winner);

        volleyRequest(10);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etAnswer.getText().toString().equalsIgnoreCase(questions.get(currentQuestion).getAnswer())){
                    // Correct answer
                    totalValue=totalValue+questions.get(currentQuestion).getValue(); //adding value(pts)
                    currentQuestion++;
                   // currentAnswer++;
                    loadNextQuestion();
                    showCurrentValue();
                    showTotalScore();
                    if(totalValue>=1000){
                        Intent intent = new Intent(getApplicationContext(), WinnerActivity.class);
                        startActivity(intent);
                    }

                } else {
                    // Wrong answer
                    Toast.makeText(GameActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnForfeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        btnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showAnswer.setVisibility(View.VISIBLE);
            showCurrentAnswer();
            questions.get(currentQuestion).setValue(0);
            showCurrentValue();
                    Toast.makeText(GameActivity.this, "you cheated, but that's OK" , Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void volleyRequest(int count) {

        //1.setup url
        String baseURL = "http://jservice.io/api/random";
        String query = "?count=" + count;
        String url = baseURL + query;


        //2. declare requestqueue Array instance and init it with volley.newrequestqueue()
        RequestQueue requestQueue = Volley.newRequestQueue(GameActivity.this);

        //3. declare jsonarrayrequest or jasonArrayrequest (depends on your .json file structure)
        //{} is Array,  [] is array
        //then init it with new json

        JsonArrayRequest request = new JsonArrayRequest(
                url, //param 1: the url string
                new Response.Listener<JSONArray>() {  //param 2: success listener
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: length is " + response.length());

// This creates the type of data we are expecting back from the json
                        Type listType = new TypeToken<ArrayList<TriviaQuestions>>() {
                        }.getType();
// Gson converts the json to the type we specified above
                        questions = new Gson().fromJson(response.toString(), listType);
                      //  Log.d(TAG, "question: "+questions);
                        loadNextQuestion();
                        showCurrentAnswer();
                        showCurrentValue();
                        showTotalScore();


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

    private void showTotalScore() {
        tvScore.setText(String.valueOf(totalValue));
    }

    //convert INT to STRING, value = int
    private void showCurrentValue() {
        tvValue.setText(String.valueOf(questions.get(currentQuestion).getValue()));

    }

    private void loadNextQuestion() {
    tvQuestion.setText(questions.get(currentQuestion).getQuestion());
    }


    private void  showCurrentAnswer(){
        showAnswer.setText(questions.get(currentQuestion).getAnswer());

    }


}


