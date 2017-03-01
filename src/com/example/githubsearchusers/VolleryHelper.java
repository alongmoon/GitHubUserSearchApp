package com.example.githubsearchusers;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.githubsearchusers.GitHubUser.ItemsEntity;

public class VolleryHelper {
	private List<ItemsEntity> list;
	private Context mContext;
	private UserListAdapter mAdapter;
	
	public VolleryHelper(Context context, List<ItemsEntity> list) {
		// TODO Auto-generated constructor stub
		mContext = context;
		this.list = list;
		mAdapter = new UserListAdapter(mContext, list);
		MainActivity.getSingleActivity().getListView().setAdapter(mAdapter);
	}
	
	public void getUserInfoFromJson(){
		RequestQueue mQueue = Volley.newRequestQueue(mContext);
		if(list.size() <= 0){
			Toast.makeText(mContext, "未查询到相关用户", Toast.LENGTH_SHORT).show();
		}
		for(int i=0; i<list.size(); i++){
			final ItemsEntity item = list.get(i);
			ImageRequest imageRequest = new ImageRequest(  
					item.getAvatar_url(),  
					new Response.Listener<Bitmap>() {  
						@Override  
						public void onResponse(Bitmap response) {
							Log.e("GitHubUsers", "githubuser:name: getbitmap ok");
							item.setphoto(response);
						}  
					}, 0, 0, Config.RGB_565, new Response.ErrorListener() {  
						@Override  
						public void onErrorResponse(VolleyError error) {  
							//imageView.setImageResource(R.drawable.default_image);  
						}  
					}); 
			mQueue.add(imageRequest);
		}
		MainActivity.getSingleActivity().dismissProgressDialog();
		mAdapter.notifyDataSetChanged();
	}
}
