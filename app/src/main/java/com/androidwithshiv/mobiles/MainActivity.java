package com.androidwithshiv.mobiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidwithshiv.mobiles.adapter.MobileDetailsAdapter;
import com.androidwithshiv.mobiles.model.MobileDetails;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context;
    private RequestQueue requestQueue;
    private List<MobileDetails> mobileDetailsList = new ArrayList<>();
    private StringRequest stringRequest;
    private ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        shimmerFrameLayout.startShimmer();
        requestJsonData();
    }
    public void init(){
        recyclerView = findViewById(R.id.mobiles_rv);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        context = MainActivity.this;
    }
    public void requestJsonData(){
        Log.d("request", "called");
        requestQueue = Volley.newRequestQueue(context);
        stringRequest = new StringRequest(Request.Method.GET,"https://dummyjson.com/products/category/smartphones",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("response", response.toString());
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("products");
                    fetchTheData(jsonArray);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast("API call error");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        requestQueue.add(stringRequest);
    }

    private void fetchTheData(JSONArray jsonArray){
        recyclerView.setVisibility(View.VISIBLE);
        for (int i=0;i<jsonArray.length();i++){
            try {
                JSONObject mobile = jsonArray.getJSONObject(i);
                mobileDetailsList.add(new MobileDetails(
                        mobile.getString("title"),
                        "$"+mobile.get("price").toString(),
                        mobile.get("rating").toString(),
                        mobile.getString("description"),
                        mobile.getString("thumbnail")));
            }
            catch (Exception e){
                e.printStackTrace();
                showToast("Mobile Detail Error");
            }
        }
        MobileDetailsAdapter adapter = new MobileDetailsAdapter(mobileDetailsList, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        shimmerFrameLayout.hideShimmer();
        shimmerFrameLayout.stopShimmer();
    }

    public void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}