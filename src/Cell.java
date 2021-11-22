public class Cell {
    private int row;
    private int col;
    private boolean mine;
    private boolean visible;
    private int nbTouchingMines;

    public Cell(int row, int col) {
        this.row=row;
        this.col=col;

        mine = false;
        visible = false;
        nbTouchingMines = 0;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public boolean isMine() {
        return mine;
    }
    public void setMine(boolean mine) {
        this.mine = mine;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public int getNbTouchingMines() {
        return nbTouchingMines;
    }
    public void setNbTouchingMines(int nbTouchingMines) {
        this.nbTouchingMines = nbTouchingMines;
    }
}