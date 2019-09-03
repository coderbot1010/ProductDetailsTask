package com.coderbot.productdetailstask.presentation.view.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.coderbot.productdetailstask.R;
import com.coderbot.productdetailstask.data.entity.Product;
import com.coderbot.productdetailstask.domain.utils.Views;
import com.coderbot.productdetailstask.presentation.view.base.BaseActivity;
import com.coderbot.productdetailstask.presentation.view_model.ProductDetailsViewModel;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseActivity
{
	@Inject
	ProductDetailsViewModel viewModel;

	@BindView(R.id.image)
	ImageView image;

	@BindView(R.id.title)
	TextView title;

	@BindView(R.id.price)
	TextView price;

	@BindView(R.id.description)
	TextView description;

	@BindView(R.id.quantity)
	TextView quantity;

	@BindView(R.id.additions)
	RecyclerView additions;

	@BindView(R.id.amount)
	TextView amount;

	@BindView(R.id.tax)
	TextView tax;

	@BindView(R.id.total)
	TextView total;

	private AdditionsAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
		ButterKnife.bind(this);

		quantity.setText("1");

		initList();

		initListeners();

		getProductDetails();
	}

	private void initList()
	{
		additions.setHasFixedSize(true);
		additions.setNestedScrollingEnabled(false);
		additions.setLayoutManager(new LinearLayoutManager(this));
		adapter = new AdditionsAdapter(this, new SelectionListener()
		{
			@Override
			public void onSelected(int addition, int subAddition)
			{
				viewModel.addAddition(addition);
				viewModel.addSubAddition(subAddition);
			}

			@Override
			public void onDeSelected(int addition, int subAddition)
			{
				viewModel.removeSubAddition(subAddition);
			}

			@Override
			public void onClear(int addition)
			{
				viewModel.removeAddition(addition);
			}
		});
		additions.setAdapter(adapter);
	}

	private void initListeners()
	{
		viewModel.observeProduct().observe(this, product -> {
			loading.dismiss();
			if (product != null)
			{
				setProductDetails(product);
			}
			else
			{
				Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
				finish();
			}
		});

		viewModel.observeOrder().observe(this, status -> {
			loading.dismiss();
			if (status != null && status)
			{
				Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
				finish();
			}
			else
			{
				Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
			}
		});

		viewModel.observeError().observe(this, error -> {
			loading.dismiss();
			if (error != null)
			{
				Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

	private void getProductDetails()
	{
		loading.show();
		viewModel.getProductDetails(1);
	}

	private void setProductDetails(Product product)
	{
		Views.ImageLoader.load(this, image, product.img, "");
		title.setText(product.title);
		price.setText(getString(R.string.amount_value, String.valueOf(product.price)));
		description.setText(String.valueOf(product.description));
		tax.setText(getString(R.string.tax_value, String.valueOf(product.tax)));
		adapter.setData(new ArrayList<>(product.additions));
		calculateMoney();
	}

	private void calculateMoney()
	{
		amount.setText(getString(R.string.amount_value, String.valueOf(viewModel.amount(Integer.parseInt(quantity.getText().toString())))));
		total.setText(getString(R.string.amount_value, String.valueOf(viewModel.totalAmount(Integer.parseInt(quantity.getText().toString())))));
	}

	@OnClick(R.id.add)
	void add()
	{
		int qty = Integer.parseInt(quantity.getText().toString()) + 1;
		quantity.setText(String.valueOf(qty));
		calculateMoney();
	}

	@OnClick(R.id.remove)
	void remove()
	{
		int qty = Integer.parseInt(quantity.getText().toString());
		if (qty > 1)
		{
			quantity.setText(String.valueOf(--qty));
			calculateMoney();
		}
	}

	@OnClick(R.id.order)
	void order()
	{
		loading.show();
		viewModel.orderProduct(1, Integer.parseInt(quantity.getText().toString()));
	}
}
