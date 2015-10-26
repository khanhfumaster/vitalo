package com.example.halalyon.vitalo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.halalyon.vitalo.R;

/**
 * Created by halalyon on 26/10/15.
 */
public class EditNotesActivity extends Activity {

    public int position = 0;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_edit);

        String editednotes = getIntent().getStringExtra("notes");
        position = getIntent().getIntExtra("position", -1);

        editText = (EditText) findViewById(R.id.editTextMulti);
        editText.setText(editednotes);
    }

    public void onSubmit(View v) {
        editText = (EditText) findViewById(R.id.editTextMulti);
        Intent data = new Intent();
        data.putExtra("updatednotes", editText.getText().toString());
        data.putExtra("position", position);
        setResult(RESULT_OK, data);
        finish();

    }

    public void onCancel(View v) {

        finish();
    }



}
