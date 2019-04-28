package edu.illinois.cs.cs125.spring2019.mp5;

import android.os.Bundle;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import edu.illinois.cs.cs125.spring2019.mp5.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.view.inputmethod.InputMethodManager;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import android.widget.ArrayAdapter;


public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private String result;
    private static final String TAG = "MP5:Main";

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;
    TextView hi;
    EditText specialty;
    String sp;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        hi = findViewById(R.id.textView);
        hi.setVisibility(View.GONE);
        specialty = findViewById(R.id.speciality);
        sp = specialty.getText().toString().toLowerCase().trim();
        final Button findDoctor = findViewById(R.id.doctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAPICall();
                hi.setVisibility(View.VISIBLE);
            }
        });
    }
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.betterdoctor.com/2016-03-01/doctors?specialty_uid=dentist&location=40.116421%2C-88.243385%2C10&user_location=40.116421%2C-88.243385&skip=0&limit=10&user_key=a07ecd33371acf7d00101371eaff5e2a",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                            result = response.toString();
                            System.out.print("Success");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    System.out.print("failed");
                    Log.e(TAG, error.toString());
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Api failed");
        }
    }
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("profile").toString());
            hi.setText(response.get("data").toString());
        } catch (JSONException ignored) { }
    }
}


