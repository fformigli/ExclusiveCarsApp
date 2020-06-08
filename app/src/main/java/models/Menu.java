package models;

import android.app.Activity;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Menu {
    @StringRes
    public int title;
    @DrawableRes
    public final int icon;
    public final Class<? extends Activity> activityClass;

    public Menu(@StringRes int title, @DrawableRes int icon, Class<? extends Activity> activityClass) {
        super();
        this.title = title;
        this.icon = icon;
        this.activityClass = activityClass;
    }
}
