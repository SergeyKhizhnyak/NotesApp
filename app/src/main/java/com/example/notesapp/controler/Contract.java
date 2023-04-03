package com.example.notesapp.controler;

import android.content.Context;


import com.example.notesapp.Note;

import java.util.List;


public interface Contract {
    interface Model {
        List<Note> getAll(Context context);

        void addItem(Context context, Note note);

        void deleteItem(Context context, Note note);

    }

    interface View {
        interface AddItemView {
            void addItem(Model model);

            void back(Model model);
        }

        void showItems(Model model);

        void deleteItems(Model model);
    }

    interface Presenter {
        interface AddItemPresenter {
            void setItem();
        }

        void getItems();

        void getItemsToDelete();
    }
}
