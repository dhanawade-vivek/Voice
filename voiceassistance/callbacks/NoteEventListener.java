package com.example.voiceassistance.callbacks;

import com.example.voiceassistance.model.Note;

public interface NoteEventListener {

    void onNoteClick(Note note);
    void onNoteLongClick(Note note);
}