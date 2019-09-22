package poalim.tmdb.test.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import poalim.tmdb.test.R;
import poalim.tmdb.test.network.ManageData;
import poalim.tmdb.test.util.Constants;

public class FavoritesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        RecyclerView listView = findViewById(R.id.list_view);

        Constants.initListRV(this, ManageData.getFavoriteMovies(this),listView,2, null);
    }
}
