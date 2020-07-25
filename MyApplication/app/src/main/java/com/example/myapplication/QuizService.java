package com.example.myapplication;


import com.example.myapplication.DataModels.Questions;
import com.example.myapplication.DataModels.Quiz;

import java.util.ArrayList;

public class QuizService {
    private static QuizService quizService = null;
    ArrayList<Quiz> allQuizes = new ArrayList<>();

    // [x] QuizService qs = new QuizService();
    //
    private QuizService(){
        makeQuizzes();
    }

    public static QuizService getInstance(){
        if(quizService == null){
            quizService = new QuizService();
        }
        return quizService;
    }

    private void makeQuizzes(){
        ArrayList<Questions> Quiz1Questions = new ArrayList<>();
        ArrayList<String> quiz1Question1Ans = new ArrayList<>();
        quiz1Question1Ans.add("True");
        quiz1Question1Ans.add("False");
        Questions CarbTorFQuestion = new Questions("A low carb diet is best for people with type 2 diabetes", quiz1Question1Ans);
        Quiz1Questions.add(CarbTorFQuestion);

        Quiz Diabetes = new Quiz("Diabetes Quiz",Quiz1Questions);
        allQuizes.add(Diabetes);

        ArrayList<Questions> Quiz2Questions = new ArrayList<>();
        ArrayList<String> quiz2Question1Ans = new ArrayList<>();
        quiz2Question1Ans.add("4");
        quiz2Question1Ans.add("1");
        quiz2Question1Ans.add("2");
        quiz2Question1Ans.add("3");
        Questions ChamberQuestion = new Questions("How many Chambers does the heart have?", quiz2Question1Ans);
        Quiz2Questions.add(ChamberQuestion);

        Quiz Cardio = new Quiz("Cardio Quiz",Quiz2Questions);
        allQuizes.add(Cardio);

        ArrayList<Questions> Quiz3Questions = new ArrayList<>();
        ArrayList<String> quiz3Question1Ans = new ArrayList<>();
        quiz3Question1Ans.add("Sweating");
        quiz3Question1Ans.add("Hunger");
        quiz3Question1Ans.add("Crying");
        quiz3Question1Ans.add("Runny nose");
        Questions AnxietyQuestion = new Questions("Common anxiety signs and symptoms include which?", quiz3Question1Ans);
        Quiz3Questions.add(AnxietyQuestion);

        Quiz Anxiety = new Quiz("Anxiety Quiz",Quiz3Questions);
        allQuizes.add(Anxiety);

    }


    // this will return a list of all quiz's names
    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<>();
        for(Quiz q:allQuizes){
            names.add(q.getQuizName());
        }
        return names;
    }

    public Quiz getQuiz(String name){
        for(Quiz q: allQuizes){
            if(q.getQuizName().equals(name)){
                return q;
            }
        }
        return null;
    }
}