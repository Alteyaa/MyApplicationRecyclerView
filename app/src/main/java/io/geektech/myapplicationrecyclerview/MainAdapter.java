package io.geektech.myapplicationrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public List<MainModel> list;

    public Context context;

    private ItemClickListener listener;


    public MainAdapter(List<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void addData(MainModel model){
        list.add(model);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_item,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position)  {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName;
        TextView txtPhone;
        ImageView imageView;
        MainModel model;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void onBind(MainModel model) {
            this.model = model;
            txtPhone.setText(model.getNumber());
            txtName.setText(model.getName());

            if (model.getImageVIew() != null) {
                Glide.with(context)
                        .load(model.getImageVIew())
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            }
        }

        @Override
        public void onClick(View view) {
            if (listener !=null){
                listener.onItemClick(getAdapterPosition());
            }
        }
    }

    public void setOnClickListener(ItemClickListener mListener){
        this.listener = mListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }

}
