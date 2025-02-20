public class Cell {
    private int state;

    public Cell(int state) {
        this.state = state;
    }

    // retorna el estado
    public int getState() {
       return this.state;
    }

    // seteo el estado
    public void setState(int state) {
        this.state = state;
    }

    // reglas del juego 1 vive 0 muere
    public int keepAlive(int aliveNeighbors) {
        if (this.state == 1 && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
            return 1;
        } else if (this.state == 0 && aliveNeighbors == 3) {
            return 1;
        } else {
            return 0;
        }
    }


}
