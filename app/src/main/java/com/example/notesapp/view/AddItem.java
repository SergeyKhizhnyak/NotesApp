package com.example.notesapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.notesapp.Note;
import com.example.notesapp.R;
import com.example.notesapp.controler.Contract;
import com.example.notesapp.model.DatabaseModel;
import com.example.notesapp.presenter.AddItemPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddItem extends AppCompatActivity implements Contract.View.AddItemView {
    private AddItemPresenter presenter;
    private EditText headerTV;
    private TextView dateTV;
    private TextView symbolCountTV;
    private EditText bodyTV;
    private ImageButton saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        presenter = new AddItemPresenter(new DatabaseModel(), this);
        headerTV = findViewById(R.id.header);
        dateTV = findViewById(R.id.date);
        symbolCountTV = findViewById(R.id.symbolCount);
        bodyTV = findViewById(R.id.body);
        saveBtn = findViewById(R.id.save);
        onPreviousActivityButtonClick();
        onSymbolCountChange(headerTV);
        onSymbolCountChange(bodyTV);
        setDate();
        onSaveButtonClick();
    }

    @Override
    public void addItem(Contract.Model model) {
        model.addItem(this, getItemToSave());
    }

    @Override
    public void back(Contract.Model model) {
        ImageButton backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> {
            Note item = getItemToSave();

            if (item != null) {
                model.addItem(this, item);
                return;
            }

            finish();
        });
    }

    private void onPreviousActivityButtonClick() {
        ImageButton previousActivity = findViewById(R.id.back);
        previousActivity.setOnClickListener(view -> {
            presenter.setItem();
            finish();
        });
    }

    private void onSaveButtonClick() {
        saveBtn.setOnClickListener(view -> presenter.setItem());
    }

    private Note getItemToSave() {
        Note item = new Note();
        String header = headerTV.getText().toString();
        String body = bodyTV.getText().toString();

        if (header.isEmpty() && body.isEmpty()) {
            return null;
        }

        String date = dateTV.getText().toString();
        String symbolCount = symbolCountTV.getText().toString();
        item.header = header;
        item.date = date;
        item.symbolCount = symbolCount;
        item.body = body;
        return item;
    }

    @SuppressLint("SimpleDateFormat")
    private void setDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd HH:mm");
        String date = dateFormat.format(new Date());
        this.dateTV.setText(date);
    }

    private void onSymbolCountChange(EditText text) {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence charSequence,
                    int start,
                    int count,
                    int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int headerCount = headerTV.length();
                int bodyCount = bodyTV.length();
                int count = headerCount + bodyCount;
                symbolCountTV.setText(String.valueOf(count));

                if (headerCount != 0 || bodyCount != 0) {
                    saveBtn.setVisibility(View.VISIBLE);
                } else {
                    saveBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
