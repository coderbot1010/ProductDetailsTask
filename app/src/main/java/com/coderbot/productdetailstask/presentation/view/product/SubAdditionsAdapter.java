package com.coderbot.productdetailstask.presentation.view.product;

import java.util.ArrayList;
import com.coderbot.productdetailstask.R;
import com.coderbot.productdetailstask.data.entity.Product;
import com.coderbot.productdetailstask.domain.utils.Views;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SubAdditionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private Product.Addition addition;
	private Context context;
	private SelectionListener listener;
	private ArrayList<Product.SubAddition> subAdditions = new ArrayList<>();

	SubAdditionsAdapter(Context context, SelectionListener listener)
	{
		this.context = context;
		this.listener = listener;
	}

	void setData(Product.Addition addition, ArrayList<Product.SubAddition> subAdditions)
	{
		this.addition = addition;
		this.subAdditions = new ArrayList<>();
		this.subAdditions.addAll(subAdditions);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sub_addition_item, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
	{
		ViewHolder viewHolder = (ViewHolder) holder;
		Product.SubAddition subAddition = getItem(position);

		viewHolder.subAddition.setText(subAddition.title);

		if (subAddition.selected)
			viewHolder.selection.setVisibility(View.VISIBLE);
		else
			viewHolder.selection.setVisibility(View.INVISIBLE);

		Views.ImageLoader.load(context, viewHolder.image, subAddition.img, "");
	}

	@Override
	public int getItemCount()
	{
		return subAdditions.size();
	}

	private Product.SubAddition getItem(int position)
	{
		return subAdditions.get(position);
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.selection)
		View selection;

		@BindView(R.id.image)
		ImageView image;

		@BindView(R.id.sub_addition)
		TextView subAddition;

		ViewHolder(View itemView)
		{
			super(itemView);
			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(view -> {
				Product.SubAddition option = getItem(getLayoutPosition());
				if (option.selected)
				{
					listener.onDeSelected(addition.id, option.id);
					addition.selectedSubAdditions--;
					if (addition.selectedSubAdditions == 0)
						listener.onClear(addition.id);
				}
				else
				{
					listener.onSelected(addition.id, option.id);
					addition.selectedSubAdditions++;
				}
				option.selected = !option.selected;
				notifyItemChanged(getLayoutPosition());
			});
		}
	}
}