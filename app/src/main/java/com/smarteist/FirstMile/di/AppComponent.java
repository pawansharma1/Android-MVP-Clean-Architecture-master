package com.smarteist.FirstMile.di;



import com.smarteist.FirstMile.App;
import com.smarteist.FirstMile.data.source.remote.NewsService;
import com.smarteist.FirstMile.di.modules.AppModule;
import com.smarteist.FirstMile.di.modules.ActivityBindingModule;
import com.smarteist.FirstMile.di.modules.NewsRepositoryModule;
import com.smarteist.FirstMile.di.modules.UtilityModule;
import com.smarteist.FirstMile.di.scopes.ApplicationScoped;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * This is the root Dagger component.
 * {@link AndroidSupportInjectionModule}
 * is the module from Dagger.Android that helps with the generation
 * and location of subcomponents, which will be in our case, activities
 */
@ApplicationScoped
@Component(modules = {
        AndroidSupportInjectionModule.class, //!IMPORTANT
        AppModule.class,
        NewsRepositoryModule.class,
        UtilityModule.class,
        ActivityBindingModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    NewsService getNewsService();

    // we can now do DaggerAppComponent.builder().application(this).build().inject(this),
    // never having to instantiate any modules or say which module we are passing the application to.
    // App will just be provided into our app graph

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(android.app.Application application);

        AppComponent build();
    }
}