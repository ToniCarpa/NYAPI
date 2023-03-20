package com.example.nytbrowser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nytbrowser.R;
import com.example.nytbrowser.model.Result;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//2nd Version Fragments

// Intent=actdetail ->JSON search

public class MainActivity extends AppCompatActivity {
    protected static String KEY = "&api-key=dXBzPLFpXkah7d4rrIYATADRGjoPEpIb";
    protected static String JSON_URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=";
    private List results;
    String textInput;

    private ImageButton buttonMain;
    private TextInputEditText textInputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HOOK
        textInputEditText = findViewById(R.id.textInputMain);
        buttonMain = findViewById(R.id.butMainSearch);

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInput = String.valueOf(textInputEditText.getText());

                getJson();
            }
        });
    }

    private void getJson() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                JSON_URL+textInput+KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = response.optString("status");
                        String num_results = response.optString("num_results");

                        if (status.equals("200")) {
                            try {
                                JSONArray jsonArray = response.optJSONArray("results");
                                List<Result> results = Arrays.asList(new GsonBuilder().create().fromJson(jsonArray.toString(), Result[].class));
                                num_results = String.valueOf(results.size());
                                status = "OK";
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putParcelableArrayListExtra("results", (ArrayList <Result>) results);
                            intent.putExtra("status", status);
                            intent.putExtra("num_results", num_results);
                            intent.putExtra("word",textInput);
                            startActivity(intent);
                        } else {
                            status = "NOT DATA";
                            num_results = "EMPTY";
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });
    }
}