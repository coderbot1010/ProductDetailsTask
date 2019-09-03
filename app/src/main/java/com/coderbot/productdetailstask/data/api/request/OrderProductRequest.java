package com.coderbot.productdetailstask.data.api.request;

import com.google.gson.annotations.SerializedName;

public class OrderProductRequest
{
	@SerializedName("user_id")
	private int userId;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("brunche_id")
	private int bruncheId;

	@SerializedName("count")
	private int count;

	@SerializedName("addition")
	private String addition;

	@SerializedName("subadd")
	private String subAddition;

	public OrderProductRequest(int userId, int productId, int bruncheId, int count, String addition, String subAddition)
	{
		this.userId = userId;
		this.productId = productId;
		this.bruncheId = bruncheId;
		this.count = count;
		this.addition = addition;
		this.subAddition = subAddition;
	}
}
