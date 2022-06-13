package com.android1.notes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NoteSourceImpl implements NoteSource {

    private List<Note> dataSource;

    public NoteSourceImpl() {
        dataSource = new ArrayList<>(10);
    }

    public NoteSourceImpl init() {
        // заполнение источника данных

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 22);
        Date noteDate = cal.getTime();

        for (int i = 0; i < 10; i++) {
            dataSource.add(new Note(noteDate, "Заметка " + (i + 1), "Содержимое заметки " + (i + 1)));
        }
        return this;
    }

    public Note getCardData(int position) {
        return dataSource.get(position);
    }

    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, Note note) {
        dataSource.set(position, note);
    }

    @Override
    public void addCardData(Note note) {
        dataSource.add(note);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }


}
