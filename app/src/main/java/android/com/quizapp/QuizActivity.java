package android.com.quizapp;

import android.com.quizapp.model.Choice;
import android.com.quizapp.model.Question;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private LinearLayout answersContainer;
    Question[] questions;
    private boolean[] answers;
    private EditText answerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Button nextQuestionButton = findViewById(R.id.view_score_btn);
        answersContainer = findViewById(R.id.questions_container);
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScore();
            }
        });
        questions=loadQuestions();
        showQuestion();
    }
    private void showScore(){
        String questionSixAnswer="framelayout";
        String userAnswer=answerEditText.getText().toString().toLowerCase();
        answers[5] = userAnswer.equals(questionSixAnswer);
        int totalCorrectScore=0;
        for (boolean answer : answers) {
            if (answer) {
                totalCorrectScore = totalCorrectScore + 1;
            }
        }
        Toast.makeText(this, getResources().getString(R.string.score_text).concat(String.valueOf(" "+totalCorrectScore+" ")).concat(getString(R.string.out_of)).concat(String.valueOf(" "+answers.length+" ")), Toast.LENGTH_SHORT).show();
    }
    private void showQuestion() {
        answers=new boolean[questions.length];
        for (int j=0;j<questions.length;j++) {
            Question question=questions[j];
            TextView questionTextview=new TextView(this);

            questionTextview.setText(question.getQuestion());
            questionTextview.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams paramsTextView=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsTextView.setMargins(0,10,0,0);
            questionTextview.setLayoutParams(paramsTextView);
            answersContainer.addView(questionTextview);
            if(j==5){
                answerEditText=new EditText(this);
                LinearLayout.LayoutParams paramsEditText=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsEditText.setMargins(0,10,0,0);
                answerEditText.setHint(R.string.question_six_hint);
                answerEditText.setLayoutParams(paramsEditText);
                answersContainer.addView(answerEditText);
                return;
            }
            List<Choice> choices = question.getChoices();
            RadioGroup radioGroup = new RadioGroup(this);
            for (int i = 0; i < question.getChoices().size(); i++) {
                final Choice choice = choices.get(i);
                if (question.isMultiple()) {
                    CheckBox checkBox = new CheckBox(this);
                    final int finalJ = j;
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            answers[finalJ] = isChecked && choice.isAnswer();
                        }
                    });
                    checkBox.setText(choice.getChoice());
                    answersContainer.addView(checkBox);
                } else {
                    RadioButton radioButton = new RadioButton(this);
                    final int finalJ1 = j;
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            answers[finalJ1] = isChecked && choice.isAnswer();

                        }
                    });
                    radioButton.setText(choice.getChoice());
                    radioGroup.addView(radioButton, i);
                }
            }
            if (!question.isMultiple()) {
                answersContainer.addView(radioGroup);
            }
        }
    }

    private Question[] loadQuestions() {
        //load choices from strings.xml
        String[] questionOneChoices = getResources().getStringArray(R.array.question_one_choices);
        String[] questionTwoChoices = getResources().getStringArray(R.array.question_two_choices);
        String[] questionOThreeChoices = getResources().getStringArray(R.array.question_three_choices);
        String[] questionFourChoices = getResources().getStringArray(R.array.question_four_choices);
        String[] questionFiveChoices = getResources().getStringArray(R.array.question_five_choices);

        //load correct answers from strings.xml
        String[] questionOneAnswers = getResources().getStringArray(R.array.question_one_correct);
        String[] questionTwoAnswers = getResources().getStringArray(R.array.question_two_correct);
        String[] questionOThreeAnswers = getResources().getStringArray(R.array.question_three_correct);
        String[] questionFourAnswers = getResources().getStringArray(R.array.question_four_correct);
        String[] questionFiveAnswers = getResources().getStringArray(R.array.question_five_correct);

        //initialize choices for each question
        List<Choice> questionOneChoicesList = new ArrayList<>();
        List<Choice> questionTwoChoicesList = new ArrayList<>();
        List<Choice> questionThreeChoicesList = new ArrayList<>();
        List<Choice> questionFourChoicesList = new ArrayList<>();
        List<Choice> questionFiveChoicesList = new ArrayList<>();

        //load choices for question 1
        for (int i = 0; i < questionOneChoices.length; i++) {
            questionOneChoicesList.add(new Choice(questionOneChoices[i], Boolean.parseBoolean(questionOneAnswers[i])));
        }

        //load choices for question 2
        for (int i = 0; i < questionTwoChoices.length; i++) {
            questionTwoChoicesList.add(new Choice(questionTwoChoices[i], Boolean.parseBoolean(questionTwoAnswers[i])));
        }

        //load choices for question 3
        for (int i = 0; i < questionOThreeChoices.length; i++) {
            questionThreeChoicesList.add(new Choice(questionOThreeChoices[i], Boolean.parseBoolean(questionOThreeAnswers[i])));
        }

        //load choices for question 4
        for (int i = 0; i < questionFourChoices.length; i++) {
            questionFourChoicesList.add(new Choice(questionOneChoices[i], Boolean.parseBoolean(questionFourAnswers[i])));
        }

        //load choices for question 5
        for (int i = 0; i < questionFiveChoices.length; i++) {
            questionFiveChoicesList.add(new Choice(questionFiveChoices[i], Boolean.parseBoolean(questionFiveAnswers[i])));
        }

        //initialize each question and its choices
        return new Question[]{
                new Question(R.string.question_one, false, questionOneChoicesList),
                new Question(R.string.question_two, true, questionTwoChoicesList),
                new Question(R.string.question_three, false, questionThreeChoicesList),
                new Question(R.string.question_four, true, questionFourChoicesList),
                new Question(R.string.question_five, false, questionFiveChoicesList),
                new Question(R.string.question_six,false,null)
        };
    }
}