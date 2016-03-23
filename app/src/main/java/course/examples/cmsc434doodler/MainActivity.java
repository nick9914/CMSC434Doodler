package course.examples.cmsc434doodler;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Doodler";

    private DoodleView mDoodleView;
    private SlidingUpPanelLayout mLayout;
    private SeekBar mOpacitySeekBar;
    private SeekBar mPBsizeSeekBar;
    private ImageButton redButton;
    private ImageButton greenButton;
    private ImageButton blueButton;
    private ImageView slidingPanelArrow;
    private ImageButton clearBtn;
    private ImageButton undoBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    slidingPanelArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
                } else {
                    slidingPanelArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_white_24dp);
                }
            }
        });
        mDoodleView = (DoodleView) findViewById(R.id.canvas);
        mOpacitySeekBar = (SeekBar) findViewById(R.id.seekBarOpacity);
        mOpacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mDoodleView.changeOpactiy(progress);

            }
        });
        mPBsizeSeekBar = (SeekBar) findViewById(R.id.seekBarBrushSize);
        mPBsizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "progress:  " + progress);
                mDoodleView.changeStrokeWidth((float) progress);

            }
        });
        redButton = (ImageButton) findViewById(R.id.red_btn);
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(Color.RED);
            }
        });
        greenButton = (ImageButton) findViewById(R.id.green_btn);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(Color.GREEN);
            }
        });
        blueButton = (ImageButton) findViewById(R.id.blue_btn);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(Color.BLUE);
            }
        });
        slidingPanelArrow = (ImageView) findViewById(R.id.sliding_panel_arrow);
        clearBtn = (ImageButton) findViewById(R.id.clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDoodleView.clear();
            }
        });
        undoBtn = (ImageButton) findViewById(R.id.undo_btn);
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDoodleView.undo();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeColor(int color) {
        redButton.setBackgroundResource(R.drawable.rectangle_red_v2_36);
        greenButton.setBackgroundResource(R.drawable.rectangle_green_36);
        blueButton.setBackgroundResource(R.drawable.rectangle_blue_36);
        switch (color) {
            case Color.RED:
                redButton.setBackgroundResource(R.drawable.button_border);
                redButton.setImageResource(R.drawable.rectangle_red_v2_36);
                break;
            case Color.GREEN:
                greenButton.setBackgroundResource(R.drawable.button_border);
                greenButton.setImageResource(R.drawable.rectangle_green_36);
                break;
            case Color.BLUE:
                blueButton.setBackgroundResource(R.drawable.button_border);
                blueButton.setImageResource(R.drawable.rectangle_blue_36);
                break;

        }
        mDoodleView.changeColor(color);
    }
}
