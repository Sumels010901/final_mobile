package com.example.myapplication.BackEnd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Models.MonHocModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class AdapterCourse extends ArrayAdapter<MonHocModel> {
    private Context context;
    private int resource;
    private ArrayList<MonHocModel> listMonHoc;

    public AdapterCourse(@NonNull Context context, int resource, @NonNull ArrayList<MonHocModel> listMonHoc){
        super(context, resource, listMonHoc);
        this.context = context;
        this.resource = resource;
        this.listMonHoc = listMonHoc;
    }
    class ViewHolder {
        TextView tvCourseID, tvCourseName, tvStudentNumber, tvTeacherID, tvTeacherName, tvTKBCourse;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.course_item_lv, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvCourseID = convertView.findViewById(R.id.tvCourseID);
            viewHolder.tvCourseName = convertView.findViewById(R.id.tvCourseName);
            viewHolder.tvStudentNumber = convertView.findViewById(R.id.tvStudentNumber);
            viewHolder.tvTeacherID = convertView.findViewById(R.id.tvTeacherID);
            viewHolder.tvTeacherName = convertView.findViewById(R.id.tvTeacherName);
            viewHolder.tvTKBCourse = convertView.findViewById(R.id.tvTKBCourse);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MonHocModel monHoc = listMonHoc.get(position);
        viewHolder.tvCourseID.setText(String.valueOf(monHoc.getID_monhoc()));
        viewHolder.tvCourseName.setText(String.valueOf(monHoc.getTen_monhoc()));
        viewHolder.tvStudentNumber.setText(String.valueOf(monHoc.getSoluong_sv()));
        viewHolder.tvTeacherID.setText(String.valueOf(monHoc.getID_giangvien()));
        viewHolder.tvTeacherName.setText(String.valueOf(monHoc.getTen_giangvien()));
        viewHolder.tvTKBCourse.setText(String.valueOf(monHoc.getTKB_monhoc()));
        return convertView;
    }
}
