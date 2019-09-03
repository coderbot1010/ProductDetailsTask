package com.coderbot.productdetailstask.domain.usecase;

import com.coderbot.productdetailstask.data.entity.Product;
import com.coderbot.productdetailstask.domain.mapper.Mappers;
import com.coderbot.productdetailstask.domain.repository.ProductRepository;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Completable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@Singleton
public class OrderProduct
{
	private ProductRepository repository;

	@Inject
	public OrderProduct(ProductRepository repository)
	{
		this.repository = repository;
	}

	public Completable order(int productId, int bruncheId, int count, Set<Integer> selectedAdditions, Set<Integer> selectedSubAdditions)
	{
		return Completable.create(emitter -> repository.orderProduct(1, productId, bruncheId, count, formatPairs(selectedAdditions), formatPairs(selectedSubAdditions)).map(Mappers.orderMapper).subscribe(new SingleObserver<Integer>()
		{
			@Override
			public void onSubscribe(Disposable d)
			{

			}

			@Override
			public void onSuccess(Integer integer)
			{
				if (integer == 200)
				{
					emitter.onComplete();
				}
				else
				{
					emitter.onError(new Exception());
				}
			}

			@Override
			public void onError(Throwable e)
			{
				e.printStackTrace();
				emitter.onError(e);
			}
		}));
	}

	private String formatPairs(Set<Integer> set)
	{
		return set.toString().replace("[", "").replace("]", "").replace(" ", "");
	}
}
