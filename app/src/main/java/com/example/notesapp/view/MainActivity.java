package com.example.notesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.notesapp.Note;
import com.example.notesapp.NotesAdapter;
import com.example.notesapp.R;
import com.example.notesapp.controler.Contract;
import com.example.notesapp.model.DatabaseModel;
import com.example.notesapp.presenter.Presenter;
import com.example.notesapp.animation.Animation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View {
    private TextView count;
    private NotesAdapter adapter;
    private RecyclerView notesRV;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = findViewById(R.id.count);
        notesRV = findViewById(R.id.notes);
        presenter = new Presenter(new DatabaseModel(), this);

        try {
            presenter.getItems();
        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }

        onCancelBtnClick();
        onSelectAllBtnClick();
        onAddItemBtnClick();
        onDeleteBtnClick();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        try {
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showItems(Contract.Model model) {
        List<Note> items = model.getAll(this);
        notesRV.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(items);
        adapter.setOnItemClickListener((itemView, position) -> {
            Note current = items.get(position);

            if (!adapter.getVisibility()) {
                Intent intent = new Intent(this, AddItem.class);
                intent.putExtra("item", current);
                startActivity(intent);
                return;
            }

            itemSelect(isItemSelected(itemView), itemView);
        });

        adapter.setOnItemLongClickListener((itemView, position) -> {
            Animation.headBarShow(this);
            Animation.addBtnHide(this);
            Animation.deleteBtnShow(this);
            adapter.setVisibility(true);
            adapter.notifyDataSetChanged();
            itemSelect(isItemSelected(itemView), itemView);
            return true;
        });

        notesRV.setAdapter(adapter);
    }

    @Override
    public void deleteItems(Contract.Model model) {
        List<Note> items = model.getAll(this);
        List<View> itemViews = this.getAllItemViews();

        for (int i = 0; i < items.size(); i++) {
            View itemView = itemViews.get(i);

            if (this.isItemSelected(itemView)) {
                Note item = items.get(i);
                model.deleteItem(this, item);
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onCancelBtnClick() {
        ImageButton cancelBtn = findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(view -> {
            Animation.headBarHide(this);
            Animation.addBtnShow(this);
            Animation.deleteBtnHide(this);
            selectAll(false);
            adapter.setVisibility(false);
            adapter.notifyDataSetChanged();
        });
    }

    private void onSelectAllBtnClick() {
        ImageButton selectAllBtn = findViewById(R.id.selectAll);
        selectAllBtn.setOnClickListener(view -> selectAll(!isAllSelected()));
    }

    private void onAddItemBtnClick() {
        ImageButton addItemBtn = findViewById(R.id.addItem);
        addItemBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddItem.class);
            startActivity(intent);
        });
    }

    private void onDeleteBtnClick() {
        ImageButton deleteBtn = findViewById(R.id.delete);
        deleteBtn.setOnClickListener(view -> {
            presenter.getItemsToDelete();
        });
    }

    private boolean isItemSelected(View itemView) {
        CheckBox isSelected = itemView.findViewById(R.id.isSelected);
        return !isSelected.isChecked();
    }

    private void itemSelect(boolean select, View itemView) {
        CardView wrapper = itemView.findViewById(R.id.wrapper);
        CheckBox isSelected = itemView.findViewById(R.id.isSelected);

        if (select) {
            int lightBlue = getColor(R.color.light_blue);
            wrapper.setCardBackgroundColor(lightBlue);
            isSelected.setChecked(true);
        } else {
            int white = getColor(R.color.white);
            wrapper.setCardBackgroundColor(white);
            isSelected.setChecked(false);
        }

        updateCount();
    }

    private boolean isAllSelected() {
        int count = Integer.parseInt(String.valueOf(this.count.getText()));
        return count == getAllItemViews().size();
    }

    private void selectAll(boolean select) {
        List<View> itemViews = getAllItemViews();

        for (int i = 0; i < itemViews.size(); i++) {
            View item = itemViews.get(i);
            itemSelect(select, item);
        }
    }

    private List<View> getAllItemViews() {
        List<View> itemViews = new ArrayList<>();

        for (int i = 0; i < notesRV.getChildCount(); i++) {
            View item = notesRV.getChildAt(i);
            itemViews.add(item);
        }

        return itemViews;
    }

    private void updateCount() {
        int count = 0;

        for (int i = 0; i < getAllItemViews().size(); i++) {
            View item = getAllItemViews().get(i);
            CheckBox isSelected = item.findViewById(R.id.isSelected);

            if (isSelected.isChecked()) {
                count += 1;
            }
        }

        this.count.setText(String.valueOf(count));
    }
}
