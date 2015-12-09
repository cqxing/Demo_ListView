package com.example.staring.demo_listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by staring on 2015/12/2.
 */
public class MyBaseAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    //一般写在那个listview的里面
    private Context context;
    private ArrayList<HashMap<String, Object>> listItem;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局 /*构造函数*/


    private boolean isBusy;

    public MyBaseAdapter(Context context) {

        this.context = context;
        this.mInflater = LayoutInflater.from(context);

        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
        for (int i = 0; i < 10000; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemImage", R.drawable.burning);//加入图片
            map.put("ItemTitle", "burning" + i);
            map.put("ItemText", "staring" + i);
            listItem.add(map);
        }
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //1.利用convertView 2.利用ViewHolder
        ViewHolder holder;
        //convertView里面有缓存
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_view_demo, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.picture);
            holder.age = (TextView) convertView.findViewById(R.id.age);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.btn = (Button) convertView.findViewById(R.id.demo_btn);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(!isBusy){
            holder.age.setText(listItem.get(position).get("ItemTitle").toString());
            holder.age.setText(listItem.get(position).get("ItemText").toString());
        }else {
            holder.age.setTag(this);
            holder.name.setTag(this);
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "按钮点击" + position, Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE: //Idle态，进行实际数据的加载显示
                isBusy = false;
                Log.e("staring", "onScrollStateChanged  IDLE");
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                Log.e("staring", "onScrollStateChanged  SCROLL");
                isBusy = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                Log.e("staring", "onScrollStateChanged  SCROLL_STATE_FLING");
                isBusy = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public final class ViewHolder {
        public ImageView imageView;
        public TextView age;
        public TextView name;
        public Button btn;
    }
}
