package android.com.quizapp.model;

/*
* This class holds the values of each choice and if its the correct choice or not
* */
public class Choice {
    //The Choice
    private String choice;
    //if its the answer or not
    private boolean isAnswer=false;

    public Choice(String choice, boolean isAnswer) {
        this.choice = choice;
        this.isAnswer = isAnswer;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }
}
