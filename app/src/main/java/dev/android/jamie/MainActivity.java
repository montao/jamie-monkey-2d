package dev.android.jamie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MotionEventCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        Log.d("myTag", "This is my message" + parent.getItemAtPosition(pos));
        String name = "Jamie";
        String str = parent.getItemAtPosition(pos).toString();
        if (str.equals("Rookie")) {
            mainLayout = new LinearLayout(this);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout menuLayout = new LinearLayout(this);
            menuLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

            textView = new TextView(this);
            textView.setVisibility(View.VISIBLE);
            str = "Score: 0";
            textView.setText(str);
            menuLayout.addView(textView);

            Button button = new Button(this);
            button.setText("Pause");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePausePlay();
                }
            });
            menuLayout.addView(button);
            Spinner spinner2 = new Spinner(this);
            spinner2.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, spinnerValue);
            spinner2.setAdapter(adapter);
            menuLayout.addView(spinner2);

            mainLayout.addView(menuLayout);

            cg = new CatchGame(this, 3, "Jamie", onScoreListener);
            cg.setBackground(getResources().getDrawable(R.drawable.bg_land_mdpi));
            mainLayout.addView(cg);
            getSupportActionBar().hide();
            setContentView(mainLayout);
            Log.d("game", "Started Advanced game");

        } else if (str.equals("Advanced")) {
            mainLayout = new LinearLayout(this);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout menuLayout = new LinearLayout(this);
            menuLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

            textView = new TextView(this);
            textView.setVisibility(View.VISIBLE);
            str = "Score: 0";
            textView.setText(str);
            menuLayout.addView(textView);

            Button button = new Button(this);
            button.setText("Pause");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePausePlay();
                }
            });
            menuLayout.addView(button);
            Spinner spinner2 = new Spinner(this);
            spinner2.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, spinnerValue);
            spinner2.setAdapter(adapter);
            menuLayout.addView(spinner2);

            mainLayout.addView(menuLayout);

            cg = new CatchGame(this, 5, "Jamie", onScoreListener);
            cg.setBackground(getResources().getDrawable(R.drawable.bg_land_mdpi));
            mainLayout.addView(cg);
//                getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            getSupportActionBar().hide();
            setContentView(mainLayout);
            Log.d("game", "Started Advanced game");
        } else if (str.equals("Expert")) {
            mainLayout = new LinearLayout(this);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout menuLayout = new LinearLayout(this);
            menuLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

            textView = new TextView(this);
            textView.setVisibility(View.VISIBLE);
            str = "Score: 0";
            textView.setText(str);
            menuLayout.addView(textView);

            Button button = new Button(this);
            button.setText("Pause");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePausePlay();
                }
            });
            menuLayout.addView(button);
            Spinner spinner2 = new Spinner(this);
            spinner2.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, spinnerValue);
            spinner2.setAdapter(adapter);
            menuLayout.addView(spinner2);

            mainLayout.addView(menuLayout);

            cg = new CatchGame(this, 7, "Jamie", onScoreListener);
            cg.setBackground(getResources().getDrawable(R.drawable.bg_land_mdpi));
            mainLayout.addView(cg);
//                getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            getSupportActionBar().hide();
            setContentView(mainLayout);
            Log.d("game", "Started Advanced game");
        } else if (str.equals("Master")) {
            mainLayout = new LinearLayout(this);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout menuLayout = new LinearLayout(this);
            menuLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

            textView = new TextView(this);
            textView.setVisibility(View.VISIBLE);
            str = "Score: 0";
            textView.setText(str);
            menuLayout.addView(textView);

            Button button = new Button(this);
            button.setText("Pause");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePausePlay();
                }
            });
            menuLayout.addView(button);
            Spinner spinner2 = new Spinner(this);
            spinner2.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, spinnerValue);
            spinner2.setAdapter(adapter);
            menuLayout.addView(spinner2);

            mainLayout.addView(menuLayout);

            cg = new CatchGame(this, 9, "Jamie", onScoreListener);
            cg.setBackground(getResources().getDrawable(R.drawable.bg_land_mdpi));
            mainLayout.addView(cg);
//                getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            getSupportActionBar().hide();
            setContentView(mainLayout);
            Log.d("game", "Started Advanced game");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    CatchGame cg;
    public TextView textView;
    public LinearLayout mainLayout;
    String[] spinnerValue = {"Difficulty", "Rookie", "Advanced", "Expert", "Master"};

    // start app
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout menuLayout = new LinearLayout(this);
        menuLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

        textView = new TextView(this);
        textView.setVisibility(View.VISIBLE);
        String str = "Score: 0";
        textView.setText(str);
        menuLayout.addView(textView);

        Button button = new Button(this);
        button.setText("Pause");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePausePlay();
            }
        });
        menuLayout.addView(button);

        Spinner spinner2 = new Spinner(this);
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, spinnerValue);
        spinner2.setAdapter(adapter);
        menuLayout.addView(spinner2);

        mainLayout.addView(menuLayout);

        cg = new CatchGame(this, 5, "Jamie", onScoreListener);
        cg.setBackground(getResources().getDrawable(R.drawable.bg_land_mdpi));
        mainLayout.addView(cg);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(mainLayout);
    }

    private void togglePausePlay() {
        if (cg.paused) {
            // play
            //  getSupportActionBar().hide();
            Toast.makeText(MainActivity.this, "Play", Toast.LENGTH_SHORT).show();
        } else {
            // pause
            //    getSupportActionBar().show();
            Toast.makeText(MainActivity.this, "Pause", Toast.LENGTH_SHORT).show();
        }

        cg.paused = !cg.paused;
    }

    private OnScoreListener onScoreListener = new OnScoreListener() {
        @Override
        public void onScore(int score) {
            textView.setText("Score: " + score);
        }
    };

    interface OnScoreListener {
        void onScore(int score);
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
        Bitmap falling, hero, jamie2, jamieleft, jamieright, falling2;
        int heroXCoord;
        int heroYCoord;
        int xsteps;
        int score;
        int offset;
        boolean gameOver; // default value is false
        boolean toastDisplayed;
        boolean paused = false;

        OnScoreListener onScoreListener;

        // constructor, load images and get sizes
        public CatchGame(Context context, int difficulty, String name, OnScoreListener onScoreListener) {
            super(context);
            NBRSTEPS = difficulty;
            heroName = name;
            this.onScoreListener = onScoreListener;

            x = new int[NBRSTEPS];
            y = new int[NBRSTEPS];
            hero_positions = new int[NBRSTEPS];
            int resourceIdFalling = 0;
            int resourceIdFalling2 = 0;
            int resourceIdHero = 0;
            if (heroName.equals("Jamie")) {
                resourceIdFalling = R.mipmap.falling_object2;
                resourceIdFalling2 = R.drawable.coconut_hdpi;
                resourceIdHero = R.drawable.left_side_hdpi;
                setBackgroundResource(R.drawable.bg_land_hdpi);
            }
            falling = BitmapFactory.decodeResource(getResources(), resourceIdFalling); //load a falling image
            falling2 = BitmapFactory.decodeResource(getResources(), resourceIdFalling2); //load a falling image
            hero = BitmapFactory.decodeResource(getResources(), resourceIdHero); //load a hero image
            jamieleft = BitmapFactory.decodeResource(getResources(), R.drawable.left_side_hdpi); //load a hero image
            jamieright = BitmapFactory.decodeResource(getResources(), R.drawable.right_side_hdpi); //load a hero image
            ballW = falling.getWidth();
            ballH = falling.getHeight();
        }

        // set coordinates, etc.
        void initialize() {
            if (!gameOver) { // run only once, when the game is first started
                int maxOffset = (NBRSTEPS - 1) / 2;
                for (int i = 0; i < x.length; i++) {
                    int origin = (screenW / 2) + xsteps * (i - maxOffset);
                    x[i] = origin - (ballW / 2);
                    hero_positions[i] = origin - hero.getWidth();
                }
                int heroWidth = hero.getWidth();
                int heroHeight = hero.getHeight();

                hero = Bitmap.createScaledBitmap(hero, heroWidth * 2, heroHeight * 2, true);
                hero = Bitmap.createScaledBitmap(hero, heroWidth * 2, heroHeight * 2, true);
                jamieleft = Bitmap.createScaledBitmap(jamieleft, jamieleft.getWidth() * 2, jamieright.getWidth() * 2, true);
                jamieright = Bitmap.createScaledBitmap(jamieright, jamieright.getWidth() * 2, jamieright.getWidth() * 2, true);

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
            xsteps = w / NBRSTEPS;
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
                    //Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                    // Vibrate for 3000 milliseconds
                    //v.vibrate(3000);

                }
                // if the hero catches a falling object
                if (x[i] < heroCentre && x[i] + ballW > heroCentre &&
                        y[i] > screenH - ballH - heroHeight) {

                    y[i] = -random.nextInt(1000); // reset to new vertical position
                    score += 1;
                    onScoreListener.onScore(score);
                }

            }

            canvas.save(); //Save the position of the canvas.

            for (int i = 0; i < y.length; i++) {
                if (i % 2 == 0)
                    canvas.drawBitmap(falling2, x[i], y[i], null); //Draw the falling on the canvas.
                else
                    canvas.drawBitmap(falling, x[i], y[i], null); //Draw the falling on the canvas.
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
            int xCentre = (screenW / 2) - (hero.getWidth() / 2);
            int maxOffset = hero_positions.length - 1; // can't move outside right edge of screen
            int minOffset = 0; // ditto left edge of screen

            if (coordX < xCentre && offset > minOffset) { // touch event left of the centre of screen
                offset--; // move hero to the left

                if (coordX < heroXCoord)// + heroWidth / 2)
                    hero = Bitmap.createScaledBitmap(jamieleft, jamieleft.getWidth(), jamieleft.getHeight(), true);

            }
            if (coordX > xCentre && offset < maxOffset) { // touch event right of the centre of screen
                offset++; // move hero to the right

                if (coordX > heroXCoord)
                    hero = Bitmap.createScaledBitmap(jamieright, jamieright.getWidth(), jamieright.getHeight(), true);

            }
            heroXCoord = hero_positions[offset];
            return true;
        }
    }
}