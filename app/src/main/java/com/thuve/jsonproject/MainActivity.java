package com.thuve.jsonproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

        private TextView textview;
        private Button btn;
        private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview=findViewById(R.id.textView);
        btn=findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText("");
                setAPIRequest();
            }
        });







//        Gson gson=new Gson();
////        persion p1=new persion("Thuve","thuva@gmail.com",22);
////
////        String json=gson.toJson(p1);
//
//        String json = "{\n" +
//                "  \"age\": 22,\n" +
//                "  \"email\": \"amma@gmail.com\",\n" +
//                "  \"name\": \"keppa\"\n" +
//                "}";
//persion p2=gson.fromJson(json,persion.class);
//        Log.i("Test",json);

    }

    private void setAPIRequest(){
        String url="https://api.jsonserve.com/4jiDtB";
        JsonObjectRequest jesonob=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("usercred");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject user=jsonArray.getJSONObject(i);
                        String name=user.getString("username");
                        String password=user.getString("password");
                        String role=user.getString("role");
                        textview.append(name+" "+password+" "+role+"\n ");
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jesonob);

    }
}