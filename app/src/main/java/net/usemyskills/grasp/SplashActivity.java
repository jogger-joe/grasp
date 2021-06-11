package net.usemyskills.grasp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.usemyskills.grasp.databinding.ActivitySplashBinding;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class SplashActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /// init all viewModels
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModelProvider.get(TagViewModel.class);
        viewModelProvider.get(RecordGroupViewModel.class);
        viewModelProvider.get(RecordViewModel.class);

        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            this.startActivity(mainIntent);
            this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }


}