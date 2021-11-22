import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    // debug flags
    private boolean showMines = false;
    private boolean showNbTouchingMines = false;

    // grid properties
    private int nbRows;
    private int nbCols;
    private int nbMines;
    private Cell[][] grid;

    public int getNbCols() {
        return nbCols;
    }

    public int getNbRows() {
        return nbRows;
    }

    public MineSweeper(int nbRows, int nbCols, int nbMines){
        this.nbRows = nbRows;
        this.nbCols = nbCols;
        this.nbMines = nbMines;
        this.grid = new Cell[nbRows][nbCols];
        for (int i = 0; i < nbRows; i++){
            for (int j = 0; j < nbCols; j++) {
                grid[i][j] = new Cell(i,j);
            }
        }
        putMines(nbMines);
    }

    //function to create mines
    public void putMines(int nbMines){
        for (int i = 0; i < nbMines; i++) {
            Random random = new Random();
            int row = random.nextInt(nbRows);  // ligne aléatoire entre 0 et nbRows
            int col = random.nextInt(nbCols);  // colonne aléatoire entre 0 et nbCols
            if(grid[row][col].isMine()){
                i--;
            } else {
                grid[row][col].setMine(true);
                LinkedList<Cell> cell = new LinkedList<Cell>(getNeighbors(this.grid[row][col]));
                for (int j = 0; j < cell.size(); j++) {
                    this.grid[cell.get(j).getRow()][cell.get(j).getCol()].setNbTouchingMines(this.grid[cell.get(j).getRow()][cell.get(j).getCol()].getNbTouchingMines() + 1);
                }
            }
        }

    }

    //funtion to show the inside of a case
    public void unveil(int row, int col){
        this.grid[row][col].setVisible(true);
    }

    //function
    public void play(){

        boolean ended = false;


        int remainingCells = nbCols*nbRows-nbMines;

        while(!ended){
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez des cordonnees");

                try {
                    String line = sc.nextLine();
                    String[] coords = line.split(" ");
                    int i = Integer.parseInt(coords[0]);
                    int j = Integer.parseInt(coords[1]);
                    unveil(i, j);

                    if(grid[i][j].isMine()){
                        System.out.println("Dommage, vous avez perdu ...");
                        ended = true;
                    }
                    else {
                        remainingCells--;
                    }

                    if(remainingCells == 0){
                        ended = true;
                        System.out.println("Bravo !! Vous avez gagné !!");
                    }

                    print();

                } catch (Exception e) {
                    System.out.println("Invalid coords");
                }
            }

    }

    // returns the String representation of a Cell, depending on its attributes and the debug flags
    public String getCellSymbol(Cell cell){

        String symbol = "#";        // default symbol = hidden cell

        // shows the mine in the cell if the cell is visible or if the showMines flag is on
        if((cell.isVisible() || this.showMines) && cell.isMine()){
            symbol = "*";
        }
        // shows the number of touching mines if the cell is visible or if the showNbTouchingMines flag is on
        else if( cell.isVisible() || this.showNbTouchingMines ){

            // special case of a visible cell : " " is displayed instead of "0"
            if( cell.isVisible() && cell.getNbTouchingMines() == 0){
                symbol = " ";
            }
            else{
                symbol = Integer.toString(cell.getNbTouchingMines());
            }

        }

        return symbol;
    }

    // prints the game grid
    public void print(){

        int firstColumnWidth = (int)Math.ceil(Math.log10(this.nbRows));     // first column width = number of digits in nbRows
        int otherColumnsWidth = (int)Math.ceil(Math.log10(this.nbCols));    // other columns width = number of digits in nbCols

        // first line = column numbers
        System.out.printf("%" + firstColumnWidth + "s ", "");
        for(int j = 0; j < this.nbCols; j++){
            System.out.printf("%" + otherColumnsWidth + "s ", j);
        }
        System.out.println();

        for(int i = 0; i < this.nbRows; i++){

            // first column = row numbers
            System.out.printf("%" + firstColumnWidth + "s ", i);
            for(int j= 0; j < this.nbCols; j++){

                Cell cell = this.grid[i][j];
                String cellSymbol = getCellSymbol(cell);
                System.out.printf("%" + otherColumnsWidth + "s ", cellSymbol);
            }
            System.out.println();
        }
    }

    // returns the neighbors of a Cell at the specified row and col in the grid
    public LinkedList<Cell> getNeighbors(Cell cell){

        LinkedList<Cell> neighbors = new LinkedList<>();
        int row = cell.getRow();
        int col = cell.getCol();

        if(row - 1 >= 0){
            neighbors.add( this.grid[row - 1][col] );

            if(col + 1 < this.nbCols){
                neighbors.add( this.grid[row - 1][col + 1] );
            }

            if(col - 1 >= 0){
                neighbors.add( this.grid[row - 1][col - 1] );
            }
        }

        if(col + 1 < this.nbCols){
            neighbors.add(  this.grid[row][col + 1] );
        }

        if(col - 1 >= 0){
            neighbors.add(  this.grid[row][col - 1] );
        }

        if(row + 1 < this.nbRows){
            neighbors.add( this.grid[row + 1][col] );

            if(col + 1 < this.nbCols){
                neighbors.add(  this.grid[row + 1][col + 1] );
            }

            if(col - 1 >= 0){
                neighbors.add( this.grid[row + 1][col - 1] );
            }
        }

        return neighbors;
    }
}
