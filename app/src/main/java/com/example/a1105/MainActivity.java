package com.example.a1105;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "chen";
    private ListView listView;
    private ListviewAdapter adapter;
    Observer<Integer> observer;
    private List<Goods> goods = new ArrayList<>();
    Observable<Integer> observable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onComplete();
            }
        });

        //创建观察者observer
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("chen", "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d("chen", value.toString());
                getBlog();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("chen", "error");
            }

            @Override
            public void onComplete() {
                Log.d("chen", "complete");
            }
        };



    }


    public void test(View view) {
        Toast.makeText(MainActivity.this,"bduasguiabsib ",Toast.LENGTH_LONG).show();
        observable.subscribe(observer);
    }

    private void getBlog() {
        SimpleDemoApiService simpleDemoApiService = SimpleDemoApiWrapper.getRetrofitInstance().create(SimpleDemoApiService.class);
        simpleDemoApiService.
                getJereChenBlog()
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    Log.d("chen", json);
                    showInListview(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }

        });
    }

    private void showInListview(final String responce){
        try {
            JSONArray jas = new JSONArray(responce);
            for (int i = 0; i < jas.length(); i++) {
                JSONObject jsonObject = jas.getJSONObject(i);
                String nameJson = jsonObject.getString("name");
                String priceJson = jsonObject.getString("price");
                final String imageJson = jsonObject.getString("imageUrl");

                goods.add(new Goods(nameJson, priceJson, imageJson));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ListviewAdapter(MainActivity.this, R.layout.item, goods);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
