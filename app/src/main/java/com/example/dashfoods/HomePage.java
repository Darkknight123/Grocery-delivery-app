package com.example.dashfoods;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomePage extends AppCompatActivity {
    List <DynamicRvModel> items=new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ArrayList<StaticRvMode> item =new ArrayList<>();
        item.add(new StaticRvMode(R.drawable.tomato,"tomato"));
        item.add(new StaticRvMode(R.drawable.kales,"kale"));
        item.add(new StaticRvMode(R.drawable.pepper,"pepper"));
        item.add(new StaticRvMode(R.drawable.pepper1,"pepper"));
        item.add(new StaticRvMode(R.drawable.onion,"onion"));
        item.add(new StaticRvMode(R.drawable.maize,"maize"));
        item.add(new StaticRvMode(R.drawable.corriander,"coriander"));
        item.add(new StaticRvMode(R.drawable.garlic,"garlic"));
        item.add(new StaticRvMode(R.drawable.blackpepper,"black pepper"));

        RecyclerView recyclerView = findViewById(R.id.rv_1);
        StaticRvAdapter staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);



        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));
        items.add(new DynamicRvModel("tomato"));

        RecyclerView drv =findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter=new DynamicRVAdapter(drv,this,items);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(() -> {
            if (items.size()<=10){
                item.add(null);
                dynamicRVAdapter.notifyItemInserted(items.size()-1);
                new Handler().postDelayed(() -> {
                    items.remove(items.size()-1);
                    dynamicRVAdapter.notifyItemRemoved(items.size());

                    int index =items.size();
                    int end=index+10;
                    for (int i = index; i<end;i++){
                        String name= UUID.randomUUID().toString();
                        DynamicRvModel item1 =new DynamicRvModel(name);
                        items.add(item1);
                    }

                    dynamicRVAdapter.notifyDataSetChanged();
                    dynamicRVAdapter.setLoaded();
                },4000);
            }else
                Toast.makeText(HomePage.this,"Data Completed",Toast.LENGTH_SHORT).show();
        });
    }
}
