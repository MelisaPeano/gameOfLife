public class Cell {
    private int state;

    public Cell(int state) {
        this.state = state;
    }

    public int getState() {
       if (this.state == 1) {
           return 1;
       } else {
           return 0;
       }
    }

     public int keepAlive(int aliveNeighbors) {
         if (this.state == 1) { // Si está viva
             return (aliveNeighbors == 2 || aliveNeighbors == 3) ? 1 : 0;
         } else { // Si está muerta
             return (aliveNeighbors == 3) ? 1 : 0;
         }
     }

}
