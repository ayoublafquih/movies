package android.eservices.movies.presentation.moviedisplay.favorite.adapter;

import android.eservices.movies.R;
import android.eservices.movies.data.api.model.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieViewHolder> {

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnailImageView;
        public TextView titleTextView;
        public Switch favoriteSwitch;
        private View v;
        private Movie movieItemViewModel;
        private MovieFavoriteActionInterface movieFavoriteActionInterface;


        public MovieViewHolder(View v, MovieFavoriteActionInterface movieFavoriteActionInterface) {
            super(v);
            titleTextView = v.findViewById(R.id.title_text_view);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            thumbnailImageView = v.findViewById(R.id.thumbnail_image_view);
            this.v = v;
            this.movieFavoriteActionInterface = movieFavoriteActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    movieFavoriteActionInterface.onRemoveFavorite(movieItemViewModel.getId(), b);
                    System.err.println("Apppui sur switch");
                }
            });
        }

        void bind(Movie movieItemViewModel) {
            this.movieItemViewModel = movieItemViewModel;
            titleTextView.setText(movieItemViewModel.getTitle());
            favoriteSwitch.setChecked(true);
            String urlPoster = movieItemViewModel.getPosterPath(v.getContext());
            Picasso.with(v.getContext())
                    .load(urlPoster)
                    .into(thumbnailImageView);


        }

    }

    private List<Movie> movieItemViewModelList;
    private MovieFavoriteActionInterface movieFavoriteActionInterface;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieFavoriteAdapter(MovieFavoriteActionInterface movieFavoriteActionInterface) {
        movieItemViewModelList = new ArrayList<>();
        this.movieFavoriteActionInterface = movieFavoriteActionInterface;
    }

    public void bindViewModels(List<Movie> movieItemViewModelList) {
        this.movieItemViewModelList.clear();
        this.movieItemViewModelList.addAll(movieItemViewModelList);
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_content, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(v, movieFavoriteActionInterface);
        return movieViewHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movieItemViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieItemViewModelList.size();
    }

}
