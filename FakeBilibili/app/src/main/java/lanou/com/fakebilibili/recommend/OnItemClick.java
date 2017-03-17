package lanou.com.fakebilibili.recommend;

import android.view.View;

/**
 * Created by Parcelable on 17/3/10.
 */

public interface OnItemClick {
    void Jump(int pos);
    void popJump(int pos, View v);
}
