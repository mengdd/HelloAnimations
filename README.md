# HelloAnimations
Sample project for Android Animations.

## Content
### Property Animation
[Property Animation](https://developer.android.com/guide/topics/graphics/prop-animation.html)

Property Animation相关的类:
- [ValueAnimator](https://developer.android.com/reference/android/animation/ValueAnimator.html)
- [ObjectAnimator](https://developer.android.com/reference/android/animation/ObjectAnimator.html)
- [AnimatorSet](https://developer.android.com/reference/android/animation/AnimatorSet.html)

都处在包`android.animation`中, 除了用到的Interpolater都在`android.view.animation`包中.

Property Animation的xml文件在`res/animator`文件夹中.

#### Layout Animation
ViewGroup默认的layout动画可以在xml中通过`android:animateLayoutChanges="true"`开启.
当ViewGroup中的成员发生增加/减少, 显示/隐藏, 就会出现该动画效果.

如果需要指定自定义的layout动画, 可以通过`setLayoutTransition()`给ViewGroup指定一个`LayoutTransition`.
`LayoutTransition.setAnimator(int transitionType, Animator animator)`方法可用于指定五种具体类型的动画:
- CHANGE_APPEARING
- CHANGE_DISAPPEARING
- APPEARING
- DISAPPEARING
- CHANGING

### View Animation
[View Animation](https://developer.android.com/guide/topics/graphics/view-animation.html)
View Animation相关的类都在`android.view.animation`包中.

View Animation相关的xml文件在`res/anim`文件夹中.


View Animation有一个缺点就是当View被移动到另一个位置以后, 其真实的位置仍在原来的位置.
即: 点击当前所看到的View并不会触发点击事件, 但点击它初始的位置会触发.

### Transitions
在Android 5.0 (API 21)之前, Activity的转场动画是靠`overridePendingTransition()`指定(指定anim), Fragment是`setCustomAnimations()`指定(v4指定anim, 非v4指定animator).
它们只能把整个屏幕作为整体做转换.

从Android 5.0开始, Lollipop APIs让我们可以独立地动画场景中的View元素, 并且可以在两个Activity/Fragment转换的过程中移动共享元素.

#### Shared Element Transition
注意实际进行动画的元素是第二屏的View, 所以如果shared view中的内容并不一致, 动画效果并不理想.


## Reference Demos
- [AnimationApiDemos](https://github.com/mengdd/AnimationApiDemos) (Old demo in Eclipse time).
- [Material-Animations](https://github.com/lgvalle/Material-Animations)


## References
- [官方文档Training Adding Animations](https://developer.android.com/training/animation/index.html)
- [官方文档Training Defining Custom Animations](https://developer.android.com/training/material/animations.html)
- [官方文档Guides Animation and Graphics Overview](https://developer.android.com/guide/topics/graphics/overview.html)
- [CodePath Animations](https://guides.codepath.com/android/Animations)

Transitions:
- [官方文档Training Transitions](https://developer.android.com/training/transitions/overview.html)
- [Activity & Fragment Transitions](http://www.androiddesignpatterns.com/2014/12/activity-fragment-transitions-in-android-lollipop-part1.html)

早年的博客系列文章:
[Android Animation](http://www.cnblogs.com/mengdd/category/514665.html)
