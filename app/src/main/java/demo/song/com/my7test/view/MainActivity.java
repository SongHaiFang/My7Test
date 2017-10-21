package demo.song.com.my7test.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import demo.song.com.my7test.R;
import demo.song.com.my7test.SuperCircleView;

public class MainActivity extends AppCompatActivity {
    SuperCircleView mSuperCircleView;
    TextView textView;
    private ValueAnimator valueAnimator;
    int[] colors ={Color.RED,Color.RED,Color.RED};
    int[] colors2 ={Color.GRAY,Color.GRAY,Color.GRAY};
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);

        aniOne();

        textView = (TextView) findViewById(R.id.tv);
        mSuperCircleView = (SuperCircleView) findViewById(R.id.superview);
        mSuperCircleView.setShowSelect(false);
        valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setTarget(textView);
        valueAnimator.setDuration(3000);
//        textView.setText("78");
//        valueAnimator.start();

        valueAnimator.setDuration(3000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int i = Integer.valueOf(String.valueOf(animation.getAnimatedValue()));
                textView.setText(i + "");
                mSuperCircleView.setSelect((int) (360 * (i / 100f)));
            }
        });
        Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, MyAct.class);
                        startActivity(intent);
                    }

                };
                timer.schedule(task,3000);
    }

    private void aniOne() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(img, "translationY", 200f, 300f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(img, "scaleY", 1f, 2f, 1f);

//        float curTranslationX = img.getTranslationX();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(img,"translationY", curTranslationX, -500f, translationX);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator).after(moveIn);
        animSet.setDuration(3000);
        animSet.start();


    }
}
