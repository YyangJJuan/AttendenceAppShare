package com.example.application2;

import static java.util.Arrays.asList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private MyAdapter mAdapter;
    private List<String> groupNames = asList("我创建的群", "我加入的群");  //asList()方法添加的是不可变的 List, 即不能添加、删除等操作
    private List<List<group>> groups = new ArrayList<>();
    private ExpandableListView mGroupList=findViewById(R.id.expandablelistview_id);
    private List<group> createdGroup;
    private List<group> joinedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_home_page);
    }

    private void initData(){

        //先创建一点，看一下
//
        createdGroup = new ArrayList<>();

        for (int i = 0;i<5;i++) {
            group group = new group();
            group.setName("Group-----"+i);
            createdGroup.add(group);
        }

        joinedGroup = new ArrayList<>();
        for (int i =0;i<8;i++){
            group group = new group();
            group.setName("Group-----"+i);
            joinedGroup.add(group);
        }

        groups.add(createdGroup);
        groups.add(joinedGroup);
        mAdapter = new MyAdapter(this, groupNames, groups);
        mGroupList.setAdapter(mAdapter);


    }




}