package com.example.massivjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);
        String rawJSON = getString(R.string.rawJSON);

    /*    String rawJSON = "array:[{\"MemberId\":{\"1\",\"Name\":\"Elena\",\"Tel\":\"495-68-45\"}"
                + ",{\"MemberId\":{\"2\",\"Name\":\"Evheny\",\"Tel\":\"495-68-45\"}"
                + ",{\"MemberId\":{\"3\",\"Name\":\"Djulus\",\"Tel\":\"495-68-45\"}]";*/

        try {

            JSONObject perem = new JSONObject(rawJSON);
            JSONArray data = perem.getJSONArray("array");

            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {

                {
                    JSONObject jsonObject = data.getJSONObject(i);

                    map = new HashMap<>();
                    map.put("MemberId", jsonObject.getString("MemberId"));
                    map.put("Name", jsonObject.getString("Name"));
                    map.put("Tel", jsonObject.getString("Tel"));

                    arrayList.add(map);
                }

                SimpleAdapter simpleAdapter;
                simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.list_item, new String[]{"MemberId", "Name", "Tel"},
                        new int[]{R.id.item_textViewMemberId, R.id.item_textViewName, R.id.item_textViewNumber});
                listView.setAdapter(simpleAdapter);

            }} catch (JSONException e) {
            e.printStackTrace();
        }
    }
}