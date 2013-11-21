package com.example.gtflashcards.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gtflashcards.objects.Course;
import com.example.gtflashcards.R;

public class CourseAdapter extends CustomAdapter<Course> {
    private List<Course> itemList;
    private Context context;

    public CourseAdapter(List<com.example.gtflashcards.objects.Course> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;        
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    public Course getItem(int position) {
        if (itemList != null)
            return itemList.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (itemList != null)
            return itemList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row_course_layout, null);
        }

        Course c = itemList.get(position);
        TextView text = (TextView) v.findViewById(R.id.courseRowTextView);
        text.setText(c.getCourse());

        return v;

    }

    public List<Course> getItemList() {
        return itemList;
    }

    public void setItemList(List<Course> itemList) {
        this.itemList = itemList;
    }

}
