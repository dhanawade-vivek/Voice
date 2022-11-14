package com.example.voiceassistance;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voiceassistance.db.NotesDB;
import com.example.voiceassistance.db.NotesDao;
import com.example.voiceassistance.model.Note;

import java.util.Date;
import java.util.Locale;

public class EditNoteActivity extends AppCompatActivity {
    private EditText inputNote;
    Button btnRead;
    Button savenote;
    TextToSpeech textToSpeech;
    private NotesDao dao;
    private Note temp;
    public static final String NOTE_EXTRA_Key = "note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        inputNote = findViewById(R.id.input_note);
        btnRead=findViewById(R.id.btn_read);
        savenote = findViewById(R.id.save_note);

        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    //select language
                    int lang=textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get editText value
                String s=inputNote.getText().toString();
                //text convert to speech
                int speech=textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveNote();
                //text convert to speech
                int speech=textToSpeech.speak("Note has been saved",TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        dao = NotesDB.getInstance(this).notesDao();
        if (getIntent().getExtras() != null) {
            int id = getIntent().getExtras().getInt(NOTE_EXTRA_Key, 0);
            temp = dao.getNoteById(id);
            inputNote.setText(temp.getNoteText());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        // TODO: 20/06/2018 Save Note
        String text = inputNote.getText().toString();
        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get  system time

            //if note exist then update else create new
            if (temp == null) {
                temp = new Note(text, date);
                dao.insertNote(temp);//created and inserted to database
            } else {
                temp.setNoteText(text);
                temp.setNoteDate(date);
                dao.updateNote(temp);//update note in database
            }
            // return to the MainActivity
            Intent intent = new Intent(this, Notes_Activity2.class);
            startActivity(intent);
        }

    }
}