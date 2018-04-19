package in.anukool.dagger2app.di.component;

import dagger.Component;
import in.anukool.dagger2app.MainActivity;
import in.anukool.dagger2app.di.PerActivity;
import in.anukool.dagger2app.di.module.ActivityModule;

/**
 * Created by Anukool Srivastav on 30/03/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
