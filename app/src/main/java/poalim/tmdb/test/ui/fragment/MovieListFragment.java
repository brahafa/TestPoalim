package poalim.tmdb.test.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import poalim.tmdb.test.R;
import poalim.tmdb.test.model.Movie;
import poalim.tmdb.test.network.Network;
import poalim.tmdb.test.network.CallBackNetwork;
import poalim.tmdb.test.ui.adapter.MovieRecyclerAdapter;
import poalim.tmdb.test.ui.activity.MainActivity;
import poalim.tmdb.test.util.Constants;

public class MovieListFragment extends Fragment {
    private View view;
    private RecyclerView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        initData();
        return view;
    }

    private void initData() {
        listView = view.findViewById(R.id.list_view);
        final MovieRecyclerAdapter.IClickListener iClickListener = new MovieRecyclerAdapter.IClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                MovieDetailsFragment fragment = new MovieDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie_data", movie);
                fragment.setArguments(bundle);
                ((MainActivity) getActivity()).openNewFragment(fragment, "MovieDetailsFragment");
            }
        };

        Network network = new Network(getActivity(), new CallBackNetwork() {
            @Override
            public void onDataDone(List responseList) {
                Constants.initListRV(getActivity(), responseList, listView, 1, iClickListener);

            }
        });
        network.sendRequest(Network.RequestName.NOW_PLAYING);
    }
}
