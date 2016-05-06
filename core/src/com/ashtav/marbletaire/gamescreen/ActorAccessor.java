package com.ashtav.marbletaire.gamescreen;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorAccessor implements TweenAccessor<Actor> {
	public static final int POSITION_XY = 1;
	public static final int OPACITY = 5;
	public static final int SIZE = 7;

	@Override
	public int getValues(Actor target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POSITION_XY:
			returnValues[0] = target.getX();
			returnValues[1] = target.getY();
			return 2;
		case OPACITY:
			returnValues[0] = target.getColor().a;
			return 1;
		case SIZE:
			returnValues[0] = target.getWidth();
			returnValues[1] = target.getHeight();
			return 3;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Actor target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POSITION_XY:
			target.setPosition(newValues[0], newValues[1]);
			break;
		case OPACITY:
			Color c = target.getColor();
			c.set(c.r, c.g, c.b, newValues[0]);
			target.setColor(c);
			break;
		case SIZE:
			target.setWidth(newValues[0]);
			target.setHeight(newValues[1]);
		default:
			assert false;
		}
	}
}
