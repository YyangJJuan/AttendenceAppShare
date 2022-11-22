package com.example.application2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class GroupActivity extends AppCompatActivity {
    private boolean isCreate;
    private String groupName;
    private int groupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.group_tool_bar);
        //setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        isCreate = getIntent().getBooleanExtra("isCreate", false);
        groupName = getIntent().getStringExtra("group_name");
        groupId = getIntent().getIntExtra("group_id", -1);
        actionBar.setTitle(groupName);

    }

}