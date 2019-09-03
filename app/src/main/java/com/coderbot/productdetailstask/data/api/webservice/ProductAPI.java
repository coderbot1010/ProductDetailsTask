package com.coderbot.productdetailstask.data.api.webservice;

import com.coderbot.productdetailstask.data.api.request.GetProductDetailsRequest;
import com.coderbot.productdetailstask.data.api.request.OrderProductRequest;
import com.coderbot.productdetailstask.data.api.response.OrderProductResponse;
import com.coderbot.productdetailstask.data.api.response.ProductDetailsResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProductAPI
{
	@POST("Product")
	Single<ProductDetailsResponse> productDetails(@Body GetProductDetailsRequest request);

	@POST("store_Order")
	Single<OrderProductResponse> orderProduct(@Body OrderProductRequest request);
}
