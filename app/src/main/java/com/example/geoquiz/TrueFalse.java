package com.example.geoquiz;

public class TrueFalse {
	private int mQuestion;
	private boolean mTrueQuestion;
	
	public TrueFalse(int question, boolean trueQuestion){
		setQuestion(question);
		setTrueQuestion(trueQuestion);
	}

	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}

	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

}
