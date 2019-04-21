package edu.illinois.cs.cs125.spring2019.mp5;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.mp5.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP5:Main";

    /** Constant to perform a read file request. */
    private static final int READ_REQUEST_CODE = 42;


    /** Constant to request permission to write to the external storage device. */
    private static final int REQUEST_WRITE_STORAGE = 112;

    /** Threshold for calling something a dog or cat. */
    private static final double RECOGNITION_THRESHOLD = 0.9;

    /** Request queue for our network requests. */
    private RequestQueue requestQueue;

    /** Whether we can write to public storage. */
    private boolean canWriteToPublicStorage = false;

    protected void onCreate(final Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button findDoctor = findViewById(R.id.doctor);
        //findDoctor.setOnClickListener(v -> {
            //Log.d(TAG, "Find button click");
            //startFindDoctor();
        //}
        //);

    }

    protected void startFindDoctor(final String jsonResult) {

        //TextView textView =


    }



}

