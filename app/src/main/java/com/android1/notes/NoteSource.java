package com.android1.notes;

public interface NoteSource {
    Note getCardData(int position);
    int size();
    void deleteCardData(int position);
    void updateCardData(int position, Note note);
    void addCardData(Note Note);
    void clearCardData();
}
