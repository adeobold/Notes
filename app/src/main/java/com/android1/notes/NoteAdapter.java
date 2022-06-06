package com.android1.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> list;

    private CardClickListener cardClickListener;

    public void setCardClickListener(CardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    public List<Note> getList() {
        return list;
    }

    public void setList(List<Note> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.getItemView().<TextView>findViewById(R.id.caption).setText(list.get(position).getCaption());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private View noteView;

        public NoteViewHolder(@NonNull View noteView) {
            super(noteView);
            this.noteView = noteView;
            noteView.findViewById(R.id.caption).setOnClickListener(view -> cardClickListener.onCaptionClick(getAdapterPosition()));
        }

        public View getItemView() {
            return this.noteView;
        }
    }


}
