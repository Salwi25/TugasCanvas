package com.example.tugascanvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private ImageView mImageView;

    private static final int OFFSET = 120;
    private int mOffset = OFFSET;

    private int mColorVinyl;
    private int mColorCenter;
    private int mColorHole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorVinyl = ResourcesCompat.getColor(getResources(),
                R.color.black, null);
        mColorCenter = ResourcesCompat.getColor(getResources(),
                R.color.black, null);
        mColorHole = ResourcesCompat.getColor(getResources(),
                R.color.white, null);
        mImageView = findViewById(R.id.my_image_view);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawVinyl(view);
            }
        });
    }


    public void drawVinyl(View view){
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mPaint.setColor(mColorVinyl);
            mCanvas.drawCircle(halfWidth, halfHeight, halfHeight/3, mPaint);
            mOffset += OFFSET;
        } else {
            if (mOffset < halfWidth && mOffset < halfHeight){
                mPaint.setColor(mColorHole);
                mCanvas.drawCircle(halfWidth, halfHeight,
                        halfHeight/8, mPaint);
                mOffset += OFFSET;
            } else {
                mPaint.setColor(mColorCenter);
                mCanvas.drawCircle(halfWidth, halfHeight,
                        halfHeight/38, mPaint);
                mOffset += OFFSET;
            }
        }
        view.invalidate();
    }
}