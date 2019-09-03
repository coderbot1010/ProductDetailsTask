package com.coderbot.productdetailstask.presentation.view.product;

public interface SelectionListener
{
	void onSelected(int addition, int subAddition);

	void onDeSelected(int addition, int subAddition);

	void onClear(int addition);
}
