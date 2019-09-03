package com.coderbot.productdetailstask.dependency_injection;

import com.coderbot.productdetailstask.presentation.view.product.ProductDetailsActivity;
import com.coderbot.productdetailstask.presentation.view.product.ProductDetailsViewModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class InjectorFactory
{
	@ContributesAndroidInjector(modules = { ProductDetailsViewModule.class })
	abstract ProductDetailsActivity injectProductDetailsActivity();
}
