package com.example.imageloader;

public class Constants {
	// һ��ͼƬ����
	public static final String[] IMAGES = new String[] {
			// Heavy images
			"http://192.168.1.121:8080/WEB/2/01.png",
			"http://192.168.1.121:8080/WEB/2/01.png",
			"http://192.168.1.121:8080/WEB/2/02.png",
			"http://192.168.1.121:8080/WEB/2/03.png",
			"http://192.168.1.121:8080/WEB/2/04.png",
			"http://192.168.1.121:8080/WEB/2/05.png",
			"http://192.168.1.121:8080/WEB/2/06.png",
			"http://192.168.1.121:8080/WEB/2/07.png",
			"http://192.168.1.121:8080/WEB/2/08.png",
			"http://192.168.1.121:8080/WEB/2/09.png",
			"http://192.168.1.121:8080/WEB/2/10.png",
			"http://192.168.1.121:8080/WEB/2/11.png",
			"http://192.168.1.121:8080/WEB/2/12.png",
			"http://192.168.1.121:8080/WEB/2/13.png",
			"http://192.168.1.121:8080/WEB/2/14.png",
			"http://192.168.1.121:8080/WEB/2/15.png",
			"http://192.168.1.121:8080/WEB/2/16.png",
			"http://192.168.1.121:8080/WEB/2/17.png",
			"http://192.168.1.121:8080/WEB/2/18.png",
			"http://192.168.1.121:8080/WEB/2/19.png",
			"http://192.168.1.121:8080/WEB/2/20.png",
			"http://192.168.1.121:8080/WEB/2/21.png",
			"http://192.168.1.121:8080/WEB/2/22.png",
			"http://192.168.1.121:8080/WEB/2/23.png",
			"http://192.168.1.121:8080/WEB/2/24.png",
			"http://192.168.1.121:8080/WEB/2/25.png",
			"http://192.168.1.121:8080/WEB/2/26.png",
			"http://192.168.1.121:8080/WEB/2/27.png",
			"http://192.168.1.121:8080/WEB/2/28.png",
			"http://192.168.1.121:8080/WEB/2/29.png",
			"http://192.168.1.121:8080/WEB/2/30.png",
			"http://192.168.1.121:8080/WEB/2/31.png",
			"http://192.168.1.121:8080/WEB/2/32.png",
			"http://192.168.1.121:8080/WEB/2/33.png",
			"http://192.168.1.121:8080/WEB/2/34.png",
			"http://192.168.1.121:8080/WEB/2/35.png",
			"http://192.168.1.121:8080/WEB/2/36.png",
			"http://192.168.1.121:8080/WEB/2/37.png",
			"http://192.168.1.121:8080/WEB/2/38.png",
			"http://192.168.1.121:8080/WEB/2/39.png",
			// Light images
			"http://192.168.1.121:8080/WEB/2/40.png",
			"http://192.168.1.121:8080/WEB/2/41.png",
			"http://192.168.1.121:8080/WEB/2/42.png",
			"http://192.168.1.121:8080/WEB/2/43.png",
			"http://192.168.1.121:8080/WEB/2/44.png",
			"http://192.168.1.121:8080/WEB/2/icon1.png",
			"http://192.168.1.121:8080/WEB/2/icon2.png",
			"http://192.168.1.121:8080/WEB/2/icon3.png",
			"http://192.168.1.121:8080/WEB/2/icon4.png",
			"http://192.168.1.121:8080/WEB/2/icon5.png",
			"http://192.168.1.121:8080/WEB/2/1.png",
			"http://192.168.1.121:8080/WEB/2/2.png",
			"http://192.168.1.121:8080/WEB/2/3.png",
			"http://192.168.1.121:8080/WEB/2/4.png",
			"http://192.168.1.121:8080/WEB/2/5.png",
			"http://192.168.1.121:8080/WEB/2/6.png",
			"http://192.168.1.121:8080/WEB/3/bubble.png",
			"http://192.168.1.121:8080/WEB/3/info.png",
			"http://192.168.1.121:8080/WEB/3/gear.png",
			"http://192.168.1.121:8080/WEB/3/drawer.png",
			"http://192.168.1.121:8080/WEB/3/folder.png",
			"http://192.168.1.121:8080/WEB/3/date.png",
			"http://192.168.1.121:8080/WEB/3/cup.png",
			"http://192.168.1.121:8080/WEB/3/clock.png",
			"http://192.168.1.121:8080/WEB/3/card.png",
			"http://192.168.1.121:8080/WEB/3/bug.png",

			// Special cases
			"http://cdn.urbanislandz.com/wp-content/uploads/2011/10/MMSposter-large.jpg", // very
																							// large
																							// image
			"file:///sdcard/Universal Image Loader @#&=+-_.,!()~'%20.png", // Image
																			// from
																			// SD
																			// card
																			// with
																			// encoded
																			// symbols
			"assets://Living Things @#&=+-_.,!()~'%20.jpg", // Image from assets
			"drawable://" + R.drawable.ic_launcher, // Image from drawables
			"http://upload.wikimedia.org/wikipedia/ru/b/b6/Как_кот_с_мышами_воевал.png", // Link
																							// with
																							// UTF-8
			"https://www.eff.org/sites/default/files/chrome150_0.jpg", // Image
																		// from
																		// HTTPS
			"http://bit.ly/soBiXr", // Redirect link
			"http://img001.us.expono.com/100001/100001-1bc30-2d736f_m.jpg", // EXIF
			"", // Empty link
			"http://wrong.site.com/corruptedLink", // Wrong link
	};

	private Constants() {
	}

	public static class Config {

		public static final boolean DEVELOPR_MODE = false;
	}

	public static class Extra {
		public static final String IMAGES = "com.example.imageloader.IMAGES";
		public static final String IMAGE_POSITION = "com.example.imageloader.IMAGE_POSITION";

	}

}
