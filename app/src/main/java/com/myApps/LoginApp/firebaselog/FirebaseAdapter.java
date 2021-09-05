package com.myApps.LoginApp.firebaselog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myApps.LoginApp.Logs.MainData;
import com.myApps.LoginApp.Logs.RoomDB;
import com.myApps.LoginApp.R;

import java.util.ArrayList;
import java.util.List;

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.FirebaseViewHolder> {

    private List<FirebaseLogData> list;
    private Context context;

    public FirebaseAdapter(List<FirebaseLogData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.firebase_item_layout, parent, false);

        return new FirebaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position) {
        FirebaseLogData data = list.get(position);

        holder.textView.setText(data.getText());
        holder.date.setText("Time:  " + data.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FirebaseViewHolder extends RecyclerView.ViewHolder {
        TextView textView, date;

        public FirebaseViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            date = itemView.findViewById(R.id.time);
        }
    }
}
