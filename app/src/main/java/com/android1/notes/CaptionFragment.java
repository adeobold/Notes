package com.android1.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;

public class CaptionFragment extends Fragment {

    private NoteAdapter noteAdapter;
    private RecyclerView rv;
    private NoteSource noteList;

    private SharedPreferences sharedPref = null;
    public static final String NOTES = "NOTES";
    public static final String NOTES_COUNT = "NOTES_COUNT";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getParentFragmentManager().setFragmentResultListener("NewNote", this, (key, bundle) -> addNewNote(bundle.getString("NewNoteName")));
        sharedPref = requireActivity().getSharedPreferences(NOTES, Context.MODE_PRIVATE);
    }

    private void readNotesFromSharedPrefs() {
        int notesCount = sharedPref.getInt(NOTES_COUNT,0);
        for (int i = 0; i < notesCount; i++){
            String tmpString = sharedPref.getString("Note" + i, null);
            Note tmpNote = new GsonBuilder().create().fromJson(tmpString, Note.class);
            noteList.addCardData(tmpNote);
        }
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onDestroy() {

        sharedPref.edit().putInt(NOTES_COUNT, noteList.size()).apply();

        for (int i = 0; i < noteList.size(); i++){
            String jsonNote = new GsonBuilder().create().toJson(noteList.getCardData(i));
            sharedPref.edit().putString("Note" + i, jsonNote).apply();
        }

        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        noteList = new NoteSourceImpl();

        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caption, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setActionBar();

        initListNotes(view);

    }

    private void setActionBar() {
        setHasOptionsMenu(true);
        ActionBar actionBar = ((AppCompatActivity)
                requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle("Список заметок");
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_caption, menu);
        //inflater.inflate(R.menu.menu_list, menu);
    }

    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:
                noteList.addCardData(new Note("Заметка " + (noteList.size()+1),
                        "Описание " + (noteList.size()+1)));
                noteAdapter.notifyItemInserted(noteList.size() - 1);
                rv.scrollToPosition(noteList.size() - 1);
                return true;
            case R.id.action_clear:
                noteList.clearCardData();
                noteAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v,
                                    @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = noteAdapter.getMenuPosition();
        switch(item.getItemId()) {
            case R.id.action_update:
                noteList.updateCardData(position, new Note("Редактированное название", "Редактированное содержимое"));
                noteAdapter.notifyItemChanged(position);
                return true;
            case R.id.action_delete:
                noteList.deleteCardData(position);
                noteAdapter.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }



    private void initListNotes(View view) {

        // Получим источник данных для списка
        noteList = new NoteSourceImpl().init();
        readNotesFromSharedPrefs();
        initRecyclerView(view);

    }

    private void initRecyclerView(View view) {

        rv = view.findViewById(R.id.noteRecycleView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        noteAdapter = new NoteAdapter(noteList, this);
        //noteAdapter.setList(noteList);
        noteAdapter.setCardClickListener(position -> showNote(noteList.getCardData(position)));

        rv.setAdapter(noteAdapter);

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