package com.ashtav.marbletaire.model;

public class Level {
	private Integer number;
	private Integer[][] matrix;
	private String message;
	private String leaderBoardId;

	public Level(Integer number, Integer[][] matrix, String message, String leaderBoardId) {
		super();
		this.number = number;
		this.matrix = matrix;
		this.message = message;
		this.leaderBoardId = leaderBoardId;
	}

	public Integer[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Integer[][] matrix) {
		this.matrix = matrix;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getLeaderBoardId() {
		return leaderBoardId;
	}

	public Integer numberOfMarbles() {
		int k = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				k++;
			}
		}
		return k;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Level other = (Level) obj;
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}
}
