
package com.example.interactiveassistant;


// FAQAdapter.java

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    private final List<FAQ> faqs;
    private OnItemClickListener listener; // Listener for item clicks

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(String answer); // Method to handle item clicks
    }

    public FAQAdapter(List<FAQ> faqs, OnItemClickListener listener) {
        this.faqs = faqs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_faq, parent, false); // Use your item layout here
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        // Get the FAQ item for the current position
        FAQ faq = faqs.get(position);
        holder.questionText.setText(faq.getQuestion()); // Set the question text

        // Set an onClickListener to the item view
        holder.itemView.setOnClickListener(v -> listener.onItemClick(faq.getAnswer())); // Call listener with the answer
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public static class FAQViewHolder extends RecyclerView.ViewHolder {
        public TextView questionText;

        public FAQViewHolder(View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text); // Ensure this matches your item layout
        }
    }


}
