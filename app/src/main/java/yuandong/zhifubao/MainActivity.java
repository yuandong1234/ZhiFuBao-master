package yuandong.zhifubao;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AppBarLayout abl_bar;
    private View tl_expand, tl_collapse;
    private View v_expand_mask, v_collapse_mask, v_pay_mask;

    private RelativeLayout toolBarExpandMenu;
    private int statusBarHeight;
    private boolean isFirstInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate()..");
        statusBarHeight = ScreenUtils.getStatusBarHeight(this);
        isFirstInit = true;
        Log.e(TAG, "状态栏的高度： " + statusBarHeight);
        initView();
    }

    private void initView() {
        toolBarExpandMenu = (RelativeLayout) findViewById(R.id.rl_toolbar_menu);
        abl_bar = (AppBarLayout) findViewById(R.id.abl_bar);
        tl_expand = (View) findViewById(R.id.tl_expand);
        tl_collapse = (View) findViewById(R.id.tl_collapse);
        v_expand_mask = (View) findViewById(R.id.v_expand_mask);
        v_collapse_mask = (View) findViewById(R.id.v_collapse_mask);
        v_pay_mask = (View) findViewById(R.id.v_pay_mask);
        abl_bar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isFirstInit) {
            isFirstInit = false;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) toolBarExpandMenu.getLayoutParams();
            int top = ScreenUtils.getViewTopInScreenLocation(toolBarExpandMenu);
            Log.e(TAG, "view 的高度： " + top);
            layoutParams.setMargins(0, top + statusBarHeight, 0, 0);
            toolBarExpandMenu.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d(TAG, "verticalOffset=" + verticalOffset);
        int total = appBarLayout.getTotalScrollRange();
        Log.e(TAG, "total=" + total);
        int offset = Math.abs(verticalOffset);
        float alpha = 1 - Math.abs(total / 2 - offset) / (total * 0.5f);
        Log.e(TAG, "alpha=" + alpha);
        if (offset <= total / 2) {
            tl_expand.setVisibility(View.VISIBLE);
            tl_collapse.setVisibility(View.GONE);
            v_expand_mask.setAlpha(alpha);
        } else {
            tl_expand.setVisibility(View.GONE);
            tl_collapse.setVisibility(View.VISIBLE);
            v_collapse_mask.setAlpha(alpha);
        }
         v_pay_mask.setAlpha(alpha);
    }
}
