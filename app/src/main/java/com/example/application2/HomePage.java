package com.example.application2;

import static java.util.Arrays.asList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private MyAdapter mAdapter;
    //不能在定义属性时赋值，即：
    //private ExpandableListView mGroupList =(ExpandableListView) findViewById(R.id.expandablelistview_id);
    //这样会报错。只能在执行语句中定义才能成功
    private ExpandableListView mGroupList;

    private List<String> groupNames = asList("我创建的群", "我加入的群");  //asList()方法添加的是不可变的 List, 即不能添加、删除等操作
    private List<List<group>> groups = new ArrayList<>();
    private List<group> createdGroup;
    private List<group> joinedGroup;
    private ImageView imageView= null;
    private ImageView imageView2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setTitle("                                 签到群");
        initData();
    }


    private void initData(){
        createdGroup = new ArrayList<>();
        joinedGroup = new ArrayList<>();
        //group group1 = new group();
        //group group2 = new group();

        new Thread(){
            public void run ()
            {
                String sql = "select groupName from creategroups";
                String sql2 = "select groupName from JoinedGroups";
                Connection connection = connectMysql.getConnection();
                if (connection != null)
                {
                    PreparedStatement ps = null;
                    PreparedStatement ps2 = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        while(rs.next())
                        {
                            group group1 = new group();
                            group1.setName(rs.getString(1));
                            createdGroup.add(group1);
                        }
                        ps2 = connection.prepareStatement(sql2);
                        ResultSet rs2 = ps2.executeQuery();
                        while(rs2.next())
                        {
                            group group1 = new group();
                            group1.setName(rs2.getString(1));
                            joinedGroup.add(group1);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
        /*for (int i = 0;i<5;i++) {
            group group = new group();
            group.setName("Group-----"+i);
            createdGroup.add(group);
        }
        joinedGroup = new ArrayList<>();
        for (int i =0;i<8;i++){
            group group = new group();
            group.setName("Group-----"+i);
            joinedGroup.add(group);
        }*/
        groups.add(createdGroup);
        groups.add(joinedGroup);
        mAdapter = new MyAdapter(this, groupNames, groups);
        mGroupList=(ExpandableListView) findViewById(R.id.expandable_listview);
        mGroupList.setAdapter(mAdapter);


        imageView = findViewById(R.id.add_more);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(HomePage.this,view);
                //创建弹出式菜单
                popupMenu.inflate(R.menu.add_menu);
                //将自制的弹出布局绑定菜单
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(HomePage.this,CreateGroupActivity.class);
                        //进行跳转
                        startActivity(intent);
                        return  true;
                    }
                });
                //弹出式菜单的单击事件
                popupMenu.show();

            }
        });


       /* imageView2 =findViewById(R.id.search);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(HomePage.this,view);
                //创建弹出式菜单
                popupMenu.inflate(R.menu.add_menu);
                //将自制的弹出布局绑定菜单
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(HomePage.this,CreateGroupActivity.class);
                        //进行跳转
                        startActivity(intent);
                        return  true;
                    }
                });
                //弹出式菜单的单击事件
                popupMenu.show();

            }
        });*/

        //跳转群聊界面
        mGroupList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                group group = groups.get(groupPosition).get(childPosition);

                ////如果点击群号码为要签到的群
                //if (group.getGroupId()==signGroupId) {
                //    Intent intent = new Intent(MainActivity.this,ToSignInActivity.class);
                //    intent.putExtra("groupId",signGroup.getGroupId());
                //    intent.putExtra("admin",signGroup.getAdminId());
                //    startActivity(intent);
                //} else {
                Intent intent = new Intent(HomePage.this,GroupActivity.class);
                boolean isCreate;
                if (groupPosition==0) {
                    isCreate = true;
                }else {
                    isCreate = false;
                }
                intent.putExtra("group_id",group.getNumber());
                intent.putExtra("isCreate",isCreate);
                intent.putExtra("group_name",group.getName());
                startActivity(intent);
                //}

                return true;
            }
        });
    }




}
