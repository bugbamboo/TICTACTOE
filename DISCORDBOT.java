import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
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
    static int player =1;
    static String challenger;
    static String challenged;
    static String name1;
    static String name2;
    public static Integer[][] exsdiag ={{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
    public static Integer[][] exrow ={{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    public static Integer[][] excol ={{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}};
    public static Integer[][] exsquare ={{1,1,0,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}};
    public static Integer[][] excross ={{1,0,1,0},{0,1,0,0},{1,0,1,0},{0,0,0,0}};
    public static Integer[][] explus ={{0,1,0,0},{1,0,1,0},{0,1,0,0},{0,0,0,0}};
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

    public static Integer[][] frows = new Integer[4][4];
    public static Integer[][] fcolumns = new Integer[4][4];
    public static Integer[][] fdiagonals = new Integer[2][4];
    public static Integer[][] crosses = new Integer[4][5];
    public static Integer[][] squares = new Integer[9][4];
    public static Integer[][] plusses = new Integer[4][4];
    static TreeMap<String, Double> sorted;
    public static HashMap<String, Double> rank = new HashMap<String,Double>();
    public static HashMap<String, Double> frank = new HashMap<String,Double>();
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        Message m = event.getMessage();
        String messageSent = m.getContentRaw();
        d=event;
        if(messageSent.equalsIgnoreCase("t!rank")){
            event.getChannel().sendMessage("Current top 5:").queue();
            sorted = new TreeMap<String, Double>(new ValueComparator(rank));
            sorted.putAll(rank);
            Object[] arr = sorted.entrySet().toArray();
            if(arr.length<5){
                for(Object x:arr ){
                    event.getChannel().sendMessage(x.toString()).queue();
                }
            }else{
                for(int i=0; i<5; i++){
                    event.getChannel().sendMessage(arr[i].toString()).queue();
                }
            }
            return;
        }
        if(messageSent.equalsIgnoreCase("t!frank")){
            event.getChannel().sendMessage("Current top 5:").queue();
            sorted = new TreeMap<String, Double>(new ValueComparator(frank));
            sorted.putAll(frank);
            Object[] arr = sorted.entrySet().toArray();
            if(arr.length<5){
                for(Object x:arr ){
                    event.getChannel().sendMessage(x.toString()).queue();
                }
            }else{
                for(int i=0; i<5; i++){
                    event.getChannel().sendMessage(arr[i].toString()).queue();
                }
            }
            return;
        }
        if(!event.getMember().getUser().isBot()) {
            if (messageSent.contains("t!challenge")) {
                player =1;
                StringTokenizer st = new StringTokenizer(messageSent);
                st.nextToken();
                challenger=event.getMember().getEffectiveName();
                name1=challenger;

                try {
                    challenged = st.nextToken();
                    name2 = event.getMessage().getMentionedMembers().get(0).getEffectiveName();
                }catch(Exception e){
                }
                if(!rank.containsKey(name1)){
                    rank.put(name1,0.0);
                }
                if(!rank.containsKey(name2)){
                    rank.put(name2,0.0);
                }
                event.getChannel().sendMessage(challenger+" challenges " +challenged+" to a game of Tic Tac Toe!").queue();
                rows =new Integer[][]{{0,0,0},{0,0,0},{0,0,0}};
                columns = new Integer[3][3];
                diagonals = new Integer[2][3];
                event.getChannel().sendMessage("Play a move @"+challenger+". Enter the column that you want to play in.").queue();
                return;
            }
        }
        if(messageSent.equalsIgnoreCase("t!fcol1")){
            inputC=1;
            event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!fcol2")){
            inputC=2;
            event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!fcol3")){
            inputC=3;
            event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!fcol4")){
            inputC=4;
            event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!frow1")){
            if(inputC==0){
                return;
            }
            inputR=1;
            if(frows[inputR-1][inputC-1]==0) {
                fInput();
            }else{
                event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
            }
            return;
        }
        if(messageSent.equalsIgnoreCase("t!frow2")){
            if(inputC==0){
                return;
            }
            inputR=2;
            if(frows[inputR-1][inputC-1]==0) {
                fInput();
            }else{
                event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
            }
            return;
        }
        if(messageSent.equalsIgnoreCase("t!frow3")){
            if(inputC==0){
                return;
            }
            inputR=3;
            if(frows[inputR-1][inputC-1]==0) {
                fInput();
            }else{
                event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
            }
            return;
        }
        if(messageSent.equalsIgnoreCase("t!frow4")){
            if(inputC==0){
                return;
            }
            inputR=4;
            if(frows[inputR-1][inputC-1]==0) {
                fInput();
            }else{
                event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
            }
            return;
        }
        if(!event.getMember().getUser().isBot()) {
            if (messageSent.contains("t!fchallenge")) {
                frows =new Integer[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
                fprocess();
                player =1;
                StringTokenizer st = new StringTokenizer(messageSent);
                st.nextToken();
                challenger=event.getMember().getEffectiveName();
                name1=challenger;
                try {
                    challenged = st.nextToken();
                    name2 = event.getMessage().getMentionedMembers().get(0).getEffectiveName();
                }catch(Exception e) {
                }
                if(!frank.containsKey(name1)){
                    frank.put(name1,0.0);
                }
                if(!frank.containsKey(name2)){
                    frank.put(name2,0.0);
                }
                event.getChannel().sendMessage(challenger+" challenges " +challenged+" to a game of Tic Tac Toe!").queue();
                event.getChannel().sendMessage("Play a move @"+challenger+". Enter the column that you want to play in.").queue();
                return;
            }
        }
        if(messageSent.equalsIgnoreCase("t!pcol1")){
            inputC=1;
            event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!pcol2")){
            inputC=2;
            event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!pcol3")){
            inputC=3;
            event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!prow1")){
            if(inputC==0){
                return;
            }
            inputR=1;
            if(rows[inputR-1][inputC-1]==0) {
                specialInput();
            }else{
                event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
            }
            return;
        }
        if(messageSent.equalsIgnoreCase("t!prow2")){
            if(inputC==0){
                return;
            }
            inputR=2;
            if(rows[inputR-1][inputC-1]==0) {
                specialInput();
            }else{
                event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
            }
            return;
        }
        if(messageSent.equalsIgnoreCase("t!prow3")){
            if(inputC==0){
                return;
            }
            inputR=3;
            if(rows[inputR-1][inputC-1]==0) {
                specialInput();
            }else{
                event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
            }
            return;
        }
        if(messageSent.equalsIgnoreCase("t!help")){
            event.getChannel().sendMessage("This is page 1 of help").queue();
            event.getChannel().sendMessage("t!start starts the game").queue();
            event.getChannel().sendMessage("to select difficulty when prompted use t!easy, t!medium, t!hard, or t!impossible").queue();
            event.getChannel().sendMessage("to respond to yes/no write t!yes or t!no").queue();
            event.getChannel().sendMessage("to say what column you want to play in, write t!rcol1, t!rcol2, or t!rcol3").queue();
            event.getChannel().sendMessage("to say what row you want to play in, write t!rrow1, t!rrow2, or t!rrow3").queue();
            event.getChannel().sendMessage("t!help2 will bring you to the second page of help documentation.").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!help2")){
            event.getChannel().sendMessage("This is page 2 of help").queue();
            event.getChannel().sendMessage("To play against another person, use t!challenge <@person>.").queue();
            event.getChannel().sendMessage("Then, during the game, both players use t!pcol1, t!pcol2, t!pcol3 for columns").queue();
            event.getChannel().sendMessage(", and t!prow1, t!prow2, t!prow3 for the rows").queue();
            event.getChannel().sendMessage("A win will be counted as 1 point, a loss as 0, and a draw as 0.5").queue();
            event.getChannel().sendMessage("To see a sorted list of the top 5 users(and their scores), use t!rank").queue();
            event.getChannel().sendMessage("t!help3 will bring you to the third page of help documentation").queue();
            return;
        }
        if(messageSent.equalsIgnoreCase("t!help3")){
            event.getChannel().sendMessage("This is page 3 of help").queue();
            event.getChannel().sendMessage("This page concerns the newest variant of Tic Tac Toe added to this server. 4x4 mode. ").queue();
            event.getChannel().sendMessage("This mode is only available for playing against other humans.").queue();
            event.getChannel().sendMessage("With 4x4 Tic Tac Toe, there are a few more win conditions than standard Tic Tac Toe to keep the game interesting.").queue();
            event.getChannel().sendMessage("As usual, we have the diagonal:").queue();
            fprint(exsdiag);
            event.getChannel().sendMessage("The row:").queue();
            fprint(exrow);
            event.getChannel().sendMessage("And the column:").queue();
            fprint(excol);
            event.getChannel().sendMessage("But unlike regular Tic Tac Toe, we have 3 new win conditions.").queue();
            event.getChannel().sendMessage("The Square:").queue();
            fprint(exsquare);
            event.getChannel().sendMessage("The Cross").queue();
            fprint(excross);
            event.getChannel().sendMessage("And the Rhombus:").queue();
            fprint(explus);
            event.getChannel().sendMessage("As with regular Tic Tac Toe, these patterns can occur anywhere on the board, and will result in a win on completion.").queue();
            event.getChannel().sendMessage("To play against another person, use t!fchallenge <@person>.").queue();
            event.getChannel().sendMessage("Then, during the game, both players use t!fcol1, t!fcol2, t!fcol3 for columns").queue();
            event.getChannel().sendMessage(", and t!frow1, t!frow2, t!frow3 for the rows").queue();
            event.getChannel().sendMessage("To check the top five players, use t!frank").queue();
            event.getChannel().sendMessage("Enjoy!").queue();
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
            if(messageSent.equalsIgnoreCase("t!rcol1")){
                inputC=1;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if(messageSent.equalsIgnoreCase("t!rcol2")){
                inputC=2;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if(messageSent.equalsIgnoreCase("t!rcol3")){
                inputC=3;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if(messageSent.equalsIgnoreCase("t!rrow1")){
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
            if(messageSent.equalsIgnoreCase("t!rrow2")){
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
            if(messageSent.equalsIgnoreCase("t!rrow3")){
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
    public static void fprint(Integer[][] boardState){
        for(Integer[] row : boardState){
            d.getChannel().sendMessage(" "+ convert(row[0])+" |"+" "+ convert(row[1])+" |"+" "+ convert(row[2])+" |"+" "+ convert(row[3])).queue();
        }


    }
    public static void fprocess(){
        fcolumns= new Integer[][]{{frows[0][0], frows[1][0], frows[2][0],frows[3][0]}, {frows[0][1], frows[1][1], frows[2][1],frows[3][1]}, {frows[0][2], frows[1][2], frows[2][2],frows[3][2]}, {frows[0][3], frows[1][3], frows[2][3],frows[3][3]}};
        fdiagonals = new Integer[][]{{frows[0][0], frows[1][1],frows[2][2],frows[3][3]},{frows[0][3],frows[1][2], frows[2][1], frows[3][0]}};
        squares=new Integer[][]{{frows[0][0],frows[0][1],frows[1][0],frows[1][1]},{frows[0][1],frows[0][2],frows[1][1],frows[1][2]},{frows[0][2],frows[0][3],frows[1][2],frows[1][3]},{frows[1][0],frows[1][1],frows[2][0],frows[2][1]},{frows[1][1],frows[1][2],frows[2][1],frows[2][2]},{frows[1][2],frows[1][3],frows[2][2],frows[2][3]},{frows[2][0],frows[2][1],frows[3][0],frows[3][1]},{frows[2][1],frows[2][2],frows[3][1],frows[3][2]},{frows[2][2],frows[2][3],frows[3][2],frows[3][3]}};
        plusses = new Integer[][]{{frows[0][1],frows[1][0],frows[2][1],frows[1][2]},{frows[0][2],frows[1][1],frows[2][2],frows[1][3]},{frows[1][1],frows[2][0],frows[3][1],frows[2][2]},{frows[1][2],frows[2][1],frows[3][2],frows[2][3]}};
        crosses = new Integer[][]{{frows[0][0],frows[1][1],frows[2][2],frows[0][2],frows[2][0]},{frows[0][1],frows[1][2],frows[2][3],frows[0][3],frows[2][1]},{frows[1][0],frows[2][1],frows[3][2],frows[1][2],frows[3][0]},{frows[1][1],frows[2][2],frows[3][3],frows[1][3],frows[3][1]}};
    }
    public static void fInput(){
        fprocess();
        int inputColumn;
        inputColumn = inputC;
        int inputRow;
        inputRow = inputR;
        recent=new Integer[]{inputRow,inputColumn};
        frows[inputRow - 1][inputColumn - 1] = player;
        if(player==1){
            player=-1;
        }
        else if(player==-1){
            player=1;
        }
        inputC=0;
        inputR=0;
        fprocess();
        fprint(frows);
        if(fevaluate()){
            return;
        }
        if(player==1) {
            d.getChannel().sendMessage("@"+challenger+"'s Move. Enter the column that you want to play in.").queue();
        }
        if(player==-1) {
            d.getChannel().sendMessage(challenged+"'s Move. Enter the column that you want to play in.").queue();
        }
    }
    public static boolean fevaluate(){
        fprocess();

        for(Integer[] row : frows){
            if(sum(row) == -4){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)+1.0);
                frank.put(name1,frank.get(name1)-1.0);
                return true;

            }
            if(sum(row) == 4){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)-1.0);
                frank.put(name1,frank.get(name1)+1.0);
                return true;
            }
        }
        for(Integer[] column : fcolumns){
            if(sum(column) == -4){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)+1.0);
                frank.put(name1,frank.get(name1)-1.0);
                return true;
            }
            if(sum(column) == 4){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)-1.0);
                frank.put(name1,frank.get(name1)+1.0);
                return true;
            }
        }
        for(Integer[] diagonal : fdiagonals){
            if(sum(diagonal) == -4){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)+1.0);
                frank.put(name1,frank.get(name1)-1.0);
                return true;
            }
            if(sum(diagonal) == 4){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)-1.0);
                frank.put(name1,frank.get(name1)+1.0);
                return true;
            }
        }
        for(Integer[] cross : crosses){
            if(sum(cross) == -5){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)+1.0);
                frank.put(name1,frank.get(name1)-1.0);
                return true;
            }
            if(sum(cross) == 5){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)-1.0);
                frank.put(name1,frank.get(name1)+1.0);
                return true;
            }
        }
        for(Integer[] plus : plusses){
            if(sum(plus) == -4){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)+1.0);
                frank.put(name1,frank.get(name1)-1.0);
                return true;
            }
            if(sum(plus) == 4){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)-1.0);
                frank.put(name1,frank.get(name1)+1.0);
                return true;
            }
        }
        for(Integer[] square : squares){
            if(sum(square) == -4){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)+1.0);
                frank.put(name1,frank.get(name1)-1.0);
                return true;
            }
            if(sum(square) == 4){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                frank.put(name2,frank.get(name2)-1.0);
                frank.put(name1,frank.get(name1)+1.0);
                return true;
            }
        }

        int filled = 0;
        for(Integer[] arr : frows){
            for(int point : arr){
                if(point != 0){
                    filled++;
                }

            }
        }
        if(filled == 16){
            d.getChannel().sendMessage("Draw! Good Game.").queue();
            frank.put(name2,frank.get(name2)+0.0);
            frank.put(name1,frank.get(name1)+0.0);
            return true;
        }
        return false;
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
    public static void specialInput(){
        process();
        int inputColumn;
        inputColumn = inputC;
        int inputRow;
        inputRow = inputR;
        recent=new Integer[]{inputRow,inputColumn};
        rows[inputRow - 1][inputColumn - 1] = player;
        if(player==1){
            player=-1;
        }
        else if(player==-1){
            player=1;
        }
        inputC=0;
        inputR=0;
        process();
        printArr(rows);
        if(Specialevaluate()){
            return;
        }
        if(player==1) {
            d.getChannel().sendMessage("@"+challenger+"'s Move. Enter the column that you want to play in.").queue();
        }
        if(player==-1) {
            d.getChannel().sendMessage(challenged+"'s Move. Enter the column that you want to play in.").queue();
        }
    }
    public static boolean Specialevaluate(){
        process();

        for(Integer[] row : rows){
            if(sum(row) == -3){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();

                rank.put(name2,rank.get(name2)+1.0);
                rank.put(name1,rank.get(name1)-1.0);
                return true;

            }
            if(sum(row) == 3){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                rank.put(name2,rank.get(name2)-1.0);
                rank.put(name1,rank.get(name1)+1.0);
                return true;
            }
        }
        for(Integer[] column : columns){
            if(sum(column) == -3){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                rank.put(name2,rank.get(name2)+1.0);
                rank.put(name1,rank.get(name1)-1.0);
                return true;
            }
            if(sum(column) == 3){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                rank.put(name2,rank.get(name2)-1.0);
                rank.put(name1,rank.get(name1)+1.0);
                return true;
            }
        }
        for(Integer[] diagonal : diagonals){
            if(sum(diagonal) == -3){
                d.getChannel().sendMessage(challenged+" Wins! Good Game.").queue();
                rank.put(name2,rank.get(name2)+1.0);
                rank.put(name1,rank.get(name1)-1.0);
                return true;
            }
            if(sum(diagonal) == 3){
                d.getChannel().sendMessage("@"+challenger+" Wins! Good Game.").queue();
                rank.put(name2,rank.get(name2)-1.0);
                rank.put(name1,rank.get(name1)+1.0);
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
            rank.put(name2,rank.get(name2)+0.0);
            rank.put(name1,rank.get(name1)+0.0);
            return true;
        }
        return false;
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
        int g=0;
        for(Integer i: line){
            g+=i;
        }
        return g;
    }
    }

class ValueComparator implements Comparator<String> {

    private Map<String, Double> map;

    public ValueComparator(Map<String, Double> map) {
        this.map = map;
    }

    public int compare(String a, String b) {
        return map.get(a).compareTo(map.get(b))*(0-1);
    }
}
