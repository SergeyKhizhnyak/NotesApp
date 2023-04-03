package com.example.notesapp.presenter;

import android.os.AsyncTask;

import com.example.notesapp.controler.Contract;

public class Presenter implements Contract.Presenter {
    private final Contract.Model model;
    private final Contract.View view;

    public Presenter(Contract.Model model, Contract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getItems() {
        AsyncTask.execute(() -> view.showItems(model));
    }

    @Override
    public void getItemsToDelete() {
        AsyncTask.execute(() -> view.deleteItems(model));
    }
}
