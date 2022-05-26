package com.android1.notes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CaptionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caption, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListNotes(view);

    }

    private void initListNotes(View view) {

        LinearLayout layoutView = (LinearLayout) view;

        ArrayList<Note> NotesList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 22);
        Date noteDate = cal.getTime();

        for (int i = 0; i < 10; i++) {
            NotesList.add(new Note(noteDate, "Заметка " + (i + 1), "Содержимое заметки " + (i + 1)));
        }

        for (Note note : NotesList) {
            TextView tv = new TextView(getContext());
            tv.setText(note.getCaption());
            tv.setTextSize(25);
            layoutView.addView(tv);
            final Note noteArg = note;
            tv.setOnClickListener(v -> CaptionFragment.this.showNote(noteArg));
        }


    }

    private void showNote(Note noteArg) {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLand(noteArg);
        } else {
            showPort(noteArg);
        }

    }

    private void showLand(Note noteArg) {
        NoteFragment noteFragment = NoteFragment.newInstance(noteArg);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .replace(R.id.note_frame, noteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void showPort(Note noteArg) {
        NoteFragment noteFragment = NoteFragment.newInstance(noteArg);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .add(R.id.caption_frame, noteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("")
                .commit();
    }

}