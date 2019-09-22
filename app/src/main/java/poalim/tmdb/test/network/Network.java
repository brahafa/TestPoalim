package poalim.tmdb.test.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import poalim.tmdb.test.model.Movie;
import poalim.tmdb.test.util.Constants;


public class Network {
    String BASE_URL = "http://api.themoviedb.org";

    private RequestQueue queue;
    ProgressDialog pDialog;
    private CallBackNetwork callBackNetwork;
    private Gson gson;

    public enum RequestName {
        NOW_PLAYING
    }

    public Network(Context context, CallBackNetwork callBackNetwork) {
        this.callBackNetwork = callBackNetwork;
        queue = Volley.newRequestQueue(context);
        pDialog = new ProgressDialog(context);
        gson = new Gson();
    }

    public void sendRequest(final RequestName requestName) {
        String url = BASE_URL;
        switch (requestName) {
            case NOW_PLAYING:
                url += "/3/movie/now_playing?api_key=" + Constants.API_KEY;
                sendRequestObject(Request.Method.GET, null, url);
                break;
        }
    }

    private void sendRequestObject(int requestMethod, JSONObject requestBody, final String url) {
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(requestMethod, url, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }
        };
        queue.add(jsonArrayRequest);
    }

    private void parseData(JSONObject response) {
        JSONArray jsonArray;
        try {
            jsonArray = response.getJSONArray("results");
            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                movies.add(gson.fromJson(String.valueOf(jsonArray.get(i)), Movie.class));
            }
            callBackNetwork.onDataDone(movies);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
