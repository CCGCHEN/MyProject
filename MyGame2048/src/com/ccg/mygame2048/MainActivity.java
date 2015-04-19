package com.ccg.mygame2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public MainActivity() {
		mainActivity = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvScore = (TextView) findViewById(R.id.tvScore);
		button = (Button) findViewById(R.id.startNewGame);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				GameView.getGameView().startGame();
				// TODO Auto-generated method stub

			}
		});
	}


	private static MainActivity mainActivity = null;

	// �������Է��ʵ�MainActivity
	public static MainActivity getMainActivity() {
		return mainActivity;

	}

	// ��ʾ����
	public void showScore() {
		tvScore.setText(score + "");

	}

	// ��շ���
	public void clearScore() {

		score = 0;
		showScore();

	}

	// ���·���
	public void addScore(int s) {
		score += s;

		showScore();
	}

	private int score = 0;
	private TextView tvScore;
	private Button button;

}
