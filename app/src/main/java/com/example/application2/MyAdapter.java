package com.example.application2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.*;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import org.w3c.dom.Text;

import java.util.ConcurrentModificationException;
import java.util.List;

public class MyAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mGroupList;
    private List<List<group>> mChildList;

    public MyAdapter(Context context, List<String> group, List<List<group>> child){
        mContext = context;
        mGroupList = group;
        mChildList = child;
    }

    @Override
    public int getGroupCount(){
        return mGroupList.size();
    }

    /**
     * @param groupPosition 父List的编号
     * @return 字List的长度
     */
    @Override
    public int getChildrenCount(int groupPosition){
        return mChildList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosittion){
        return mGroupList.get(groupPosittion);
    }

    @Override
    public Object getChild(int groupPositon, int childPositon){
        return mChildList.get(groupPositon).get(childPositon);
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }


    @Override
    public long getChildId(int groupPositon, int childPositon){
        return childPositon;
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    /**
     *
     * 返回分组头部的视图
     * @param groupPosition  组位置
     * @param isExpanded   该组是展开状态还是伸缩状态，true=展开
     * @param convertView   重用已有的视图对象
     * @param parent  返回的视图对象始终依附于视图组
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent){
        GroupViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.activity_home_page2, parent,false);
            viewHolder = new GroupViewHolder();
            viewHolder.groupImage = (ImageView) convertView
                    .findViewById(R.id.expandable_list_group_image);
            viewHolder.groupName = (TextView) convertView
                    .findViewById(R.id.expandable_list_group_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (GroupViewHolder)convertView.getTag();
        }

        //if (isExpanded){
          //  viewHolder.groupImage.setImageResource(R.drawable.list_image_open);
        //}else {
          //  viewHolder.groupImage.setImageResource(R.drawable.list_image_close);
        //}
        viewHolder.groupName.setText(mGroupList.get(groupPosition));
        return convertView;
    }


    private class GroupViewHolder{
        private ImageView groupImage;
        private TextView groupName;
    }

    //返回子项视图
    @Override
    public View getChildView(int groupPositon, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent){
        group group = mChildList.get(groupPositon).get(childPosition);
        ChildViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.activity_home_page3, parent, false);
            viewHolder = new ChildViewHolder();
            viewHolder.childImage = convertView.findViewById(R.id.expandable_list_child_image);
            viewHolder.childName = convertView.findViewById(R.id.expandable_list_child_name);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ChildViewHolder)convertView.getTag();
        }
        //TODO 设置图片viewHolder.childImage.set
        viewHolder.childName.setText(group.getName());
        return convertView;
    }
    private class ChildViewHolder{

        private ImageView childImage;
        private TextView childName;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
