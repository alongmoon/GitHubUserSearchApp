package com.example.githubsearchusers;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubsearchusers.GitHubUser.ItemsEntity;

public class UserListAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<ItemsEntity> mList;

	public UserListAdapter(Context context, List<ItemsEntity> list) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mList = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mList == null){
			return 0;
		}
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(mList == null){
			return null;
		}
		ViewHolder holder = null;
		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(
					R.layout.listview_item, null);
			holder = new ViewHolder();
			holder.mName = (TextView) view.findViewById(R.id.tv);
			holder.mPhoto = (ImageView) view.findViewById(R.id.iv);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.mName.setText(mList.get(position).getLogin());
		holder.mPhoto.setImageBitmap(mList.get(position).getPhoto());
		return view;
	}

	class ViewHolder{
		TextView mName;
		ImageView mPhoto;
	}
}
