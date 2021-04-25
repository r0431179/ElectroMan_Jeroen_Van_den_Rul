package be.ucll.electroman_jeroen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProblemActivity extends AppCompatActivity {

    TextView textviewDetailedProblem, textviewRepairInformation;
    EditText edittextDetailedProblem, edittextRepairInformation;
    Button buttonBack, buttonDone;
    WorkOrderDao workOrderDao;
    UserDao userDao;
    int currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        AppDatabase appDatabase = AppDatabase.getUserDatabase(getApplicationContext());
        userDao = appDatabase.userDao();
        workOrderDao = appDatabase.workOrderDao();
        Intent intent = getIntent();



        edittextDetailedProblem = findViewById(R.id.editTextDetailedProblem);
        edittextRepairInformation = findViewById(R.id.editTextRepairInformation);

        edittextDetailedProblem.setFocusable(false);
        edittextDetailedProblem.setEnabled(false);
        edittextDetailedProblem.setCursorVisible(false);

        buttonBack = findViewById(R.id.buttonBack);
        buttonDone = findViewById(R.id.buttonDone);

        //Nakijken of gebruiker de edittexts mag bewerken. (Of we via row-klik op de activity terecht gekomen zijn, of via de processed-button)
        Bundle bundle = getIntent().getExtras();
        boolean checkIfEditable = bundle.getBoolean("EditableProblem");
        if(checkIfEditable == false){
            disableTextFieldsProperties();
            buttonDone.setVisibility(View.GONE);
        }

        int currentWorkOrderID = getIntent().getExtras().getInt("workorderID");
        WorkOrderEntity workOrderEntityToEdit = workOrderDao.loadSpecificWorkOrders(currentWorkOrderID);
        fillFields(workOrderEntityToEdit);
        currentUserID = workOrderEntityToEdit.userId;

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workOrderEntityToEdit.repairInformation = edittextRepairInformation.getText().toString();
                workOrderEntityToEdit.processed = true;
                workOrderDao.updateWorkOrderEntity(workOrderEntityToEdit);

                Intent intent = new Intent(ProblemActivity.this, OverviewActivity.class);
                intent.putExtra("userid", workOrderEntityToEdit.userId);
                ProblemActivity.this.startActivity(intent);
                finish();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProblemActivity.this, OverviewActivity.class);
                intent.putExtra("userid", workOrderEntityToEdit.userId);
                ProblemActivity.this.startActivity(intent);
                finish();
            }
        });
    }



    private void disableTextFieldsProperties(){


        edittextRepairInformation.setFocusable(false);
        edittextRepairInformation.setEnabled(false);
        edittextRepairInformation.setCursorVisible(false);
    }

    private void fillFields(WorkOrderEntity workOrderEntity){
        edittextDetailedProblem.setText(workOrderEntity.detailedProblemDescription.toString());
        edittextRepairInformation.setText(workOrderEntity.repairInformation.toString());
    }
}