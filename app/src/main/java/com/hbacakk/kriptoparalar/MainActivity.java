package com.hbacakk.kriptoparalar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hbacakk.kriptoparalar.model.ParaBirimi;
import com.hbacakk.kriptoparalar.network.ApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    CompositeDisposable compositeDisposable;//Tek kullanımlık kullan at objesi
    static String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit=new Retrofit.Builder()
                .baseUrl("http://data.hasanbacak.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        loadData();
    }
    private void loadData(){
        final ApiService apiService=retrofit.create(ApiService.class);

        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(apiService.getParaBirimleri()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));
    }

    private void handleResponse(List<ParaBirimi> paraBirimiList){
        Log.d(TAG, "handleResponse: ----------- Yenilendi-------------------");
        for (ParaBirimi paraBirimi:paraBirimiList){
            Log.d(TAG, "handleResponse: "+paraBirimi.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}