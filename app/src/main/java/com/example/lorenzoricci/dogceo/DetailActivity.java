package com.example.lorenzoricci.dogceo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lorenzoricci.dogceo.models.BreedResponse;
import com.example.lorenzoricci.dogceo.models.ImageResponse;
import com.example.lorenzoricci.dogceo.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private TextView mTextView;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        String serializedBreed = extras.getString("breed");

        mImageView = (ImageView) findViewById(R.id.breed_img);
        mTextView = (TextView) findViewById(R.id.breed_name);
        mTextView.setText( serializedBreed );

        ServiceBuilder.getService().getImage(serializedBreed).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                Log.d(TAG, "onResponse: ok");
                String imgURL = response.body().getMessage();

                Glide.with(DetailActivity.this).load(imgURL).into(mImageView);

            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }
}
