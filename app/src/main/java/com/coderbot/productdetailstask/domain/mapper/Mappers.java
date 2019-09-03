package com.coderbot.productdetailstask.domain.mapper;

import com.coderbot.productdetailstask.data.api.response.OrderProductResponse;
import com.coderbot.productdetailstask.data.api.response.ProductDetailsResponse;
import com.coderbot.productdetailstask.data.entity.Product;
import java.util.ArrayList;
import io.reactivex.functions.Function;

public class Mappers
{
	public static Function<ProductDetailsResponse, Product> productMapper = response -> {
		Product product = new Product();
		product.id = response.product.id;
		product.title = response.product.title;
		product.description = response.product.description;
		product.price = Double.parseDouble(response.product.price);
		product.img = response.product.img;
		product.catId = response.product.catId;
		product.confirm = response.product.confirm;
		product.createdBy = response.product.createdBy;
		product.updatedBy = response.product.updatedBy;
		product.createdAt = response.product.createdAt;
		product.updatedAt = response.product.updatedAt;
		product.tax = Double.parseDouble(response.product.tax);
		product.additions = new ArrayList<>();
		for (ProductDetailsResponse.Addition item : response.product.additions)
		{
			Product.Addition addition = new Product.Addition(item.id, item.title);
			addition.subAddition = new ArrayList<>();
			for (ProductDetailsResponse.SubAddition subItem : item.subAddition)
			{
				addition.subAddition.add(new Product.SubAddition(subItem.id, subItem.title, subItem.subAdd, subItem.img));
			}
			product.additions.add(addition);
		}
		return product;
	};

	public static Function<OrderProductResponse, Integer> orderMapper = response -> response.code;
}
