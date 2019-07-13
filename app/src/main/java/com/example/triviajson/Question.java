package com.example.triviajson;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question{

    @SerializedName("id")
    private String id;

    @SerializedName("answer")
    private String answer;

    @SerializedName("value")
    private String value;

    @SerializedName("clues_count")
    private Integer cluesCount;

    @SerializedName("question")
    private String question;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCluesCount() {
        return cluesCount;
    }

    public void setCluesCount(Integer cluesCount) {
        this.cluesCount = cluesCount;
    }


    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return question;

    }



    @Override
    public String toString(){
        return
                "Question{" +
                        "id = '" + id + '\'' +
                        ",answer = '" + question + '\'' +
                        ",value = '" + value + '\'' +
                        ",cluesCount = '" + cluesCount + '\'' +
                        ",question = '" + question + '\'' +
                        "}";
    }
}