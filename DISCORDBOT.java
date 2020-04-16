import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;
public class hi {
    public static void main(String[] args) throws javax.security.auth.login.LoginException {
        JDA jda = new JDABuilder("").build();
        jda.addEventListener(new TICTACTOE());
    }
}
class TICTACTOE extends ListenerAdapter {
    static GuildMessageReceivedEvent d;
    static int inputC;
    static int inputR;

    public static Integer[][] rows ={{0,0,0},{0,0,0},{0,0,0}};
    public static Integer[][] columns = new Integer[3][3];
    public static Integer[][] diagonals = new Integer[2][3];
    public static Integer[] recent = new Integer[2];
    public static int randomChance =0;
    public static Random errorChance = new Random();
    public static HashSet<String> trick = new HashSet<String>(){
        {
            add(Arrays.deepToString(new Integer[][]{{1,0,0},{0,-1,0},{0,0,1}}));
            add(Arrays.deepToString(new Integer[][]{{0,0,1},{0,-1,0},{1,0,0}}));
        }
    };
    public static Map<String,Integer[]> twoFer = new HashMap<String,Integer[]>() {
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
    public static HashMap<String,Integer[]> corners = new HashMap<String,Integer[]>(){
        {
            put(Arrays.toString(new Integer[]{0,0}), new Integer[]{2,2});
            put(Arrays.toString(new Integer[]{2,2}), new Integer[]{0,0});
            put(Arrays.toString(new Integer[]{0,2}), new Integer[]{2,0});
            put(Arrays.toString(new Integer[]{2,0}), new Integer[]{0,2});
        }
    };
    static String input ="";
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String messageSent = event.getMessage().getContentRaw();
        d=event;
        if(messageSent.equalsIgnoreCase("t!shutdown")){
            event.getChannel().sendMessage("Ok, bye!").queue();
            System.exit(0);
        }
        if(messageSent.equalsIgnoreCase("t!help")){
            event.getChannel().sendMessage("t!start starts the game").queue();
            event.getChannel().sendMessage("to select difficulty when prompted use t!easy, t!medium, t!hard, or t!impossible").queue();
            event.getChannel().sendMessage("to respond to yes/no write t!yes or t!no").queue();
            event.getChannel().sendMessage("to say what column you want to play in, write t!col1, t!col2, or t!col3").queue();
            event.getChannel().sendMessage("to say what row you want to play in, write t!row1, t!row2, or t!row3").queue();
            event.getChannel().sendMessage("please do not start a new game while someone is playing, and don't try to break the system, because it absolutely will. Thx fellow Exonians!").queue();
            return;
        }
        if (messageSent.equalsIgnoreCase("t!start")){
            rows =new Integer[][]{{0,0,0},{0,0,0},{0,0,0}};
            columns = new Integer[3][3];
            diagonals = new Integer[2][3];
            event.getChannel().sendMessage("Hello! My Name is Theodore, the Tic-Tac-Toe Automaton!. What Difficulty would you like to play at? (Easy/Medium/Hard/Impossible)").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!easy")){
            randomChance=1;
            event.getChannel().sendMessage("Do you want to play first?").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!medium")){
                randomChance=2;
            event.getChannel().sendMessage("Do you want to play first?").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!hard")){
                randomChance=3;
            event.getChannel().sendMessage("Do you want to play first?").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!impossible")){
            randomChance=0;
            event.getChannel().sendMessage("Do you want to play first?").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!yes")){
            event.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!no")){
                MasterAlgorithm();
            return;
        }
            if(messageSent.equalsIgnoreCase("t!col1")){
                inputC=1;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if(messageSent.equalsIgnoreCase("t!col2")){
                inputC=2;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if(messageSent.equalsIgnoreCase("t!col3")){
                inputC=3;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if(messageSent.equalsIgnoreCase("t!row1")){
                if(inputC==0){
                    return;
                }
                inputR=1;
                if(rows[inputR-1][inputC-1]==0) {
                    generalInput();
                }else{
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if(messageSent.equalsIgnoreCase("t!row2")){
                if(inputC==0){
                    return;
                }
                inputR=2;
                if(rows[inputR-1][inputC-1]==0) {
                    generalInput();
                }else{
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if(messageSent.equalsIgnoreCase("t!row3")){
                if(inputC==0){
                    return;
                }
                inputR=3;
                if(rows[inputR-1][inputC-1]==0) {
                    generalInput();
                }else{
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
        }


    public static void main(String[] args){
    }
    public static void MasterAlgorithm(){
        process();
        if(evaluate()){
            return;
        }
        if(randomChance == 1){
            int randRow = errorChance.nextInt(3);
            int randColumn = errorChance.nextInt(3);
            while(rows[randRow][randColumn]!=0){
                randRow = errorChance.nextInt(3);
                randColumn = errorChance.nextInt(3);
            }
            rows[randRow][randColumn] = -1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
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
                if(evaluate()){
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
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
                if(evaluate()){
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
        }

        for(Integer[] arr :rows){
            if(sum(arr)== -2){
                for(int i=0; i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        process();
                        if(evaluate()){
                            return;
                        }
                        printArr(rows);
                        d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                        return;
                    }
                }
            }
        }
        for(Integer[] arr : columns){
            if(sum(arr)== -2){
                for(int i=0; i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncCol();
                        process();
                        if(evaluate()){
                            return;
                        }
                        printArr(rows);
                        d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                        return;
                    }
                }
            }
        }for(Integer[] arr : diagonals){
            if(sum(arr)== -2){
                for(int i=0; i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncDiag();
                        process();
                        if(evaluate()){
                            return;
                        }
                        printArr(rows);
                        d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                        return;
                    }
                }
            }
        }
        for(Integer[] arr :rows){
            if(sum(arr)== 2){
                for(int i=0; i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        process();
                        if(evaluate()){
                            return;
                        }
                        printArr(rows);
                        d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                        return;
                    }
                }
            }
        }
        for(Integer[] arr : columns){
            if(sum(arr)== 2){
                for(int i=0; i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncCol();
                        process();
                        if(evaluate()){
                            return;
                        }
                        printArr(rows);
                        d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                        return;
                    }
                }
            }
        }for(Integer[] arr : diagonals){
            if(sum(arr)== 2){
                for(int i=0; i<arr.length; i++){
                    if( arr[i] == 0) {
                        arr[i] = -1;
                        rowSyncDiag();
                        process();
                        if(evaluate()){
                            return;
                        }
                        printArr(rows);
                        d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                        return;
                    }
                }
            }
        }
        if(twoFer.containsKey(Arrays.deepToString(rows))){
            rows[twoFer.get(Arrays.deepToString(rows))[0]] [twoFer.get(Arrays.deepToString(rows))[1]] = -1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;

        }
        if(trick.contains(Arrays.deepToString(rows))){
            rows[1][2] =-1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;
        }
        if(rows[1][1] == 0){
            rows[1][1] = -1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;
        }
        if(corners.containsKey(Arrays.toString(recent))){
            if(rows[corners.get(Arrays.toString(recent))[0]][corners.get(Arrays.toString(recent))[1]] == 0){
                rows[corners.get(Arrays.toString(recent))[0]][corners.get(Arrays.toString(recent))[1]] = -1;
                process();
                if(evaluate()){
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
        }
        if(rows[0][0] == 0){
            rows[0][0] = -1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;
        }
        if(rows[2][2] == 0){
            rows[2][2] = -1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;
        }
        if(rows[0][2] == 0){
            rows[0][2] = -1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;
        }
        if(rows[2][0] == 0){
            rows[2][0] = -1;
            process();
            if(evaluate()){
                return;
            }
            printArr(rows);
            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
            return;
        }
        for(int count = 0; count<rows.length; count++){
            for(int count2=0; count2<rows[count].length; count2++){
                if(rows[count][count2]==0){
                    rows[count][count2] = -1;
                    process();
                    if(evaluate()){
                        return;
                    }
                    printArr(rows);
                    d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                    return;
                }
            }
        }

    }
    public static void printArr(Integer[][] boardState){
        for(Integer[] row : boardState){
            d.getChannel().sendMessage(" "+ convert(row[0])+" |"+" "+ convert(row[1])+" |"+" "+ convert(row[2])).queue();
        }


    }
    public static String convert(int point){
        if(point == -1){
            return ":x:";

        }
        if(point == 1){
            return ":regional_indicator_o:";

        }
        else{
            return ":black_large_square:";
        }

    }
    public static void generalInput(){
        process();
        int inputColumn;
        inputColumn = inputC;
        int inputRow;
        inputRow = inputR;
        recent=new Integer[]{inputRow,inputColumn};
        rows[inputRow - 1][inputColumn - 1] = 1;
        inputC=0;
        inputR=0;
        process();
        printArr(rows);
        d.getChannel().sendMessage("Thinking...").queue();
        MasterAlgorithm();
    }

    public static void process(){
        columns = new Integer[][]{{rows[0][0], rows[1][0], rows[2][0]}, {rows[0][1], rows[1][1], rows[2][1]}, {rows[0][2], rows[1][2], rows[2][2]}};
        diagonals = new Integer[][] {{rows[0][0], rows[1][1], rows[2][2]},{rows[2][0],rows[1][1],rows[0][2]}};
    }
    public static void rowSyncCol(){
        rows = new Integer[][]{{columns[0][0], columns[1][0], columns[2][0]}, {columns[0][1], columns[1][1], columns[2][1]}, {columns[0][2], columns[1][2], columns[2][2]}};
    }
    public static void rowSyncDiag(){
        rows = new Integer[][]{{diagonals[0][0],rows[0][1], diagonals[1][2]},{rows[1][0], diagonals[0][1], rows[1][2]},{diagonals[1][0],rows[2][1], diagonals[0][2]}};
    }

    public static boolean evaluate(){
        Scanner evalIn = new Scanner(System.in);
        process();
        for(Integer[] row : rows){
            if(sum(row) == -3){
                printArr(rows);
                d.getChannel().sendMessage("I Win! Good Game.").queue();
                return true;

            }
            if(sum(row) == 3){
                printArr(rows);
                d.getChannel().sendMessage("You Win :( Good Game.").queue();
                return true;
            }
        }
        for(Integer[] column : columns){
            if(sum(column) == -3){
                printArr(rows);
                d.getChannel().sendMessage("I Win! Good Game.").queue();
                return true;
            }
            if(sum(column) == 3){
                printArr(rows);
                d.getChannel().sendMessage("You Win :( Good Game.").queue();
                return true;
            }
        }
        for(Integer[] diagonal : diagonals){
            if(sum(diagonal) == -3){
                printArr(rows);
                d.getChannel().sendMessage("I Win! Good Game.").queue();
                return true;
            }
            if(sum(diagonal) == 3){
                printArr(rows);
                d.getChannel().sendMessage("You Win :( Good Game.").queue();
                return true;
            }
        }
        int filled = 0;
        for(Integer[] arr : rows){
            for(int point : arr){
                if(point != 0){
                    filled++;
                }

            }
        }
        if(filled == 9){
            d.getChannel().sendMessage("Draw! Good Game.").queue();
            return true;
        }
        return false;
    }
    public static int sum(Integer[] line){
        return line[0] + line[1] + line[2];
    }
    }


