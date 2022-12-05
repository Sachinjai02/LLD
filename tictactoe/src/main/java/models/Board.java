package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;

    private int dimension;

    public Board(int dimension) {
        this.dimension = dimension;
        this.cells = new ArrayList<>();
        for(int i=0;i<dimension;++i) {
            List<Cell> row = new ArrayList<>();
            cells.add(row);
            for(int j=0;j<dimension;++j) {
                row.add(new Cell(i,j));
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}
