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
 * ��Ϸ�����࣬�̳�GridLayout GameView �࣬�̳���GridLayout
 */
public class GameView extends GridLayout {

	/**
	 * �������췽��
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
		// ������������ж�����
		setColumnCount(4);

		// ���ý���ı�����ɫ
		setBackgroundColor(0xffbbada0);

		// ���ô��������¼�
		setOnTouchListener(new View.OnTouchListener() {

			/*
			 * startX: ��ʼ��λ�õ�X���� startY: ��ʼλ�õ�Y���� offsetX: X����ƫ���� offsetY:
			 * Y����ƫ����
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

					// ��һ����X��ƫ��������Y��ƫ������
					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						// ����Ϊ5�ǿ��ǵ����ܴ������
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

	// ��ȡ�ֻ��Ŀ�ߣ���̬�����ÿ�Ƭ�Ĵ�С��������Ļ����
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// ��Ƭ�Ŀ��
		int cardWidth = (Math.min(w, h) - 10) / 4;

		// ��ӿ�Ƭ
		addCards(cardWidth, cardWidth);

		startGame();
	}

	// ��ӿ�Ƭ����
	private void addCards(int cardWidth, int cardHeight) {

		Card c;

		for (int y = 0; y < 4; y++) {

			for (int x = 0; x < 4; x++) {

				c = new Card(getContext());

				c.setNum(0);

				// ����Ƭ�������
				addView(c, cardWidth, cardHeight);

				cardsMap[x][y] = c;
			}
		}
	}

	// ��ʼ��Ϸ����
	public void startGame() {

		// ����������
		MainActivity.getMainActivity().clearScore();

		// ��ʼ����cardsMap��ֵ���ȶ�����
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardsMap[x][y].setNum(0);
			}
		}

		addRandomNum();
		addRandomNum();
	}

	// ������������
	private void addRandomNum() {

		//
		emptyPoints.clear();
		// �����еĿ�Ƭ��ֵ���б��������յĴ����
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}

		// ����Ƴ�һ����
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size()));

		// �������������֣�2���ֵĸ�����0.9,4����0.1
		cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	// ���Ʒ���ʵ��,��Ϸʵ���߼�
	private void swipeLeft() {

		boolean merge = false;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				// ���ұ߱���

				for (int x1 = x + 1; x1 < 4; x1++) {
					// ���x1����
					if (cardsMap[x1][y].getNum() > 0) {
						// �����ǰ�ǿյ�
						if (cardsMap[x][y].getNum() <= 0) {
							// ����ǰ��������Ϊ�ұߵ�����
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							// �ұߵ�������Ϊ0
							cardsMap[x1][y].setNum(0);
							// ���±���һ��
							x--;
							// ������
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							// ���������ȵĻ���ô����ӣ������ߣ�Ȼ���ұߵ���Ϊ��
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							// ���·���
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}
						// ִ�����������ѭ��
						break;
					}
				}
			}
		}
		if (merge) {
			// ��������˵Ļ����Ӷ�һ��������

			addRandomNum();
			// ����Ƿ���Ϸ�Ѿ�������
			checkComplete();
		}
	}

	// ���Ʒ���ʵ��
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

	// �ϻ�����ʵ��
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

	// �»�����ʵ��
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

	// ������Ϸ�Ƿ��Ѿ�����
	private void checkComplete() {

		boolean complete = true;

		// ���һ��All��ǩ�������������һ�־�����ѭ��
		ALL: for (int y = 0; y < 4; y++) {
			/*
			 * ֻҪ��������һ����������������ѭ���� �п���λ�� �ĸ������ϵ�λ������ͬ������
			 */
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum() == 0
						|| (x > 0 && cardsMap[x][y].equals(cardsMap[x - 1][y]))
						|| (x < 3 && cardsMap[x][y].equals(cardsMap[x + 1][y]))
						|| (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1]))
						|| (y < 3 && cardsMap[x][y].equals(cardsMap[x][y + 1]))) {

					complete = false;
					break ALL; // ��������ѭ��
				}
			}
		}

		// ����Ѿ������Ļ�����һ����ʾ��
		if (complete) {
			new AlertDialog.Builder(getContext())
					.setTitle("���")
					.setMessage("��Ϸ����")
					.setPositiveButton("����",
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
	
	// ��ʼ��һ��4x4�Ŀ�Ƭ���������ڼ�¼ÿ����ӵĿ�Ƭ
	private Card[][] cardsMap = new Card[4][4];
	// �洢�յ�Ķ���
	private List<Point> emptyPoints = new ArrayList<Point>();
}
