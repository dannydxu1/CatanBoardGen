import java.util.Random;
public class CatanBoard {
    private int pieceCount;
    private String[] resource_tiles;
    private int[] number_tiles;

    public CatanBoard(int pieceCount) {
        this.pieceCount = pieceCount;
        resource_tiles = new String[pieceCount];
        number_tiles = new int[pieceCount];
        int i;
        for ( i = 0; i < number_tiles.length; i++) {
            resource_tiles[i] = "EMPTY";
            number_tiles[i] = 0;
        }
    }

    public void setBoard(String[] arr) {
        resource_tiles = arr;
    }

    public void printBoard() {
        if (pieceCount != 19) {
            return;
        } else {
            int i = 0;
            System.out.println();
            System.out.print("   ");
            for (; i < 3; i++) {
                System.out.print(resource_tiles[i]+":"+number_tiles[i]+" ");
            }
            System.out.println();
            System.out.print(" ");
            for (; i < 3 + 4; i++) {
                System.out.print(resource_tiles[i]+":"+number_tiles[i]+" ");
            }
            System.out.println();
            for (; i < 3 + 4 + 5; i++) {
                System.out.print(resource_tiles[i]+":"+number_tiles[i]+" ");
            }
            System.out.println();
            System.out.print(" ");
            for (; i < 3 + 4 + 5 + 4; i++) {
                System.out.print(resource_tiles[i]+":"+number_tiles[i]+" ");
            }
            System.out.println();
            System.out.print("   ");
            for (; i < 3 + 4 + 5 + 4 + 3; i++) {
                System.out.print(resource_tiles[i]+":"+number_tiles[i]+" ");
            }
            System.out.println();
        }
    }

    public void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+", ");
        }
    }

    public boolean isDone(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                return false;
            }
        }
        return true;
    }

    public String[] shuffleArr(String[] arr){
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomIndexToSwap = rand.nextInt(arr.length);
            String temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public int[] shuffleArr(int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomIndexToSwap = rand.nextInt(arr.length);
            int temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public void generateBoard(){
        // init and shuffle arrs
        Random rand = new Random();
        String[] resource_arr = { "Stone", "Sheep", "Wood", "Wheat", "Brick", "Sheep", "Brick", "Wheat", "Wood", "Desert",
                "Wood", "Stone", "Wood", "Stone", "Wheat", "Sheep", "Brick", "Wheat", "Sheep" };
        int[] number_arr = {-1,2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
        resource_arr = shuffleArr(resource_arr);
        number_arr = shuffleArr(number_arr);

        //generate appropriate resource index
        for (int i = 0; i < pieceCount; i++) {
        int resource_ind = rand.nextInt(resource_arr.length);
        boolean isResourceInvalid = resource_arr[resource_ind].equals("EMPTY");
        while (isResourceInvalid == true) {
            resource_ind = rand.nextInt(resource_arr.length);
            isResourceInvalid = resource_arr[resource_ind].equals("EMPTY");
        }

        // generate appropriate number index
        int number_ind = rand.nextInt(number_arr.length);
        boolean isNumInvalid;
        if (number_arr[number_ind] == -1) {
            isNumInvalid = true;
        } else {
            isNumInvalid = false;
        }
        while (isNumInvalid == true) {
            number_ind = rand.nextInt(number_arr.length);
            if (number_arr[number_ind] != -1) {
                isNumInvalid = false;
            } else {
                if (isDone(number_arr)) {
                    isNumInvalid = false;
                }
            }
        } 

        //create temp copies of the elems at the appropriate indices
        String new_resource = resource_arr[resource_ind];
        int new_number = number_arr[number_ind];

        if (new_resource.equals("Desert")) { //special desert case
            new_number = 7;
        }
        // give aforementioned elems to the fields
        resource_tiles[i] = new_resource;
        number_tiles[i] = new_number;
        
        //properly block the indicies so they cannot be accessed again
        resource_arr[resource_ind] = "EMPTY";
        if (!new_resource.equals("Desert")) {
        number_arr[number_ind] = -1;
        }
        }
    }
}

