package com.ashtav.marbletaire.model;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameData<K, J, V> {
	
	private Integer left;
	private Level level;
	private float deltaTime;
	private boolean soundEnabled;
	
	private Map<GameKeys<K, J>, V> map;
	private DecimalFormat timeFormatter;

	public GameData() {
		left = 0;
		this.map = new HashMap<GameKeys<K, J>, V>();
		timeFormatter = new DecimalFormat("##00");
	}

	public V put(K keyOne, J keyTwo, V value) {
		return map.put(new GameKeys<K, J>(keyOne, keyTwo), value);
	}

	public V get(K keyOne, J keyTwo) {
		return map.get(new GameKeys<K, J>(keyOne, keyTwo));
	}

	public V remove(K keyOne, J keyTwo) {
		return map.remove(new GameKeys<K, J>(keyOne, keyTwo));
	}

	public boolean containsKey(K keyOne, J keyTwo) {
		return map.containsKey(new GameKeys<K, J>(keyOne, keyTwo));
	}

	public Set<java.util.Map.Entry<GameKeys<K, J>, V>> entrySet() {
		return map.entrySet();
	}
	
	public Set<GameKeys<K, J>> keySet() {
		return map.keySet();
	}
	
	public int size() {
		return map.size();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public float getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(float deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getRows() {
		if (this.level != null) {
			return level.getMatrix().length;
		}
		return null;
	}
	
	public boolean isNewGame() {
		if(size() == this.level.numberOfMarbles() || deltaTime == 0) {
			return true;
		}
		return false;
	}

	public Integer getColumns() {
		if (this.level != null) {
			if (this.level.getMatrix().length > 0) {
				return this.level.getMatrix()[0].length;
			}
		}
		return null;
	}
	
	public String getTimeSpent() {
		int hours = (int) deltaTime / 3600;
		int remainder = (int) deltaTime - hours * 3600;
		int mins = remainder / 60;
		remainder = remainder - mins * 60;
		int secs = remainder;

		StringBuffer time = new StringBuffer(timeFormatter.format(hours));
		time.append(":").append(timeFormatter.format(mins)).append(":").append(timeFormatter.format(secs));
		
		return time.toString();//String.format("%02d:%02d:%02d", hours, mins, secs);
	}
	
	public String formatHHMMSS(long secondsCount){  
	    //Calculate the seconds to display:  
	    int seconds = (int) (secondsCount %60);  
	    secondsCount -= seconds;  
	    //Calculate the minutes:  
	    long minutesCount = secondsCount / 60;  
	    long minutes = minutesCount % 60;  
	    minutesCount -= minutes;  
	    //Calculate the hours:  
	    long hoursCount = minutesCount / 60;  
	    //Build the String  
	    return "" + hoursCount + ":" + minutes + ":" + seconds;  
	} 
	
	public Integer getLayoutElementType(Integer row, Integer column) {
		return this.level.getMatrix()[row][column];
	}

	public static class GameKeys<K, J> {
		private K keyOne;
		private J keyTwo;

		public GameKeys(K keyOne, J keyTwo) {
			this.keyOne = keyOne;
			this.keyTwo = keyTwo;
		}

		public K getKeyOne() {
			return keyOne;
		}

		public J getKeyTwo() {
			return keyTwo;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((keyOne == null) ? 0 : keyOne.hashCode());
			result = prime * result + ((keyTwo == null) ? 0 : keyTwo.hashCode());
			return result;
		}

		@SuppressWarnings("unchecked")
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
			GameKeys<K, J> other = (GameKeys<K, J>) obj;
			if (keyOne == null) {
				if (other.keyOne != null) {
					return false;
				}
			} else if (!keyOne.equals(other.keyOne)) {
				return false;
			}
			if (keyTwo == null) {
				if (other.keyTwo != null) {
					return false;
				}
			} else if (!keyTwo.equals(other.keyTwo)) {
				return false;
			}
			return true;
		}
		
		@Override
		public String toString() {
			return keyOne + "," + keyTwo;
		}
	}
}
