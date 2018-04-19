package in.anukool.dagger2app.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import in.anukool.dagger2app.Dagger2Application;
import in.anukool.dagger2app.data.DataManager;
import in.anukool.dagger2app.data.PreferenceHelper;
import in.anukool.dagger2app.data.SqliteHelper;
import in.anukool.dagger2app.di.ApplicationContext;
import in.anukool.dagger2app.di.module.ApplicationModule;

/**
 * Created by Anukool Srivastav on 30/03/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Dagger2Application demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    PreferenceHelper getPreferenceHelper();

    SqliteHelper getDbHelper();

}