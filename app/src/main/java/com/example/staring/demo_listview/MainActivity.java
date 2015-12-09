package com.example.staring.demo_listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView listView;

    private Button demo_btn;

    private String[] data = new String[]{"staring", "burning", "just demo", "i love you"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        //设置数据源
        //ArrayAdapter SimpleAdapter都是继承了BaseAdapter
        //ArrayAdapter使用简单 就是一个数组 加一个布局带有一个TextView的
        //SimpleAdapter 绑定的是一个arraylist的数据 比ArrayAdapter更加好用
//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
//        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
//        for (int i = 0; i < 100; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemImage", R.drawable.burning);//加入图片
//            map.put("ItemTitle", "burning" + i);
//            map.put("ItemText", "staring" + i);
//            listItem.add(map);
//        }
//
//
//        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem,
//                R.layout.list_view_demo
//                , new String[]{"ItemImage"
//                , "ItemTitle", "ItemText"}, new int[]{R.id.picture, R.id.age, R.id.name
//        });
//        listView.setAdapter(mSimpleAdapter);
//        listView.setOnItemClickListener(this);

        //如果添加了button button的点击效果会影响item的效果
        //那么baseAdapter呢
        MyBaseAdapter myBaseAdapter=new MyBaseAdapter(this);
        listView.setAdapter(myBaseAdapter);
        listView.setOnScrollListener(myBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_LONG).show();
            }
        });

    }
}
