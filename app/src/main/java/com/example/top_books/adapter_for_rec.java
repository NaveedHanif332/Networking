package com.example.top_books;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class adapter_for_rec extends RecyclerView.Adapter<adapter_for_rec.adapter_holder> {
    Context context;
    List<String> title=new ArrayList<>();
    List<String> author=new ArrayList<>();
    List<String> pages=new ArrayList<>();
    List<String> category=new ArrayList<>();
    List<String> rating=new ArrayList<>();
    List<String> thumbnail_list=new ArrayList<>();
public adapter_for_rec(Context context,List t,List a,List p,List c,List r,List img)
{
    title.addAll(t);
    author.addAll(a);
    pages.addAll(p);
    category.addAll(c);
    rating.addAll(r);
    thumbnail_list.addAll(img);
    this.context=context;
}
    public  class adapter_holder extends RecyclerView.ViewHolder
    {  TextView title_txt,author_txt,pages_txt,cat_text,rat_text;
       ImageView thumbnail;
        public adapter_holder(@NonNull View itemView) {
            super(itemView);
            title_txt=itemView.findViewById(R.id.title);
            author_txt=itemView.findViewById(R.id.author);
            pages_txt=itemView.findViewById(R.id.total_pages);
            cat_text=itemView.findViewById(R.id.cat);
            rat_text=itemView.findViewById(R.id.rating);
            thumbnail=itemView.findViewById(R.id.thumbnail);
        }
    }
    @NonNull
    @Override
    public adapter_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.layout_for_desplay,parent,false);
        return new adapter_for_rec.adapter_holder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapter_holder holder, int position) {
               holder.title_txt.setText(title.get(position));
               holder.author_txt.setText(author.get(position));
               holder.pages_txt.setText(pages.get(position));
               holder.cat_text.setText(category.get(position));
               holder.rat_text.setText(rating.get(position));
               Picasso.get().load(thumbnail_list.get(position)).resize(400,500).into(holder.thumbnail);
    }
    @Override
    public int getItemCount() {
        return rating.size();
    }
}

