package com.coderbot.productdetailstask.data.entity;

import java.util.ArrayList;
import java.util.List;

public class Product
{
	public int id;
	public String title = "";
	public String description = "";
	public double price;
	public String img = "";
	public String catId = "";
	public String confirm = "";
	public String createdBy = "";
	public String updatedBy = "";
	public String createdAt = "";
	public String updatedAt = "";
	public List<Addition> additions = new ArrayList<>();
	public double tax;

	public static class Addition
	{
		public int id;
		public String title;
		public int selectedSubAdditions = 0;
		public List<SubAddition> subAddition = new ArrayList<>();

		public Addition(int id, String title)
		{
			this.id = id;
			this.title = title;
		}
	}

	public static class SubAddition
	{
		public int id;
		public String title;
		public int subAdd;
		public String img;
		public boolean selected = false;

		public SubAddition(int id, String title, int subAdd, String img)
		{
			this.id = id;
			this.title = title;
			this.subAdd = subAdd;
			this.img = img;
		}
	}
}
