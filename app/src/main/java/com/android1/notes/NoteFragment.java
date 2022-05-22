package com.android1.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteFragment extends Fragment {

    private static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            Note note = (Note) arguments.getSerializable(ARG_INDEX);

            TextView txNoteText = view.findViewById(R.id.tvNoteText);

            txNoteText.setText("Заметка: " + note.getCaption() + "\n Дата: " + note.getDate() + "\n Текст заметки: " + note.getNote());

        }
    }


    public static NoteFragment newInstance(Note note) {
        Bundle args = new Bundle();
        NoteFragment fragment = new NoteFragment();
        args.putSerializable(ARG_INDEX, note);
        fragment.setArguments(args);
        return fragment;
    }

}