package com.example.triviajson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<String> QuestionUrls;
    private Context context;
    private OnQuestionClicked listener;

    QuestionAdapter(List<String> QuestionUrls, OnQuestionClicked onQuestionClicked) {
        this.QuestionUrls = QuestionUrls;
        this.listener= onQuestionClicked;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       // View view = inflater.inflate(R.layout.trivia_image, parent, false);
        View view = inflater.inflate(R.layout.trivia_text, parent, false);
        context = parent.getContext();
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        String url = QuestionUrls.get(position);
     //   Glide.with(context).load(url).into(holder.ivPhoto);
     //   Glide.with(context).load(url).into(holder.txText);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.questionClicked(url);

            }
        });

    }

    @Override
    public int getItemCount() {
        return QuestionUrls.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
       // ImageView ivPhoto;
        TextView tvText;

        QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
           // ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }
    public interface OnQuestionClicked {
        void questionClicked(String url);
    }
}
