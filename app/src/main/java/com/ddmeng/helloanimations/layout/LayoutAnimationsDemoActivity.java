package com.ddmeng.helloanimations.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.ddmeng.helloanimations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LayoutAnimationsDemoActivity extends AppCompatActivity {

    @BindView(R.id.activity_layout_animations_demo)
    ViewGroup parentContainer;
    @BindView(R.id.appearing_checkbox)
    CheckBox appearingCB;
    @BindView(R.id.disappearing_checkbox)
    CheckBox disappearingCB;
    @BindView(R.id.changing_appearing_checkbox)
    CheckBox changingAppearingCB;
    @BindView(R.id.changing_disappearing_checkbox)
    CheckBox changingDisappearingCB;
    @BindView(R.id.custom_animation_checkbox)
    CheckBox customAnimCB;

    private int numButtons = 1;
    ViewGroup container = null;

    Animator defaultAppearingAnim, defaultDisappearingAnim;
    Animator defaultChangingAppearingAnim, defaultChangingDisappearingAnim;

    Animator customAppearingAnim, customDisappearingAnim;
    Animator customChangingAppearingAnim, customChangingDisappearingAnim;

    private LayoutTransition layoutTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animations_demo);
        ButterKnife.bind(this);
        container = new FixedGridLayout(this);
        container.setClipChildren(false);
        ((FixedGridLayout) container).setCellHeight(120);
        ((FixedGridLayout) container).setCellWidth(120);

        layoutTransition = new LayoutTransition();
        container.setLayoutTransition(layoutTransition);
        parentContainer.addView(container);
        parentContainer.setClipChildren(false);

        defaultAppearingAnim = layoutTransition
                .getAnimator(LayoutTransition.APPEARING);
        defaultDisappearingAnim = layoutTransition
                .getAnimator(LayoutTransition.DISAPPEARING);
        defaultChangingAppearingAnim = layoutTransition
                .getAnimator(LayoutTransition.CHANGE_APPEARING);
        defaultChangingDisappearingAnim = layoutTransition
                .getAnimator(LayoutTransition.CHANGE_DISAPPEARING);

        createCustomAnimations(layoutTransition);
    }

    private void createCustomAnimations(LayoutTransition transition) {
        // Changing while Adding
        // 多个属性同时动画
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);

        // 自定义的ChangingAppearing动画
        // 感觉就是从无到有地缩放了一遍
        customChangingAppearingAnim = ObjectAnimator.ofPropertyValuesHolder(new Object(), pvhLeft, pvhTop, pvhRight,
                pvhBottom, pvhScaleX, pvhScaleY)
                .setDuration(transition.getDuration(LayoutTransition.CHANGE_APPEARING));
        customChangingAppearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        // 自定义的ChangingDisappearing动画
        // 有一个平面旋转
        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        customChangingDisappearingAnim = ObjectAnimator.ofPropertyValuesHolder(new Object(), pvhLeft, pvhTop, pvhRight,
                pvhBottom, pvhRotation)
                .setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        customChangingDisappearingAnim
                .addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator anim) {
                        View view = (View) ((ObjectAnimator) anim).getTarget();
                        view.setRotation(0f);
                    }
                });

        // 自定义的Appearing动画：纵向旋转（以Y为轴翻转）出现
        // Adding
        customAppearingAnim = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f)
                .setDuration(transition.getDuration(LayoutTransition.APPEARING));
        customAppearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // 自定义的Disappearing动画：横向旋转（以X为轴翻转）消失
        // Removing
        customDisappearingAnim = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f)
                .setDuration(transition.getDuration(LayoutTransition.DISAPPEARING));
        customDisappearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });

    }

    @OnClick(R.id.add_new_button)
    void onAddNewButtonClick() {
        Button newButton = new Button(this);
        newButton.setText(String.valueOf(numButtons++));
        newButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                container.removeView(v);
            }
        });
        container.addView(newButton, Math.min(1, container.getChildCount()));
    }

    @OnCheckedChanged({R.id.custom_animation_checkbox, R.id.appearing_checkbox, R.id.disappearing_checkbox,
            R.id.changing_appearing_checkbox, R.id.changing_disappearing_checkbox})
    void onCheckBoxChanged() {
        setupTransition(layoutTransition);
    }

    private void setupTransition(LayoutTransition transition) {

        transition.setAnimator(LayoutTransition.APPEARING, appearingCB.isChecked() ?
                (customAnimCB.isChecked() ? customAppearingAnim : defaultAppearingAnim) : null);
        transition.setAnimator(LayoutTransition.DISAPPEARING, disappearingCB.isChecked() ?
                (customAnimCB.isChecked() ? customDisappearingAnim : defaultDisappearingAnim) : null);
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, changingAppearingCB.isChecked() ?
                (customAnimCB.isChecked() ? customChangingAppearingAnim : defaultChangingAppearingAnim) : null);
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changingDisappearingCB.isChecked() ?
                (customAnimCB.isChecked() ? customChangingDisappearingAnim : defaultChangingDisappearingAnim) : null);
    }
}
