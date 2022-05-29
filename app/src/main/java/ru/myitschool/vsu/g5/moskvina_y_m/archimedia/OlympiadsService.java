package ru.myitschool.vsu.g5.moskvina_y_m.archimedia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.myitschool.vsu.g5.moskvina_y_m.archimedia.entities.Olympiads;

public interface OlympiadsService {
    @GET("{subject_id}/olymp.json")
    Call<List<Olympiads>> getOlympiads(@Path("subject_id") int subj_id);
}
