package com.example.myapplication.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVienModel> {
    private Context context;
    private int resource;
    private ArrayList<SinhVienModel> listSV;

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SinhVienModel> listSV) {
        super(context, resource, listSV);
        this.context = context;
        this.resource = resource;
        this.listSV = listSV;
    }

    class ViewHolder {
        TextView tvMSSV, tvTenSV, tvSinhNhat,tvSex;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sv_item_lv,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMSSV = convertView.findViewById(R.id.tvMSSV);
            viewHolder.tvTenSV = convertView.findViewById(R.id.tvTenSV);
            viewHolder.tvSinhNhat = convertView.findViewById(R.id.tvSinhNhat);
            viewHolder.tvSex = convertView.findViewById(R.id.tvSex);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SinhVienModel sinhVien = listSV.get(position);
        viewHolder.tvMSSV.setText(String.valueOf(sinhVien.getID()));
        viewHolder.tvTenSV.setText(String.valueOf(sinhVien.getName()));
        viewHolder.tvSinhNhat.setText(String.valueOf(sinhVien.getDob()));
        if (sinhVien.isSex())
            viewHolder.tvSex.setText("Nam");
        else viewHolder.tvSex.setText("Nữ");
        return convertView;
    }
}
