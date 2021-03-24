package com.example.myhomefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggeButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    int index = 1;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.gambar);
        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        switchButton = (Switch) findViewById(R.id.switch1);
        toggeButton = (ToggleButton) findViewById(R.id.toggleButton);
        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        switchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(switchButton.isChecked()){
                    gd.setColors(new int[]{ Color.YELLOW , Color.TRANSPARENT });
                    imageView.setBackground(gd);
                }else{
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        toggeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(toggeButton.isChecked()){
                    // Ketika button di klik Aktif kipas akan otomatis lvl 1 karena index = 1
                    rotateAnimator.setDuration(SPEED[index]);
                    seekBar.setProgress(index);
                    rotateAnimator.start();
                }else{
                    rotateAnimator.end();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                index = progress;
                if(index == 0){
                    // Ketika kipas di set ke level 0 maka kipas akan otomatis mati
                    rotateAnimator.end();
                    toggeButton.setChecked(false);
                }else if(toggeButton.isChecked()){
                    // kipas akan berputar ketika button nya aktif
                    rotateAnimator.setDuration(SPEED[index]);
                    rotateAnimator.start();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

}