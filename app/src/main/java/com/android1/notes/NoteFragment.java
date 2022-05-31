package com.android1.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteFragment extends Fragment {

    private static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            Note note = arguments.getParcelable(ARG_INDEX);

            TextView txNoteText = view.findViewById(R.id.tvNoteText);

            txNoteText.setText(String.format("Заметка: %s\n Дата: %s\n Текст заметки: %s", note.getCaption(), note.getDate(), note.getNote()));

        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_note, menu);
        MenuItem itemActionSearch = menu.findItem(R.id.action_search);
        if (itemActionSearch != null) {
            itemActionSearch.setVisible(false);
        }
        MenuItem itemActionSort = menu.findItem(R.id.action_sort);
        if (itemActionSort != null) {
            itemActionSort.setVisible(false);
        }
    }

    public static NoteFragment newInstance(Note note) {
        Bundle args = new Bundle();
        NoteFragment fragment = new NoteFragment();
        args.putParcelable(ARG_INDEX, note);
        fragment.setArguments(args);
        return fragment;
    }

}