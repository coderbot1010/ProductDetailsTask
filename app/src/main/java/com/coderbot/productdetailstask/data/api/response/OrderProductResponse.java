package com.coderbot.productdetailstask.data.api.response;

import com.google.gson.annotations.SerializedName;

public class OrderProductResponse
{
	@SerializedName("code")
	public Integer code;

	@SerializedName("Status")
	public String status;

	@SerializedName("data")
	public Data data;

	public class Data
	{
		@SerializedName("user_id")
		public String userId;

		@SerializedName("product_id")
		public String productId;

		@SerializedName("brunche_id")
		public String bruncheId;

		@SerializedName("addition")
		public String addition;

		@SerializedName("subadd")
		public String subadd;

		@SerializedName("count")
		public String count;

		@SerializedName("totlePrice")
		public Object totlePrice;

		@SerializedName("status")
		public Integer status;

		@SerializedName("created_at")
		public String createdAt;

		@SerializedName("updated_at")
		public String updatedAt;

		@SerializedName("id")
		public Integer id;
	}
}
