package com.coderbot.productdetailstask.presentation.view.product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coderbot.productdetailstask.R;
import com.coderbot.productdetailstask.data.entity.Product;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdditionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private Context context;
	private SelectionListener listener;
	private ArrayList<Product.Addition> additions = new ArrayList<>();

	AdditionsAdapter(Context context, SelectionListener listener)
	{
		this.context = context;
		this.listener = listener;
	}

	void setData(ArrayList<Product.Addition> additions)
	{
		this.additions = new ArrayList<>();
		this.additions.addAll(additions);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_addition_item, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
	{
		ViewHolder viewHolder = (ViewHolder) holder;
		Product.Addition addition = getItem(position);

		viewHolder.addition.setText(addition.title);

		viewHolder.adapter.setData(addition, new ArrayList<>(addition.subAddition));
	}

	@Override
	public int getItemCount()
	{
		return additions.size();
	}

	private Product.Addition getItem(int position)
	{
		return additions.get(position);
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.addition)
		TextView addition;

		@BindView(R.id.sub_additions)
		RecyclerView subAdditions;

		SubAdditionsAdapter adapter;

		ViewHolder(View itemView)
		{
			super(itemView);
			ButterKnife.bind(this, itemView);

			initSubList(subAdditions);
		}

		private void initSubList(RecyclerView list)
		{
			list.setHasFixedSize(true);
			list.setNestedScrollingEnabled(false);
			list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
			adapter = new SubAdditionsAdapter(context, listener);
			list.setAdapter(adapter);
		}
	}
}