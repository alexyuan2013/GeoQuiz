package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity{

	public static final String EXTRA_ANSWER_IS_TRUE =
			"com.example.geoquiz.answer_is_true";   //避免键值重复
	public static final String EXTRA_ANSWER_SHOWN =
			"com.example.geoquiz.answer_shown";   //避免键值重复
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;

	private void setAnswerShownResult(boolean isAnswerShown){
		//构造Intent，将结果返回给QuizActivity
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		//调用setResult(...)方法，将Intent传给QuizActivity
		setResult(RESULT_OK, data);
	}

	protected void onCreate(Bundle savedInstanceState){
		//父类的onCreate(...)方法是必须要调用的
		//然后是绑定Activity的布局文件
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		//getIntent(...)方法得到由QuizActivity传递过来的参数
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

		setAnswerShownResult(false);

		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});
	}

}
