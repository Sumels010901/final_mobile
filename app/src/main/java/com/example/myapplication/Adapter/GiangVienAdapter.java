package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class GiangVienAdapter  extends ArrayAdapter<GiangVienModel> {
    private Context context;
    private int resource;
    private ArrayList<GiangVienModel> listGV;

    public GiangVienAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GiangVienModel> listGV) {
        super(context, resource, listGV);
        this.context = context;
        this.resource = resource;
        this.listGV = listGV;
    }

    class ViewHolder {
        TextView tvTeacherID, tvTeacherName, tvTeacherEmail, tvTeacherExp;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GiangVienAdapter.ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_lv,parent,false);
            viewHolder = new GiangVienAdapter.ViewHolder();
            viewHolder.tvTeacherID = convertView.findViewById(R.id.tvTeacherID);
            viewHolder.tvTeacherName = convertView.findViewById(R.id.tvTeacherName);
            viewHolder.tvTeacherEmail = convertView.findViewById(R.id.tvTeacherEmail);
            viewHolder.tvTeacherExp = convertView.findViewById(R.id.tvTeacherExp);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (GiangVienAdapter.ViewHolder) convertView.getTag();
        }
        GiangVienModel giangVien = listGV.get(position);
        viewHolder.tvTeacherID.setText(String.valueOf(giangVien.getID_giangvien()));
        viewHolder.tvTeacherName.setText(String.valueOf(giangVien.getTen_giangvien()));
        viewHolder.tvTeacherEmail.setText(String.valueOf(giangVien.getEmail_giangvien()));
        viewHolder.tvTeacherExp.setText(String.valueOf(giangVien.getSonam_giangday()));
        return convertView;
    }
}
