package poalim.tmdb.test.network;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import poalim.tmdb.test.model.Movie;
import poalim.tmdb.test.util.Constants;
import poalim.tmdb.test.util.SharePref;

public class ManageData {

    /**
     * Get favorites list
     *
     */
    public static List<Movie> getFavoriteMovies(Context context) {
        List<Movie> movies = new ArrayList<>();
        Gson gson = new Gson();
        String json = SharePref.getInstance(context).getData(Constants.SAVED_MOVIES_PREF);
        if (json.isEmpty()) {
            Toast.makeText(context, "There is something error", Toast.LENGTH_LONG).show();
        } else {
            Type type = new TypeToken<List<Movie>>() {
            }.getType();
            List<Movie> arrPackageData = gson.fromJson(json, type);
            movies.addAll(arrPackageData);
        }
        return movies;
    }

    /**
     * Check if the movie already contain in favorite list
     *
     */
    public static  boolean isMovieAlreadySaved(Movie movie, Context context) {
        List<Movie> movies = getFavoriteMovies(context);
        for (int i = 0; i < movies.size(); i++) {
            if (movie.getId() == movies.get(i).getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set to un follow movie in the content provider
     *
     */
    public static  void unFollowMovie(int id, Context context) {
        Gson gson = new Gson();
        List<Movie> movies = getFavoriteMovies(context);
        for (int i = 0; i < movies.size(); i++) {
            if (id == movies.get(i).getId()) {
                movies.remove(i);
                break;
            }
        }
        SharePref.getInstance(context).saveData(Constants.SAVED_MOVIES_PREF, gson.toJson(movies));
    }

    /**
     * Set to follow movie in the content provider
     *
     */
    public static void followMovie(Movie movie, Context context) {
        List<Movie> movieList = getFavoriteMovies(context);
        movieList.add(movie);
        SharePref.getInstance(context).saveData(Constants.SAVED_MOVIES_PREF,  (new Gson()).toJson(movieList));
    }
}
