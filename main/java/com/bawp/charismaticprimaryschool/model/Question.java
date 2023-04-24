package com.bawp.charismaticprimaryschool.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int questionId, option1Id, option2Id, option3Id, option4Id;
    private int correctOptionId;

    public Question(int questionId, int option1Id, int option2Id, int option3Id, int option4Id, int correctOptionId) {
        this.questionId = questionId;
        this.option1Id = option1Id;
        this.option2Id = option2Id;
        this.option3Id = option3Id;
        this.option4Id = option4Id;
        this.correctOptionId = correctOptionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getOption1Id() {
        return option1Id;
    }

    public void setOption1Id(int option1Id) {
        this.option1Id = option1Id;
    }

    public int getOption2Id() {
        return option2Id;
    }

    public void setOption2Id(int option2Id) {
        this.option2Id = option2Id;
    }

    public int getOption3Id() {
        return option3Id;
    }

    public void setOption3Id(int option3Id) {
        this.option3Id = option3Id;
    }

    public int getOption4Id() {
        return option4Id;
    }

    public void setOption4Id(int option4Id) {
        this.option4Id = option4Id;
    }

    public int getCorrectOptionId() {
        return correctOptionId;
    }

    public void setCorrectOptionId(int correctOptionId) {
        this.correctOptionId = correctOptionId;
    }
}
