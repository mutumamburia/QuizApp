package android.com.quizapp.model;

import java.util.List;

/*
* This class holds the question and its multiple choices
* */
public class Question {
    private int question;
    private boolean isMultiple;
    private List<Choice> mChoices;

    public Question(int question, boolean isMultiple, List<Choice> choices) {
        this.question = question;
        this.isMultiple = isMultiple;
        mChoices = choices;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(boolean multiple) {
        isMultiple = multiple;
    }

    public List<Choice> getChoices() {
        return mChoices;
    }

    public void setChoices(List<Choice> choices) {
        mChoices = choices;
    }
}
