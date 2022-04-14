package com.example.atellier3;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.atellier3.beans.User;

public class MainActivity extends AppCompatActivity {
    private MyDataBase mydb;

    private EditText editTextName;
    private EditText editTextPwd;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        editTextName = findViewById(R.id.editTextName);
        editTextPwd = findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(e->{
            String name = editTextName.getText().toString();
            String pwd = editTextPwd.getText().toString();

            if(name.isEmpty() || pwd.isEmpty()){
                Toast.makeText(this, "Remplissez tout les champs", Toast.LENGTH_SHORT).show();
            } else{
                User user = new User(name, pwd);
                MyDataBase mydb = new MyDataBase(MainActivity.this);
                Long res = mydb.addUser(user);
                if(res == -1){
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(this, "Bien ajouter", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}