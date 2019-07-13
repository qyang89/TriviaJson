package com.example.triviajson.pojo;

import com.google.gson.annotations.SerializedName;

public class TriviaQuestions {

	@SerializedName("answer")
	private String answer;

	@SerializedName("question")
	private String question;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("category_id")
	private int categoryId;

	@SerializedName("airdate")
	private String airdate;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("invalid_count")
	private Object invalidCount;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private Category category;

	@SerializedName("value")
	private int value;

	@SerializedName("game_id")
	private Object gameId;

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setAirdate(String airdate){
		this.airdate = airdate;
	}

	public String getAirdate(){
		return airdate;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setInvalidCount(Object invalidCount){
		this.invalidCount = invalidCount;
	}

	public Object getInvalidCount(){
		return invalidCount;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	public void setGameId(Object gameId){
		this.gameId = gameId;
	}

	public Object getGameId(){
		return gameId;
	}

	@Override
 	public String toString(){
		return 
			"TriviaQuestions{" +
			"answer = '" + answer + '\'' + 
			",question = '" + question + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",airdate = '" + airdate + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",invalid_count = '" + invalidCount + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",value = '" + value + '\'' + 
			",game_id = '" + gameId + '\'' + 
			"}";
		}
}