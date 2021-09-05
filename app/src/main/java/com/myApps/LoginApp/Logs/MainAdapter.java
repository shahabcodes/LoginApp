package com.myApps.LoginApp.Logs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myApps.LoginApp.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    private Activity context;
    private List<MainData> dataList;
    private RoomDB database;

    public MainAdapter(Activity context, List<MainData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MainAdapter.viewHolder holder, int position) {
        MainData data = dataList.get(position);
        database = RoomDB.getInstance(context);

        holder.textView.setText(data.getText());
        holder.pass.setText("Password:  " + data.getPassward());
        holder.userid.setText("Email:  " + data.getUserid());
        holder.date.setText("Time:  " + data.getDate());


//        Code for deleting from room db
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainData d = dataList.get(holder.getAdapterPosition());
//                database.mainDao().delete(d);
//                int removedPosition = holder.getAdapterPosition();
//                dataList.remove(removedPosition);
//                notifyItemRemoved(removedPosition);
//                notifyItemRangeChanged(position, dataList.size());
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView, date, pass, userid;

        public viewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            date = itemView.findViewById(R.id.time);
            pass = itemView.findViewById(R.id.pass);
            userid = itemView.findViewById(R.id.userid);
        }
    }
}
