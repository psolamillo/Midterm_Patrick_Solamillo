package com.example.midterm_patrick_solalmillo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText multNumber;
    private Button multButton;
    private ListView multResult;

    EditText editText;
    int product;
    private ArrayAdapter<String> adapter;



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
        multButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.buttonM){
            StringBuffer buffer = new StringBuffer();

            String fs = editText.getText().toString();

            int numMult = Integer.parseInt(fs);

            for (int i = 1; i <= 10; i++) {
                product = (i * numMult);
                buffer.append(numMult + " X " +
                        i + " = " + numMult + "\n\n");
            }

        }


    }
}