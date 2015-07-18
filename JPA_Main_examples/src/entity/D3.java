package entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//ISSUE:
//org.hibernate.InstantiationException: No default constructor for entity: :
//entity.D3

enum Figure {
	SQUARE, TRIANGLE
};

@Embeddable
public class D3 {
	float z;
	@Enumerated(EnumType.STRING)
	Figure figure;

	public D3() {
	}

	public D3(float z, Figure f) {
		this.z = z;
		this.figure = f;
	}

	@Override
	public String toString() {
		return String.format("D3(%.2f, %s)", this.z, this.figure);
	}
}
