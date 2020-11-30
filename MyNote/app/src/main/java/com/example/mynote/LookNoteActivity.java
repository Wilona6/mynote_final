package com.example.mynote;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
public class LookNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView_title,textView_author,textView;
    private Button delete, back, edit;
    private NotePadDB notePadDB;
    private SQLiteDatabase database;
    private String pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_note);
        initView();
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        notePadDB = new NotePadDB(this);
        database = notePadDB.getWritableDatabase();
        pos = getIntent().getStringExtra(NotePadDB.ID);
        String title = getIntent().getStringExtra(NotePadDB.TITLE);
        textView_title.setText(title);
        String author = getIntent().getStringExtra(NotePadDB.AUTHOR);
        textView_author.setText(author);
        String content = getIntent().getStringExtra(NotePadDB.CONTENT);
        textView.setText(content);
    }

    private void initView() {
        textView_title = findViewById(R.id.look_text_title);
        textView_author = findViewById(R.id.look_text_author);
        textView = findViewById(R.id.look_text);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                //deleteByPos();

                AlertDialog.Builder dialog = new AlertDialog.Builder(LookNoteActivity.this);
                dialog.setTitle("提示框");
                dialog.setMessage("是否确认删除");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent intent1 = new Intent(LookNoteActivity.this, MyService.class);
                        intent1.putExtra("pos", pos);
                        startService(intent1);
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                });
                dialog.show();
                break;
            default:
                break;
            //Intent intent1 = new Intent(LookNoteActivity.this, MyService.class);
            //intent1.putExtra("pos", pos);
            //startService(intent1);
            //finish();
            //break;

            case R.id.back:
                finish();
                break;
            case R.id.edit:
                Intent intent = new Intent(LookNoteActivity.this, EditTextActivity.class);
                intent.putExtra(NotePadDB.ID, getIntent().getStringExtra(NotePadDB.ID));
                intent.putExtra(NotePadDB.TITLE, getIntent().getStringExtra(NotePadDB.TITLE));
                intent.putExtra(NotePadDB.AUTHOR, getIntent().getStringExtra(NotePadDB.AUTHOR));
                intent.putExtra(NotePadDB.CONTENT, getIntent().getStringExtra(NotePadDB.CONTENT));
                startActivity(intent);
                break;
        }
    }

    private void deleteByPos() {
        database.delete(NotePadDB.NAME, "_id=" + pos, null);
    }
}