package utils.eiffelyk.www.multi_languagetest2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class LanguageAct extends Activity {
	private TextView txtInfo;
	private Button btnChinese;
	private Button btnEnglish;
	private BtnListener btnListener;
	private LinearLayout linearLayout;
	private TextView[] txtViewArr;
	private int[] colors = { 0xffff0000, 0xffffA500, 0xffffff00, 0xff00ff00, 0xff0000ff, 0xff9B30FF };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("ANDROID_LAB", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Strings.initLanguage(this);
		initViews();
		btnListener = new BtnListener();
		btnChinese = (Button) findViewById(R.id.btnChinese);
		btnChinese.setOnClickListener(btnListener);
		btnEnglish = (Button) findViewById(R.id.btnEnglish);
		btnEnglish.setOnClickListener(btnListener);
	}

	private void initViews() {
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
//		txtInfo = (TextView) findViewById(R.id.txtInfo);
		int length = colors.length;
		txtViewArr = new TextView[length];
		LayoutParams layParams = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < length; i++) {
			TextView txt = new TextView(this);
			txt.setBackgroundColor(colors[i]);
			txt.setTextColor(((~colors[i]) | 0xff000000));
			// addContentView(txt, layParams);
			linearLayout.addView(txt, layParams);
			txtViewArr[i] = txt;
		}
		refreshUI();
	}

	private void refreshUI() {
		// 标题栏
		setTitle(Strings.getString(R.string.app_name));
		// 简介
		txtInfo.setText(Strings.getString(R.string.hello));
		// 五个彩条
		String[] arr = Strings.getStringArray(R.array.colors);
		for (int i = 0; i < arr.length; i++) {
			txtViewArr[i].setText(arr[i]);
		}
	}

	class BtnListener implements Button.OnClickListener {
		public void onClick(View view) {
			if (view == btnChinese) {
				updateLanguage(Strings.LANGUAGE_CHINESE);
			} else if (view == btnEnglish) {
				updateLanguage(Strings.LANGUAGE_ENGLISH);
			}
		}
	}

	private void updateLanguage(String language) {
		// 初始化文字信息。
		Strings.setLanguage(this, language);
		// 更新界面。
		refreshUI();
	}

}