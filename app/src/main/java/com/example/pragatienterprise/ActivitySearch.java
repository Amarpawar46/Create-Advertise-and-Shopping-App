package com.example.pragatienterprise;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivitySearch extends AppCompatActivity {
    Button imageView;
    GridLayout gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        imageView = findViewById(R.id.next);
        imageView.setOnClickListener(view -> startActivity(new Intent(ActivitySearch.this, ThirdActivity.class)));
        gridView = findViewById(R.id.grid1);
        setSingleEvent(gridView);
    }

    private void setSingleEvent(GridLayout gridView) {
        for (int i = 0; i < gridView.getChildCount(); i++) {
            CardView cardView = (CardView) gridView.getChildAt(i);

            cardView.setOnClickListener(v -> {
                if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                    // change bg color
                    cardView.setCardBackgroundColor(Color.parseColor("#FBBB00"));
                    Toast.makeText(ActivitySearch.this, "Selected", Toast.LENGTH_SHORT).show();
                } else {
                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                }
            });
        }
    }
}






