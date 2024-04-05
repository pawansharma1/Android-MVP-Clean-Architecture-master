package com.smarteist.FirstMile.di.modules;

import com.smarteist.FirstMile.di.scopes.ActivityScoped;
import com.smarteist.FirstMile.di.scopes.FragmentScoped;
import com.smarteist.FirstMile.util.ChromeTabsUtils.ChromeTabsWrapper;
import com.smarteist.FirstMile.views.activities.NewsActivity;
import com.smarteist.FirstMile.views.fragments.NewsContracts;
import com.smarteist.FirstMile.views.fragments.NewsPresenter;
import com.smarteist.FirstMile.views.fragments.NewsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.reactivex.disposables.CompositeDisposable;


/**
 * NewsActivityModule contains an inner abstract module that binds {@link NewsContracts.Presenter}
 * and {@link NewsFragment}
 * This is an alternative to having an abstract NewsActivityModule class with static @Provides methods
 */
@Module(includes = {NewsActivityModule.NewsAbstractModule.class})
public class NewsActivityModule {

    @ActivityScoped
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @ActivityScoped
    @Provides
    ChromeTabsWrapper providesChromeTabsWrapper(NewsActivity context) {
        return new ChromeTabsWrapper(context);
    }

    @Module
    public abstract class NewsAbstractModule {

        @ActivityScoped
        @Binds
        abstract NewsContracts.Presenter newsPresenter(NewsPresenter presenter);

        @FragmentScoped
        @ContributesAndroidInjector
        abstract NewsFragment newsFragment();

    }
}