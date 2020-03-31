{
    private static int[][] rows ={{0,0,0},{0,0,0},{0,0,0}};
    private static int[][] columns = new int[3][3];
    private static int[][] diagonals = new int [2][3];
    private static String name;
    public static void main(String[] args) {
        startInput();

    }
    private static void printArr(int[][] boardState){
        for(int[] row : boardState){
           for(int index = 0;index< row.length-1; index++){
               System.out.print(" "+ convert(row[index])+" |");
           }
            System.out.print(" "+convert(row[row.length-1]));
            System.out.println();

        }


    }
    private static void process(){
        columns = new int[][]{{rows[0][0], rows[1][0], rows[2][0]}, {rows[0][1], rows[1][1], rows[2][1]}, {rows[0][2], rows[1][2], rows[2][2]}};
        diagonals = new int[][] {{rows[0][0], rows[1][1], rows[2][2]},{rows[2][0],rows[1][1],rows[0][2]}};
    }
    private static String convert(int point){
        if(point == -1){
            return "X";

        }
        if(point == 1){
            return "O";

        }
        else{
            return " ";
        }

    }
    private static void evaluate(){
        process();
        for(int[] row : rows){
            if(sum(row) ==-3){
                System.out.println("I Win!");
                System.exit(0);
            }
            if(sum(row) == 3){
                System.out.println("You Win :(");
                System.exit(0);
            }
        }
        for(int[] column : columns){
            if(sum(column) ==-3){
                System.out.println("I Win!");
                System.exit(0);
            }
            if(sum(column) == 3){
                System.out.println("You Win :(");
                System.exit(0);
            }
        }
        for(int[] diagonal : diagonals){
            if(sum(diagonal) ==-3){
                System.out.println("I Win!");
                System.exit(0);
            }
            if(sum(diagonal) == 3){
                System.out.println("You Win :(");
                System.exit(0);
            }
        }
    }
    private static int sum(int[] line){
        return line[0] + line[1] + line[2];
    }
    private static void generalInput(){
        process();
        printArr(rows);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Column that you want to play in:");
        int inputColumn = input.nextInt();
        System.out.println("Enter Row that you want to play in:");
        int inputRow = input.nextInt();
        rows[inputRow-1][inputColumn-1] = 1;
        process();
        printArr(rows);
    }
    private static void startInput(){
        Scanner input1 = new Scanner(System.in);
        System.out.println("Hello! My Name is Theodore, the Tic-Tac-Toe Automaton!. Let's play a game. What's your name?");
        name = input1.next();
        System.out.println("Do you want to go first " +name+ "? (Yes/No)");
        String choice = input1.next();
        if(choice.equals("Yes")|| choice.equals("yes")){
            generalInput();
        }else{
            //this is where u call the algorithm function to make the first move
        }
    }


}
