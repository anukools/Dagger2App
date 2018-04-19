package in.anukool.dagger2app;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import in.anukool.dagger2app.data.DataManager;
import in.anukool.dagger2app.di.component.ApplicationComponent;
import in.anukool.dagger2app.di.component.DaggerApplicationComponent;
import in.anukool.dagger2app.di.module.ApplicationModule;

/**
 * Created by Anukool Srivastav on 30/03/18.
 */
public class Dagger2Application extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static Dagger2Application get(Context context) {
        return (Dagger2Application) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }


    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

}
