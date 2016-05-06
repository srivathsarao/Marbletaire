package com.ashtav.marbletaire.model.gamescreen;

import static com.ashtav.marbletaire.model.GameAssetManager.CLICK_SOUND;
import static com.ashtav.marbletaire.model.GameAssetManager.getAsset;

import java.util.Map.Entry;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Cubic;
import aurelienribon.tweenengine.equations.Quad;

import com.ashtav.marbletaire.core.view.screen.GameScreen;
import com.ashtav.marbletaire.gamescreen.ActorAccessor;
import com.ashtav.marbletaire.gamescreen.BoardManager;
import com.ashtav.marbletaire.gamescreen.Empty;
import com.ashtav.marbletaire.gamescreen.Marble;
import com.ashtav.marbletaire.model.ColorData;
import com.ashtav.marbletaire.model.Direction;
import com.ashtav.marbletaire.model.GameData;
import com.ashtav.marbletaire.model.GameData.GameKeys;
import com.ashtav.marbletaire.model.UndoBuffer;
import com.ashtav.marbletaire.model.UndoBuffer.UndoItem;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoardTable {
	private Integer cellWidth;
	private Integer top;
	private BoardManager manager;
	private Group group;
	private Integer left;
	private Boolean lock;

	private GameData<Integer, Integer, Empty> emptyMap;
	private UndoBuffer<UndoItem> undoBuffer;

	public static final TweenManager tweenManager = new TweenManager();

	public BoardTable(BoardManager manager, UndoBuffer<UndoItem> undoBuffer, Stage stage, Integer cellWidth, Integer left, Integer top) {
		this.cellWidth = cellWidth;
		this.top = top;
		this.left = left;
		this.manager = manager;
		this.group = new Group();

		this.emptyMap = new GameData<Integer, Integer, Empty>();
		this.undoBuffer = undoBuffer;
		this.lock = false;

		Tween.registerAccessor(Marble.class, new ActorAccessor());
	}

	public void add(Marble marble, Integer row, Integer column) {
		marble.setWidth(cellWidth);
		marble.setHeight(cellWidth);
		marble.setRow(row);
		marble.setColumn(column);
		marble.setX(getX(column));
		marble.setY(getY(row));
		group.addActor(marble);
		getGameData().put(row, column, marble);
	}

	public void add(Empty empty, Integer row, Integer column) {
		empty.setWidth(cellWidth);
		empty.setHeight(cellWidth);
		empty.setRow(row);
		empty.setColumn(column);
		empty.setX(getX(column));
		empty.setY(getY(row));
		group.addActor(empty);
		emptyMap.put(row, column, empty);
	}

	public Group getGroup() {
		return group;
	}

	public void remove(final Marble marble) {
		Tween.to(marble, ActorAccessor.OPACITY, 0.6f).target(0).ease(Cubic.INOUT).setCallback(new TweenCallback() {
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				getGameData().remove(marble.getRow(), marble.getColumn());
			}
		}).start(tweenManager);
	}

	public Marble getMarble(Integer row, Integer column) {
		return getGameData().get(row, column);
	}

	private float getX(Integer column) {
		return left + (column * cellWidth);
	}

	private float getY(Integer row) {
		return top - ((row + 1) * cellWidth);
	}

	public void moveAndRemove(Marble from, Empty empty, final Marble middle) {
		getGameData().remove(from.getRow(), from.getColumn());
		from.setRow(empty.getRow());
		from.setColumn(empty.getColumn());
		if (GameScreen.soundEnabled) {
			getAsset(CLICK_SOUND, Sound.class).play();
		}

		Timeline.createSequence()
		.push(Tween.to(from, ActorAccessor.POSITION_XY, 0.2f).target(empty.getX(), empty.getY()).ease(Quad.INOUT))
		.push(Tween.to(middle, ActorAccessor.OPACITY, 0.6f).target(0).ease(Cubic.INOUT).setCallback(new TweenCallback() {
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				middle.remove();
			}
		})).start(tweenManager);
		getGameData().remove(middle.getRow(), middle.getColumn());
		getGameData().put(empty.getRow(), empty.getColumn(), from);
	}

	public Marble getLeft(Marble source) {
		if (source != null) {
			return getGameData().get(source.getRow(), source.getColumn() - 1);
		}
		return null;
	}

	public Marble getRight(Marble source) {
		if (source != null) {
			return getGameData().get(source.getRow(), source.getColumn() + 1);
		}
		return null;
	}

	public Marble getTop(Marble source) {
		if (source != null) {
			return getGameData().get(source.getRow() - 1, source.getColumn());
		}
		return null;
	}

	public Marble getBottom(Marble source) {
		if (source != null) {
			return getGameData().get(source.getRow() + 1, source.getColumn());
		}
		return null;
	}

	public Empty getNextLeftEmpty(Marble source) {
		if (source != null) {
			Marble next = getGameData().get(source.getRow(), source.getColumn() - 2);
			Empty empty = emptyMap.get(source.getRow(), source.getColumn() - 2);
			if (next == null) {
				return empty;
			}
		}
		return null;
	}

	public Empty getNextRightEmpty(Marble source) {
		if (source != null) {
			Marble next = getGameData().get(source.getRow(), source.getColumn() + 2);
			Empty empty = emptyMap.get(source.getRow(), source.getColumn() + 2);
			if (next == null) {
				return empty;
			}
		}
		return null;
	}

	public Empty getNextTopEmpty(Marble source) {
		if (source != null) {
			Marble next = getGameData().get(source.getRow() - 2, source.getColumn());
			Empty empty = emptyMap.get(source.getRow() - 2, source.getColumn());
			if (next == null) {
				return empty;
			}
		}
		return null;
	}

	public Empty getNextBottomEmpty(Marble source) {
		if (source != null) {
			Marble next = getGameData().get(source.getRow() + 2, source.getColumn());
			Empty empty = emptyMap.get(source.getRow() + 2, source.getColumn());
			if (next == null) {
				return empty;
			}
		}
		return null;
	}

	public void moveMarble(Marble marble, Direction direction) {
		if (lock) {
			return;
		}
		lock = true;
		if (direction.equals(Direction.RIGHT)) {
			move(marble, this.getRight(marble), this.getNextRightEmpty(marble));
		} else if (direction.equals(Direction.LEFT)) {
			move(marble, this.getLeft(marble), this.getNextLeftEmpty(marble));
		} else if (direction.equals(Direction.TOP)) {
			move(marble, this.getTop(marble), this.getNextTopEmpty(marble));
		} else if (direction.equals(Direction.BOTTOM)) {
			move(marble, this.getBottom(marble), this.getNextBottomEmpty(marble));
		}
		lock = false;
	}

	private void move(Marble marble, Marble middle, Empty next) {
		if (middle != null && next != null) {
			undoBuffer.add(new UndoItem(marble.getRow(), marble.getColumn(), next.getRow(), next.getColumn()));
			moveAndRemove(marble, next, middle);
			getGameData().setLeft(getGameData().getLeft() - 1);
			manager.updateScore();
		}
		if (!movesPossible()) {
			manager.showScoreScreen();
		}
	}

	public boolean movesPossible() {
		for (Entry<GameKeys<Integer, Integer>, Marble> entry : getGameData().entrySet()) {
			Marble marble = entry.getValue();
			if (marble == null) {
				continue;
			}
			if (this.getRight(marble) != null && this.getNextRightEmpty(marble) != null) {
				return true;
			} else if (this.getLeft(marble) != null && this.getNextLeftEmpty(marble) != null) {
				return true;
			} else if (this.getTop(marble) != null && this.getNextTopEmpty(marble) != null) {
				return true;
			} else if (this.getBottom(marble) != null && this.getNextBottomEmpty(marble) != null) {
				return true;
			}
		}
		return false;
	}
	
	public void undoMove(ColorData colorData) {
		if (lock) {
			return;
		}
		lock = true;
		UndoItem undoItem = undoBuffer.get();
		if (undoItem == null) {
			lock = false;
			return;
		}
		Integer fromRow = undoItem.getFromRow();
		Integer fromColumn = undoItem.getFromColumn();

		Integer toRow = undoItem.getToRow();
		Integer toColumn = undoItem.getToColumn();

		Integer middleRow = fromRow;
		Integer middleColumn = fromColumn;

		if (fromRow == toRow) {
			middleColumn = (fromColumn + toColumn) / 2;
		} else if (fromColumn == toColumn) {
			middleRow = (fromRow + toRow) / 2;
		}

		manager.createMarble(colorData, fromRow, fromColumn);
		manager.createMarble(colorData, middleRow, middleColumn);

		Marble toMarble = getMarble(toRow, toColumn);
		if (toMarble != null) {
			remove(getMarble(toRow, toColumn));
		}

		getGameData().setLeft(getGameData().getLeft() + 1);
		manager.updateScore();
		lock = false;
	}

	public BoardManager getManager() {
		return manager;
	}

	public GameData<Integer, Integer, Marble> getGameData() {
		return manager.getGameData();
	}
}
