package com.ccg.mygame2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * 
 * @author chencangui 
 * 游戏界面类，继承GridLayout GameView 类，继承自GridLayout
 */
public class GameView extends GridLayout {

	/**
	 * 三个构造方法
	 */
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		gameView = this;
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		gameView = this;
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gameView = this;
		initGameView();
	}

	
	private void initGameView() {
		// 设置这个界面有多少列
		setColumnCount(4);

		// 设置界面的背景颜色
		setBackgroundColor(0xffbbada0);

		// 设置触摸监听事件
		setOnTouchListener(new View.OnTouchListener() {

			/*
			 * startX: 起始的位置的X坐标 startY: 起始位置的Y坐标 offsetX: X坐标偏移量 offsetY:
			 * Y坐标偏移量
			 */
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;

				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;

					// 看一下是X的偏移量大还是Y的偏移量大，
					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						// 设置为5是考虑到可能存在误差
						if (offsetX < -5) {
							swipeLeft();
						} else if (offsetX > 5) {
							swipeRight();
						}
					} else {
						if (offsetY < -5) {
							swipeUp();
						} else if (offsetY > 5) {
							swipeDown();
						}
					}

					break;
				}
				return true;
			}
		});
	}

	// 获取手机的宽高，动态地设置卡片的大小，算是屏幕适配
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// 卡片的宽度
		int cardWidth = (Math.min(w, h) - 10) / 4;

		// 添加卡片
		addCards(cardWidth, cardWidth);

		startGame();
	}

	// 添加卡片方法
	private void addCards(int cardWidth, int cardHeight) {

		Card c;

		for (int y = 0; y < 4; y++) {

			for (int x = 0; x < 4; x++) {

				c = new Card(getContext());

				c.setNum(0);

				// 将卡片添加听来
				addView(c, cardWidth, cardHeight);

				cardsMap[x][y] = c;
			}
		}
	}

	// 开始游戏方法
	public void startGame() {

		// 将分数清零
		MainActivity.getMainActivity().clearScore();

		// 初始化，cardsMap的值首先都是零
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardsMap[x][y].setNum(0);
			}
		}

		addRandomNum();
		addRandomNum();
	}

	// 添加随机数方法
	private void addRandomNum() {

		//
		emptyPoints.clear();
		// 对所有的卡片的值进行遍历，将空的存进来
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}

		// 随机移除一个点
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size()));

		// 设置这个点的数字，2出现的概率是0.9,4的是0.1
		cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	// 左移方法实现,游戏实现逻辑
	private void swipeLeft() {

		boolean merge = false;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				// 从右边遍历

				for (int x1 = x + 1; x1 < 4; x1++) {
					// 如果x1不空
					if (cardsMap[x1][y].getNum() > 0) {
						// 如果当前是空的
						if (cardsMap[x][y].getNum() <= 0) {
							// 将当前的数字设为右边的数字
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							// 右边的数字设为0
							cardsMap[x1][y].setNum(0);
							// 重新遍历一次
							x--;
							// 交换了
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							// 如果数字相等的话那么久相加，存回左边，然后右边的设为零
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							// 更新分数
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}
						// 执行完毕则跳出循环
						break;
					}
				}
			}
		}
		if (merge) {
			// 如果交换了的话，加多一个进来。

			addRandomNum();
			// 检查是否游戏已经结束了
			checkComplete();
		}
	}

	// 右移方法实现
	private void swipeRight() {

		boolean merge = false;

		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {

				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardsMap[x1][y].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							x++;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}

						break;
					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	// 上滑方法实现
	private void swipeUp() {

		boolean merge = false;

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {

				for (int y1 = y + 1; y1 < 4; y1++) {
					if (cardsMap[x][y1].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							y--;

							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}

						break;

					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	// 下滑方法实现
	private void swipeDown() {

		boolean merge = false;

		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >= 0; y--) {

				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cardsMap[x][y1].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							y++;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}

						break;
					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	// 监听游戏是否已经结束
	private void checkComplete() {

		boolean complete = true;

		// 添加一个All标签，如果符合任意一种就跳出循环
		ALL: for (int y = 0; y < 4; y++) {
			/*
			 * 只要下面任意一个条件成立就跳出循环： 有空余位置 四个方向上的位置有相同的数字
			 */
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum() == 0
						|| (x > 0 && cardsMap[x][y].equals(cardsMap[x - 1][y]))
						|| (x < 3 && cardsMap[x][y].equals(cardsMap[x + 1][y]))
						|| (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1]))
						|| (y < 3 && cardsMap[x][y].equals(cardsMap[x][y + 1]))) {

					complete = false;
					break ALL; // 跳出整个循环
				}
			}
		}

		// 如果已经结束的话跳出一个提示框
		if (complete) {
			new AlertDialog.Builder(getContext())
					.setTitle("你好")
					.setMessage("游戏结束")
					.setPositiveButton("重来",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									startGame();
								}
							}).show();
		}

	}

	
	private static GameView gameView = null;
	public static GameView getGameView(){
		
		return gameView;
	}  
	
	// 初始化一个4x4的卡片变量，用于记录每次添加的卡片
	private Card[][] cardsMap = new Card[4][4];
	// 存储空点的对象
	private List<Point> emptyPoints = new ArrayList<Point>();
}
