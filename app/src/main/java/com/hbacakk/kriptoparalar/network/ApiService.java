package com.hbacakk.kriptoparalar.network;


import com.hbacakk.kriptoparalar.model.ParaBirimi;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("parabirimi.json")//gözlemlenebilir
    Observable<List<ParaBirimi>> getParaBirimleri();

}
