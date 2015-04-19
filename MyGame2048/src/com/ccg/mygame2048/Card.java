package com.ccg.mygame2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 
 * @author chencangui
 * 卡片类，用于添加卡片，继承自FrameLayout
 */
public class Card extends FrameLayout {

	public Card(Context context) {
		super(context);
		//初始化
		label = new TextView(getContext());
		//设置文字大小
		label.setTextSize(32);
		//设置文字的文职
		label.setGravity(Gravity.CENTER);
		//设置文字颜色
		label.setBackgroundColor(0x33ffffff);
		//布局参数，宽高都是-1-1，充满布局容器
		LayoutParams lp = new LayoutParams(-1, -1);
		//设置与父容器之间的间距
		lp.setMargins(10, 10, 0, 0);
		//将label添加进去
		addView(label, lp);
		//初始化时卡片都是空的
		setNum(0);
	}
	//卡片里面应该有一个数字
	private int num = 0;
	//取得数字
	public int getNum() {
		return num;
	}
	//设置数字
	public void setNum(int num) {
		this.num = num;
		//如果数字是零，则显示应为空
		if (num<=0) {
			label.setText("");
		}else{
			label.setText(num+"");
		}
	}
	//判断卡片里面的文字是否相同
	public boolean equals(Card o) {
		return getNum()==o.getNum();
	}

	private TextView label;
}
