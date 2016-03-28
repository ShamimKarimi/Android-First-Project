package io.sharif.prj1.st91106235.prj1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageButton upButton, downButton, leftButton, rightButton;
    ImageView golangPlayer;

    static int STEP = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView golangImageView = (ImageView) findViewById(R.id.golang_image_view);
        setRotateAnimation(golangImageView);

        upButton = (ImageButton) findViewById(R.id.up_button);
        downButton = (ImageButton) findViewById(R.id.down_button);
        rightButton = (ImageButton) findViewById(R.id.right_button);
        leftButton = (ImageButton) findViewById(R.id.left_button);

        golangPlayer = (ImageView) findViewById(R.id.golang_player);

        setArrowFunctions();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about_me) {
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void showDialog() {
        CustomDialog customDialog = new CustomDialog();
        customDialog.show(getFragmentManager(), "dialog");
    }


    void setRotateAnimation(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setDuration(2000);
        view.startAnimation(rotateAnimation);
    }

    void setArrowFunctions() {
        upButton.setOnClickListener(new ArrowButtonsOnClickListener(0, -STEP));
        downButton.setOnClickListener(new ArrowButtonsOnClickListener(0, STEP));
        leftButton.setOnClickListener(new ArrowButtonsOnClickListener(-STEP, 0));
        rightButton.setOnClickListener(new ArrowButtonsOnClickListener(STEP, 0));
    }

    class ArrowButtonsOnClickListener implements View.OnClickListener {

        int left, top;

        public ArrowButtonsOnClickListener(int left, int top) {
            this.left = left;
            this.top = top;
        }

        @Override
        public void onClick(View view) {
            if (golangPlayer.getAnimation() != null
                    && !golangPlayer.getAnimation().hasEnded()) {
                return;
            }

            TranslateAnimation animation = new TranslateAnimation(0, left, 0, top);
            animation.setDuration(500);
            animation.setFillAfter(true);

            animation.setAnimationListener(new TranslateAnimation.AnimationListener() {


                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    golangPlayer.clearAnimation();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) golangPlayer.getLayoutParams();
                    params.leftMargin += left;
                    params.topMargin += top;
                    golangPlayer.setLayoutParams(params);
                }
            });

            golangPlayer.startAnimation(animation);
        }
    }

}
