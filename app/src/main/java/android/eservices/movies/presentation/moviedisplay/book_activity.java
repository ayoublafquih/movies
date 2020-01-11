package android.eservices.movies.presentation.moviedisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.eservices.movies.R;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class book_activity extends AppCompatActivity {

    private ImageView thumbnailImageView;
    private TextView titleTextView;
    private TextView dateTextView;
    private TextView descriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_activity);

        Intent intent = getIntent();

        titleTextView = findViewById(R.id.title);
        dateTextView = findViewById(R.id.date);
        descriptionTextView = findViewById(R.id.description);
        thumbnailImageView = findViewById(R.id.image);

        String title = intent.getExtras().getString("title");
        String description = intent.getExtras().getString("description");
        String urlPoster = intent.getExtras().getString("thumbnail");
        String date = intent.getExtras().getString("date");

        titleTextView.setText(title);
        dateTextView.setText(date);
        descriptionTextView.setText(description);
        Picasso.with(descriptionTextView.getContext())
                .load(urlPoster)
                .into(thumbnailImageView);
    }


}
