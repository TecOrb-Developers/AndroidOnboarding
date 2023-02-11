package com.vkpal.onboardlibrary;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public abstract class OnboardingActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private CircleIndicatorView circleIndicatorView;
    private ViewPager viewPagerOnboarding;
    private OnboardingAdapter onboardingAdapter;
    private TextView btnSkip;
    private ImageView ivNext, ivPrev;
    private FrameLayout navigationControls;
    private ImageView backgroundImage;

    private ShadowTransformer mCardShadowTransformer;
    private Typeface typeface;
    private List<Integer> colorList;
    private boolean solidBackground = false;
    private List<OnboardingCard> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        setStatusBackgroundColor();
        hideActionBar();

        circleIndicatorView = findViewById(R.id.circle_indicator_view);
        btnSkip = findViewById(R.id.btn_skip);
        navigationControls = findViewById(R.id.navigation_layout);
        ivNext = findViewById(R.id.ivNext);
        ivPrev = findViewById(R.id.ivPrev);
        backgroundImage = findViewById(R.id.background_image);
        viewPagerOnboarding = findViewById(R.id.vp_pager);
        viewPagerOnboarding.addOnPageChangeListener(this);
        btnSkip.setOnClickListener(this);
        ivPrev.setOnClickListener(this);
        ivNext.setOnClickListener(this);

        hideFinish(false);
        fadeOut(ivPrev, false);
    }

    public void setOnboardPages(List<OnboardingCard> pages) {
        this.pages = pages;
        onboardingAdapter = new OnboardingAdapter(pages, getSupportFragmentManager(), dpToPixels(0, this), typeface);
        mCardShadowTransformer = new ShadowTransformer(viewPagerOnboarding, onboardingAdapter);
        mCardShadowTransformer.enableScaling(true);
        viewPagerOnboarding.setAdapter(onboardingAdapter);
        viewPagerOnboarding.setPageTransformer(false, mCardShadowTransformer);
        circleIndicatorView.setPageIndicators(pages.size());
    }

    public float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    private void setStatusBackgroundColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_transparent));
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        boolean isInFirstPage = viewPagerOnboarding.getCurrentItem() == 0;
        boolean isInLastPage = viewPagerOnboarding.getCurrentItem() == onboardingAdapter.getCount() - 1;

        if ((i == R.id.btn_skip && isInLastPage) /*|| i == R.id.fla_skip*/) {
            onFinishButtonPressed();
        } else if (i == R.id.ivPrev && !isInFirstPage) {
            viewPagerOnboarding.setCurrentItem(viewPagerOnboarding.getCurrentItem() - 1);
        } else if (i == R.id.ivNext && !isInLastPage) {
            viewPagerOnboarding.setCurrentItem(viewPagerOnboarding.getCurrentItem() + 1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        int firstPagePosition = 0;
        int lastPagePosition = onboardingAdapter.getCount() - 1;
        circleIndicatorView.setCurrentPage(position);
        circleIndicatorView.setCurrentPage(position);

        if (position == lastPagePosition) {
            fadeOut(circleIndicatorView);
            showFinish();
            fadeOut(ivNext);
            fadeIn(ivPrev);
        } else if (position == firstPagePosition) {
            fadeOut(ivPrev);
            fadeIn(ivNext);
            hideFinish();
            fadeIn(circleIndicatorView);
        } else {
            fadeIn(circleIndicatorView);
            hideFinish();
            fadeIn(ivPrev);
            fadeIn(ivNext);
        }

        if (solidBackground && (pages.size() == colorList.size())) {
            backgroundImage.setBackgroundColor(ContextCompat.getColor(this, colorList.get(position)));
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void fadeOut(View v) {
        fadeOut(v, true);
    }

    private void fadeOut(final View v, boolean delay) {

        long duration = 0;
        if (delay) {
            duration = 300;
        }

        if (v.getVisibility() != View.GONE) {
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
            fadeOut.setDuration(duration);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(fadeOut);
        }
    }

    private void fadeIn(final View v) {

        if (v.getVisibility() != View.VISIBLE) {
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
            fadeIn.setDuration(300);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    v.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(fadeIn);
        }
    }

    private void showFinish() {
        btnSkip.setVisibility(View.VISIBLE);
        this.btnSkip.animate().translationY(0 - dpToPixels(5, this)).setInterpolator(new DecelerateInterpolator()).setDuration(500).start();
    }

    private void hideFinish(boolean delay) {

        long duration = 0;
        if (delay) {
            duration = 250;
        }

        this.btnSkip.animate().translationY(this.btnSkip.getBottom() + dpToPixels(100, this)).setInterpolator(new AccelerateInterpolator()).setDuration(duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                btnSkip.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();
    }

    private void hideFinish() {
        hideFinish(true);
    }

    private void hideActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    abstract public void onFinishButtonPressed();

    public void showNavigationControls(boolean navigation) {
        if (navigation) {
            navigationControls.setVisibility(View.VISIBLE);
        } else {
            navigationControls.setVisibility(View.GONE);
        }
    }

    public void setImageBackground(int resId) {
        backgroundImage.setImageResource(resId);
    }

    public void setColorBackground(@ColorRes int color) {
        backgroundImage.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    public void setColorBackground(List<Integer> color) {
        this.colorList = color;
        solidBackground = true;
        backgroundImage.setBackgroundColor(ContextCompat.getColor(this, color.get(0)));
    }


    public void setInactiveIndicatorColor(int color) {
        this.circleIndicatorView.setInactiveIndicatorColor(color);
    }

    public void setActiveIndicatorColor(int color) {
        this.circleIndicatorView.setActiveIndicatorColor(color);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setFinishButtonDrawableStyle(Drawable res) {
        btnSkip.setBackground(res);
    }

    public void setFinishButtonTitle(CharSequence title) {
        btnSkip.setText(title);
    }

    public void setFinishButtonTitle(@StringRes int titleResId) {
        btnSkip.setText(titleResId);
    }

    public void setFont(Typeface typeface) {
        this.btnSkip.setTypeface(typeface);
        this.typeface = typeface;
    }

}
