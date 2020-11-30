package com.example.mynote;


import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();
    }

    private void initViews() {
        editText = (EditText) findViewById(R.id.author);
        button = (Button) findViewById(R.id.save_author);
        button.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_author:
                String author = editText.getText().toString().trim();
                SharedPreferences.Editor editor = getSharedPreferences("author", MODE_PRIVATE).edit();
                editor.putString("author", author);
                editor.apply();
                Toast.makeText(getApplicationContext(), "修改成功!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}