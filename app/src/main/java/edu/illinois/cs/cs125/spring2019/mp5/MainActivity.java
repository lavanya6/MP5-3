package edu.illinois.cs.cs125.spring2019.mp5;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mp5.R;
import com.google.gson.JsonObject;




public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP5:Main";

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        final TextView hi = findViewById(R.id.textView);
        hi.setText("Name of Doctors");
        hi.setVisibility(View.GONE);


        final Button findDoctor = findViewById(R.id.doctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hi.setVisibility(View.VISIBLE);
            }
        });


    }

    //protected void startFindDoctor(final String jsonResult) {

        //TextView textView =


    //}



}

