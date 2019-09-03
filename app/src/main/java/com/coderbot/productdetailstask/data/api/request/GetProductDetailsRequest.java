package com.coderbot.productdetailstask.data.api.request;

import com.google.gson.annotations.SerializedName;

public class GetProductDetailsRequest
{
	@SerializedName("id")
	private int id;

	public GetProductDetailsRequest(int id)
	{
		this.id = id;
	}
}
