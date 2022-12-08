package com.example.dailyfeeder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class OutputActivity extends AppCompatActivity {
    GridView ListView;
    VolleyListViewAdapter adapter;
    private List<CommonBean> VolleyList = new ArrayList<CommonBean>();
    private ProgressDialog mprocessingdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        ListView = (GridView) findViewById(R.id.ListView);
        adapter = new VolleyListViewAdapter(OutputActivity.this, VolleyList);
        ListView.setAdapter(adapter);


        RequestQueue queue;
        String URL = "https://api.fnpplus.com/productapi/api/rest/v1.2/productList?catalogId=india";
        queue = Volley.newRequestQueue(OutputActivity.this);
        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rlog", response.toString());
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONObject jsonObject = jsonObj.getJSONObject("data");
                            JSONArray jsonArray = jsonObject.getJSONArray("productResults");
                            Log.d("rlog", jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                CommonBean commonBean = new CommonBean();
                                commonBean.setTextView1(obj.optString("productName"));
                                JSONObject jsonObject2 = obj.getJSONObject("price");
                                Log.d("rlog1", jsonObject2.toString());
                                commonBean.setTextView2(jsonObject2.optString("price"));
                                Log.d("rlog12", jsonObject2.optString("price"));
                                JSONObject jsonObject1 = obj.getJSONObject("productImage");
                                Log.d("rlog1", jsonObject1.toString());
                                commonBean.setImageView(jsonObject1.optString("path"));
                                Log.d("rlog12", jsonObject1.optString("path"));
                                VolleyList.add(commonBean);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("rlog", "Error: " + error.getMessage());
            }
        });
        request.setRetryPolicy(new

                DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(OutputActivity.this);
        requestQueue.add(request);



}

}