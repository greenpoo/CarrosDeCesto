package greenpoo;

import greenfoot.World;
import greenfoot.Greenfoot;

public class NavButton extends Button {

	private static class LabeledNavButton extends NavButton {
		private String label;

		LabeledNavButton(GenericWorld toWorld, String label) {
			super(toWorld);
			this.label = label;
		}

		public String getLabel() { return label; }
	}

	public static NavButton
		labeled(GenericWorld toWorld, String label) {
			return new LabeledNavButton(toWorld, label);
		}

	private GenericWorld toWorld;

	NavButton(GenericWorld toWorld) {
		super();
		this.toWorld = toWorld;
	}

	public String getLabel() {
		return toWorld.getName();
	}

	public void buttonAction() {
		this.toWorld.enter();
	}

	// public void setToWorld(GenericWorld toWorld) {
	// 	this.toWorld = toWorld;
	// }
}
