package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Models.MonHocModel;
import com.example.myapplication.Models.SinhVien_MonHoc;
import com.example.myapplication.R;

import java.util.ArrayList;

public class EnrollAdapter extends ArrayAdapter<SinhVien_MonHoc> {
    private Context context;
    private int resource;
    private ArrayList<SinhVien_MonHoc> listSV_MonHoc;

    public EnrollAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SinhVien_MonHoc> listSV_MonHoc){
        super(context, resource, listSV_MonHoc);
        this.context = context;
        this.resource = resource;
        this.listSV_MonHoc = listSV_MonHoc;
    }
    class ViewHolder {
        TextView tvSV_MH, tvMH_SV;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.enroll_item_lv, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvSV_MH = convertView.findViewById(R.id.tvSV_MH);
            viewHolder.tvMH_SV = convertView.findViewById(R.id.tvMH_SV);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (EnrollAdapter.ViewHolder) convertView.getTag();
        }
        SinhVien_MonHoc svmh = listSV_MonHoc.get(position);
        viewHolder.tvSV_MH.setText(String.valueOf(svmh.getID_SV()));
        viewHolder.tvMH_SV.setText(String.valueOf(svmh.getID_MH()));
        return convertView;
    }
}
