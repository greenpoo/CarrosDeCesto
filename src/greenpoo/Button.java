package greenpoo;

import greenfoot.World;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.Color;

import java.awt.Font;
import java.awt.Canvas;
import java.awt.FontMetrics;

public abstract class Button extends Actor {
	private static Font fixedFont = new Font("fixed", 0, 13);
	private static FontMetrics metrics = (new Canvas()).getFontMetrics(fixedFont);

	static GreenfootSound click = new GreenfootSound("sounds/se/click.mp3");
	static int padding = 10, doublePadding = 2*padding;

	@Override
	protected void addedToWorld(World world) {
		updateLabel();
	}

	/**
	 * update button label.
	 * <p>It might change size</p>
	 * @param label a string of text displayed by the button
	 */
	public final void updateLabel() {
		setImage(new GreenfootImage(
					metrics.stringWidth(getLabel()) + doublePadding,
					metrics.getAscent() + metrics.getDescent() + doublePadding));

		redraw();
	}


	private boolean pressed = false, hovering = false;

	/**
	 * redraws the GreenfootImage that represents the button
	 */
	private void redraw() {
		GreenfootImage dc = getImage();

		dc.setColor(pressed ? Color.BLUE : Color.BLACK);
		dc.fill();

		dc.setColor(hovering ? Color.WHITE : Color.GRAY);
		dc.drawString(getLabel(), padding, padding + metrics.getAscent());
	}

	@Override
	public final void act() {
		boolean movedOver = Greenfoot.mouseMoved(this),

						hoverStateChanged =
							!hovering && movedOver ||
							hovering && !movedOver &&
							Greenfoot.mouseMoved(null),

						mousePressed = Greenfoot.mousePressed(this),

						pressedStateChanged =
							!pressed && mousePressed ||
							pressed && !mousePressed &&
							Greenfoot.mouseClicked(null);

		if (hoverStateChanged) hovering = !hovering;

		if (pressedStateChanged) {
			pressed = !pressed;

			if (hovering && !pressed) {
				Button.click.play();
				buttonAction();
			}

		} else if (!hoverStateChanged) return;

		redraw();
	}

	public abstract String getLabel();
	public abstract void buttonAction();
}
