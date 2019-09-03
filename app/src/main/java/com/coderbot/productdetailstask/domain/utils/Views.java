package com.coderbot.productdetailstask.domain.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.coderbot.productdetailstask.presentation.view.loading.LoadingDialog;

public class Views
{
	public static class LoadingView
	{
		private LoadingDialog loading;

		public LoadingView(Context context)
		{
			loading = new LoadingDialog(context);
		}

		public void show()
		{
			if (loading != null && !loading.isShowing())
			{
				loading.show();
			}
		}

		public void dismiss()
		{
			if (loading != null && loading.isShowing())
			{
				loading.dismiss();
			}
		}
	}

	public static class ImageLoader
	{
		public static void load(Context context, ImageView imageView, String imageURl, String auth)
		{
			glideLoader(context, imageView, new GlideUrl(imageURl, new LazyHeaders.Builder().addHeader("Authorization", auth).build()));
		}

		public static void glideLoader(Context context, ImageView imageView, String imagePath)
		{
			Glide.with(context).load(imagePath).thumbnail(0.1f).into(imageView);
		}

		private static void glideLoader(Context context, ImageView imageView, GlideUrl uri)
		{
			Glide.with(context).load(uri).thumbnail(0.1f).into(imageView);
		}

		public static void load(Context context, String imageURl, String auth, ImageView imageView, Drawable placeHolder)
		{
			Glide.with(context).load(new GlideUrl(imageURl, new LazyHeaders.Builder().addHeader("Authorization", auth).build())).thumbnail(0.1f).apply(new RequestOptions().placeholder(placeHolder)).into(imageView);
		}

		public static void load(Context context, String imagePath, ImageView imageView, Drawable placeHolder)
		{
			Glide.with(context).load(imagePath).thumbnail(0.1f).apply(new RequestOptions().placeholder(placeHolder)).into(imageView);
		}
	}
}
