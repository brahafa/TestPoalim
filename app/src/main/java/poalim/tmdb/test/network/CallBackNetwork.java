package poalim.tmdb.test.network;

import java.util.List;

public interface CallBackNetwork <T> {
    void onDataDone(List<T> responseList);
}
