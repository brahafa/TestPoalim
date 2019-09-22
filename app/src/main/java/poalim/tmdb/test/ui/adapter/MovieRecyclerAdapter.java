package poalim.tmdb.test.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import poalim.tmdb.test.R;
import poalim.tmdb.test.model.Movie;
import poalim.tmdb.test.util.Constants;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<Movie> data;
    private Context context;
    private IClickListener listener;

    /**
     * Constructs a new MovieRecyclerAdapter with a context
     */
    public MovieRecyclerAdapter(Context ctx, List<Movie> data) {
        this.context = ctx;
        this.data = data;
    }

    /**
     * Sets the click listener
     *
     * @param listener
     */
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movies_row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = data.get(position);
        holder.title.setText(movie.getTitle());
        holder.title_original.setText(movie.getOriginalTitle());
        holder.rating.setText(context.getString(R.string.row_rating, movie.getRating() + ""));
        holder.releaseYear.setText(context.getString(R.string.row_released, movie.getReleaseDate()));
        Picasso.get().load(Constants.createPosterLink(movie.getPoster())).placeholder(R.drawable.placeholder)
                .into(holder.thumb_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Interface for click listeners for this adapter
     */
    public interface IClickListener {
        void onItemClick(Movie movie);
    }

    /**
     * List row members
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView title_original;
        TextView rating;
        TextView releaseYear;
        ImageView thumb_image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title_details);
            title_original = itemView.findViewById(R.id.title_original);
            rating = itemView.findViewById(R.id.rating);
            releaseYear = itemView.findViewById(R.id.release_year);
            thumb_image = itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
}
