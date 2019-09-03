package com.coderbot.productdetailstask.data.api.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsResponse
{
	@SerializedName("code")
	public int code;

	@SerializedName("Status")
	public String status = "";

	@SerializedName("Product")
	public Product product;

	public class Product
	{
		@SerializedName("id")
		public int id;

		@SerializedName("title")
		public String title = "";

		@SerializedName("description")
		public String description = "";

		@SerializedName("price")
		public String price = "";

		@SerializedName("img")
		public String img = "";

		@SerializedName("cat_id")
		public String catId = "";

		@SerializedName("confirm")
		public String confirm = "";

		@SerializedName("created_by")
		public String createdBy = "";

		@SerializedName("updated_by")
		public String updatedBy = "";

		@SerializedName("created_at")
		public String createdAt = "";

		@SerializedName("updated_at")
		public String updatedAt = "";

		@SerializedName("Additions")
		public List<Addition> additions = new ArrayList<>();

		@SerializedName("tax")
		public String tax = "";
	}

	public class Addition
	{
		@SerializedName("id")
		public int id;

		@SerializedName("title")
		public String title = "";

		@SerializedName("subAdd")
		public List<SubAddition> subAddition = new ArrayList<>();
	}

	public class SubAddition
	{
		@SerializedName("id")
		public int id;

		@SerializedName("title")
		public String title = "";

		@SerializedName("subAdd")
		public int subAdd;

		@SerializedName("img")
		public String img = "";
	}
}
