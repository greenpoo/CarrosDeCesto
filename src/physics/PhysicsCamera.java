package physics;

public class PhysicsCamera extends PhysicsActor implements Camera {
	CameraWalls(double mass) {
		super(mass, cam.getS().scale(2));
	}
}
