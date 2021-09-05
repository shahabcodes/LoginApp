package com.myApps.LoginApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<ItemData> list;
    private DatabaseReference reference;
    private String json;

    public ItemAdapter(Context context, ArrayList<ItemData> list, String json) {
        this.context = context;
        this.list = list;
        this.json = json;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        reference = FirebaseDatabase.getInstance().getReference().child("items");

        SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.apply();


        ItemData data = list.get(position);
//        key = data.getId();
        holder.title.setText(data.getTitle());
        if (data.getStatus().equals("1")) {
            holder.switchCompat.setChecked(true);
            holder.switchCompat.setText("Yes");
            editor.putString(data.getId(), "1");
            editor.apply();
        }
        if (data.getStatus().equals("0")) {
            holder.switchCompat.setChecked(false);
            holder.switchCompat.setText("No");
            editor.putString(data.getId(), "0");
            editor.apply();
        }

        holder.switchCompat.setOnCheckedChangeListener((compoundButton, b) -> {

            if (b) {
                editor.putString(data.getId(), "1");
                editor.apply();
                holder.switchCompat.setText("Yes");
            } else {
                editor.putString(data.getId(), "0");
                editor.apply();
                holder.switchCompat.setText("No");
            }
        });

        if (position == (list.size() - 1)) {
            holder.save.setVisibility(View.VISIBLE);
        } else holder.save.setVisibility(View.GONE);

        holder.save.setOnClickListener(v -> {

            for (int i = 0; i < list.size(); i++) {
                HashMap newmap = new HashMap();
                newmap.put("status", preferences.getString(list.get(i).getId(), null));
                reference.child(list.get(i).getId()).updateChildren(newmap);

            }

            Toast.makeText(context, "Data Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("json", json);
            context.startActivity(intent);

        });


//        holder.yes.setOnClickListener(v -> {
//
//            HashMap data1 = new HashMap();
//            data1.put("status","1");
//
//            reference.child(data.getId()).updateChildren(data1).addOnCompleteListener(new OnCompleteListener() {
//                @Override
//                public void onComplete(@NonNull @NotNull Task task) {
//                    notifyDataSetChanged();
//
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull @NotNull Exception e) {
//                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//
//        });
//        holder.no.setOnClickListener(v -> {
//            HashMap data1 = new HashMap();
//            data1.put("status","0");
//
//            reference.child(data.getId()).updateChildren(data1).addOnCompleteListener(new OnCompleteListener() {
//                @Override
//                public void onComplete(@NonNull @NotNull Task task) {
//                    notifyDataSetChanged();
//
//
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull @NotNull Exception e) {
//                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView title, status;
        //        Button yes, no;
        SwitchCompat switchCompat;
        Button save;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.question);
            status = itemView.findViewById(R.id.status);
//            yes = itemView.findViewById(R.id.yes);
//            no = itemView.findViewById(R.id.no);
            switchCompat = itemView.findViewById(R.id.switch1);
            save = itemView.findViewById(R.id.save);
        }
    }
}
