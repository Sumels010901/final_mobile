package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.TaiKhoanModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class TaiKhoanAdapter extends ArrayAdapter<TaiKhoanModel> {
    private Context context;
    private int resource;
    private ArrayList<TaiKhoanModel> listTK;

    public TaiKhoanAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TaiKhoanModel> listTK) {
        super(context, resource, listTK);
        this.context = context;
        this.resource = resource;
        this.listTK = listTK;
    }

    class ViewHolder {
        TextView tvUsername, tvPassword, tvType;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TaiKhoanAdapter.ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_lv,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvUsername = convertView.findViewById(R.id.tvUsername);
            viewHolder.tvPassword = convertView.findViewById(R.id.tvPassword);
            viewHolder.tvType = convertView.findViewById(R.id.tvType);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (TaiKhoanAdapter.ViewHolder) convertView.getTag();
        }
        TaiKhoanModel taiKhoan = listTK.get(position);
        viewHolder.tvUsername.setText(String.valueOf(taiKhoan.getSvID()));
        viewHolder.tvPassword.setText(String.valueOf(taiKhoan.getPassword()));
        viewHolder.tvType.setText(String.valueOf(taiKhoan.getAccType()));
        return convertView;
    }
}
