package com.example.midterm_patrick_solalmillo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {


    private static final String SHARED_PREF ="History";
    private static final String History = "history_list";

    Button backButton;
    private ListView historyListView;
    private ArrayAdapter<String> historyAdapter;
    //private ArrayList<String> historyList;
    private Gson gson = new Gson();
    private List<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        historyListView = findViewById(R.id.histList);
        loadHistory();

        historyAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,historyList
        );
        historyListView.setAdapter(historyAdapter);


        backButton=findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this,MainActivity.class));
        });
    }

    private void loadHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String jsonHistory = sharedPreferences.getString(History,null);

        Type listType = new TypeToken<List<String>>(){}.getType();
        historyList = gson.fromJson(jsonHistory,listType);
    }

}