package com.jalaj.firstapp.autosearchproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements TextWatcher{
    SearchDBHelper searchDBHelper;
    AutoCompleteTextView saSearchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saSearchBar = (AutoCompleteTextView)findViewById(R.id.saSearchBox);
        saSearchBar.addTextChangedListener(this);
         searchDBHelper = new SearchDBHelper(this);
        searchDBHelper.buildDatabase();
        String [] results =  searchDBHelper.getProductFullName("");
        for(int i = 0; i < results.length; i++)
        {
            Log.i(this.toString(), String.valueOf(results[i]));
        }
        ArrayAdapter <String> arrayAdapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results);
       arrayAdapter.setNotifyOnChange(true);
        saSearchBar.setThreshold(1);
        saSearchBar.setAdapter(arrayAdapter);

    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    public void onDestroy()
    {
        super.onDestroy();
        searchDBHelper.close();
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
}
