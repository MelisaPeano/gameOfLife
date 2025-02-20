public class Cell {
    private int state;

    public Cell(int state) {
        this.state = state;
    }

    public int getState() {
       return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public int keepAlive(int aliveNeighbors) {
        if (this.state == 1 && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
            return 1; // Sobrevive
        } else if (this.state == 0 && aliveNeighbors == 3) {
            return 1; // Revive
        } else {
            return 0; // Muere
        }
    }


}
