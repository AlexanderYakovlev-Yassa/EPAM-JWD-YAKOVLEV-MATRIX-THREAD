package by.epam.jwd.yakovlev.multithread.entity;

import java.util.ArrayList;
import java.util.HashSet;

public class Matrix {

    private HashSet<Cell> matrix;
    private int dimension;

    public Matrix(int dimension) {
        this.dimension = dimension;
        this.matrix = initialiseMatrix(dimension);
    }

    public Matrix() {
        this.matrix = new HashSet<>();
    }

    public HashSet<Cell> getMatrixCellSet() {
        return matrix;
    }

    public void setMatrix(HashSet<Cell> matrix) {
        this.matrix = matrix;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        matrix = initialiseMatrix(dimension);
    }

    public Cell getCellByLocation(int x, int y) {

        HashSet<Cell> tempHashSet = new HashSet<>();

        for (Cell c : matrix) {
            if (c.getLocation().getX() == x) {
                tempHashSet.add(c);
            }
        }
        for (Cell c : tempHashSet) {
            if (c.getLocation().getY() == y) {
                return c;
            }
        }

        return null;
    }

    public String getStringVisualisedMatrix() {

        StringBuilder sb = new StringBuilder();
        int maxCellValueStringLength = getMaxMatrixCellStringLength();
        String stringCellRepresentation;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                stringCellRepresentation = visualiseCell(getCellByLocation(i, j), maxCellValueStringLength);
                sb.append(stringCellRepresentation);
            }
            if (i != dimension - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public String getStringVisualisedMatrixState() {

        StringBuilder sb = new StringBuilder();
        int maxCellValueStringLength = getMaxMatrixCellStringLength();
        String stringCellRepresentation;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                //visualiseCell(, maxCellValueStringLength);
                if (getCellByLocation(i, j).getState().equals("FREE")){
                    stringCellRepresentation = "[ ]";
                } else {
                    stringCellRepresentation = "[X]";
                }
                sb.append(stringCellRepresentation);
            }
            if (i != dimension - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public ArrayList<Cell> getDiagonalRange(){

        ArrayList<Cell> range = new ArrayList<>();

        int x;
        int y;

        for (Cell c : matrix){

            x = c.getLocation().getX();
            y = c.getLocation().getY();

            if (x == y){
                range.add(c);
            }
        }

        return range;
    }

    public ArrayList<Cell> getCruciformRange(Cell crossPointCell){

        if (crossPointCell == null){
            return null;
        }

        if (!matrix.contains(crossPointCell)){

            return null;
        }

        ArrayList<Cell> range = new ArrayList<>();

        int crossPointX = crossPointCell.getLocation().getX();
        int crossPointY = crossPointCell.getLocation().getY();

        int x;
        int y;

        for (Cell c : matrix){
            x = c.getLocation().getX();
            y = c.getLocation().getY();

            if (!(x == crossPointX && y == crossPointY) &&
                    (x == crossPointX || y == crossPointY)){
                range.add(c);
            }
        }

        return range;
    }

    public void resetCellsState(){

        for (Cell c : matrix){
            c.markAsFree();
        }
    }

    private HashSet<Cell> initialiseMatrix(int dimension) {

        Location tempLocation = new Location();
        HashSet<Cell> res = new HashSet<>();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tempLocation = new Location();
                tempLocation.setX(i);
                tempLocation.setY(j);
                res.add(new Cell(tempLocation, 0));
            }
        }

        return res;
    }

    private String visualiseCell(Cell cell, int maxStringLength) {

        String stringValue = Integer.toString(cell.getValue());
        int prefixLength = maxStringLength - stringValue.length();
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < prefixLength; i++) {
            sb.append(" ");
        }
        sb.append(stringValue);
        sb.append("]");

        return sb.toString();
    }

    private int getMaxMatrixCellStringLength() {

        int max = 0;
        int stringValueLength;

        for (Cell c : matrix) {
            stringValueLength = Integer.toString(c.getValue()).length();
            max = max < stringValueLength ? stringValueLength : max;
        }

        return max;
    }
}
