package com.example.gamestate;

public class GameState {
	private static GameState gameState;
	// ��� ��������������� �������
	private int id_Profile;
	// ��� ���������� ����
	private int id_Game;
	// ��������� ����� �������� ���������� ���������� ������
	private String LastChar;
	
	// Singleton constructor 
	private GameState(){		
	}
	
	public static GameState getInstance(){
		if(gameState == null){
			gameState = new GameState();
			return gameState;
		}else{
			return gameState;
		}
	}
	
	public int getId_Profile() {
		return id_Profile;
	}
	public void setId_Profile(int id_Profile) {
		this.id_Profile = id_Profile;
	}
	
	public int getId_Game() {
		return id_Game;
	}
	public void setId_Game(int id_Game) {
		this.id_Game = id_Game;
	}
	
	public String getLastChar() {
		return LastChar;
	}
	public void setLastChar(String lastChar) {
		LastChar = lastChar;
	}

}
