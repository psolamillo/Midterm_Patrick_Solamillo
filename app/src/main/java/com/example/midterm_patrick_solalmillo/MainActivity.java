package com.example.midterm_patrick_solalmillo;



import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //private EditText multNumber;
    private Button multButton;
    private Button historyButton;
    private ListView multResult;

    EditText editText;
    int product;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> rows = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editText = (EditText)findViewById(R.id.inputNum);
        multResult = findViewById(R.id.resultList);
        multButton = findViewById(R.id.buttonM);
        historyButton = findViewById(R.id.historyButton);
        multButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);


        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
            rows);
        multResult.setAdapter(adapter);

        multResult.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Delete row?")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String removed = rows.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,
                                "Deleted: " + removed, Toast.LENGTH_SHORT).show();

                    }
                })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                });


                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

    }

    @Override
    public void onClick(View v)
    {

        rows.clear();
        String txt = editText.getText().toString().trim();
        if(v.getId() == R.id.buttonM){
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();


            int numMult = Integer.parseInt(txt);

            for (int i = 1; i <= 10; i++) {
                product = (i * numMult);
                rows.add(numMult + " X " + i + " = " + product);


            }

        }
        adapter.notifyDataSetChanged();

        if (v.getId() == R.id.historyButton)
        {
            Toast.makeText(this, "HISTORYACTIVITYSTART", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        }



    }
}