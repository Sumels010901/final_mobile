package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Models.BangDiemModel;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<BangDiemModel> {
    private Context context;
    private int resource;
    private ArrayList<BangDiemModel> listBangDiem;

    public ScoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BangDiemModel> listBangDiem) {
        super(context, resource, listBangDiem);
        this.context = context;
        this.resource = resource;
        this.listBangDiem = listBangDiem;
    }

    class ViewHolder {
        TextView tvStudentID, tvCourseID, tvScore;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ScoreAdapter.ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.score_item_lv,parent,false);
            viewHolder = new ScoreAdapter.ViewHolder();
            viewHolder.tvStudentID = convertView.findViewById(R.id.tvStudentID);
            viewHolder.tvCourseID = convertView.findViewById(R.id.tvCourseID);
            viewHolder.tvScore = convertView.findViewById(R.id.tvScore);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ScoreAdapter.ViewHolder) convertView.getTag();
        }
        BangDiemModel bangDiem = listBangDiem.get(position);
        viewHolder.tvStudentID.setText(String.valueOf(bangDiem.getID_SV()));
        viewHolder.tvCourseID.setText(String.valueOf(bangDiem.getID_MH()));
        viewHolder.tvScore.setText(String.valueOf(bangDiem.getDiem()));

        return convertView;
    }
}
