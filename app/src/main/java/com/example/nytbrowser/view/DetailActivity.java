package com.example.nytbrowser.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nytbrowser.R;
import com.example.nytbrowser.adapter.AdapterRow;
import com.example.nytbrowser.model.Multimedia;
import com.example.nytbrowser.model.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterRow adapterRow;
    ArrayList<Result> results = new ArrayList<>();

    TextView stats;
    TextView resultat;
    String URLJSON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        stats.findViewById(R.id.secondStatusTxt);
        resultat.findViewById(R.id.secondResultTxt);

        recyclerView = findViewById(R.id.recyclerViewSecond);

        getResultats();
        Intent intent = this.getIntent();
        List<Result> results = intent.getParcelableExtra("results");

        stats.setText(intent.getStringExtra("status"));
        resultat.setText(intent.getStringExtra("num"));
    }

    private void getResultats() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                getURLJSON(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultArray = response.getJSONArray("results");
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject resultObj = resultArray.getJSONObject(i);
                                Result res = new Result();

                                res.setDisplay_title(resultObj.getString("display_title"));
                                res.setByline(resultObj.getString("byline"));
                                res.setHeadline(resultObj.getString("headline"));
                                res.setPublication_date(resultObj.getString("publication_date"));
                                res.setSummary_short(resultObj.getString("summary_short"));
//OPCION 1
                                res.setImage(resultObj.getString("multimedia"));


//OPCION 2
                                JSONObject mediaObject = resultObj.getJSONObject("multimedia");
                                String [] mediaArray = mediaObject.toString().split(",");
                                String img = mediaArray[1];
                                res.setImage(img);
//OPCION 3
                                JSONArray multimediaArray = resultObj.getJSONArray("multimedia");
                                for (int j = 0; j < multimediaArray.length(); j++) {
                                    res.setMultimedias(new Multimedia[]{(Multimedia) multimediaArray.get(j)});
                                }

                                results.add(res);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapterRow = new AdapterRow(getApplicationContext(), results);
                        recyclerView.setAdapter(adapterRow);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }

    private String getURLJSON() {
        Intent intent = this.getIntent();
        String word = intent.getStringExtra("word");
        String key = intent.getStringExtra(MainActivity.KEY);
        String json = intent.getStringExtra(MainActivity.JSON_URL);
        return json + word + key;
    }
}