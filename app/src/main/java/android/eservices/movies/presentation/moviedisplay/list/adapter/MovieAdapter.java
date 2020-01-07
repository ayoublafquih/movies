package android.eservices.movies.presentation.moviedisplay.list.adapter;

import android.content.Context;
import android.content.Intent;
import android.eservices.movies.R;
import android.eservices.movies.book_activity;
import android.eservices.movies.data.api.model.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnailImageView;
        public TextView titleTextView;
        public Switch favoriteSwitch;
        private View v;
        private Movie movieItemViewModel;
        private MovieActionInterface movieActionInterface;
        CardView cardView;


        public MovieViewHolder(View v, MovieActionInterface movieActionInterface) {
            super(v);
            titleTextView = v.findViewById(R.id.title_text_view);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            thumbnailImageView = v.findViewById(R.id.thumbnail_image_view);
            cardView = v.findViewById(R.id.card_view_id);
            this.v = v;
            this.movieActionInterface = movieActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    movieActionInterface.onFavoriteToggle(movieItemViewModel.getId(), b);
                }
            });
        }

        void bind(Movie movieItemViewModel) {
            this.movieItemViewModel = movieItemViewModel;
            titleTextView.setText(movieItemViewModel.getTitle());
            favoriteSwitch.setChecked(movieItemViewModel.isFavorite());
            String urlPoster = movieItemViewModel.getPosterPath(v.getContext());
            Picasso.with(v.getContext())
                    .load(urlPoster)
                    .into(thumbnailImageView);
        }

    }

    private List<Movie> movieItemViewModelList;
    private MovieActionInterface movieActionInterface;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieAdapter(MovieActionInterface movieActionInterface) {
        movieItemViewModelList = new ArrayList<>();
        this.movieActionInterface = movieActionInterface;
    }

    public void bindViewModels(List<Movie> movieItemViewModelList) {
        this.movieItemViewModelList.clear();
        this.movieItemViewModelList.addAll(movieItemViewModelList);
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_content, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(v, movieActionInterface);
        return movieViewHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movieItemViewModelList.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, book_activity.class);
                intent.putExtra("title",movieItemViewModelList.get(position).getTitle());
                intent.putExtra("description",movieItemViewModelList.get(position).getOverview());
                intent.putExtra("thumbnail",movieItemViewModelList.get(position).getPosterPath(context));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieItemViewModelList.size();
    }

}
