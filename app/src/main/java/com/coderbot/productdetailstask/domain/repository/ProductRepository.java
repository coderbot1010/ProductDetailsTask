package com.coderbot.productdetailstask.domain.repository;

import com.coderbot.productdetailstask.data.api.response.OrderProductResponse;
import com.coderbot.productdetailstask.data.api.response.ProductDetailsResponse;
import io.reactivex.Single;

public interface ProductRepository
{
	Single<ProductDetailsResponse> getProductDetails(int productId);

	Single<OrderProductResponse> orderProduct(int userId, int productId, int bruncheId, int count, String addition, String subAddition);
}
