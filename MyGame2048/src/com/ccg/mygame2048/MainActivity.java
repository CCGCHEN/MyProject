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

	// 让外界可以访问到MainActivity
	public static MainActivity getMainActivity() {
		return mainActivity;

	}

	// 显示分数
	public void showScore() {
		tvScore.setText(score + "");

	}

	// 清空分数
	public void clearScore() {

		score = 0;
		showScore();

	}

	// 更新分数
	public void addScore(int s) {
		score += s;

		showScore();
	}

	private int score = 0;
	private TextView tvScore;
	private Button button;

}
