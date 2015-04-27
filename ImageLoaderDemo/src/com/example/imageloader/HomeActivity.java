
package com.example.imageloader;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imageloader.Constants.Extra;
import com.nostra13.universalimageloader.utils.L;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class HomeActivity extends BaseActivity {

	private static final String TEST_FILE_NAME = "Universal Image Loader @#&=+-_.,!()~'%20.png";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_home);

		// 定义文件对象，目录：/mnt/sdcard, 文件名:TEST_FILE_NAME
		File testImageOnSdCard = new File("/mnt/sdcard", TEST_FILE_NAME);
		if (!testImageOnSdCard.exists()) {	// 如果文件不存在
			// 把文件复制到SD卡
			copyTestImageToSdCard(testImageOnSdCard);
		}
	}

	// 点击进入ListView展示界面
	public void onImageListClick(View view) {
		Intent intent = new Intent(this, ImageListActivity.class);
		intent.putExtra(Extra.IMAGES, Constants.IMAGES);
		startActivity(intent);
	}

	/**
	 * 开一个线程把assert目录下的图片复制到SD卡目录下
	 * @param testImageOnSdCard
	 */
	private void copyTestImageToSdCard(final File testImageOnSdCard) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					InputStream is = getAssets().open(TEST_FILE_NAME);
					FileOutputStream fos = new FileOutputStream(testImageOnSdCard);
					byte[] buffer = new byte[8192];
					int read;
					try {
						while ((read = is.read(buffer)) != -1) {
							fos.write(buffer, 0, read);	// 写入输出流
						}
					} finally {
						fos.flush();		// 写入SD卡
						fos.close();		// 关闭输出流
						is.close();			// 关闭输入流
					}
				} catch (IOException e) {
					L.w("Can't copy test image onto SD card");
				}
			}
		}).start();		// 启动线程
	}
}