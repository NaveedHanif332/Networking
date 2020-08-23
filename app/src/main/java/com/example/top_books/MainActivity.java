package com.example.top_books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static List<String> title = new ArrayList<>();
    public static List<String> author = new ArrayList<>();
    public static List<String> pages = new ArrayList<>();
    public static List<String> category = new ArrayList<>();
    public static List<String> rating = new ArrayList<>();
    public  static List<String> thumbnail=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_rec);
        Fetch_data fetch_data= new Fetch_data(MainActivity.this);
        fetch_data.execute();

    }
}