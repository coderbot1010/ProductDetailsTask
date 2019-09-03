package com.coderbot.productdetailstask.data.repository;

import com.coderbot.productdetailstask.data.api.request.GetProductDetailsRequest;
import com.coderbot.productdetailstask.data.api.request.OrderProductRequest;
import com.coderbot.productdetailstask.data.api.response.OrderProductResponse;
import com.coderbot.productdetailstask.data.api.response.ProductDetailsResponse;
import com.coderbot.productdetailstask.data.api.webservice.ProductAPI;
import com.coderbot.productdetailstask.domain.repository.ProductRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@Singleton
public class ProductRepositoryImplementation implements ProductRepository
{
	private ProductAPI api;

	@Inject
	public ProductRepositoryImplementation(Retrofit retrofit)
	{
		api = retrofit.create(ProductAPI.class);
	}

	@Override
	public Single<ProductDetailsResponse> getProductDetails(int productId)
	{
		return api.productDetails(new GetProductDetailsRequest(productId)).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
	}

	@Override
	public Single<OrderProductResponse> orderProduct(int userId, int productId, int bruncheId, int count, String addition, String subAddition)
	{
		return api.orderProduct(new OrderProductRequest(userId, productId, bruncheId, count, addition, subAddition)).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
	}
}
