package poalim.tmdb.test.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import poalim.tmdb.test.R;
import poalim.tmdb.test.model.Movie;
import poalim.tmdb.test.network.ManageData;
import poalim.tmdb.test.util.Constants;

public class MovieDetailsFragment extends Fragment {
    private View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.activity_movie, container, false);
        //ask for the movie
        if (getArguments() != null && getArguments().get("movie_data") != null) {
            setData((Movie) Objects.requireNonNull(getArguments().get("movie_data")));
        }

        return mainView;
    }


    public void setData(final Movie data) {

        /*
         * Init variable
         */
        ImageView backdropView = this.mainView.findViewById(R.id.movie_back_drop_path);
        ImageView imageView = this.mainView.findViewById(R.id.movie_cover);
        TextView titleTV = this.mainView.findViewById(R.id.movie_title_details);
        TextView overviewTV = this.mainView.findViewById(R.id.overview);
        TextView ratingTV = this.mainView.findViewById(R.id.movie_rating);
        TextView releaseYearTV = this.mainView.findViewById(R.id.movie_release_date);
        final ToggleButton favoriteButton = this.mainView.findViewById(R.id.favorite_button);

        /*
         * Load images
         */
        Picasso.get().load(Constants.createBackdropLink(data.getBackdropPath())).into(backdropView);
        Picasso.get().load(Constants.createPosterLink(data.getPoster())).into(imageView);

        /*
         * Load text
         */
        titleTV.setText(data.getTitle());
        ratingTV.setText(this.getString(R.string.row_rating, data.getRating() + ""));
        overviewTV.setText(data.getOverview());
        releaseYearTV.setText(this.getString(R.string.row_released, data.getReleaseDate()));

        /*
         * Favorites
         */
        if (ManageData.isMovieAlreadySaved(data, getActivity())) {
            favoriteButton.setChecked(true);
        }
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favoriteButton.isChecked()) {
                    ManageData.followMovie(data, getActivity());
                } else {
                    ManageData.unFollowMovie(data.getId(), getActivity());
                }
            }
        });
    }


}
