package com.epicodus.filmexplorer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.epicodus.filmexplorer.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.searchButton) ImageButton mSearchButton;
    @Bind(R.id.searchQuery)EditText mSearchEditText;
    @Bind(R.id.spinner) Spinner mSearchSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.search, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSearchSpinner.setAdapter(adapter);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultListActivity.class);
                String search = mSearchEditText.getText().toString();
                String type = mSearchSpinner.getSelectedItem().toString();
                intent.putExtra("search", search);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }
}
