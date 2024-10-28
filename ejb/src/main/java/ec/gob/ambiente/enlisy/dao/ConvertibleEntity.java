package ec.gob.ambiente.enlisy.dao;

import java.io.Serializable;

public abstract class ConvertibleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract long getId();

	public abstract String getDescription();

	@Override
	public int hashCode() {
		return (getClass().getSimpleName().hashCode() + Long.valueOf(getId()).hashCode());
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && other.getClass().isAssignableFrom(getClass())
				&& getClass().isAssignableFrom(other.getClass())) ? getId()==((ConvertibleEntity) other).getId()
						: (other == this);
	}

	@Override
	public String toString() {
		return getDescription();
	}

}