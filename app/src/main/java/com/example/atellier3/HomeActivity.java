package com.example.atellier3;

import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.atellier3.beans.User;

public class HomeActivity extends AppCompatActivity {
    private MyDataBase mydb;

    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewMessage = findViewById(R.id.textViewMessage);
        mydb = new MyDataBase(HomeActivity.this);
        User user = mydb.getUser();
        textViewMessage.setText("Bonjour "+user.getName()+" votre mot de passe est "+user.getPwd());

    }
}