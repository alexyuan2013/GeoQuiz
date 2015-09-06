package com.example.geoquiz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private Button mTrueButton;
	private Button mFalseButton;
	private Button mCheatButton;
	//private Button mNextButton;
	//private Button mPrevButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	private TextView mQuestionTextView;

	private static final String TAG = "QuizActivity"; //日志常量
	private static final String KEY_INDEX = "index"; //保存当前位置


	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.question_capital, false),
			new TrueFalse(R.string.question_mideast, true),
			new TrueFalse(R.string.question_ocean, true),
			new TrueFalse(R.string.question_asia, true),
			new TrueFalse(R.string.question_baikal, true),
			new TrueFalse(R.string.question_manas, false)
	};
	private int mCurrentIndex = 0;
	private boolean mIsCheat = false;

	private void updateQuestion(){
		//这里获得是question的资源号
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		//这里根据资源号来查找对应的文本
		mQuestionTextView.setText(question);
	}

	private void checkAnswer(boolean userPressedTrue){
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		int messageResId = 0;
		if(userPressedTrue == answerIsTrue){
			messageResId = R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

		if(mIsCheat){
			messageResId = R.string.judgment_toast;
			Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(TAG, "onCreate(Buddle) called");

		setContentView(R.layout.activity_quiz);

		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		mQuestionTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
		//根据保存的状态参数设置当前的问题索引
		if(savedInstanceState != null){
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
		}
		//更新问题
		updateQuestion();


		mTrueButton = (Button)findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});

		mFalseButton = (Button)findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});

		mNextButton = (ImageButton)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});

		mPrevButton = (ImageButton)findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
				updateQuestion();
			}
		});

		mCheatButton = (Button)findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//创建Intent，用于启动CheatActivity
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				//传入参数
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				//startActivity(i);
				//启动Activity，并请求返回结果，在onActivityResult方法中获得结果
				startActivityForResult(i, 0);
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG, "onStart() called");
	}

	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}

	@Override
	public void onSaveInstanceState(Bundle saveInstanceState){
		super.onSaveInstanceState(saveInstanceState);
		Log.d(TAG, "onSaveInstanceState");
		//保存当前Activity的状态，用于重建Activity
		saveInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	//获得Activity的返回结果
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(data ==null){
			return;
		}
		mIsCheat = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}

}
