package com.thevarungupta.firebaseauthdemo.realtimedatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thevarungupta.firebaseauthdemo.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder>{

    Context mContext;
    ArrayList<User> mList = new ArrayList<>();
    ArrayList<String> keys = new ArrayList<>();

    public AdapterUser(Context context, ArrayList<String> keys){
        this.mContext = context;
        this.keys = keys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_user_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //User user = mList.get(position);
        //holder.textViewName.setText(user.getName()+","+user.getEmail());
        holder.textViewName.setText(keys.get(position));
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    public void setData(ArrayList<String> list){
        keys = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String id = keys.get(getAdapterPosition());
            Intent intent = new Intent(mContext, UserDetailActivity.class);
            intent.putExtra("DATA", id);
            mContext.startActivity(intent);
        }
    }

}
