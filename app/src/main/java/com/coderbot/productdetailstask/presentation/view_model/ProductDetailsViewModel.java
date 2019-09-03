package com.coderbot.productdetailstask.presentation.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.coderbot.productdetailstask.data.entity.Product;
import com.coderbot.productdetailstask.domain.usecase.GetProductDetails;
import com.coderbot.productdetailstask.domain.usecase.OrderProduct;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailsViewModel extends ViewModel
{
	private Product product;

	private GetProductDetails productUseCase;

	private OrderProduct orderUseCase;

	private Set<Integer> selectedAdditions = new HashSet<>();

	private Set<Integer> selectedSubAdditions = new HashSet<>();

	private MutableLiveData<Boolean> orderObserver = new MutableLiveData<>();

	public MutableLiveData<Boolean> observeOrder()
	{
		return orderObserver;
	}

	private MutableLiveData<Product> productObserver = new MutableLiveData<>();

	public MutableLiveData<Product> observeProduct()
	{
		return productObserver;
	}

	private MutableLiveData<String> errorObserver = new MutableLiveData<>();

	public MutableLiveData<String> observeError()
	{
		return errorObserver;
	}

	@Inject
	public ProductDetailsViewModel(GetProductDetails productUseCase, OrderProduct orderUseCase)
	{
		this.productUseCase = productUseCase;
		this.orderUseCase = orderUseCase;
	}

	public void getProductDetails(int productId)
	{
		productUseCase.getProductDetails(productId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Product>()
		{
			@Override
			public void onSubscribe(Disposable d)
			{

			}

			@Override
			public void onSuccess(Product product)
			{
				ProductDetailsViewModel.this.product = product;
				productObserver.postValue(product);
			}

			@Override
			public void onError(Throwable e)
			{
				errorObserver.postValue(e.getMessage());
			}
		});
	}

	public void addAddition(int addition)
	{
		selectedAdditions.add(addition);
	}

	public void removeAddition(int addition)
	{
		selectedAdditions.remove(addition);
	}

	public void addSubAddition(int subAddition)
	{
		selectedSubAdditions.add(subAddition);
	}

	public void removeSubAddition(int subAddition)
	{
		selectedSubAdditions.remove(subAddition);
	}

	public double amount(int quantity)
	{
		return quantity * product.price;
	}

	public double totalAmount(int quantity)
	{
		double amount = amount(quantity);
		return amount + (amount / product.tax);
	}

	public void orderProduct(int bruncheId, int count)
	{
		orderUseCase.order(product.id, bruncheId, count, selectedAdditions, selectedSubAdditions).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver()
		{
			@Override
			public void onSubscribe(Disposable d)
			{

			}

			@Override
			public void onComplete()
			{
				orderObserver.postValue(true);
			}

			@Override
			public void onError(Throwable e)
			{
				orderObserver.postValue(false);
			}
		});
	}
}
