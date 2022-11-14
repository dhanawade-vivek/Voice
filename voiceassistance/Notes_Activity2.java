package com.example.voiceassistance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voiceassistance.adapters.NotesAdapter;
import com.example.voiceassistance.callbacks.NoteEventListener;
import com.example.voiceassistance.db.NotesDB;
import com.example.voiceassistance.db.NotesDao;
import com.example.voiceassistance.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.voiceassistance.EditNoteActivity.NOTE_EXTRA_Key;

public class Notes_Activity2 extends AppCompatActivity implements NoteEventListener {
    private static final String TAG="Activity2";
    private RecyclerView recyclerView;
    private ArrayList<Note>notes;
    private NotesAdapter adapter;
    TextToSpeech textToSpeech;
    private NotesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    //select language
                    int lang=textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        //init recyclerview
        recyclerView=findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int speech=textToSpeech.speak("Tap to open earlier note and long press to delete",TextToSpeech.QUEUE_FLUSH,null);

        //init fab button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewNote();
            }
        });
        dao= NotesDB.getInstance(this).notesDao();
    }
    private void loadNotes(){
        this.notes=new ArrayList<>();
        List<Note> list=dao.getNotes();//get all notes from database
        this.notes.addAll(list);
        this.adapter=new NotesAdapter(this,notes);
        this.adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }
    private void onAddNewNote(){
        //start EditNoteActivity
        startActivity(new Intent(this,EditNoteActivity.class));
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu,this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main,menu);
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onNoteClick(Note note) {
        Intent edit =new Intent(this,EditNoteActivity.class);
        edit.putExtra(NOTE_EXTRA_Key, note.getId());
        startActivity(edit);
    }

    @Override
    public void onNoteLongClick(final Note note) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete note from database and refresh
                        dao.deleteNote(note); //delete
                        loadNotes(); //refresh
                    }
                })

                .create()
                .show();
    }
}



