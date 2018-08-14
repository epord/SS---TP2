package automaton;

public class Cell {
	Integer x;
	Integer y;
	Integer z;
	State state;

	public Cell(Integer x, Integer y, Integer z, State state) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.state = state;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	@Override
	public int hashCode() {
		return (getX().toString() + getY().toString() + getZ().toString() + state.toString()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		Cell other = (Cell) obj;

		return getX().equals(other.getX()) && getY().equals(other.getY()) && getZ().equals(other.getZ()) &&
				getState().equals(other.getState());
	}
}
