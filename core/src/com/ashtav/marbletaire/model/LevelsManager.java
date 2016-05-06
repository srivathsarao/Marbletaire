package com.ashtav.marbletaire.model;

import java.util.ArrayList;
import java.util.List;

public class LevelsManager {
	public static Integer[][] level1 = {{9, 9, 1, 1, 1, 9, 9}, 
										{9, 9, 1, 1, 1, 9, 9},
										{1, 1, 1, 1, 1, 1, 1},
										{1, 1, 1, 0, 1, 1, 1},
										{1, 1, 1, 1, 1, 1, 1},
										{9, 9, 1, 1, 1, 9, 9},
										{9, 9, 1, 1, 1, 9, 9}};
	
	public static Integer[][] level2 = {{1, 1, 1, 1, 1, 1}, 
										{1, 1, 1, 1, 1, 1},
										{1, 1, 1, 0, 1, 1},
										{1, 1, 1, 1, 1, 1},
										{1, 1, 1, 1, 1, 1},
										{1, 1, 1, 1, 1, 1}};
	
	public static Integer[][] level3 = {{9, 9, 1, 1, 1, 9, 9}, 
										{9, 1, 1, 1, 1, 1, 9},
										{1, 1, 1, 1, 1, 1, 1},
										{1, 1, 1, 0, 1, 1, 1},
										{1, 1, 1, 1, 1, 1, 1},
										{9, 1, 1, 1, 1, 1, 9},
										{9, 9, 1, 1, 1, 9, 9}};
	
	public static Integer[][] level4 = {{9, 9, 9, 1, 9, 9, 9}, 
										{9, 9, 1, 1, 1, 9, 9},
										{9, 1, 1, 1, 1, 1, 9},
										{1, 1, 1, 0, 1, 1, 1},
										{1, 1, 1, 1, 1, 1, 1},
										{9, 1, 1, 1, 1, 1, 9},
										{9, 9, 1, 1, 1, 9, 9},
										{9, 9, 9, 1, 9, 9, 9}};

	public static Integer[][] level5 = {{9, 9, 9, 9, 1, 9, 9, 9, 9}, 
										{9, 9, 9, 1, 1, 1, 9, 9, 9},
										{9, 9, 9, 1, 1, 1, 9, 9, 9},
										{9, 1, 1, 1, 1, 1, 1, 1, 9},
										{1, 1, 1, 1, 0, 1, 1, 1, 1},
										{9, 1, 1, 1, 1, 1, 1, 1, 9},
										{9, 9, 9, 1, 1, 1, 9, 9, 9},
										{9, 9, 9, 1, 1, 1, 9, 9, 9},
										{9, 9, 9, 9, 1, 9, 9, 9, 9}};
	
	public static Integer[][] level6 = {{9, 9, 9, 1, 9, 9, 9}, 
										{9, 9, 1, 1, 1, 9, 9},
										{9, 1, 1, 1, 1, 1, 9},
										{1, 1, 1, 1, 1, 1, 1},
										{1, 1, 1, 0, 1, 1, 1},
										{1, 1, 1, 1, 1, 1, 1},
										{9, 1, 1, 1, 1, 1, 9},
										{9, 9, 1, 1, 1, 9, 9},
										{9, 9, 9, 1, 9, 9, 9}};
	
	public static Integer[][] level7 = {{9, 9, 9, 9, 1, 9, 9, 9, 9}, 
										{9, 9, 9, 1, 1, 1, 9, 9, 9},
										{9, 9, 1, 1, 1, 1, 1, 9, 9},
										{9, 1, 1, 1, 1, 1, 1, 1, 9},
										{1, 1, 1, 1, 0, 1, 1, 1, 1},
										{9, 1, 1, 1, 1, 1, 1, 1, 9},
										{9, 9, 1, 1, 1, 1, 1, 9, 9},
										{9, 9, 9, 1, 1, 1, 9, 9, 9},
										{9, 9, 9, 9, 1, 9, 9, 9, 9}};
	
	public static Integer[][] level8 = {{9, 1, 1, 1, 9}, 
										{9, 1, 1, 1, 9},
										{1, 1, 1, 1, 1},
										{1, 1, 0, 1, 1},
										{1, 1, 1, 1, 1},
										{9, 1, 1, 1, 9},
										{9, 1, 1, 1, 9}};
	
	public static Integer[][] level9 = {{9, 1, 1, 1, 9, 9}, 
										{9, 1, 1, 1, 9, 9},
										{1, 1, 1, 1, 1, 1},
										{1, 1, 0, 1, 1, 1},
										{1, 1, 1, 1, 1, 1},
										{9, 1, 1, 1, 9, 9}};
	
	public static Integer[][] level10 = {{9, 9, 1, 1, 1, 9, 9}, 
										 {9, 9, 1, 1, 1, 9, 9},
										 {9, 9, 1, 1, 1, 9, 9},
										 {1, 1, 1, 1, 1, 1, 1},
										 {1, 1, 1, 0, 1, 1, 1},
										 {1, 1, 1, 1, 1, 1, 1},
										 {9, 9, 1, 1, 1, 9, 9},
										 {9, 9, 1, 1, 1, 9, 9},
										 {9, 9, 1, 1, 1, 9, 9}};
   
	public static Integer[][] level11 = {{9, 9, 1, 1, 1, 9, 9, 9}, 
										 {9, 9, 1, 1, 1, 9, 9, 9},
										 {9, 9, 1, 1, 1, 9, 9, 9},
										 {1, 1, 1, 1, 1, 1, 1, 1},
										 {1, 1, 1, 0, 1, 1, 1, 1},
										 {1, 1, 1, 1, 1, 1, 1, 1},
										 {9, 9, 1, 1, 1, 9, 9, 9},
										 {9, 9, 1, 1, 1, 9, 9, 9}};
	
	
	static List<Level> levels;
	
	public static void initializeLevels() {
		levels = new ArrayList<Level>();
		levels.add(new Level(1, level1, "English", "CgkI2trM_NIeEAIQAA"));
		levels.add(new Level(2, level2, "6x6", "CgkI2trM_NIeEAIQCA"));
		levels.add(new Level(3, level3, "French", "CgkI2trM_NIeEAIQAQ"));
		levels.add(new Level(4, level4, "Diamond 32", "CgkI2trM_NIeEAIQCQ"));
		levels.add(new Level(5, level5, "Diamond 37", "CgkI2trM_NIeEAIQCg"));
		levels.add(new Level(6, level6, "Diamond 39", "CgkI2trM_NIeEAIQCw"));
		levels.add(new Level(7, level7, "Diamond 41", "CgkI2trM_NIeEAIQDA"));
		levels.add(new Level(8, level8, "Cross 27A", "CgkI2trM_NIeEAIQDQ"));
		levels.add(new Level(9, level9, "Cross 27B", "CgkI2trM_NIeEAIQDg"));
		levels.add(new Level(10, level10, "Cross 39A", "CgkI2trM_NIeEAIQDw"));
		levels.add(new Level(11, level11, "Cross 39B", "CgkI2trM_NIeEAIQEA"));
	}
	
	public static void disposeLevels() {
		levels = null;
	}
	
	public static Level getNextLevel(Integer index) {
		if(levels.size() - 1 == index) {
			return levels.get(0);
		} else {
			return levels.get(index + 1);
		}
	}

	public static Integer getLevelIndex(Level level) {
		return levels.indexOf(level);
	}

	public static Level getLevel(Integer index) {
		try {
			Level level = (Level) levels.get(index);
			return new Level(level.getNumber(), deepCopyIntMatrix(level.getMatrix()), level.getMessage(), level.getLeaderBoardId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Integer[][] deepCopyIntMatrix(Integer[][] input) {
		if (input == null) {
			return null;
		}
		Integer[][] result = new Integer[input.length][];
		for (Integer r = 0; r < input.length; r++) {
			result[r] = new Integer[input[r].length];
			System.arraycopy(input[r], 0, result[r], 0, input[r].length);
			//result[r] = input[r].clone();
		}
		return result;
	}
}
