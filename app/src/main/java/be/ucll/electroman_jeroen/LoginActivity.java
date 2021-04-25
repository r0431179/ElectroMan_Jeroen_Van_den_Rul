package be.ucll.electroman_jeroen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    List<UserEntity> gevondenUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppDatabase appDatabase = AppDatabase.getUserDatabase(getApplicationContext());
        appDatabase.populateDB();

        TextView textViewError;
        EditText userName, password;
        Button login, registreer;

        textViewError = findViewById(R.id.textViewError);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        registreer = findViewById(R.id.register);

        textViewError.setVisibility(View.GONE);

        registreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kijken in Room Database of deze gebruiker bestaat.
                    UserDao userDao = appDatabase.userDao();
                    gevondenUsers = userDao.checkLoginAttempt(userName.getText().toString(), password.getText().toString());

                    if(gevondenUsers.size() > 0){
                        Intent intent = new Intent(LoginActivity.this, OverviewActivity.class);
                        intent.putExtra("userid", gevondenUsers.get(0).getId());
                        LoginActivity.this.startActivity(intent);
                        finish();
                    }else{
                        textViewError.setVisibility(View.VISIBLE);
                    }
                }
        });
    }
}