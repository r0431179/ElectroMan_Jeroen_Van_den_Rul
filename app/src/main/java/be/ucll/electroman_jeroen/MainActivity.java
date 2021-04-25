package be.ucll.electroman_jeroen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText userName, password, firstname, lastname;
    Button register, login, testbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);



                AppDatabase appDatabase = AppDatabase.getUserDatabase(getApplicationContext());
                UserDao userDao = appDatabase.userDao();
                WorkOrderDao workOrderDao = appDatabase.workOrderDao();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UserEntity aanmaken, om dit door te geven aan de DB
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(userName.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setFirstname(firstname.getText().toString());
                userEntity.setLastname(lastname.getText().toString());

                if(validateInput(userEntity)){
                    AppDatabase appDatabase = AppDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = appDatabase.userDao();
                    userDao.registerUser(userEntity);
                    register.setEnabled(false);
                    register.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"User geregistreerd, gebruik de login-knop om verder te gaan", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Gelieve alle velden in te vullen", Toast.LENGTH_LONG).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    private Boolean validateInput(UserEntity userEntity){
        if(userEntity.getUsername().isEmpty() ||
            userEntity.getPassword().isEmpty() ||
            userEntity.getFirstname().isEmpty() ||
            userEntity.getLastname().isEmpty()){
            return  false;
        }
        return true;
    }
}