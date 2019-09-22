package poalim.tmdb.test.util;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import poalim.tmdb.test.model.Movie;
import poalim.tmdb.test.ui.adapter.MovieRecyclerAdapter;

public class Constants {
    static String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    static String POSTER_SIZE = "w185";
    static String BACKDROP_SIZE = "w780";

    public static String TOKEN_PREF = "token_pref";
    public static String SAVED_MOVIES_PREF = "saved_movies_pref";

    public static String API_KEY = "eef8f076315d2106662552d8f9c98866";

    /**
     * Transform a relative path to a complete URI poster image
     *
     * @param path
     * @return
     */
    public static String createPosterLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_IMAGES_URL);
        stringBuilder.append(POSTER_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }

    /**
     * Transform a relative backdrop path to a complete URI
     *
     * @param path
     * @return
     */
    public static String createBackdropLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(BASE_IMAGES_URL);
        stringBuilder.append(BACKDROP_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }

    public static void initListRV(Context context, List<Movie> responseList, RecyclerView listView, int columns
            , MovieRecyclerAdapter.IClickListener iClickListener) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, columns);
        listView.setLayoutManager(gridLayoutManager);
        MovieRecyclerAdapter movieRecyclerAdapter = new MovieRecyclerAdapter(context, responseList);
        movieRecyclerAdapter.setListener(iClickListener);

        listView.setAdapter(movieRecyclerAdapter);
        movieRecyclerAdapter.notifyDataSetChanged();
    }

}
