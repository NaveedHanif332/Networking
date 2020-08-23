package com.example.top_books;
import android.content.Context;
import android.os.AsyncTask;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Fetch_data  extends AsyncTask<Void,Void,Void> {
    StringBuilder data =new StringBuilder();
Context context;
    public Fetch_data(Context context) {
        this.context=context;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                data = data.append(line);
                line = bufferedReader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (data!=null) {
            try {
                JSONObject main_object = new JSONObject(String.valueOf(data));
                JSONArray item = main_object.getJSONArray("items");
                for (int i = 0; i < item.length(); i++) {
                    JSONObject first_item = item.getJSONObject(i);
                    JSONObject detail_obj = first_item.getJSONObject("volumeInfo");
                    MainActivity.title.add(detail_obj.optString("title"));
                    MainActivity.author.add(detail_obj.getJSONArray("authors").optString(0));
                    MainActivity.pages.add(detail_obj.optString("pageCount"));
                    MainActivity.category.add(detail_obj.getJSONArray("categories").optString(0));
                    MainActivity.rating.add(detail_obj.optString("averageRating"));
                    MainActivity.thumbnail.add(detail_obj.getJSONObject("imageLinks").getString("thumbnail"));
                }
                    adapter_for_rec adapter = new adapter_for_rec(context, MainActivity.title, MainActivity.author,
                                                       MainActivity.pages, MainActivity.category, MainActivity.rating,MainActivity.thumbnail);
                    MainActivity.recyclerView.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                    MainActivity.recyclerView.setLayoutManager(layoutManager);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
