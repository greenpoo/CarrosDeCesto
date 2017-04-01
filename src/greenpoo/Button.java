package greenpoo;

import greenfoot.MouseInfo;
import greenfoot.Greenfoot;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.Color;
import greenfoot.GreenfootSound;

public abstract class Button extends Actor {
	static int padding = 10;

	static Color
		B = Color.BLACK, // background color
		BACTIVE = Color.BLUE, // background color when clicked
		F = Color.GRAY, // foreground color
		FHOVER = Color.WHITE; // foreground color when hovering

	static GreenfootSound click =
		new GreenfootSound("sounds/se/click.mp3");

	private boolean pressed = false, hovering = false;
	private int x, y, minx, maxx, miny, maxy, labelx, labely;
	private GreenfootImage dc;
	private String label;

	Button(String label, int x, int y, GenericWorld world) {
		super();

		this.x = x;
		this.y = y;
		world.addObject(this, x, y);
		setLabel(label);
		setImage(dc);
	}

	/**
	 * set label for the button
	 * <p>It might change size</p>
	 * @param label a string of text displayed by the button
	 */
	public final void setLabel(String label) {
		GreenfootImage textAuxImg =
			new GreenfootImage(label, 13, Color.WHITE, Color.BLACK, Color.WHITE);

		int w = textAuxImg.getWidth(),
				h = textAuxImg.getHeight(),
				hw = w / 2,
				hh = h / 2;

		w = w + 2*padding; h = h + 2*padding;
		this.dc = new GreenfootImage(w, h);

		this.labelx = w/2 - hw;
		this.labely = (h + hh)/2; // wtf greenfoot

		this.minx = x - w/2;
		this.maxx = this.minx + w;
		this.miny = y - h/2;
		this.maxy = this.miny + h;

		this.label = label;

		redraw();
		setImage(dc);
	}

	/**
	 * redraws the GreenfootImage that represents the button
	 */
	private void redraw() {
		dc.setColor(this.pressed ? Color.BLUE : Color.BLACK);
		dc.fill();

		dc.setColor(this.hovering ? Color.WHITE : Color.GRAY);
		dc.drawString(this.label, this.labelx, this.labely);
	}

	@Override
	public final void act() {
		MouseInfo mouse = Greenfoot.getMouseInfo();

		if (mouse != null) {
			int mx = mouse.getX(), my = mouse.getY();

			if (mx > this.minx && mx <= this.maxx &&
					my > this.miny && my <= this.maxy) {

				if (!hovering) { hovering = true; redraw(); }

			} else if (hovering) { hovering = false; redraw(); }
		}

		if (Greenfoot.mousePressed(this) && !this.pressed) {
			this.pressed = true;
			redraw();
		}

		if (this.pressed && Greenfoot.mouseClicked(null)) {
			this.pressed = false;
			redraw();

			if (this.hovering) {
				Button.click.play();
				buttonAction();
			}
		}
	}

	public abstract void buttonAction();
}
