package yuandong.zhifubao;

import android.content.Context;
import android.view.View;

/**
 * Created by yuandong on 2017/12/21.
 */

public class ScreenUtils {

    /**
     * 获得手机状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /***
     * 注意：在view未加载完的时候,得到的坐标为0
     * 获得view在屏幕中的位置的左（left）坐标
     * @param view
     * @return
     */
    public static int getViewLeftInScreenLocation(View view) {
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        return locations[0];
    }
    /***
     * 注意：在view未加载完的时候,得到的坐标为0
     * 获得view在屏幕中的位置的上（top）坐标
     * @param view
     * @return
     */
    public static int getViewTopInScreenLocation(View view) {
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        return locations[1];
    }
}
