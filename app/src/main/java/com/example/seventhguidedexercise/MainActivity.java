package com.example.seventhguidedexercise;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView rate;
    Button click, close;
    AlertDialog.Builder alertDialogBuilder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        alertDialogBuilder = new AlertDialog.Builder(this);
        ratingBar = findViewById(R.id.ratingBarGE7);
        rate = findViewById(R.id.ratingText);
        click = findViewById(R.id.btnClick);
        close = findViewById(R.id.btnClose);

        showRating();
        closeApplication();
    }

    private void closeApplication() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.setTitle("Warning Message!")
                        .setMessage("Do you want to close the Application?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // once the Yes is selected it will close the application using
                                // this pre-defined method named finish() in android
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // once the No is selected it will simply close/cancel the
                                // dialogInterface
                                dialogInterface.cancel();
                            }
                        })
                        // this will sets whether this dialog is cancelable or not, by default is TRUE
                        .setCancelable(false);
                // display our dialog builder
                alertDialogBuilder.show();
            }
        });

    }

    private void showRating() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (ratingBar.getRating() >= 3) {
                    rate.setTextColor(Color.GREEN);
                } else {
                    rate.setTextColor(Color.RED);
                }
                rate.setText("Rate: " + ratingBar.getRating());
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Rate: " + ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}