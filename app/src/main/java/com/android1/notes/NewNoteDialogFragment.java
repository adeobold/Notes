package com.android1.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewNoteDialogFragment extends DialogFragment {

    public static final String TAG = "NewNoteDialogFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View newNoteView = getLayoutInflater().inflate(R.layout.fragment_dialog_new_note, null);

        newNoteView.findViewById(R.id.newNoteButtonOK).setOnClickListener(view -> {
            String text =
                    newNoteView.<EditText>findViewById(R.id.noteCaption).getText().toString();

            Bundle result = new Bundle();
            result.putString("NewNoteName", text);

            requireActivity().getSupportFragmentManager().setFragmentResult("NewNote", result);

            dismiss();
        });

        newNoteView.findViewById(R.id.newNoteButtonCancel).setOnClickListener(view -> dismiss());

        return newNoteView;
    }



}
