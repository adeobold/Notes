package com.android1.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private NoteSource list;

    private CardClickListener cardClickListener;

    private final Fragment fragment;

    private int menuPosition;

    public NoteAdapter(NoteSource list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    public void setCardClickListener(CardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    public void setList(NoteSource list) {
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
        holder.getItemView().<TextView>findViewById(R.id.caption).setText(list.getCardData(position).getCaption());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getMenuPosition() {
        return menuPosition;
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private final View noteView;

        public NoteViewHolder(@NonNull View noteView) {
            super(noteView);
            this.noteView = noteView;
            registerContextMenu(itemView);
            noteView.findViewById(R.id.caption).setOnClickListener(view -> cardClickListener.onCaptionClick(getAdapterPosition()));

            noteView.findViewById(R.id.caption).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu(10, 10);
                    return true;
                }
            });


        }

        public View getItemView() {
            return this.noteView;
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null){
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        menuPosition = getLayoutPosition();
                        return false;
                    }
                });
                fragment.registerForContextMenu(itemView);
            }
        }
    }


}
