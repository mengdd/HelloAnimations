package com.ddmeng.helloanimations.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.ddmeng.helloanimations.ShapeHolder;

import java.util.ArrayList;

public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {
    public static final int ANIMATION_DURATION = 500;
    private float density;
    private final ArrayList<ShapeHolder> balls = new ArrayList<>();
    private AnimatorSet animatorSet;

    public MyAnimationView(Context context) {
        super(context);
        init();
    }

    public MyAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        density = getContext().getResources().getDisplayMetrics().density;
        addBall(50f, 25f);
        addBall(200f, 25f);
        addBall(350f, 25f);
        addBall(500f, 25f);
        addBall(650f, 25f);
    }

    public void play() {
        if (animatorSet == null) {
            createAnimation();
        }
        animatorSet.start();
    }

    private void createAnimation() {
        // Animation 1: Use ValueAnimator
        final ShapeHolder ball1 = balls.get(0);
        ValueAnimator animator1 = ValueAnimator.ofFloat(0f, getHeight() - ball1.getHeight());
        animator1.setDuration(ANIMATION_DURATION);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ball1.setY((Float) animation.getAnimatedValue());
                invalidate();
            }
        });

        // Animation 2: Use ObjectAnimator
        ShapeHolder ball2 = balls.get(1);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(ball2, "y", 0f, getHeight() - ball2.getHeight());
        animator2.setDuration(ANIMATION_DURATION);
        animator2.addUpdateListener(this);


        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < balls.size(); ++i) {
            ShapeHolder shapeHolder = balls.get(i);
            canvas.save();
            canvas.translate(shapeHolder.getX(), shapeHolder.getY());
            shapeHolder.getShape().draw(canvas);
            canvas.restore();
        }
    }


    private ShapeHolder addBall(float x, float y) {
        OvalShape circle = new OvalShape();
        circle.resize(50f * density, 50f * density);
        ShapeDrawable drawable = new ShapeDrawable(circle);
        ShapeHolder shapeHolder = new ShapeHolder(drawable);
        shapeHolder.setX(x - 25f);
        shapeHolder.setY(y - 25f);
        int red = (int) (100 + Math.random() * 155);
        int green = (int) (100 + Math.random() * 155);
        int blue = (int) (100 + Math.random() * 155);
        int color = 0xff000000 | red << 16 | green << 8 | blue;
        Paint paint = drawable.getPaint(); // new
        // Paint(Paint.ANTI_ALIAS_FLAG);
        int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue
                / 4;
        RadialGradient gradient = new RadialGradient(37.5f, 12.5f, 50f,
                color, darkColor, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        shapeHolder.setPaint(paint);
        balls.add(shapeHolder);
        return shapeHolder;
    }


    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        invalidate();
    }
}
