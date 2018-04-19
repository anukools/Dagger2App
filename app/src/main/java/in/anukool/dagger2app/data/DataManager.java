package in.anukool.dagger2app.data;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.anukool.dagger2app.data.model.User;
import in.anukool.dagger2app.di.ApplicationContext;

/**
 * Created by Anukool Srivastav on 30/03/18.
 */
public class DataManager {
    private Context mContext;
    private SqliteHelper mDbHelper;
    private PreferenceHelper mSharedPrefsHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       SqliteHelper dbHelper,
                       PreferenceHelper sharedPrefsHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveRandomKey(String key) {
        mSharedPrefsHelper.put(PreferenceHelper.PREF_KEY_ACCESS_TOKEN, key);
    }

    public String geSavedKey() {
        return mSharedPrefsHelper.get(PreferenceHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(userId);
    }
}
