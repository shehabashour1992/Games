package com.shehabashour.testermessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MathActivity extends Activity implements View.OnClickListener
{

        Button buttonObjectChoice1;
        Button buttonObjectChoice2;
        Button buttonObjectChoice3;

        TextView textObjectPartA;
        TextView textObjectPartB;
        TextView textObjectScore;
        TextView textObjectLevel;

        int correctAnswer;
        int wrongAnswer2;
        int wrongAnswer1;
        int currentScore = 0;
        int currentLevel = 1;
        int partA;
        int partB;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_math);

            getWindow().setBackgroundDrawableResource(R.drawable.wallpaper);
            //Here we initialize all our variables
            partA = 9;
            partB = 9;

            correctAnswer = partA * partB;
            wrongAnswer1 = correctAnswer - 1;
            wrongAnswer2 = correctAnswer + 1;

        /* object based on either the button or TextView class and base as well as linking our new objects directly to the appropriate UI elements that we created previously*/
            textObjectPartA = (TextView) findViewById(R.id.textPartA);
            textObjectPartB = (TextView) findViewById(R.id.textPartB);
            textObjectScore = (TextView) findViewById(R.id.textScore);
            textObjectLevel = (TextView) findViewById(R.id.textLevel);

            buttonObjectChoice1 = (Button) findViewById(R.id.buttonChoice1);
            buttonObjectChoice2 = (Button) findViewById(R.id.buttonChoice2);
            buttonObjectChoice3 = (Button) findViewById(R.id.buttonChoice3);

            textObjectScore.setText("Score: " + currentScore);
            textObjectLevel.setText("Level: " + currentLevel);

            setQuestion();

            //Now we use the setText method of the class on our objects to show our variable values on the UI elements.
            textObjectPartA.setText(partA + "");
            textObjectPartB.setText(partB + "");

            //which button receives which answer, at this stage is arbitrary.
            buttonObjectChoice1.setText(correctAnswer + "");
            buttonObjectChoice2.setText(wrongAnswer1 + "");
            buttonObjectChoice3.setText(wrongAnswer2 + "");

            // makes buttons react to clicks
            buttonObjectChoice1.setOnClickListener(this);
            buttonObjectChoice2.setOnClickListener(this);
            buttonObjectChoice3.setOnClickListener(this);
        }


        public void setQuestion()
        {    int i=1;
            //generate the parts of the question
            int numberRange = currentLevel * 3;
            Random randInt = new Random();

            partA = randInt.nextInt(numberRange)+i;
            partB = randInt.nextInt(numberRange)+i;

            correctAnswer = partA * partB;
            wrongAnswer1 = correctAnswer - 1;
            wrongAnswer2 = correctAnswer + 1;

            //set the multi choice buttons a number between 0 and 2
            int buttonLayout = randInt.nextInt(3);

            switch (buttonLayout)
            {
                case 0:
                    buttonObjectChoice1.setText(correctAnswer+"");
                    buttonObjectChoice2.setText(wrongAnswer1+"");
                    buttonObjectChoice3.setText(wrongAnswer2+"");
                    break;
                case 1:
                    buttonObjectChoice2.setText(correctAnswer+"");
                    buttonObjectChoice3.setText(wrongAnswer1+"");
                    buttonObjectChoice1.setText(wrongAnswer2+"");
                    break;
                case 2:
                    buttonObjectChoice3.setText(correctAnswer+"");
                    buttonObjectChoice1.setText(wrongAnswer1+"");
                    buttonObjectChoice2.setText(wrongAnswer2+"");
                    break;
            }

            textObjectPartA.setText(partA+"");
            textObjectPartB.setText(partB+"");
        }

        void updateScoreAndLevel(int answerGiven)
        {
            if(isCorrect(answerGiven))
            {
                for(int i = 1; i <= currentLevel; i++)
                {
                    currentScore = currentScore + i;
                }
                currentLevel++;
            }
            else
            {
                currentScore = 0;
                currentLevel = 1;
                Intent i = new Intent(this, MathActivity.class);
                startActivity(i);
            }
            //Actually update the two TextViews
            textObjectScore.setText("Score: " + currentScore);
            textObjectLevel.setText("Level: " + currentLevel);
        }

        boolean isCorrect(int answerGiven)
        {
            boolean correctTrueOrFalse;

            if (answerGiven == correctAnswer)
            {   //YAY!
                Toast t=Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
                correctTrueOrFalse = true;
            }
            else
            {   //Uh-oh!
                Toast t=Toast.makeText(getApplicationContext(), "Sorry Good Luck Next Time ", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
                correctTrueOrFalse = false;
            }

            return correctTrueOrFalse;
        }

        @Override
        public void onClick(View view)
        {
            //declare a new int to be used in all the cases
            int answerGiven=0;

            switch (view.getId())
            {

                case R.id.buttonChoice1:
                    // integer.parseInt to change from string to int
                    answerGiven = Integer.parseInt(buttonObjectChoice1.getText()+"");
                    break;

                case R.id.buttonChoice2:
                    //same as previous case but using the next button
                    answerGiven = Integer.parseInt(buttonObjectChoice2.getText()+"");
                    break;

                case R.id.buttonChoice3:
                    //same as previous case but using the next button
                    answerGiven = Integer.parseInt(buttonObjectChoice3.getText()+"");
                    break;
            }
            updateScoreAndLevel(answerGiven);
            setQuestion();
        }

    }
