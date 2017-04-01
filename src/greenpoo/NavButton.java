package greenpoo;

import greenfoot.World;
import greenfoot.Greenfoot;

public class NavButton extends Button {
	private GenericWorld toWorld;

	NavButton(int x, int y, GenericWorld thisWorld, GenericWorld toWorld) {
		this(toWorld.getName(), x, y, thisWorld, toWorld);
	}

	NavButton(String label, int x, int y, GenericWorld thisWorld, GenericWorld toWorld)
	{
		super(label, x, y, thisWorld);
		this.toWorld = toWorld;
	}

	@Override
	public void buttonAction() {
		this.toWorld.enter();
	}
}
