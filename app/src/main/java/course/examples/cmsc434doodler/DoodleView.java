package course.examples.cmsc434doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Nicolas on 3/9/2016.
 */
public class DoodleView extends View {

    private Paint mPaintDoodle = new Paint();
    /*Array of points*/
    private Path mPath = new Path();
    private List<Path> mPaths;
    private List<Paint> mPaintObjects;
    private Float mStrokeWidth;
    private int mOpacity;
    private int mColor;


    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet atts, int defStyle) {
        mOpacity = 255;
        mStrokeWidth = 1.0F;
        mColor = Color.RED;
        setPaintProperties();
        mPaths = new ArrayList<>();
        mPaintObjects = new ArrayList<>();

    }

    private void setPaintProperties() {
        mPaintDoodle.setColor(mColor);
        mPaintDoodle.setAntiAlias(true);
        mPaintDoodle.setStyle(Paint.Style.STROKE);
        mPaintDoodle.setStrokeWidth(mStrokeWidth);
        mPaintDoodle.setAlpha(mOpacity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaintDoodle);
        for(int i = 0; i < mPaths.size(); i++) {
            canvas.drawPath(mPaths.get(i), mPaintObjects.get(i));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                mPaths.add(mPath);
                mPaintObjects.add(mPaintDoodle);
                mPath = new Path();
                mPaintDoodle = new Paint();
                setPaintProperties();
                break;
        }
        invalidate();
        return true;
    }

    public void changeStrokeWidth(Float width) {
        mStrokeWidth = width;
        setPaintProperties();
    }

    public void changeOpactiy(int opacity) {
        mOpacity = Math.abs(255 - opacity);
        setPaintProperties();
    }

    public void changeColor(int color) {
        mColor = color;
        setPaintProperties();
    }
}
