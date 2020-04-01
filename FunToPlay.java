import java.util.*;
import java.util.concurrent.TimeUnit;

public class TICTACTOE {
    private static Integer[][] rows ={{0,0,0},{0,0,0},{0,0,0}};
    private static Integer[][] columns = new Integer[3][3];
    private static Integer[][] diagonals = new Integer[2][3];
    private static Integer[] recent = new Integer[2];
    private static String name;
    private static int randomChance =0;
    private static Random errorChance = new Random();
    private static Map<String,Integer[]> twoFer = new HashMap<>() {
        {
            put(Arrays.deepToString(new Integer[][]{{1,0,0},{0,-1,1},{0,0,-1}}),new Integer[] {2,1});
            put(Arrays.deepToString(new Integer[][]{{0,0,1},{0,-1,0},{-1,1,0}}),new Integer[] {1,0});
            put(Arrays.deepToString(new Integer[][]{{-1,0,0},{1,-1,0},{0,0,1}}),new Integer[] {0,1});
            put(Arrays.deepToString(new Integer[][]{{0,1,-1},{0,-1,0},{1,0,0}}),new Integer[] {1,2});
            put(Arrays.deepToString(new Integer[][]{{0,0,1},{1,-1,0},{-1,0,0}}),new Integer[] {2,1});
            put(Arrays.deepToString(new Integer[][]{{-1,1,0},{0,-1,0},{0,0,1}}),new Integer[] {1,0});
            put(Arrays.deepToString(new Integer[][]{{0,0,-1},{0,-1,1},{1,0,0}}),new Integer[] {0,1});
            put(Arrays.deepToString(new Integer[][]{{1,0,0},{0,-1,0},{0,1,-1}}),new Integer[] {1,2});
        }
    };
    private static TimeUnit time = TimeUnit.MILLISECONDS;
    private static HashMap<String,Integer[]> corners = new HashMap<>(){
        {
            put(Arrays.toString(new Integer[]{0,0}), new Integer[]{2,2});
            put(Arrays.toString(new Integer[]{2,2}), new Integer[]{0,0});
            put(Arrays.toString(new Integer[]{0,2}), new Integer[]{2,0});
            put(Arrays.toString(new Integer[]{2,0}), new Integer[]{0,2});
        }
    };
    public static void main(String[] args)  throws InterruptedException{
        startInput();
    }
    private static void MasterAlgorithm() throws InterruptedException{
        System.out.println();
        System.out.println("Thinking...");
        time.sleep(600L);
        process();
        evaluate();
        if(randomChance == 1){
               int randRow = errorChance.nextInt(3);
               int randColumn = errorChance.nextInt(3);
               while(rows[randRow][randColumn]!=0){
                   randRow = errorChance.nextInt(3);
                   randColumn = errorChance.nextInt(3);
               }
               rows[randRow][randColumn] = -1;
               process();
               evaluate();
               generalInput();
               return;

        }
        if(randomChance==2){
            if(errorChance.nextInt(2) == 1){
                int randRow = errorChance.nextInt(3);
                int randColumn = errorChance.nextInt(3);
                while(rows[randRow][randColumn]!=0){
                    randRow = errorChance.nextInt(3);
                    randColumn = errorChance.nextInt(3);
                }
                rows[randRow][randColumn]=-1;
                process();
                evaluate();
                generalInput();
                return;
            }
        }
        if(randomChance==3){
            if(errorChance.nextInt(5) == 3){
                int randRow = errorChance.nextInt(3);
                int randColumn = errorChance.nextInt(3);
                while(rows[randRow][randColumn]!=0){
                    randRow = errorChance.nextInt(3);
                    randColumn = errorChance.nextInt(3);
                }
                rows[randRow][randColumn]=-1;
                process();
                evaluate();
                generalInput();
                return;
            }
        }

        for(Integer[] arr :rows){
            if(sum(arr)== -2){
                for(int i=0;i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        process();
                        evaluate();
                        generalInput();
                        return;
                    }
                }
            }
        }
        for(Integer[] arr : columns){
            if(sum(arr)== -2){
                for(int i=0;i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncCol();
                        process();
                        evaluate();
                        generalInput();
                        return;
                    }
                }
            }
        }for(Integer[] arr : diagonals){
            if(sum(arr)== -2){
                for(int i=0;i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncDiag();
                        process();
                        evaluate();
                        generalInput();
                        return;
                    }
                }
            }
        }
        for(Integer[] arr :rows){
            if(sum(arr)== 2){
                for(int i=0;i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        process();
                        evaluate();
                        generalInput();
                        return;
                    }
                }
            }
        }
        for(Integer[] arr : columns){
            if(sum(arr)== 2){
                for(int i=0;i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncCol();
                        process();
                        evaluate();
                        generalInput();
                        return;
                    }
                }
            }
        }for(Integer[] arr : diagonals){
            if(sum(arr)== 2){
                for(int i=0;i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncDiag();
                        process();
                        evaluate();
                        generalInput();
                        return;
                    }
                }
            }
        }
        if(twoFer.containsKey(Arrays.deepToString(rows))){
            rows[twoFer.get(Arrays.deepToString(rows))[0]] [twoFer.get(Arrays.deepToString(rows))[1]] = -1;
            process();
            evaluate();
            generalInput();
            return;

        }
        if(rows[1][1] == 0){
            rows[1][1] = -1;
            process();
            evaluate();
            generalInput();
            return;
        }
        if(corners.containsKey(Arrays.toString(recent))){
            if(rows[corners.get(Arrays.toString(recent))[0]][corners.get(Arrays.toString(recent))[1]] == 0){
                rows[corners.get(Arrays.toString(recent))[0]][corners.get(Arrays.toString(recent))[1]] = -1;
                process();
                evaluate();
                generalInput();
                return;
            }
        }
        if(rows[0][0] == 0){
            rows[0][0] = -1;
            process();
            evaluate();
            generalInput();
            return;
        }
        if(rows[2][2] == 0){
            rows[2][2] = -1;
            process();
            evaluate();
            generalInput();
            return;
        }
        if(rows[0][2] == 0){
            rows[0][2] = -1;
            process();
            evaluate();
            generalInput();
            return;
        }
        if(rows[2][0]==0){
            rows[2][0] = -1;
            process();
            evaluate();
            generalInput();
            return;
        }
       for(int count = 0; count<rows.length; count++){
           for(int count2=0; count2<rows[count].length;count2++){
                if(rows[count][count2]==0){
                    rows[count][count2] = -1;
                    process();
                    evaluate();
                    generalInput();
                    return;
                }
            }
        }

    }
    private static void printArr(Integer[][] boardState){
        for(Integer[] row : boardState){
            for(int index = 0;index< row.length-1; index++){
                System.out.print(" "+ convert(row[index])+" |");
            }
            System.out.print(" "+convert(row[row.length-1]));
            System.out.println();

        }


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
    private static void generalInput() throws InterruptedException{
        process();
        printArr(rows);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Column that you want to play in:");
        Integer inputColumn = input.nextInt();
        System.out.println("Enter Row that you want to play in:");
        Integer inputRow = input.nextInt();
        recent=new Integer[]{inputRow,inputColumn};
        if(rows[inputRow-1][inputColumn-1] != 0) {
            System.out.println("That square is full, try again");
            generalInput();
        }
        rows[inputRow - 1][inputColumn - 1] = 1;
        process();
        printArr(rows);
        MasterAlgorithm();
    }
    private static void startInput() throws InterruptedException{
        Scanner input1 = new Scanner(System.in);
        System.out.println("Hello! My Name is Theodore, the Tic-Tac-Toe Automaton!. Let's play a game. What's your name?");
        name = input1.next();
        System.out.println("What Difficulty would you like to play at? (Easy/Medium/Hard/Impossible)");
        String diffChoice = input1.next();
        if(diffChoice.equals("Easy")|| diffChoice.equals("easy")){
            randomChance = 1;
        }else if(diffChoice.equals("Medium")||diffChoice.equals("medium")){
            randomChance = 2;
        }else if(diffChoice.equals("Hard")||diffChoice.equals("hard")){
            randomChance = 3;
        }else{
            randomChance=0;
        }
        System.out.println("Do you want to go first " +name+ "? (Yes/No)");
        String choice = input1.next();
        if(choice.equals("Yes")|| choice.equals("yes")){
            generalInput();
        }else{
            MasterAlgorithm();
        }
    }
    private static void process(){
        columns = new Integer[][]{{rows[0][0], rows[1][0], rows[2][0]}, {rows[0][1], rows[1][1], rows[2][1]}, {rows[0][2], rows[1][2], rows[2][2]}};
        diagonals = new Integer[][] {{rows[0][0], rows[1][1], rows[2][2]},{rows[2][0],rows[1][1],rows[0][2]}};
    }
    private static void rowSyncCol(){
        rows = new Integer[][]{{columns[0][0], columns[1][0], columns[2][0]}, {columns[0][1], columns[1][1], columns[2][1]}, {columns[0][2], columns[1][2], columns[2][2]}};
    }
    private static void rowSyncDiag(){
        rows = new Integer[][]{{diagonals[0][0],rows[0][1], diagonals[1][2]},{rows[1][0], diagonals[0][1], rows[1][2]},{diagonals[1][0],rows[2][1], diagonals[0][2]}};
    }

    private static void evaluate(){
        process();
        for(Integer[] row : rows){
            if(sum(row) == -3){
                printArr(rows);
                System.out.println("I Win!");
                System.exit(0);
            }
            if(sum(row) == 3){
                printArr(rows);
                System.out.println("You Win :(");
                System.exit(0);
            }
        }
        for(Integer[] column : columns){
            if(sum(column) == -3){
                printArr(rows);
                System.out.println("I Win!");
                System.exit(0);
            }
            if(sum(column) == 3){
                printArr(rows);
                System.out.println("You Win :(");
                System.exit(0);
            }
        }
        for(Integer[] diagonal : diagonals){
            if(sum(diagonal) ==-3){
                printArr(rows);
                System.out.println("I Win!");
                System.exit(0);
            }
            if(sum(diagonal) == 3){
                printArr(rows);
                System.out.println("You Win :(");
                System.exit(0);
            }
        }
        int filled =0;
        for(Integer[] arr : rows){
            for(int point : arr){
                if(point!=0){
                    filled++;
                }

            }
        }
        if(filled ==9){
            System.out.println("Draw");
            System.exit(0);
        }
    }
    private static int sum(Integer[] line){
        return line[0] + line[1] + line[2];
    }



}
