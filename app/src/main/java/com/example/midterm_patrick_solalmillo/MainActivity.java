package com.example.midterm_patrick_solalmillo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //private EditText multNumber;
    private Button multButton;
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
        multButton.setOnClickListener(this);


        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
            rows);
        multResult.setAdapter(adapter);

        multResult.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String removed = rows.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        "Deleted: " + removed, Toast.LENGTH_SHORT).show();
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



    }
}