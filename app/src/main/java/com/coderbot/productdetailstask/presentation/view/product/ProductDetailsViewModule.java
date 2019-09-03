package com.coderbot.productdetailstask.presentation.view.product;

import com.coderbot.productdetailstask.domain.usecase.GetProductDetails;
import com.coderbot.productdetailstask.domain.usecase.OrderProduct;
import com.coderbot.productdetailstask.presentation.view_model.ProductDetailsViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class ProductDetailsViewModule
{
	@Provides
	ProductDetailsViewModel provideProductDetailsViewModel(GetProductDetails productUseCase, OrderProduct orderUseCase)
	{
		return new ProductDetailsViewModel(productUseCase, orderUseCase);
	}
}
