package com.example.notesapp.presenter;

import android.os.AsyncTask;

import com.example.notesapp.controler.Contract;

public class AddItemPresenter implements Contract.Presenter.AddItemPresenter {
    private final Contract.Model model;
    private final Contract.View.AddItemView view;

    public AddItemPresenter(Contract.Model model, Contract.View.AddItemView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void setItem() {
        AsyncTask.execute(() -> view.addItem(model));
    }
}
