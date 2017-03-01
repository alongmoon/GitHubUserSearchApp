package com.example.githubsearchusers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.githubsearchusers.GitHubUser.ItemsEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonHelper {
	
	public static final String sUsersApi = "https://api.github.com/search/users?q=";
	public static final String sReposApi = "https://api.github.com/search/users/";
	private List<ItemsEntity> list;
	private Context mContext;
	
	public static String getReposApi(String qName){
		return sReposApi + qName + "/repos";
	}
	
	public JsonHelper(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	 
    private static Gson mGson = new Gson();
 
    /**
     * 将json字符串转化成实体对象
     * @param json
     * @param classOfT
     * @return
     */
    public static Object stringToObject( String json , Class classOfT){
        return  mGson.fromJson(json, classOfT);
    }
 
    /**
     * 将对象准换为json字符串 或者 把list 转化成json
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectToString(T object) {
        return mGson.toJson(object);
    }
 
    /**
     * 把json 字符串转化成list
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T>  List<T> stringToList(String json ,Class<T> cls){
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, cls));
        }
        return list ;
    }
 
	public void getJsonArrayByName(String inputname){
		RequestQueue mQueue = Volley.newRequestQueue(mContext);
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonHelper.sUsersApi+inputname, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						GitHubUser user = (GitHubUser) JsonHelper.stringToObject(response.toString(), GitHubUser.class);
						list = user.getItems();
						new VolleryHelper(mContext, list).getUserInfoFromJson();							
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.e("GitHubUsers", "onErrorResponse"+error.getMessage(), error);
					}
				});
		mQueue.add(jsonObjectRequest);
	}
}