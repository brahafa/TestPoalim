package poalim.tmdb.test.model;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Movie implements Serializable {
    /**
     * The movie id
     **/
    protected int id;
    /**
     * The movie title
     **/
    protected String title;
    /**
     * The movie original title
     **/
    protected String original_title;
    /**
     * The movie release date
     **/
    protected String release_date;

    /**
     * The movie backdrop image
     **/
    protected String backdrop_path;
    /**
     * The movie rating
     **/
    protected double vote_average;

    /**
     * The movie overview
     **/
    protected String overview;
    /**
     * The movie popularity
     **/
    protected double popularity;
    /**
     * The movie poster image
     **/
    protected String poster_path;

    public Movie(int id, String title, String original_title, String release_date,
                 String poster, double rating, double popularity) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster_path = poster;
        this.vote_average = rating;
        this.popularity = popularity;
    }

    public String getBackdropPath(){
        return backdrop_path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public double getRating() {
        return vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public long whenIsBeingReleased() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(this.release_date);
            return date.getTime() - Calendar.getInstance().getTime().getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}
