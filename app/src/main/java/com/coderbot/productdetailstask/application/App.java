package com.coderbot.productdetailstask.application;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import com.coderbot.productdetailstask.dependency_injection.DaggerInjectorComponent;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;

public class App extends Application implements HasActivityInjector, HasFragmentInjector
{
	@Inject
	DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

	@Inject
	DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

	@Override
	public DispatchingAndroidInjector<Activity> activityInjector()
	{
		return activityDispatchingAndroidInjector;
	}

	@Override
	public AndroidInjector<Fragment> fragmentInjector()
	{
		return fragmentDispatchingAndroidInjector;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();

		DaggerInjectorComponent.builder().application(this).build().inject(this);
	}
}
