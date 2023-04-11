public class CatanBoardGenerator {
    public static void main(String[] args) {
       generate(3);
    }

    public static void generate(int numBoards){
        CatanBoard temp = new CatanBoard(19);
        for (int i = 0; i<numBoards; i++){
            temp.generateBoard();
            temp.printBoard();
        }
    }

   
}
