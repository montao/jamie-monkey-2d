package dev.android.gamex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends ActionBarActivity {
    CatchGame cg;

    // start app
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cg = new CatchGame(this, 5, "Elsa");
        setContentView(cg);
        cg.setBackground(getResources().getDrawable(R.mipmap.forest));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // method called when top right menu is tapped
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int difficulty = cg.NBRSTEPS;
        String name = cg.heroName;

        switch (item.getItemId()) {
            case R.id.item11:
                cg = new CatchGame(this, 3, name);
                setContentView(cg);
                return true;
            case R.id.item12:
                cg = new CatchGame(this, 5, name);
                setContentView(cg);
                return true;
            case R.id.item13:
                cg = new CatchGame(this, 7, name);
                setContentView(cg);
                return true;
            case R.id.item14:
                cg = new CatchGame(this, 9, name);
                setContentView(cg);
                return true;
            case R.id.item15:
                cg = new CatchGame(this, 11, name);
                setContentView(cg);
                return true;
            case R.id.item21:
                cg = new CatchGame(this, difficulty, "Elsa");
                setContentView(cg);
                return true;
            case R.id.item22:
                cg = new CatchGame(this, difficulty, "Bamse");
                setContentView(cg);
                return true;
            default:
                cg.paused = true;
                return super.onOptionsItemSelected(item);
        }
    }


}


class CatchGame extends View {
    int NBRSTEPS; // number of discrete positions in the x-dimension; must be uneven
    String heroName;
    int screenW;
    int screenH;
    int[] x; // x-coordinates for falling objects
    int[] y; // y-coordinates for falling objects
    int[] hero_positions; // x-coordinates for hero
    Random random = new Random();
    int ballW; // width of each falling object
    int ballH; // height of ditto
    float dY; //vertical speed
    Bitmap ball, hero;
    int heroXCoord;
    int heroYCoord;
    int xsteps;
    int score;
    int offset;
    boolean gameOver; // default value is false
    boolean toastDisplayed;
    boolean paused = false;

    // constructor, load images and get sizes
    public CatchGame(Context context, int difficulty, String name) {
        super(context);
        NBRSTEPS = difficulty;
        heroName = name;
        x = new int[NBRSTEPS];
        y = new int[NBRSTEPS];
        hero_positions = new int[NBRSTEPS];
        int resourceIdFalling = 0;
        int resourceIdHero = 0;
        if (heroName.equals("Elsa")) {
            resourceIdFalling = R.mipmap.falling_object;
            resourceIdHero = R.mipmap.hero;
        }
        if (heroName.equals("Bamse")) {
            resourceIdFalling = R.mipmap.falling_object_bamse;
            resourceIdHero = R.mipmap.hero_bamse;
        }
        ball = BitmapFactory.decodeResource(getResources(), resourceIdFalling); //load a ball image
        hero = BitmapFactory.decodeResource(getResources(), resourceIdHero); //load a hero image
        ballW = ball.getWidth();
        ballH = ball.getHeight();
    }

    // set coordinates, etc.
    void initialize() {
        if (!gameOver) { // run only once, when the game is first started
            int maxOffset = (NBRSTEPS - 1) / 2;
            for (int i = 0; i < x.length; i++) {
                int origin = (int) (screenW / 2) + xsteps * (i - maxOffset);
                x[i] = origin - (ballW / 2);
                hero_positions[i] = origin - hero.getWidth();
            }
            int heroWidth = hero.getWidth();
            int heroHeight = hero.getHeight();

            hero = Bitmap.createScaledBitmap(hero, heroWidth * 2, heroHeight * 2, true);
            heroYCoord = screenH - 2 * heroHeight; // bottom of screen

        }
        for (int i = 0; i < y.length; i++) {
            y[i] = -random.nextInt(1000); // place items randomly in vertical direction
        }

        offset = (NBRSTEPS - 1) / 2; // place hero at centre of the screen
        heroXCoord = hero_positions[offset];

        // initialize or reset global attributes
        dY = 2.0f;
        score = 0;
        gameOver = false;
        toastDisplayed = false;
    }

    // method called when the screen opens
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
        xsteps = (int) (w / NBRSTEPS);
        initialize();
    }

    // method called when the "game over" toast has finished displaying
    void restart(Canvas canvas) {

        toastDisplayed = true;

        initialize();
        draw(canvas);
    }

    // update the canvas in order to display the game action
    @Override
    public void onDraw(Canvas canvas) {
        if (toastDisplayed) {
            restart(canvas);
            return;
        }
        super.onDraw(canvas);

        int heroHeight = hero.getHeight();
        int heroWidth = hero.getWidth();
        int heroCentre = heroXCoord + heroWidth / 2;

        Context context = this.getContext();

        // compute locations of falling objects
        for (int i = 0; i < y.length; i++) {
            if (!paused) {
                y[i] += (int) dY;
            }
            // if falling object hits bottom of screen
            if (y[i] > (screenH - ballH) && !gameOver) {
                dY = 0;
                gameOver = true;
                paused = true;
                int duration = Toast.LENGTH_SHORT;

                final Toast toast = Toast.makeText(context, "GAME OVER!\nScore: " + score, duration);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                        toastDisplayed = true;
                    }
                }, 3000);
                Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                // Vibrate for 3000 milliseconds
                v.vibrate(3000);

            }
            // if the hero catches a falling object
            if (x[i] < heroCentre && x[i] + ballW > heroCentre &&
                    y[i] > screenH - ballH - heroHeight) {

                y[i] = -random.nextInt(1000); // reset to new vertical position
                score += 1;
            }

            LinearLayout layout = new LinearLayout(this.getContext());

            TextView textView = new TextView(context);
            textView.setVisibility(View.VISIBLE);
            textView.setText("Score: " + score);
            layout.addView(textView);

            layout.measure(canvas.getWidth(), canvas.getHeight());
            layout.layout(0, 0, canvas.getWidth(), canvas.getHeight());

            layout.draw(canvas);

        }

        canvas.save(); //Save the position of the canvas.

        for (int i = 0; i < y.length; i++) {
            canvas.drawBitmap(ball, x[i], y[i], null); //Draw the ball on the canvas.
        }
        canvas.drawBitmap(hero, heroXCoord, heroYCoord, null); //Draw the hero on the canvas.

        canvas.restore();
        //Call the next frame.
        invalidate();
    }

    // event listener for when the user touches the screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (paused) {
            paused = false;
        }
        int action = MotionEventCompat.getActionMasked(event);
        if (action != MotionEvent.ACTION_DOWN || gameOver) { // non-touchdown event or gameover
            return true; // do nothing
        }
        int coordX = (int) event.getX();
        int xCentre = (int) ((screenW / 2) - (hero.getWidth() / 2));
        int maxOffset = hero_positions.length - 1; // can't move outside right edge of screen
        int minOffset = 0; // ditto left edge of screen
        if (coordX < xCentre && offset > minOffset) { // touch event left of the centre of screen
            offset--; // move hero to the left
        }
        if (coordX > xCentre && offset < maxOffset) { // touch event right of the centre of screen
            offset++; // move hero to the right
        }
        heroXCoord = hero_positions[offset];

        return true;
    }
}