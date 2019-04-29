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
import android.text.method.ScrollingMovementMethod;


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
        specialty = (EditText) findViewById(R.id.speciality_id);
        sp = specialty.getText().toString().toLowerCase();
        Log.d(TAG, "test");
        Log.i(TAG, sp);
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
                    "https://api.betterdoctor.com/2016-03-01/doctors?specialty_uid=pediatrician&location=40.116421%2C%20-88.243385%2C%2010&user_location=40.116421%2C%20-88.243385&skip=0&limit=10&user_key=a07ecd33371acf7d00101371eaff5e2a",
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
            // Example of how to pull a field off the returned JSON object

            JSONObject responseJSON = new JSONObject(response.toString());
            System.out.print(response.toString());
            JSONArray dataJSON = responseJSON.getJSONArray("data");

            //1Doctor
            JSONObject fDoctorJSON = dataJSON.getJSONObject(0);
            JSONArray fDoctorPractices = fDoctorJSON.getJSONArray("practices");
            JSONObject fDoctorUn = fDoctorPractices.getJSONObject(0);
            Object fDoctorName = fDoctorUn.get("name");
            String fDoctorNameString = fDoctorName.toString();
            JSONObject fDoctorOffice = fDoctorUn.getJSONObject("visit_address");
            String fDoctorOfficeAddress = fDoctorOffice.get("street").toString() + "," + fDoctorOffice.get("city").toString() + "," + fDoctorOffice.get("state").toString() + "-" + fDoctorOffice.get("zip").toString();
            JSONArray fDoctorP = fDoctorUn.getJSONArray("phones");
            JSONObject fDoctorPhone = fDoctorP.getJSONObject(0);
            String fDoctorNumber = fDoctorPhone.get("number").toString();

            //2doctor
            JSONObject sDoctorJSON = dataJSON.getJSONObject(1);
            JSONArray sDoctorPractices = sDoctorJSON.getJSONArray("practices");
            JSONObject sDoctorUn = sDoctorPractices.getJSONObject(0);
            Object sDoctorName = sDoctorUn.get("name");
            String sDoctorNameString = sDoctorName.toString();
            JSONObject sDoctorOffice = sDoctorUn.getJSONObject("visit_address");
            String sDoctorOfficeAddress = sDoctorOffice.get("street").toString() + "," + sDoctorOffice.get("city").toString() + "," + sDoctorOffice.get("state").toString() + "-" + sDoctorOffice.get("zip").toString();
            JSONArray sDoctorP = sDoctorUn.getJSONArray("phones");
            JSONObject sDoctorPhone = sDoctorP.getJSONObject(0);
            String sDoctorNumber = sDoctorPhone.get("number").toString();

            //3doctor
            JSONObject tDoctorJSON = dataJSON.getJSONObject(2);
            JSONArray tDoctorPractices = tDoctorJSON.getJSONArray("practices");
            JSONObject tDoctorUn = tDoctorPractices.getJSONObject(0);
            Object tDoctorName = tDoctorUn.get("name");
            String tDoctorNameString = tDoctorName.toString();
            JSONObject tDoctorOffice = tDoctorUn.getJSONObject("visit_address");
            String tDoctorOfficeAddress = tDoctorOffice.get("street").toString() + "," + tDoctorOffice.get("city").toString() + "," + tDoctorOffice.get("state").toString() + "-" + tDoctorOffice.get("zip").toString();
            JSONArray tDoctorP = tDoctorUn.getJSONArray("phones");
            JSONObject tDoctorPhone = tDoctorP.getJSONObject(0);
            String tDoctorNumber = tDoctorPhone.get("number").toString();

            //4doctor
            JSONObject bDoctorJSON = dataJSON.getJSONObject(3);
            JSONArray bDoctorPractices = bDoctorJSON.getJSONArray("practices");
            JSONObject bDoctorUn = bDoctorPractices.getJSONObject(0);
            Object bDoctorName = bDoctorUn.get("name");
            String bDoctorNameString = bDoctorName.toString();
            JSONObject bDoctorOffice = bDoctorUn.getJSONObject("visit_address");
            String bDoctorOfficeAddress = bDoctorOffice.get("street").toString() + "," + bDoctorOffice.get("city").toString() + "," + bDoctorOffice.get("state").toString() + "-" + bDoctorOffice.get("zip").toString();
            JSONArray bDoctorP = bDoctorUn.getJSONArray("phones");
            JSONObject bDoctorPhone = bDoctorP.getJSONObject(0);
            String bDoctorNumber = bDoctorPhone.get("number").toString();

            //5doctor
            JSONObject pentDoctorJSON = dataJSON.getJSONObject(4);
            JSONArray pentDoctorPractices = pentDoctorJSON.getJSONArray("practices");
            JSONObject pentDoctorUn = pentDoctorPractices.getJSONObject(0);
            Object pentDoctorName = pentDoctorUn.get("name");
            String pentDoctorNameString = pentDoctorName.toString();
            JSONObject pentDoctorOffice = pentDoctorUn.getJSONObject("visit_address");
            String pentDoctorOfficeAddress = pentDoctorOffice.get("street").toString() + "," + pentDoctorOffice.get("city").toString() + "," + pentDoctorOffice.get("state").toString() + "-" + pentDoctorOffice.get("zip").toString();
            JSONArray pentDoctorP = pentDoctorUn.getJSONArray("phones");
            JSONObject pentDoctorPhone = pentDoctorP.getJSONObject(0);
            String pentDoctorNumber = pentDoctorPhone.get("number").toString();

            //6doctor
            JSONObject hexDoctorJSON = dataJSON.getJSONObject(5);
            JSONArray hexDoctorPractices = hexDoctorJSON.getJSONArray("practices");
            JSONObject hexDoctorUn = hexDoctorPractices.getJSONObject(0);
            Object hexDoctorName = hexDoctorUn.get("name");
            String hexDoctorNameString = hexDoctorName.toString();
            JSONObject hexDoctorOffice = hexDoctorUn.getJSONObject("visit_address");
            String hexDoctorOfficeAddress = hexDoctorOffice.get("street").toString() + "," + hexDoctorOffice.get("city").toString() + "," + hexDoctorOffice.get("state").toString() + "-" + hexDoctorOffice.get("zip").toString();
            JSONArray hexDoctorP = hexDoctorUn.getJSONArray("phones");
            JSONObject hexDoctorPhone = hexDoctorP.getJSONObject(0);
            String hexDoctorNumber = hexDoctorPhone.get("number").toString();

            //7doctor
            JSONObject heptDoctorJSON = dataJSON.getJSONObject(6);
            JSONArray heptDoctorPractices = heptDoctorJSON.getJSONArray("practices");
            JSONObject heptDoctorUn = heptDoctorPractices.getJSONObject(0);
            Object heptDoctorName = heptDoctorUn.get("name");
            String heptDoctorNameString = heptDoctorName.toString();
            JSONObject heptDoctorOffice = heptDoctorUn.getJSONObject("visit_address");
            String heptDoctorOfficeAddress = heptDoctorOffice.get("street").toString() + "," + heptDoctorOffice.get("city").toString() + "," + heptDoctorOffice.get("state").toString() + "-" + heptDoctorOffice.get("zip").toString();
            JSONArray heptDoctorP = heptDoctorUn.getJSONArray("phones");
            JSONObject heptDoctorPhone = heptDoctorP.getJSONObject(0);
            String heptDoctorNumber = heptDoctorPhone.get("number").toString();

            //8doctor
            JSONObject octDoctorJSON = dataJSON.getJSONObject(7);
            JSONArray octDoctorPractices = octDoctorJSON.getJSONArray("practices");
            JSONObject octDoctorUn = octDoctorPractices.getJSONObject(0);
            Object octDoctorName = octDoctorUn.get("name");
            String octDoctorNameString = octDoctorName.toString();
            JSONObject octDoctorOffice = octDoctorUn.getJSONObject("visit_address");
            String octDoctorOfficeAddress = octDoctorOffice.get("street").toString() + "," + octDoctorOffice.get("city").toString() + "," + octDoctorOffice.get("state").toString() + "-" + octDoctorOffice.get("zip").toString();
            JSONArray octDoctorP = octDoctorUn.getJSONArray("phones");
            JSONObject octDoctorPhone = octDoctorP.getJSONObject(0);
            String octDoctorNumber = octDoctorPhone.get("number").toString();

            //9doctor
            JSONObject nonDoctorJSON = dataJSON.getJSONObject(8);
            JSONArray  nonDoctorPractices = nonDoctorJSON.getJSONArray("practices");
            JSONObject nonDoctorUn = nonDoctorPractices.getJSONObject(0);
            Object nonDoctorName = nonDoctorUn.get("name");
            String nonDoctorNameString = nonDoctorName.toString();
            JSONObject nonDoctorOffice = nonDoctorUn.getJSONObject("visit_address");
            String nonDoctorOfficeAddress = nonDoctorOffice.get("street").toString() + "," + nonDoctorOffice.get("city").toString() + "," + nonDoctorOffice.get("state").toString() + "-" + nonDoctorOffice.get("zip").toString();
            JSONArray nonDoctorP = nonDoctorUn.getJSONArray("phones");
            JSONObject nonDoctorPhone = nonDoctorP.getJSONObject(0);
            String nonDoctorNumber = nonDoctorPhone.get("number").toString();

            //10doctor
            JSONObject decDoctorJSON = dataJSON.getJSONObject(9);
            JSONArray  decDoctorPractices = decDoctorJSON.getJSONArray("practices");
            JSONObject decDoctorUn = decDoctorPractices.getJSONObject(0);
            Object decDoctorName = decDoctorUn.get("name");
            String decDoctorNameString = decDoctorName.toString();
            JSONObject decDoctorOffice = decDoctorUn.getJSONObject("visit_address");
            String decDoctorOfficeAddress = decDoctorOffice.get("street").toString() + "," + decDoctorOffice.get("city").toString() + "," + decDoctorOffice.get("state").toString() + "-" + decDoctorOffice.get("zip").toString();
            JSONArray decDoctorP = decDoctorUn.getJSONArray("phones");
            JSONObject decDoctorPhone = decDoctorP.getJSONObject(0);
            String decDoctorNumber = decDoctorPhone.get("number").toString();
            Log.d(TAG, sDoctorNameString);
            hi.setText("Results" + "\n" + "\n" + "1. " + fDoctorNameString + "\n" + fDoctorOfficeAddress + "\n" + fDoctorNumber
                    + "\n" + "\n" + "2. " + sDoctorNameString + "\n" + sDoctorOfficeAddress + "\n" + sDoctorNumber
                    + "\n" + "\n" + "3. " + tDoctorNameString + "\n" + tDoctorOfficeAddress + "\n" + tDoctorNumber
                    + "\n" + "\n" + "4. " + bDoctorNameString + "\n" + bDoctorOfficeAddress + "\n" + bDoctorNumber
                    + "\n" + "\n" + "5. " + pentDoctorNameString + "\n" + pentDoctorOfficeAddress + "\n" + pentDoctorNumber
                    + "\n" + "\n" + "6. " + hexDoctorNameString + "\n" + hexDoctorOfficeAddress + "\n" + hexDoctorNumber
                    + "\n" + "\n" + "7. " + heptDoctorNameString + "\n" + heptDoctorOfficeAddress + "\n" + heptDoctorNumber
                    + "\n" + "\n" + "8. " + octDoctorNameString + "\n" + octDoctorOfficeAddress + "\n" + octDoctorNumber
                    + "\n" + "\n" + "9. " + nonDoctorNameString + "\n" + nonDoctorOfficeAddress + "\n" + nonDoctorNumber
                    + "\n" + "\n" + "10. " + decDoctorNameString + "\n" + decDoctorOfficeAddress + "\n" + decDoctorNumber);
            hi.setTextSize(18);
            hi.setMovementMethod(new ScrollingMovementMethod());
        } catch (JSONException ignored) { }
    }
}


