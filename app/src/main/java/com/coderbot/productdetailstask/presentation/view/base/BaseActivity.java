package com.coderbot.productdetailstask.presentation.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.coderbot.productdetailstask.domain.utils.Views;
import dagger.android.AndroidInjection;

public abstract class BaseActivity extends AppCompatActivity
{
	public Views.LoadingView loading;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		AndroidInjection.inject(this);
		loading = new Views.LoadingView(this);
	}
}
