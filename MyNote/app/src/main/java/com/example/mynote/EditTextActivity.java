package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTextActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_title,editText_author,editText;
    private Button save, cancel;
    private NotePadDB notePadDB;
    private SQLiteDatabase database;
    private String pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        initView();
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        notePadDB = new NotePadDB(this);
        database = notePadDB.getWritableDatabase();
        pos = getIntent().getStringExtra(NotePadDB.ID);
        String title = getIntent().getStringExtra(NotePadDB.TITLE);
        editText_title.setText(title);
        editText_title.setSelection(title.length());
        String author = getIntent().getStringExtra(NotePadDB.AUTHOR);
        editText_author.setText(author);
        editText_author.setSelection(author.length());
        String content = getIntent().getStringExtra(NotePadDB.CONTENT);
        editText.setText(content);
        editText.setSelection(content.length());
    }

    private void initView() {
        editText_title = findViewById(R.id.edit_text_title);
        editText_author = findViewById(R.id.edit_text_author);
        editText = findViewById(R.id.edit_text);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                database.delete(NotePadDB.NAME, "_id=" + pos, null);
                insertDB();
                Intent intent = new Intent(EditTextActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }
    private void insertDB() {
        ContentValues value = new ContentValues();
        value.put(NotePadDB.AUTHOR, editText_author.getText().toString());
        value.put(NotePadDB.TITLE, editText_title.getText().toString());
        value.put(NotePadDB.CONTENT, editText.getText().toString());
        value.put(NotePadDB.TIME, formatTime());
        database.insert(NotePadDB.NAME, null, value);
    }

    private String formatTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}