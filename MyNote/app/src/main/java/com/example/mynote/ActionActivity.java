package com.example.mynote;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener {
    private int action;
    private Button btnAdd, btnBack;
    private EditText editText_title;
    private EditText editText_author;
    private EditText editText;
    private NotePadDB notePadDB;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        action = getIntent().getIntExtra("action", 1);
        SharedPreferences pref = getSharedPreferences("author",MODE_PRIVATE);

        notePadDB = new NotePadDB(this);
        database = notePadDB.getWritableDatabase();
        initView();
        String author = pref.getString("author","yoyo");
        if(!author.equals(""))
            editText_author.setText(author);
        else
            editText_author.setText("yoyo");
        doAction();
    }

    private void initView() {
        editText_title = findViewById(R.id.edit_text_title);
        editText_author = findViewById(R.id.edit_text_author);
        editText = findViewById(R.id.edit_text);
        btnAdd = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);
        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void doAction() {
        switch (action){
            case 1:

                break;
            default:
                return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                insertDB();
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void insertDB() {
        ContentValues value = new ContentValues();
        value.put(NotePadDB.TITLE, editText_title.getText().toString());
        value.put(NotePadDB.AUTHOR, editText_author.getText().toString());
        value.put(NotePadDB.CONTENT, editText.getText().toString());
        value.put(NotePadDB.TIME, formatTime());
        database.insert(NotePadDB.NAME, null, value);
    }

    private String formatTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

}