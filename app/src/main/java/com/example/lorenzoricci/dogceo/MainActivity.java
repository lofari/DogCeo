package com.example.lorenzoricci.dogceo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.lorenzoricci.dogceo.models.BreedResponse;
import com.example.lorenzoricci.dogceo.services.ServiceBuilder;
import com.example.lorenzoricci.dogceo.services.DogService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<String> breedList = new ArrayList<>();
    private ListView mListView;
    private ArrayAdapter<String> arrayAdapter;
    private ProgressBar mProgressBar;
    private EditText mInputSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mListView = (ListView) findViewById(R.id.breed_list);

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item, breedList);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("breed", mListView.getItemAtPosition(position).toString() );
                startActivity(intent);
            }
        });

        ServiceBuilder.getService().getBreed().enqueue(new Callback<BreedResponse>() {
            @Override
            public void onResponse(Call<BreedResponse> call, Response<BreedResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                breedList.clear();
                breedList.addAll(response.body().getMessage());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<BreedResponse> call, Throwable t) {

            }
        });

    }

}
