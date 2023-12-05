package edu.sjsu.android.accgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    private final int BALL_SIZE = 150;
    private Bitmap field, ball;
    private float originX, originY, horizontalBound, verticalBound;
    private Particle particle;


    public MyView(Context context) {
        super(context);

        field = BitmapFactory.decodeResource(context.getResources(), R.drawable.court);
        ball = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
        ball = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, false);
        particle = new Particle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originX = w / 2f;
        originY = h / 2f;
        horizontalBound = w / 2f - BALL_SIZE / 2f ;
        verticalBound = h / 2f - BALL_SIZE / 2f;
        field = Bitmap.createScaledBitmap(field, w, h, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(field, 0, 0, null);
        canvas.drawBitmap(ball, originX + particle.mPosX - BALL_SIZE / 2f, originY - particle.mPosY - BALL_SIZE / 2f, null);

        Paint textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        textPaint.setTextSize(120);
        textPaint.setColor(Color.YELLOW);
        canvas.drawText("MDD", xPos, yPos, textPaint);

        particle.updatePosition(MainActivity.x, MainActivity.y, MainActivity.timestamp);
        particle.resolveCollisionWithBounds(horizontalBound, verticalBound);
        invalidate();
    }
}
