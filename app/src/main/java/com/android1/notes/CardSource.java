package com.android1.notes;

public interface CardSource {
    Note getCardData(int position);
    int size();
}
