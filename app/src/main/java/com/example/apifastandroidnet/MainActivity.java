package com.example.apifastandroidnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.apifastandroidnet.adapter.RVApiAdapter;
import com.example.apifastandroidnet.model.RVApiModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<RVApiModel> dataItemList = new ArrayList<>();
    RecyclerView recyclerView;

    ArrayList<String> data = new ArrayList<>();

    RVApiAdapter adapter;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        listView = findViewById(R.id.listView); // Assuming you have a ListView with the id 'list_view' in your layout




        new FetchData().execute();




        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new RVApiAdapter(MainActivity.this,dataItemList);
        recyclerView.setAdapter(adapter);



    }

    private class FetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
//            String apiUrl = "http://localhost:8000/posts";
//            String apiUrl = "https://pokeapi.co/api/v2/pokemon/ditto";
            String apiUrl = "http://test.techsy.pk/temp/gettestingdata";
//            String apiUrl = "https://api.publicapis.org/entries";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Set request method to GET
                urlConnection.setRequestMethod("GET");

                // Get the response
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Log the JSON response
                String jsonResponse = response.toString();
                Log.d("JSONResponse", jsonResponse);


                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    // Access the data inside the JSON object
                    JSONArray dataArray = jsonObject.getJSONArray("Data");
//                    JSONArray dataArray = jsonObject.getJSONArray("entries");
//                    JSONArray dataArray = jsonObject.getJSONArray("Data");

                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject item = dataArray.getJSONObject(i);
                        int id = item.getInt("id");
                        String name = item.getString("name");
                        String stdClass = item.getString("Class");

                        data.add(name);
//                        Log.e("dataDetails", "Id is " + id + " and Name is " + name +" And CLass is " + stdClass);

//                        RVApiModel dataItem = new RVApiModel(id, name);
//                        Log.e("dataDetails", " List is " + dataItem);
//
//                        dataItemList.add(dataItem);
                    }
                    Log.d("DataItem", data.toString()); // Log the item

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
                    listView.setAdapter(arrayAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("error ", "Error message is " + e.getMessage()); // Log the item
                }


            } catch (Exception e) {
                Log.e("message ", "Error message is " + e.getMessage());
                e.printStackTrace();
            }

            return null;
        }
    }
}
