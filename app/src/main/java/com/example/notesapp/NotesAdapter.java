package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private final List<Note> notes;
    private CustomClickListener clickListener;
    private CustomLongClickListener longClickListener;
    private boolean isVisible;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.notes_item,
                parent,
                false);
        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.header.setText(note.header);
        holder.body.setText(note.body);
        holder.date.setText(note.date);
        View itemView = holder.itemView;
        CheckBox checkBox = itemView.findViewById(R.id.isSelected);

        if (note.header.isEmpty()) {
            TextView header = itemView.findViewById(R.id.header);
            header.setVisibility(View.GONE);
        }

        if (note.body.isEmpty()) {
            TextView body = itemView.findViewById(R.id.body);
            body.setVisibility(View.GONE);
        }

        if (isVisible) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        private final TextView header;
        private final TextView body;
        private final TextView date;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            body = itemView.findViewById(R.id.body);
            date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(view -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickListener.onClick(itemView, position);
                    }
                }
            });
            itemView.setOnLongClickListener(view -> {
                if (longClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        longClickListener.onLongClick(itemView, position);
                    }
                }

                return true;
            });
        }
    }

    public void setOnItemClickListener(CustomClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnItemLongClickListener(CustomLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getVisibility() {
        return isVisible;
    }
}
