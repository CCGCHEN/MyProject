package com.ccg.mygame2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 
 * @author chencangui
 * ��Ƭ�࣬������ӿ�Ƭ���̳���FrameLayout
 */
public class Card extends FrameLayout {

	public Card(Context context) {
		super(context);
		//��ʼ��
		label = new TextView(getContext());
		//�������ִ�С
		label.setTextSize(32);
		//�������ֵ���ְ
		label.setGravity(Gravity.CENTER);
		//����������ɫ
		label.setBackgroundColor(0x33ffffff);
		//���ֲ�������߶���-1-1��������������
		LayoutParams lp = new LayoutParams(-1, -1);
		//�����븸����֮��ļ��
		lp.setMargins(10, 10, 0, 0);
		//��label��ӽ�ȥ
		addView(label, lp);
		//��ʼ��ʱ��Ƭ���ǿյ�
		setNum(0);
	}
	//��Ƭ����Ӧ����һ������
	private int num = 0;
	//ȡ������
	public int getNum() {
		return num;
	}
	//��������
	public void setNum(int num) {
		this.num = num;
		//����������㣬����ʾӦΪ��
		if (num<=0) {
			label.setText("");
		}else{
			label.setText(num+"");
		}
	}
	//�жϿ�Ƭ����������Ƿ���ͬ
	public boolean equals(Card o) {
		return getNum()==o.getNum();
	}

	private TextView label;
}
