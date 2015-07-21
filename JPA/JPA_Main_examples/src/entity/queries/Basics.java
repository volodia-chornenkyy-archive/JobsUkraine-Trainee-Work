package entity.queries;

import java.util.List;

import entity.Figure;
import entity.Point;

public interface Basics {
	List<Point> selectAll();

	List<Point> selectFigure(Figure figure);

	long countAll();

	List<Point> filterPoints(Figure f, Float x, Float y, Float z);
}
