package com.example.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText title;
    EditText note;
    ListView notes;
    TextView count;

    DBHelper helper;
    SQLiteDatabase notesDB;

    SimpleCursorAdapter adapter;

    String[] notes_fields;
    Cursor cur;

    int[] views = { R.id.id, R.id.title, R.id.note };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = findViewById(R.id.notes);
        title = findViewById(R.id.title);
        note = findViewById(R.id.note);

        helper = new DBHelper(this);
        notesDB = helper.getWritableDatabase();

        cur = notesDB.rawQuery("SELECT * FROM notes", null);
        notes_fields = cur.getColumnNames();

        adapter = new SimpleCursorAdapter(this, R.layout.playlist_item, cur, notes_fields, views, 0 );
        notes.setAdapter(adapter);
    }

    public void onClick(View v) {
        count = findViewById(R.id.count);

        String title_s = title.getText().toString();
        String note_s = note.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("title", title_s);
        cv.put("note", note_s);
        notesDB.insert(helper.TABLE_NAME, null, cv);

        cur = notesDB.rawQuery("SELECT * FROM notes", null);
        adapter = new SimpleCursorAdapter(this, R.layout.playlist_item, cur, notes_fields, views, 0 );
        adapter.notifyDataSetChanged();
        notes.setAdapter(adapter);

        count.setText("Total number of Items are: " + notes.getAdapter().getCount());
    }
}