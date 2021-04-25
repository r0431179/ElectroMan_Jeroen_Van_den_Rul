package be.ucll.electroman_jeroen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    UsersWithWorkOrders currentUserWithWorkorders;
    TableLayout overviewTable;
    WorkOrderDao workOrderDao;
    UserDao userDao;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        setContentView(R.layout.activity_overview);

        AppDatabase appDatabase = AppDatabase.getUserDatabase(getApplicationContext());
        userDao = appDatabase.userDao();
        workOrderDao = appDatabase.workOrderDao();

        Intent intent = getIntent();
        int currentUserId = getIntent().getExtras().getInt("userid");

        TextView welcomeTextview = findViewById(R.id.welcomeTextView);
        overviewTable = findViewById(R.id.overviewTable);

        currentUserWithWorkorders = userDao.loadSpecificUserWithWorkOrders(currentUserId);
        //Scherm invullen
        welcomeTextview.setText("Welcome: " + currentUserWithWorkorders.userEntity.getFirstname().toString() + " " + currentUserWithWorkorders.userEntity.getLastname().toString());
        maakTabel();
        vulTabelMetData();
    }

    private void maakTabel(){
        //Tabel opmaken
        //TabelRow voor header
        TableRow tbRow = new TableRow(this);

        //Headers toevoegen
        TextView tv0 = new TextView(this);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        TextView tv3 = new TextView(this);
        TextView tv4 = new TextView(this);

        tv0.setText("City");
        tv0.setBackgroundColor(0xFFFFC107);
        tv0.setTextColor(Color.WHITE);
        tv0.setWidth(170);
        tv0.setTextSize(11);
        tbRow.addView(tv0);

        tv1.setText("Device");
        tv1.setBackgroundColor(0xFFFFC107);
        tv1.setTextColor(Color.WHITE);
        tv1.setWidth(250);
        tv1.setTextSize(11);
        tbRow.addView(tv1);

        tv2.setText("Problem code");
        tv2.setBackgroundColor(0xFFFFC107);
        tv2.setTextColor(Color.WHITE);
        tv2.setWidth(250);
        tv2.setTextSize(11);
        tbRow.addView(tv2);

        tv3.setText("Name");
        tv3.setBackgroundColor(0xFFFFC107);
        tv3.setTextColor(Color.WHITE);
        tv3.setWidth(120);
        tv3.setTextSize(11);
        tbRow.addView(tv3);

        tv4.setText("Processed");
        tv4.setBackgroundColor(0xFFFFC107);
        tv4.setTextColor(Color.WHITE);
        tv4.setWidth(200);
        tv4.setTextSize(11);
        tbRow.addView(tv4);

        overviewTable.addView(tbRow);
    }

    private void vulTabelMetData(){
        //Huidige tabel leegmaken voor eventuele refreshes
        overviewTable.removeViews(1, Math.max(0, overviewTable.getChildCount() - 1));
        //Refresh van de currentUserWithWorkorder, om een eventuele aanpassing van deze user al direct in de tabel te laten zien


        //Tabel opvullen met data van database die al in de 'currentUserWithWorkorders' variabele zitten
        for(i = 0; i < currentUserWithWorkorders.workorders.size(); i++){
            int workorderID = currentUserWithWorkorders.workorders.get(i).id;
            TableRow tbRowInsert = new TableRow(this);
            TextView tvCity = new TextView(this);
            TextView tvDevice = new TextView(this);
            TextView tvProblemCode = new TextView(this);
            TextView tvName = new TextView(this);
            TextView tvProcessed = new TextView(this);

            WorkOrderEntity workOrderEntityToEdit = currentUserWithWorkorders.workorders.get(i);

            tvCity.setText(currentUserWithWorkorders.workorders.get(i).city.toString());
            tvCity.setTextSize(11);
            tvDevice.setText(currentUserWithWorkorders.workorders.get(i).device.toString());
            tvDevice.setTextSize(11);
            tvProblemCode.setText(currentUserWithWorkorders.workorders.get(i).problemCode.toString());
            tvProblemCode.setTextSize(11);
            tvName.setText(currentUserWithWorkorders.workorders.get(i).customerName.toString());
            tvName.setTextSize(11);


            Button button = new Button(this);
            button.setText("No");
            button.setTextSize(11);
            button.getBackground().setColorFilter(0xFF373D61, PorterDuff.Mode.MULTIPLY);
            button.setTextColor(Color.WHITE);
            //button.setBackgroundColor(0xFF373D61);



            if(currentUserWithWorkorders.workorders.get(i).processed == true){
                button.setText("Yes");
                button.setEnabled(false);
                button.getBackground().setColorFilter(0xFF79797C, PorterDuff.Mode.MULTIPLY);
            }

            tbRowInsert.addView(tvCity);
            tbRowInsert.addView(tvDevice);
            tbRowInsert.addView(tvProblemCode);
            tbRowInsert.addView(tvName);
            tbRowInsert.addView(button);


            tbRowInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OverviewActivity.this, ProblemActivity.class);
                    intent.putExtra("EditableProblem", false);
                    intent.putExtra("workorderID", workOrderEntityToEdit.id);
                    OverviewActivity.this.startActivity(intent);
                    finish();
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OverviewActivity.this, ProblemActivity.class);
                    intent.putExtra("EditableProblem", true);
                    intent.putExtra("workorderID", workOrderEntityToEdit.id);
                    OverviewActivity.this.startActivity(intent);
                    finish();
                }
            });

            overviewTable.addView(tbRowInsert);
        }
    }

}