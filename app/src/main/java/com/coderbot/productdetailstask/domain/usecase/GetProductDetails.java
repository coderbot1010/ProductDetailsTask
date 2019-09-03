package com.coderbot.productdetailstask.domain.usecase;

import android.util.Log;
import com.coderbot.productdetailstask.data.entity.Product;
import com.coderbot.productdetailstask.domain.mapper.Mappers;
import com.coderbot.productdetailstask.domain.repository.ProductRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@Singleton
public class GetProductDetails
{
	private ProductRepository repository;

	@Inject
	public GetProductDetails(ProductRepository repository)
	{
		this.repository = repository;
	}

	public Single<Product> getProductDetails(int productId)
	{
		return Single.create(emitter -> repository.getProductDetails(productId).map(Mappers.productMapper).subscribe(new SingleObserver<Product>()
		{
			@Override
			public void onSubscribe(Disposable d)
			{

			}

			@Override
			public void onSuccess(Product product)
			{
				Log.i("Product", product.description);
				emitter.onSuccess(product);
			}

			@Override
			public void onError(Throwable e)
			{
				e.printStackTrace();
				emitter.onError(e);
			}
		}));
	}
}
