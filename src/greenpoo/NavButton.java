package greenpoo;

public class NavButton extends Button {
	private GenericWorld to;

	NavButton(int x, int y, GenericWorld thisWorld, GenericWorld toWorld) {
		this(toWorld.getName(), x, y, thisWorld, toWorld);
	}

	NavButton(String label, int x, int y, GenericWorld thisWorld, GenericWorld toWorld) {
		super(label, x, y, thisWorld);
		this.to = toWorld;
	}

	public void buttonAction() {
		this.to.enter();
	}
}
