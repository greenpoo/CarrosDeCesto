package physics;

import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.TreeMap;
import java.util.Map;

import java.time.Instant;
import java.time.Duration;

// import java.awt.Frame;

public class PhysicsWorld extends World {
	protected static final Vector2D dim = new Vector2D(600, 400);
	private Map<Integer, PhysicsActor> _actors = new TreeMap<Integer, PhysicsActor>();
	private int _actorIds = 0;
	private double _scale;
	private Vector2D _size;

	public PhysicsWorld(double scale) {
		super((int) dim.getX(), (int) dim.getY(), 1);
		_size = new Vector2D(dim.getX() / scale, dim.getY() / scale);
		_scale = scale;
		_before = Instant.now();
	}
// Cria a escala por metros por pixies 
	public double getScale() { return _scale; }

	private Instant _before;

	public void started() { _before = Instant.now(); }
	// public void stopped() {}

	public void act() {
		Instant now = Instant.now();

		double dt = ((double) Duration.between(_before, now).toMillis()) / 1000, dtDtO2 = dt*dt/2;// Calcula o tempo de cada frame??
			//Aplica a cada actor estas funções 
		for (Map.Entry<Integer, PhysicsActor> entry : _actors.entrySet()) {
			PhysicsActor actor = entry.getValue();

			actor.setScale(_scale);// Faz scale da image do actor.
			actor.collideWithWalls(_size);// Faz a colição com as paredes
			actor.simulateMovement(dt, dtDtO2);//Atualiza o vector do deslocamento, tendo em conta a equação do deslocamento.
			actor.updateGFLocation(_scale);// Faz a atualização do actor tendo em conta o novo vector de deslocamento calculado por simulateMovement.
			
			
		}

		_before = now;
	}

	public void add(PhysicsActor actor, int x, int y) {
		addObject(actor, x, y);
		actor.setPosition(x / _scale, y / _scale);
		_actors.put(_actorIds, actor);
		_actorIds += 1;
	}
}
