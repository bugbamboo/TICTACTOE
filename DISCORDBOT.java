import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import yahoofinance.YahooFinance;

import java.io.*;
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
        static int player = 1;
        static String challenger;
        static String challenged;
        static String name1;
        static String name2;
        public static Integer[][] exsdiag = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        public static Integer[][] exrow = {{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        public static Integer[][] excol = {{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        public static Integer[][] exsquare = {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        public static Integer[][] excross = {{1, 0, 1, 0}, {0, 1, 0, 0}, {1, 0, 1, 0}, {0, 0, 0, 0}};
        public static Integer[][] explus = {{0, 1, 0, 0}, {1, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        public static Integer[][] rows = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        public static Integer[][] columns = new Integer[3][3];
        public static Integer[][] diagonals = new Integer[2][3];
        public static Integer[] recent = new Integer[2];
        public static int randomChance = 0;
        public static Random errorChance = new Random();
        public static HashSet<String> trick = new HashSet<String>() {
            {
                add(Arrays.deepToString(new Integer[][]{{1, 0, 0}, {0, -1, 0}, {0, 0, 1}}));
                add(Arrays.deepToString(new Integer[][]{{0, 0, 1}, {0, -1, 0}, {1, 0, 0}}));
            }
        };
        public static Map<String, Integer[]> twoFer = new HashMap<String, Integer[]>() {
            {
                put(Arrays.deepToString(new Integer[][]{{1, 0, 0}, {0, -1, 1}, {0, 0, -1}}), new Integer[]{2, 1});
                put(Arrays.deepToString(new Integer[][]{{0, 0, 1}, {0, -1, 0}, {-1, 1, 0}}), new Integer[]{1, 0});
                put(Arrays.deepToString(new Integer[][]{{-1, 0, 0}, {1, -1, 0}, {0, 0, 1}}), new Integer[]{0, 1});
                put(Arrays.deepToString(new Integer[][]{{0, 1, -1}, {0, -1, 0}, {1, 0, 0}}), new Integer[]{1, 2});
                put(Arrays.deepToString(new Integer[][]{{0, 0, 1}, {1, -1, 0}, {-1, 0, 0}}), new Integer[]{2, 1});
                put(Arrays.deepToString(new Integer[][]{{-1, 1, 0}, {0, -1, 0}, {0, 0, 1}}), new Integer[]{1, 0});
                put(Arrays.deepToString(new Integer[][]{{0, 0, -1}, {0, -1, 1}, {1, 0, 0}}), new Integer[]{0, 1});
                put(Arrays.deepToString(new Integer[][]{{1, 0, 0}, {0, -1, 0}, {0, 1, -1}}), new Integer[]{1, 2});
            }
        };
        public static HashMap<String, Integer[]> corners = new HashMap<String, Integer[]>() {
            {
                put(Arrays.toString(new Integer[]{0, 0}), new Integer[]{2, 2});
                put(Arrays.toString(new Integer[]{2, 2}), new Integer[]{0, 0});
                put(Arrays.toString(new Integer[]{0, 2}), new Integer[]{2, 0});
                put(Arrays.toString(new Integer[]{2, 0}), new Integer[]{0, 2});
            }
        };
        static int accepted;
        static Member buyer;
        static Member seller;
        static int item;
        static Double price;
        public static Integer[][] frows = new Integer[4][4];
        public static Integer[][] fcolumns = new Integer[4][4];
        public static Integer[][] fdiagonals = new Integer[2][4];
        public static Integer[][] conrows = new Integer[7][7];
        public static Integer[][] concolumns = new Integer[7][7];
        public static Integer[][] condiagonals = new Integer[32][4];
        public static Integer[][] crosses = new Integer[4][5];
        public static Integer[][] squares = new Integer[9][4];
        public static Integer[][] plusses = new Integer[4][4];
        static TreeMap<String, Double> sorted;
        public static HashMap<String, Double> bank = new HashMap<String, Double>();
        public static HashMap<String, Double> shop = new HashMap<String, Double>() {
        };

        public static HashMap<String, Double> rank = new HashMap<String, Double>();
        public static HashMap<String, Double> frank = new HashMap<String, Double>();
        static HashMap<String, Double> crank = new HashMap<String, Double>();
        public static HashMap<String, Integer[]> amount = new HashMap<String, Integer[]>();
        public double getStock1 () throws IOException {
            return YahooFinance.get("AUY").getQuote().getPrice().doubleValue();
        }
        public double getStock2 () throws IOException {
            return YahooFinance.get("VTI").getQuote().getPrice().doubleValue();
        }
        public double getStock3 () throws IOException {
            return YahooFinance.get("BTC-USD").getQuote().getPrice().doubleValue();
        }
        public double getStock4 () throws IOException {
            return YahooFinance.get("NTDOY").getQuote().getPrice().doubleValue();
        }
        public double getStock5 () throws IOException {
            return YahooFinance.get("MRNA").getQuote().getPrice().doubleValue();
        }
        public double getStock6 () throws IOException {
            return YahooFinance.get("005930.KS").getQuote().getPrice().doubleValue();
        }
        public double getStock7 () throws IOException {
            return YahooFinance.get("YELP").getQuote().getPrice().doubleValue();
        }
        public double getStock8 () throws IOException {
            return YahooFinance.get("DAL").getQuote().getPrice().doubleValue();
        }
        public double getStock9 () throws IOException {
            return YahooFinance.get("NVS").getQuote().getPrice().doubleValue();
        }
        public double getStock10 () throws IOException {
            return YahooFinance.get("DIS").getQuote().getPrice().doubleValue();
        }
        public String shopconvert (Integer x){
            if (x == 0) {
                return "gold";
            }
            if (x == 1) {
                return "vanguard";
            }
            if (x == 2) {
                return "bitcoin";
            }
            if (x == 3) {
                return "nintendo";
            }
            if (x == 4) {
                return "moderna";
            }
            if (x == 5) {
                return "samsung";
            }
            if (x == 6) {
                return "yelp";
            }
            if (x == 7) {
                return "delta";
            }
            if (x == 8) {
                return "novartis";
            }
            if (x == 9) {
                return "disney";
            }
            return "What";
        }
        public int shopconvert (String st){
            if (st.equals("theodore_gold")) {
                return 0;
            }
            if (st.equals("theodore_vanguard")) {
                return 1;
            }
            if (st.equals("theodore_bitcoin")) {
                return 2;
            }
            if (st.equals("theodore_nintendo")) {
                return 3;
            }
            if (st.equals("theodore_moderna")) {
                return 4;
            }
            if (st.equals("theodore_samsung")) {
                return 5;
            }
            if (st.equals("theodore_yelp")) {
                return 6;
            }
            if (st.equals("theodore_delta")) {
                return 7;
            }
            if (st.equals("theodore_novartis")) {
                return 8;
            }
            if (st.equals("theodore_disney")) {
                return 9;
            }
            return 24;
        }
        public void onGuildMessageReceived (GuildMessageReceivedEvent event){
            try {
                shop.put("theodore_gold", Math.round(getStock1() * 100000.0) / 1000.0);
                shop.put("theodore_vanguard", Math.round(getStock2() * 1000.0) / 1000.0);
                shop.put("theodore_bitcoin", Math.round(getStock3() * 100.0) / 1000.0);
                shop.put("theodore_nintendo", Math.round(getStock4() * 10000.0) / 1000.0);
                shop.put("theodore_moderna", Math.round(getStock5() * 10000.0) / 1000.0);
                shop.put("theodore_samsung", Math.round(getStock6() * 10.0) / 1000.0);
                shop.put("theodore_yelp", Math.round(getStock7() * 10000.0) / 1000.0);
                shop.put("theodore_delta", Math.round(getStock8() * 10000.0) / 1000.0);
                shop.put("theodore_novartis", Math.round(getStock9() * 10000.0) / 1000.0);
                shop.put("theodore_disney", Math.round(getStock10() * 4000.0) / 1000.0);

            } catch (Exception e) {
            }

            Message m = event.getMessage();
            String messageSent = m.getContentRaw();
            d = event;
            if(!event.getAuthor().isBot()){
                if(messageSent.contains("t!flip")){
                    Random random = new Random();
                    if(messageSent.equalsIgnoreCase("t!flip")){
                        if(random.nextBoolean()){
                            event.getChannel().sendMessage("You flipped Heads! You Win!").queue();
                            return;
                        }else{
                            event.getChannel().sendMessage("You flipped Tails! You Lose!").queue();
                            return;
                        }


                    }else{
                        StringTokenizer st = new StringTokenizer(messageSent);
                        st.nextToken();
                        int bet = Integer.parseInt(st.nextToken());
                        if(!bank.containsKey(event.getMember().getEffectiveName())|| bank.get(event.getMember().getEffectiveName())<bet){
                            event.getChannel().sendMessage("You cannot afford this bet!").queue();
                            return;
                        }
                        if(random.nextBoolean()){
                            event.getChannel().sendMessage("You flipped Heads! You Win "+bet+" Toastcoin!").queue();
                            bank.put(event.getMember().getEffectiveName(),bank.get(event.getMember().getEffectiveName())+bet);
                            return;
                        }else{
                            event.getChannel().sendMessage("You flipped Tails! You Lose "+bet+" Toastcoin!").queue();
                            bank.put(event.getMember().getEffectiveName(),bank.get(event.getMember().getEffectiveName())-bet);
                            return;
                        }
                    }
                }
            }
            if (messageSent.equalsIgnoreCase("t!deny")) {
                if (accepted == 1) {
                    if (event.getMember().equals(buyer)) {
                        accepted = 0;
                        return;
                    } else {
                        event.getChannel().sendMessage("Wrong User!").queue();
                    }
                }
            }
            if (messageSent.equalsIgnoreCase("t!accept")) {
                if (accepted == 1) {
                    if (event.getMember().equals(buyer)) {
                        if (bank.get(buyer.getEffectiveName()) < price) {
                            event.getChannel().sendMessage("You cannot afford this item!").queue();
                        } else {
                            bank.put(buyer.getEffectiveName(), bank.get(buyer.getEffectiveName()) - price);
                            bank.put(seller.getEffectiveName(), bank.get(seller.getEffectiveName()) + price);
                            Integer[] temp = amount.get(buyer.getEffectiveName());
                            Integer[] temp2 = amount.get(seller.getEffectiveName());
                            temp[item] = temp[item] + 1;
                            temp2[item] = temp2[item] - 1;
                            amount.put(buyer.getEffectiveName(), temp);
                            amount.put(seller.getEffectiveName(), temp2);
                        }
                        Role gold = event.getGuild().getRolesByName("gold", true).get(0);
                        Role vanguard = event.getGuild().getRolesByName("vanguard", true).get(0);
                        Role bitcoin = event.getGuild().getRolesByName("bitcoin", true).get(0);
                        Role nintendo = event.getGuild().getRolesByName("nintendo", true).get(0);
                        Role moderna = event.getGuild().getRolesByName("moderna", true).get(0);
                        Role samsung = event.getGuild().getRolesByName("samsung", true).get(0);
                        Role yelp = event.getGuild().getRolesByName("yelp", true).get(0);
                        Role delta = event.getGuild().getRolesByName("delta", true).get(0);
                        Role novartis = event.getGuild().getRolesByName("novartis", true).get(0);
                        Role disney = event.getGuild().getRolesByName("disney", true).get(0);
                        for (Member member : event.getGuild().getMembers()) {
                            if (amount.containsKey(member.getEffectiveName())) {
                                event.getGuild().removeRoleFromMember(member, gold).complete();
                                event.getGuild().removeRoleFromMember(member, vanguard).complete();
                                event.getGuild().removeRoleFromMember(member, bitcoin).complete();
                                event.getGuild().removeRoleFromMember(member, nintendo).complete();
                                event.getGuild().removeRoleFromMember(member, moderna).complete();
                                event.getGuild().removeRoleFromMember(member, samsung).complete();
                                event.getGuild().removeRoleFromMember(member, yelp).complete();
                                event.getGuild().removeRoleFromMember(member, delta).complete();
                                event.getGuild().removeRoleFromMember(member, novartis).complete();
                                event.getGuild().removeRoleFromMember(member, disney).complete();
                                Integer[] temp = amount.get(member.getEffectiveName());
                                for (int i = 0; i < 10; i++) {
                                    if (temp[i] > 0) {
                                        event.getGuild().addRoleToMember(member, event.getGuild().getRolesByName(shopconvert(i), true).get(0)).complete();
                                    }
                                }
                            }
                        }
                        HashSet<Role> hgold = new HashSet<Role>();
                        HashSet<Role> hvanguard = new HashSet<Role>();
                        HashSet<Role> hbitcoin = new HashSet<Role>();
                        HashSet<Role> hnintendo = new HashSet<Role>();
                        HashSet<Role> hmoderna = new HashSet<Role>();
                        HashSet<Role> hsamsung = new HashSet<Role>();
                        HashSet<Role> hyelp = new HashSet<Role>();
                        HashSet<Role> hdelta = new HashSet<Role>();
                        HashSet<Role> hnovartis = new HashSet<Role>();
                        HashSet<Role> hdisney = new HashSet<Role>();
                        hgold.add(gold);
                        hvanguard.add(vanguard);
                        hbitcoin.add(bitcoin);
                        hnintendo.add(nintendo);
                        hmoderna.add(moderna);
                        hsamsung.add(samsung);
                        hyelp.add(yelp);
                        hdelta.add(delta);
                        hnovartis.add(novartis);
                        hdisney.add(disney);


                        List<Emote> x = event.getGuild().getEmotes();
                        for (Emote n : x) {

                            if (n.getName().equalsIgnoreCase("theodore_gold")) {
                                n.getManager().setName(n.getName()).setRoles(hgold).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_vanguard")) {
                                n.getManager().setName(n.getName()).setRoles(hvanguard).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_bitcoin")) {
                                n.getManager().setName(n.getName()).setRoles(hbitcoin).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_nintendo")) {
                                n.getManager().setName(n.getName()).setRoles(hnintendo).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_moderna")) {
                                n.getManager().setName(n.getName()).setRoles(hmoderna).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_samsung")) {
                                n.getManager().setName(n.getName()).setRoles(hsamsung).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_yelp")) {
                                n.getManager().setName(n.getName()).setRoles(hyelp).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_delta")) {
                                n.getManager().setName(n.getName()).setRoles(hdelta).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_novartis")) {
                                n.getManager().setName(n.getName()).setRoles(hnovartis).queue();
                                continue;
                            }
                            if (n.getName().equalsIgnoreCase("theodore_disney")) {
                                n.getManager().setName(n.getName()).setRoles(hdisney).queue();
                            }

                        }
                        event.getChannel().sendMessage("Sale Complete!").queue();
                        accepted = 0;
                        return;
                    } else {
                        event.getChannel().sendMessage("Wrong User!").queue();
                        accepted = 0;
                        return;
                    }
                }

            }
            if (!event.getAuthor().isBot()) {
                accepted = 0;
            }
            if (messageSent.equalsIgnoreCase("t!stock")) {
                if (amount.isEmpty()) {
                    try {
                        BufferedReader f = new BufferedReader(new FileReader("amount.txt"));
                        int i = Integer.parseInt(f.readLine());
                        for (int d = 0; d < i; d++) {
                            StringTokenizer st = new StringTokenizer(f.readLine());
                            String name = st.nextToken();
                            Integer[] temp = new Integer[10];
                            for (int s = 0; s < 10; s++) {
                                temp[s] = Integer.parseInt(st.nextToken());
                            }
                            amount.put(name, temp);
                        }
                    } catch (Exception e) {
                    }
                }
                if (bank.isEmpty()) {
                    try {
                        BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                        int i = Integer.parseInt(f.readLine());
                        for (int d = 0; d < i; d++) {
                            StringTokenizer st = new StringTokenizer(f.readLine());
                            bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                        }
                    } catch (Exception e) {
                    }
                }
                if (!amount.containsKey(event.getMember().getEffectiveName())) {
                    amount.put(event.getMember().getEffectiveName(), new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                }
                Integer[] stocks = amount.get(event.getMember().getEffectiveName());
                for (int i = 0; i < 10; i++) {
                    event.getChannel().sendMessage(shopconvert(i) + " Amount: " + stocks[i]).queue();
                }
                event.getChannel().sendMessage("This is your current Portfolio").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!shop")) {
                if (amount.isEmpty()) {
                    try {
                        BufferedReader f = new BufferedReader(new FileReader("amount.txt"));
                        int i = Integer.parseInt(f.readLine());
                        for (int d = 0; d < i; d++) {
                            StringTokenizer st = new StringTokenizer(f.readLine());
                            String name = st.nextToken();
                            Integer[] temp = new Integer[10];
                            for (int s = 0; s < 10; s++) {
                                temp[s] = Integer.parseInt(st.nextToken());
                            }
                            amount.put(name, temp);
                        }
                    } catch (Exception e) {
                    }
                }
                if (bank.isEmpty()) {
                    try {
                        BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                        int i = Integer.parseInt(f.readLine());
                        for (int d = 0; d < i; d++) {
                            StringTokenizer st = new StringTokenizer(f.readLine());
                            bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                        }
                    } catch (Exception e) {
                    }
                }
                event.getChannel().sendMessage("Welcome to the shop. Use t!buy <item name> to purchase items.").queue();
                for (String x : shop.keySet()) {
                    event.getChannel().sendMessage("You can buy " + x + " for " + shop.get(x) + " toastcoin.").queue();
                }
                event.getChannel().sendMessage("These are the contents of the shop").queue();
            }
            if (!event.getAuthor().isBot()) {
                if (messageSent.contains("t!sell")) {
                    if (amount.isEmpty()) {
                        try {
                            BufferedReader f = new BufferedReader(new FileReader("amount.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                String name = st.nextToken();
                                Integer[] temp = new Integer[10];
                                for (int s = 0; s < 10; s++) {
                                    temp[s] = Integer.parseInt(st.nextToken());
                                }
                                amount.put(name, temp);
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (bank.isEmpty()) {
                        try {
                            BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    try {
                        buyer = event.getMessage().getMentionedMembers().get(0);
                    } catch (Exception e) {
                        return;
                    }

                    seller = event.getMember();
                    StringTokenizer st = new StringTokenizer(messageSent);
                    st.nextToken();
                    st.nextToken();
                    String item1 = st.nextToken();
                    item = shopconvert(item1);
                    if (item == 24) {
                        event.getChannel().sendMessage("Invalid Item").queue();
                        return;
                    }
                    price = Double.parseDouble(st.nextToken());
                    if (price < 0.0) {
                        event.getChannel().sendMessage("You cannot sell items for less that 0 toastcoin!").queue();
                        return;
                    }
                    if (!amount.containsKey(event.getMember().getEffectiveName())) {
                        amount.put(event.getMember().getEffectiveName(), new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                        event.getChannel().sendMessage("You cannot sell items that you don't have!").queue();
                        return;
                    }
                    if (!bank.containsKey(buyer.getEffectiveName())) {
                        bank.put(buyer.getEffectiveName(), 0.0);
                    }
                    if (!bank.containsKey(event.getMember().getEffectiveName())) {
                        bank.put(event.getMember().getEffectiveName(), 0.0);
                    }
                    if (!amount.containsKey(buyer.getEffectiveName())) {
                        amount.put(buyer.getEffectiveName(), new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                    }
                    if ((amount.get(seller.getEffectiveName()))[item] > 0) {
                        accepted = 1;
                        event.getChannel().sendMessage("Waiting on " + buyer.getEffectiveName() + " to respond. Use t!accept or t!deny").queue();
                        return;
                    }


                }
            }
            if (!event.getAuthor().isBot()) {
                if (messageSent.contains("t!buy")) {
                    if (amount.isEmpty()) {
                        try {
                            BufferedReader f = new BufferedReader(new FileReader("amount.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                String name = st.nextToken();
                                Integer[] temp = new Integer[10];
                                for (int s = 0; s < 10; s++) {
                                    temp[s] = Integer.parseInt(st.nextToken());
                                }
                                amount.put(name, temp);
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (bank.isEmpty()) {
                        try {
                            BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    StringTokenizer st = new StringTokenizer(messageSent);
                    st.nextToken();
                    String item;
                    if (st.hasMoreTokens()) {
                        item = st.nextToken();
                        if (shop.containsKey(item)) {
                            if (bank.containsKey(event.getMember().getEffectiveName()) && shop.get(item) < bank.get(event.getMember().getEffectiveName())) {
                                bank.put(event.getMember().getEffectiveName(), bank.get(event.getMember().getEffectiveName()) - shop.get(item));
                                if (!amount.containsKey(event.getMember().getEffectiveName())) {
                                    amount.put(event.getMember().getEffectiveName(), new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                                }
                                Integer[] temp = amount.get(event.getMember().getEffectiveName());
                                temp[shopconvert(item)] = temp[shopconvert(item)] + 1;
                                amount.put(event.getMember().getEffectiveName(), temp);

                            } else {
                                event.getChannel().sendMessage("You cannot afford this item.").queue();
                            }
                        } else {
                            event.getChannel().sendMessage("Invalid Item!").queue();
                        }
                    } else {
                        event.getChannel().sendMessage("Invalid Item!").queue();
                    }
                    Role gold = event.getGuild().getRolesByName("gold", true).get(0);
                    Role vanguard = event.getGuild().getRolesByName("vanguard", true).get(0);
                    Role bitcoin = event.getGuild().getRolesByName("bitcoin", true).get(0);
                    Role nintendo = event.getGuild().getRolesByName("nintendo", true).get(0);
                    Role moderna = event.getGuild().getRolesByName("moderna", true).get(0);
                    Role samsung = event.getGuild().getRolesByName("samsung", true).get(0);
                    Role yelp = event.getGuild().getRolesByName("yelp", true).get(0);
                    Role delta = event.getGuild().getRolesByName("delta", true).get(0);
                    Role novartis = event.getGuild().getRolesByName("novartis", true).get(0);
                    Role disney = event.getGuild().getRolesByName("disney", true).get(0);
                    for (Member member : event.getGuild().getMembers()) {
                        if (amount.containsKey(member.getEffectiveName())) {
                            event.getGuild().removeRoleFromMember(member, gold).complete();
                            event.getGuild().removeRoleFromMember(member, vanguard).complete();
                            event.getGuild().removeRoleFromMember(member, bitcoin).complete();
                            event.getGuild().removeRoleFromMember(member, nintendo).complete();
                            event.getGuild().removeRoleFromMember(member, moderna).complete();
                            event.getGuild().removeRoleFromMember(member, samsung).complete();
                            event.getGuild().removeRoleFromMember(member, yelp).complete();
                            event.getGuild().removeRoleFromMember(member, delta).complete();
                            event.getGuild().removeRoleFromMember(member, novartis).complete();
                            event.getGuild().removeRoleFromMember(member, disney).complete();
                            Integer[] temp = amount.get(member.getEffectiveName());
                            for (int i = 0; i < 10; i++) {
                                if (temp[i] > 0) {
                                    event.getGuild().addRoleToMember(member, event.getGuild().getRolesByName(shopconvert(i), true).get(0)).complete();
                                }
                            }
                        }
                    }
                    HashSet<Role> hgold = new HashSet<Role>();
                    HashSet<Role> hvanguard = new HashSet<Role>();
                    HashSet<Role> hbitcoin = new HashSet<Role>();
                    HashSet<Role> hnintendo = new HashSet<Role>();
                    HashSet<Role> hmoderna = new HashSet<Role>();
                    HashSet<Role> hsamsung = new HashSet<Role>();
                    HashSet<Role> hyelp = new HashSet<Role>();
                    HashSet<Role> hdelta = new HashSet<Role>();
                    HashSet<Role> hnovartis = new HashSet<Role>();
                    HashSet<Role> hdisney = new HashSet<Role>();
                    hgold.add(gold);
                    hvanguard.add(vanguard);
                    hbitcoin.add(bitcoin);
                    hnintendo.add(nintendo);
                    hmoderna.add(moderna);
                    hsamsung.add(samsung);
                    hyelp.add(yelp);
                    hdelta.add(delta);
                    hnovartis.add(novartis);
                    hdisney.add(disney);
                    List<Emote> x = event.getGuild().getEmotes();
                    for (Emote n : x) {

                        if (n.getName().equalsIgnoreCase("theodore_gold")) {
                            n.getManager().setName(n.getName()).setRoles(hgold).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_vanguard")) {
                            n.getManager().setName(n.getName()).setRoles(hvanguard).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_bitcoin")) {
                            n.getManager().setName(n.getName()).setRoles(hbitcoin).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_nintendo")) {
                            n.getManager().setName(n.getName()).setRoles(hnintendo).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_moderna")) {
                            n.getManager().setName(n.getName()).setRoles(hmoderna).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_samsung")) {
                            n.getManager().setName(n.getName()).setRoles(hsamsung).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_yelp")) {
                            n.getManager().setName(n.getName()).setRoles(hyelp).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_delta")) {
                            n.getManager().setName(n.getName()).setRoles(hdelta).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_novartis")) {
                            n.getManager().setName(n.getName()).setRoles(hnovartis).queue();
                            continue;
                        }
                        if (n.getName().equalsIgnoreCase("theodore_disney")) {
                            n.getManager().setName(n.getName()).setRoles(hdisney).queue();
                        }

                    }
                    event.getChannel().sendMessage("Item Bought!").queue();
                    return;
                }
            }
            if (messageSent.equalsIgnoreCase("t!balance")) {
                if (bank.isEmpty()) {
                    try {
                        BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                        int i = Integer.parseInt(f.readLine());
                        for (int d = 0; d < i; d++) {
                            StringTokenizer st = new StringTokenizer(f.readLine());
                            bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                        }
                    } catch (Exception e) {
                    }
                }
                if (bank.containsKey(event.getMember().getEffectiveName())) {
                    event.getChannel().sendMessage("Your balance is " + bank.get(event.getMember().getEffectiveName()) + " Toastcoin").queue();
                } else {
                    bank.put(event.getMember().getEffectiveName(), 0.0);
                    event.getChannel().sendMessage("Your balance is 0 Toastcoin.").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!claim")) {
                Role rone = event.getGuild().getRolesByName("one", true).get(0);
                Role rtwo = event.getGuild().getRolesByName("two", true).get(0);
                Role rthree = event.getGuild().getRolesByName("three", true).get(0);
                Role rfour = event.getGuild().getRolesByName("four", true).get(0);
                Role rfive = event.getGuild().getRolesByName("five", true).get(0);
                HashSet<Role> two = new HashSet<Role>();
                HashSet<Role> three = new HashSet<Role>();
                HashSet<Role> four = new HashSet<Role>();
                HashSet<Role> five = new HashSet<Role>();
                two.add(rtwo);
                two.add(rthree);
                two.add(rfour);
                two.add(rfive);
                three.add(rthree);
                three.add(rfour);
                three.add(rfive);
                four.add(rfour);
                four.add(rfive);
                five.add(rfive);
                List<Emote> x = event.getGuild().getEmotes();
                for (Emote n : x) {

                    if (n.getName().equalsIgnoreCase("theodore_farmer") || n.getName().equalsIgnoreCase("theodore_blacksmith")) {
                        n.getManager().setName(n.getName()).setRoles(two).queue();
                        continue;
                    }
                    if (n.getName().equalsIgnoreCase("theodore_knight") || n.getName().equalsIgnoreCase("theodore_archer") || n.getName().equalsIgnoreCase("theodore_viking")) {
                        n.getManager().setName(n.getName()).setRoles(three).queue();
                        continue;
                    }
                    if (n.getName().equalsIgnoreCase("theodore_noble") || n.getName().equalsIgnoreCase("theodore_artist") || n.getName().equalsIgnoreCase("theodore_rich") || n.getName().equalsIgnoreCase("theodore_musician")) {
                        n.getManager().setName(n.getName()).setRoles(four).queue();
                        continue;
                    }
                    if (n.getName().equalsIgnoreCase("theodore_king") || n.getName().equalsIgnoreCase("theodore_princess") || n.getName().equalsIgnoreCase("theodore_jester") || n.getName().equalsIgnoreCase("theodore_queen") || n.getName().equalsIgnoreCase("theodore_prince")) {
                        n.getManager().setName(n.getName()).setRoles(five).queue();
                    }

                }

                for (Member member : event.getGuild().getMembers()) {
                    double count = 0.0;
                    if (rank.containsKey(member.getEffectiveName())) {
                        count += rank.get(member.getEffectiveName());
                    }
                    if (frank.containsKey(member.getEffectiveName())) {
                        count += frank.get(member.getEffectiveName());
                    }
                    if (crank.containsKey(member.getEffectiveName())) {
                        count += crank.get(member.getEffectiveName());
                    }

                    if (count >= 15.0 || member.getEffectiveName().equalsIgnoreCase("Achyuta") || member.getEffectiveName().equalsIgnoreCase("Debbie")) {
                        event.getGuild().addRoleToMember(member, rfive).queue();
                    } else if (count >= 10.0) {
                        event.getGuild().removeRoleFromMember(member, rfive).queue();
                        event.getGuild().addRoleToMember(member, rfour).queue();
                    } else if (count >= 6.0) {
                        event.getGuild().removeRoleFromMember(member, rfive).queue();
                        event.getGuild().removeRoleFromMember(member, rfour).queue();
                        event.getGuild().addRoleToMember(member, rthree).queue();
                    } else if (count >= 3.0) {
                        event.getGuild().removeRoleFromMember(member, rfive).queue();
                        event.getGuild().removeRoleFromMember(member, rfour).queue();
                        event.getGuild().removeRoleFromMember(member, rthree).queue();
                        event.getGuild().addRoleToMember(member, rtwo).queue();
                    } else {
                        event.getGuild().removeRoleFromMember(member, rfive).queue();
                        event.getGuild().removeRoleFromMember(member, rfour).queue();
                        event.getGuild().removeRoleFromMember(member, rthree).queue();
                        event.getGuild().removeRoleFromMember(member, rtwo).queue();
                        event.getGuild().addRoleToMember(member, rone).queue();
                    }
                }
                event.getChannel().sendMessage("Rewards Claimed!").queue();
                return;

            }
            if (messageSent.equalsIgnoreCase("t!safe_shutdown")) {
                if (event.getMember().getEffectiveName().equalsIgnoreCase("Achyuta")) {
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rank.txt")));
                        PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("frank.txt")));
                        PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter("bank.txt")));
                        PrintWriter out4 = new PrintWriter(new BufferedWriter(new FileWriter("amount.txt")));
                        PrintWriter out5 = new PrintWriter(new BufferedWriter(new FileWriter("crank.txt")));
                        out.println(rank.size());
                        out2.println(frank.size());
                        out3.println(bank.size());
                        out4.println(amount.size());
                        out5.println(crank.size());
                        for (Member h : event.getGuild().getMembers()) {
                            if (rank.containsKey(h.getEffectiveName())) {
                                out.println(h.getEffectiveName() + " " + rank.get(h.getEffectiveName()));
                            }
                            if (frank.containsKey(h.getEffectiveName())) {
                                out2.println(h.getEffectiveName() + " " + frank.get(h.getEffectiveName()));
                            }
                            if (bank.containsKey(h.getEffectiveName())) {
                                out3.println(h.getEffectiveName() + " " + bank.get(h.getEffectiveName()));
                            }
                            if (amount.containsKey(h.getEffectiveName())) {
                                out4.println(h.getEffectiveName() + " " + amount.get(h.getEffectiveName())[0] + " " + amount.get(h.getEffectiveName())[1] + " " + amount.get(h.getEffectiveName())[2] + " " + amount.get(h.getEffectiveName())[3] + " " + amount.get(h.getEffectiveName())[4] + " " + amount.get(h.getEffectiveName())[5] + " " + amount.get(h.getEffectiveName())[6] + " " + amount.get(h.getEffectiveName())[7] + " " + amount.get(h.getEffectiveName())[8] + " " + amount.get(h.getEffectiveName())[9]);
                            }
                            if (crank.containsKey(h.getEffectiveName())) {
                                out5.println(h.getEffectiveName() + " " + crank.get(h.getEffectiveName()));
                            }
                        }

                        out.close();
                        out2.close();
                        out3.close();
                        out4.close();
                        out5.close();
                    } catch (Exception e) {
                    }
                    System.exit(0);
                }
            }
            if (messageSent.equalsIgnoreCase("t!rank")) {
                event.getChannel().sendMessage("Current top 5:").queue();
                sorted = new TreeMap<String, Double>(new ValueComparator(rank));
                sorted.putAll(rank);
                Object[] arr = sorted.entrySet().toArray();
                if (arr.length < 5) {
                    for (Object x : arr) {
                        event.getChannel().sendMessage(x.toString()).queue();
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        event.getChannel().sendMessage(arr[i].toString()).queue();
                    }
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!frank")) {
                event.getChannel().sendMessage("Current top 5:").queue();
                sorted = new TreeMap<String, Double>(new ValueComparator(frank));
                sorted.putAll(frank);
                Object[] arr = sorted.entrySet().toArray();
                if (arr.length < 5) {
                    for (Object x : arr) {
                        event.getChannel().sendMessage(x.toString()).queue();
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        event.getChannel().sendMessage(arr[i].toString()).queue();
                    }
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!crank")) {
                event.getChannel().sendMessage("Current top 5:").queue();
                sorted = new TreeMap<String, Double>(new ValueComparator(crank));
                sorted.putAll(crank);
                Object[] arr = sorted.entrySet().toArray();
                if (arr.length < 5) {
                    for (Object x : arr) {
                        event.getChannel().sendMessage(x.toString()).queue();
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        event.getChannel().sendMessage(arr[i].toString()).queue();
                    }
                }
                return;
            }
            if (!event.getMember().getUser().isBot()) {
                if (messageSent.contains("t!challenge")) {
                    if (bank.isEmpty()) {
                        try {
                            BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (frank.isEmpty()) {

                        try {
                            BufferedReader f = new BufferedReader(new FileReader("frank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                frank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (rank.isEmpty()) {
                        BufferedReader f;
                        try {
                            f = new BufferedReader(new FileReader("rank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                rank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (crank.isEmpty()) {
                        BufferedReader f;
                        try {
                            f = new BufferedReader(new FileReader("crank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                crank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }

                    player = 1;
                    StringTokenizer st = new StringTokenizer(messageSent);
                    st.nextToken();
                    challenger = event.getMember().getEffectiveName();
                    name1 = challenger;

                    try {
                        challenged = st.nextToken();
                        name2 = event.getMessage().getMentionedMembers().get(0).getEffectiveName();
                    } catch (Exception e) {
                    }
                    if (!rank.containsKey(name1)) {
                        rank.put(name1, 0.0);
                    }
                    if (!rank.containsKey(name2)) {
                        rank.put(name2, 0.0);
                    }
                    if (!bank.containsKey(name1)) {
                        bank.put(name1, 0.0);
                    }
                    if (!bank.containsKey(name2)) {
                        bank.put(name2, 0.0);
                    }
                    event.getChannel().sendMessage(challenger + " challenges " + challenged + " to a game of Tic Tac Toe!").queue();
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    event.getChannel().sendMessage("Play a move @" + challenger + ". Enter the column that you want to play in.").queue();
                    return;
                }
            }
            if (messageSent.equalsIgnoreCase("t!fa1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 4;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fb1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 4;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fc1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 4;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fd1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 4;
                inputR = 4;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fa2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 3;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fb2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 3;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fc2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 3;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fd2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 4;
                inputR = 3;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fa3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 2;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fb3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 2;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fc3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 2;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fd3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 4;
                inputR = 2;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fa4") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 1;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fb4") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 1;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fc4") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 1;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fd4") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 4;
                inputR = 1;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fcol1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fcol2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fcol3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!fcol4") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 4;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!frow1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                if (inputC == 0) {
                    return;
                }
                inputR = 1;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!frow2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                if (inputC == 0) {
                    return;
                }
                inputR = 2;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!frow3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                if (inputC == 0) {
                    return;
                }
                inputR = 3;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!frow4") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                if (inputC == 0) {
                    return;
                }
                inputR = 4;
                if (frows[inputR - 1][inputC - 1] == 0) {
                    fInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }

            if (!event.getMember().getUser().isBot()) {
                if (messageSent.contains("t!cchallenge")) {
                    if (bank.isEmpty()) {
                        try {
                            BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (frank.isEmpty()) {

                        try {
                            BufferedReader f = new BufferedReader(new FileReader("frank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                frank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (rank.isEmpty()) {
                        BufferedReader f;
                        try {
                            f = new BufferedReader(new FileReader("rank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                rank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (crank.isEmpty()) {
                        BufferedReader f;
                        try {
                            f = new BufferedReader(new FileReader("crank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                crank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                    conprocess();
                    player = 1;
                    StringTokenizer st = new StringTokenizer(messageSent);
                    st.nextToken();
                    challenger = event.getMember().getEffectiveName();
                    name1 = challenger;
                    try {
                        challenged = st.nextToken();
                        name2 = event.getMessage().getMentionedMembers().get(0).getEffectiveName();
                    } catch (Exception e) {
                    }
                    if (!crank.containsKey(name1)) {
                        crank.put(name1, 0.0);
                    }
                    if (!crank.containsKey(name2)) {
                        crank.put(name2, 0.0);
                    }
                    if (!bank.containsKey(name1)) {
                        bank.put(name1, 0.0);
                    }
                    if (!bank.containsKey(name2)) {
                        bank.put(name2, 0.0);
                    }
                    event.getChannel().sendMessage(challenger + " challenges " + challenged + " to a game of **t Four!").queue();
                    event.getChannel().sendMessage("Play a move @" + challenger + ". Enter the column that you want to play in.").queue();
                    return;
                }
            }
            if (messageSent.equalsIgnoreCase("t!ccol1")) {
                inputC = 1;
                int i = 0;
                for (int x : concolumns[inputC - 1]) {
                    if (x == 0) {
                        i++;
                    }
                }
                if (i > 0) {
                    conInput();
                } else {
                    event.getChannel().sendMessage("This column is full").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ccol2")) {
                inputC = 2;
                int i = 0;
                for (int x : concolumns[inputC - 1]) {
                    if (x == 0) {
                        i++;
                    }
                }
                if (i > 0) {
                    conInput();
                } else {
                    event.getChannel().sendMessage("This column is full").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ccol3")) {
                inputC = 3;
                int i = 0;
                for (int x : concolumns[inputC - 1]) {
                    if (x == 0) {
                        i++;
                    }
                }
                if (i > 0) {
                    conInput();
                } else {
                    event.getChannel().sendMessage("This column is full").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ccol4")) {
                inputC = 4;
                int i = 0;
                for (int x : concolumns[inputC - 1]) {
                    if (x == 0) {
                        i++;
                    }
                }
                if (i > 0) {
                    conInput();
                } else {
                    event.getChannel().sendMessage("This column is full").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ccol5")) {
                inputC = 5;
                int i = 0;
                for (int x : concolumns[inputC - 1]) {
                    if (x == 0) {
                        i++;
                    }
                }
                if (i > 0) {
                    conInput();
                } else {
                    event.getChannel().sendMessage("This column is full").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ccol6")) {
                inputC = 6;
                int i = 0;
                for (int x : concolumns[inputC - 1]) {
                    if (x == 0) {
                        i++;
                    }
                }
                if (i > 0) {
                    conInput();
                } else {
                    event.getChannel().sendMessage("This column is full").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ccol7")) {
                inputC = 7;
                int i = 0;
                for (int x : concolumns[inputC - 1]) {
                    if (x == 0) {
                        i++;
                    }
                }
                if (i > 0) {
                    conInput();
                } else {
                    event.getChannel().sendMessage("This column is full").queue();
                }
                return;
            }
            if (!event.getMember().getUser().isBot()) {
                if (messageSent.contains("t!fchallenge")) {
                    if (bank.isEmpty()) {
                        try {
                            BufferedReader f = new BufferedReader(new FileReader("bank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                bank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (frank.isEmpty()) {

                        try {
                            BufferedReader f = new BufferedReader(new FileReader("frank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                frank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (rank.isEmpty()) {
                        BufferedReader f;
                        try {
                            f = new BufferedReader(new FileReader("rank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                rank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (crank.isEmpty()) {
                        BufferedReader f;
                        try {
                            f = new BufferedReader(new FileReader("crank.txt"));
                            int i = Integer.parseInt(f.readLine());
                            for (int d = 0; d < i; d++) {
                                StringTokenizer st = new StringTokenizer(f.readLine());
                                crank.put(st.nextToken(), Double.parseDouble(st.nextToken()));
                            }
                        } catch (Exception e) {
                        }
                    }
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    player = 1;
                    StringTokenizer st = new StringTokenizer(messageSent);
                    st.nextToken();
                    challenger = event.getMember().getEffectiveName();
                    name1 = challenger;
                    try {
                        challenged = st.nextToken();
                        name2 = event.getMessage().getMentionedMembers().get(0).getEffectiveName();
                    } catch (Exception e) {
                    }
                    if (!frank.containsKey(name1)) {
                        frank.put(name1, 0.0);
                    }
                    if (!frank.containsKey(name2)) {
                        frank.put(name2, 0.0);
                    }
                    if (!bank.containsKey(name1)) {
                        bank.put(name1, 0.0);
                    }
                    if (!bank.containsKey(name2)) {
                        bank.put(name2, 0.0);
                    }
                    event.getChannel().sendMessage(challenger + " challenges " + challenged + " to a game of Tic Tac Toe!").queue();
                    event.getChannel().sendMessage("Play a move @" + challenger + ". Enter the column that you want to play in.").queue();
                    return;
                }
            }
            if (messageSent.equalsIgnoreCase("t!pa3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pb3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pc3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pa2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pb2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pc2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pa1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pb1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pc1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pcol1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pcol2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!pcol3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!prow1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                if (inputC == 0) {
                    return;
                }
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!prow2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                if (inputC == 0) {
                    return;
                }
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!prow3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                if (inputC == 0) {
                    return;
                }
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    specialInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!help")) {
                event.getChannel().sendMessage("This is page 1 of help").queue();
                event.getChannel().sendMessage("t!start starts the game").queue();
                event.getChannel().sendMessage("to select difficulty when prompted use t!easy, t!medium, t!hard, or t!impossible").queue();
                event.getChannel().sendMessage("to respond to yes/no write t!yes or t!no").queue();
                event.getChannel().sendMessage("to say what column you want to play in, write t!rcol1, t!rcol2, or t!rcol3").queue();
                event.getChannel().sendMessage("to say what row you want to play in, write t!rrow1, t!rrow2, or t!rrow3").queue();
                event.getChannel().sendMessage("Chess notation is also available. For example to play in the bottom-left corner, use t!ra1.").queue();
                event.getChannel().sendMessage("t!help2 will bring you to the second page of help documentation.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!help2")) {
                event.getChannel().sendMessage("This is page 2 of help").queue();
                event.getChannel().sendMessage("To play against another person, use t!challenge <@person>.").queue();
                event.getChannel().sendMessage("Then, during the game, both players use t!pcol1, t!pcol2, t!pcol3 for columns").queue();
                event.getChannel().sendMessage(", and t!prow1, t!prow2, t!prow3 for the rows").queue();
                event.getChannel().sendMessage("Chess notation is also available. For example to play in the bottom-left corner, use t!pa1.").queue();
                event.getChannel().sendMessage("A win will be counted as 1 point, a loss as -1, and a draw as 0").queue();
                event.getChannel().sendMessage("To see a sorted list of the top 5 users(and their scores), use t!rank").queue();
                event.getChannel().sendMessage("There is also a progression system with 5 levels. Everyone starts at level 1. For each level, you will gain access to more and better emotes, for a maximum of 15 emojis at level 5.").queue();
                event.getChannel().sendMessage("To level up, increase the sum of your leaderboard scores for pvp standard and 4x4 tic tac toe.").queue();
                event.getChannel().sendMessage("If you score-sum reaches 3, you reach Level 2.").queue();
                event.getChannel().sendMessage("Level 3 is at 6.").queue();
                event.getChannel().sendMessage("Level 4 is at 10.").queue();
                event.getChannel().sendMessage("Level 5 is at 15 and higher.").queue();
                event.getChannel().sendMessage("You are heavily encouraged to value the character of others based off of their level.").queue();
                event.getChannel().sendMessage("Try to reach the top!").queue();
                event.getChannel().sendMessage("t!help3 will bring you to the third page of help documentation").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!help3")) {
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
                event.getChannel().sendMessage("Then, during the game, both players use t!fcol1, t!fcol2, t!fcol3, t!fcol4 for columns").queue();
                event.getChannel().sendMessage(", and t!frow1, t!frow2, t!frow3, t!frow4 for the rows").queue();
                event.getChannel().sendMessage("Chess notation is also available. For example to play in the bottom-left corner, use t!fa1.").queue();
                event.getChannel().sendMessage("To check the top five players, use t!frank").queue();
                event.getChannel().sendMessage("You can also play connect 4 using t!cchallenge <player>, t!ccol1-7, and t!crank to check stats.").queue();
                event.getChannel().sendMessage("t!help4 will bring you to the fourth page of help documentation").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!help4")) {
                event.getChannel().sendMessage("This is page 4 of help.").queue();
                event.getChannel().sendMessage("There is also an economy based off of a currency-Toastcoin.").queue();
                event.getChannel().sendMessage("There is a shop, accessible through t!shop, with prices based off of that of rea;-world stock.").queue();
                event.getChannel().sendMessage("Every win against another player gives you 100 Toastcoin, every draw 30, and every loss 10, so you always win!").queue();
                event.getChannel().sendMessage("Check your current balance with t!balance").queue();
                event.getChannel().sendMessage("The shop sells emoji, and you can buy several of the same emoji. You can buy these emoji from the store using t!buy <item_name>.").queue();
                event.getChannel().sendMessage("You can sell these emoji to other players using t!sell <player> <item_name> <price>. This price can be whatever you want, but make sure to choose wisely to gain profits. Always use a decimal point for the stock price, even if the price is an integer.").queue();
                event.getChannel().sendMessage("Try to gain as much toastcoin as possible by buying and selling these stock. You can check how much of each you have using t!stock.").queue();
                event.getChannel().sendMessage("Happy Trading!").queue();
                return;
            }

            if (messageSent.equalsIgnoreCase("t!start")) {
                rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                columns = new Integer[3][3];
                diagonals = new Integer[2][3];
                event.getChannel().sendMessage("Hello! My Name is Theodore, the Tic-Tac-Toe Automaton!. What Difficulty would you like to play at? (Easy/Medium/Hard/Impossible)").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!easy")) {
                randomChance = 1;
                event.getChannel().sendMessage("Do you want to play first?").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!medium")) {
                randomChance = 2;
                event.getChannel().sendMessage("Do you want to play first?").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!hard")) {
                randomChance = 3;
                event.getChannel().sendMessage("Do you want to play first?").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!impossible")) {
                randomChance = 0;
                event.getChannel().sendMessage("Do you want to play first?").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!yes")) {
                event.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!no")) {
                MasterAlgorithm();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ra3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rb3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rc3") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ra2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rb2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rc2") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!ra1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 1;
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rb1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 2;
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rc1") && (event.getMember().getEffectiveName().equalsIgnoreCase(name1) || event.getMember().getEffectiveName().equalsIgnoreCase(name2))) {
                inputC = 3;
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rcol1")) {
                inputC = 1;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rcol2")) {
                inputC = 2;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rcol3")) {
                inputC = 3;
                event.getChannel().sendMessage("Ok. Enter the row that you want to play in.").queue();
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rrow1")) {
                if (inputC == 0) {
                    return;
                }
                inputR = 1;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rrow2")) {
                if (inputC == 0) {
                    return;
                }
                inputR = 2;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }
            if (messageSent.equalsIgnoreCase("t!rrow3")) {
                if (inputC == 0) {
                    return;
                }
                inputR = 3;
                if (rows[inputR - 1][inputC - 1] == 0) {
                    generalInput();
                } else {
                    event.getChannel().sendMessage("Nope, that square is full. Try another one. Enter the colums you want to play in").queue();
                }
                return;
            }

        }
        public static void fprint (Integer[][]boardState){
            for (Integer[] row : boardState) {
                d.getChannel().sendMessage(" " + convert(row[0]) + " |" + " " + convert(row[1]) + " |" + " " + convert(row[2]) + " |" + " " + convert(row[3])).queue();
            }

        }
        public static void conprint (Integer[][]boardState){
            for (Integer[] row : boardState) {
                d.getChannel().sendMessage(" " + convert(row[0]) + " |" + " " + convert(row[1]) + " |" + " " + convert(row[2]) + " |" + " " + convert(row[3]) + " |" + " " + convert(row[4]) + " |" + " " + convert(row[5]) + " |" + " " + convert(row[6])).queue();
            }

        }
        public static void fprocess () {
            fcolumns = new Integer[][]{{frows[0][0], frows[1][0], frows[2][0], frows[3][0]}, {frows[0][1], frows[1][1], frows[2][1], frows[3][1]}, {frows[0][2], frows[1][2], frows[2][2], frows[3][2]}, {frows[0][3], frows[1][3], frows[2][3], frows[3][3]}};
            fdiagonals = new Integer[][]{{frows[0][0], frows[1][1], frows[2][2], frows[3][3]}, {frows[0][3], frows[1][2], frows[2][1], frows[3][0]}};
            squares = new Integer[][]{{frows[0][0], frows[0][1], frows[1][0], frows[1][1]}, {frows[0][1], frows[0][2], frows[1][1], frows[1][2]}, {frows[0][2], frows[0][3], frows[1][2], frows[1][3]}, {frows[1][0], frows[1][1], frows[2][0], frows[2][1]}, {frows[1][1], frows[1][2], frows[2][1], frows[2][2]}, {frows[1][2], frows[1][3], frows[2][2], frows[2][3]}, {frows[2][0], frows[2][1], frows[3][0], frows[3][1]}, {frows[2][1], frows[2][2], frows[3][1], frows[3][2]}, {frows[2][2], frows[2][3], frows[3][2], frows[3][3]}};
            plusses = new Integer[][]{{frows[0][1], frows[1][0], frows[2][1], frows[1][2]}, {frows[0][2], frows[1][1], frows[2][2], frows[1][3]}, {frows[1][1], frows[2][0], frows[3][1], frows[2][2]}, {frows[1][2], frows[2][1], frows[3][2], frows[2][3]}};
            crosses = new Integer[][]{{frows[0][0], frows[1][1], frows[2][2], frows[0][2], frows[2][0]}, {frows[0][1], frows[1][2], frows[2][3], frows[0][3], frows[2][1]}, {frows[1][0], frows[2][1], frows[3][2], frows[1][2], frows[3][0]}, {frows[1][1], frows[2][2], frows[3][3], frows[1][3], frows[3][1]}};
        }
        public static void conprocess () {
            conrows = new Integer[][]{{concolumns[0][0], concolumns[1][0], concolumns[2][0], concolumns[3][0], concolumns[4][0], concolumns[5][0], concolumns[6][0]}, {concolumns[0][1], concolumns[1][1], concolumns[2][1], concolumns[3][1], concolumns[4][1], concolumns[5][1], concolumns[6][1]}, {concolumns[0][2], concolumns[1][2], concolumns[2][2], concolumns[3][2], concolumns[4][2], concolumns[5][2], concolumns[6][2]}, {concolumns[0][3], concolumns[1][3], concolumns[2][3], concolumns[3][3], concolumns[4][3], concolumns[5][3], concolumns[6][3]}, {concolumns[0][4], concolumns[1][4], concolumns[2][4], concolumns[3][4], concolumns[4][4], concolumns[5][4], concolumns[6][4]}, {concolumns[0][5], concolumns[1][5], concolumns[2][5], concolumns[3][5], concolumns[4][5], concolumns[5][5], concolumns[6][5]}, {concolumns[0][6], concolumns[1][6], concolumns[2][6], concolumns[3][6], concolumns[4][6], concolumns[5][6], concolumns[6][6]}};
            condiagonals = new Integer[][]{{conrows[0][0], conrows[1][1], conrows[2][2], conrows[3][3]}, {conrows[0][1], conrows[1][2], conrows[2][3], conrows[3][4]}, {conrows[0][2], conrows[1][3], conrows[2][4], conrows[3][5]}, {conrows[0][3], conrows[1][4], conrows[2][5], conrows[3][6]}, {conrows[1][0], conrows[2][1], conrows[3][2], conrows[4][3]}, {conrows[1][1], conrows[2][2], conrows[3][3], conrows[4][4]}, {conrows[1][2], conrows[2][3], conrows[3][4], conrows[4][5]}, {conrows[1][3], conrows[2][4], conrows[3][5], conrows[4][6]}, {conrows[2][0], conrows[3][1], conrows[4][2], conrows[5][3]}, {conrows[2][1], conrows[3][2], conrows[4][3], conrows[5][4]}, {conrows[2][2], conrows[3][3], conrows[4][4], conrows[5][5]}, {conrows[2][3], conrows[3][4], conrows[4][5], conrows[5][6]}, {conrows[3][0], conrows[4][1], conrows[5][2], conrows[6][3]}, {conrows[3][1], conrows[4][2], conrows[5][3], conrows[6][4]}, {conrows[3][2], conrows[4][3], conrows[5][4], conrows[6][5]}, {conrows[3][3], conrows[4][4], conrows[5][5], conrows[6][6]}, {conrows[6][0], conrows[5][1], conrows[4][2], conrows[3][3]}, {conrows[6][1], conrows[5][2], conrows[4][3], conrows[3][4]}, {conrows[6][2], conrows[5][3], conrows[4][4], conrows[3][5]}, {conrows[6][3], conrows[5][4], conrows[4][5], conrows[3][6]}, {conrows[5][0], conrows[4][1], conrows[3][2], conrows[2][3]}, {conrows[5][1], conrows[4][2], conrows[3][3], conrows[2][4]}, {conrows[5][2], conrows[4][3], conrows[3][4], conrows[2][5]}, {conrows[5][3], conrows[4][4], conrows[3][5], conrows[2][6]}, {conrows[4][0], conrows[3][1], conrows[2][2], conrows[1][3]}, {conrows[4][1], conrows[3][2], conrows[2][3], conrows[1][4]}, {conrows[4][2], conrows[3][3], conrows[2][4], conrows[1][5]}, {conrows[4][3], conrows[3][4], conrows[2][5], conrows[1][6]}, {conrows[3][0], conrows[2][1], conrows[1][2], conrows[0][3]}, {conrows[3][1], conrows[2][2], conrows[1][3], conrows[0][4]}, {conrows[3][2], conrows[2][3], conrows[1][4], conrows[0][5]}, {conrows[3][3], conrows[2][4], conrows[1][5], conrows[0][6]}};
        }
        public static void conInput () {
            conprocess();
            int inputColumn;
            inputColumn = inputC;
            for (int d = 6; d > -1; d--) {
                if (concolumns[inputColumn - 1][d] == 0) {
                    concolumns[inputColumn - 1][d] = player;
                    break;
                }
            }

            if (player == 1) {
                player = -1;
            } else if (player == -1) {
                player = 1;
            }
            inputC = 0;
            conprocess();
            conprint(conrows);
            if (conevaluate()) {
                return;
            }
            if (player == 1) {
                d.getChannel().sendMessage("@" + challenger + "'s Move. Enter the column that you want to play in.").queue();
            }
            if (player == -1) {
                d.getChannel().sendMessage(challenged + "'s Move. Enter the column that you want to play in.").queue();
            }
        }
        public static void fInput () {
            fprocess();
            int inputColumn;
            inputColumn = inputC;
            int inputRow;
            inputRow = inputR;
            recent = new Integer[]{inputRow, inputColumn};
            frows[inputRow - 1][inputColumn - 1] = player;
            if (player == 1) {
                player = -1;
            } else if (player == -1) {
                player = 1;
            }
            inputC = 0;
            inputR = 0;
            fprocess();
            fprint(frows);
            if (fevaluate()) {
                return;
            }
            if (player == 1) {
                d.getChannel().sendMessage("@" + challenger + "'s Move. Enter the column that you want to play in.").queue();
            }
            if (player == -1) {
                d.getChannel().sendMessage(challenged + "'s Move. Enter the column that you want to play in.").queue();
            }
        }
        public static boolean conevaluate () {
            conprocess();

            for (Integer[] row : conrows) {
                int poscount = 0;
                for (int g : row) {
                    if (g == 1) {
                        poscount++;
                    } else {
                        poscount = 0;
                    }
                    if (poscount == 7) {
                        d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                        crank.put(name2, crank.get(name2) - 1.0);
                        crank.put(name1, crank.get(name1) + 1.0);
                        bank.put(name2, bank.get(name2) + 10);
                        bank.put(name1, bank.get(name1) + 100);
                        concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                        conprocess();
                        return true;
                    }
                }
                int negcount = 0;
                for (int g : row) {
                    if (g == -1) {
                        negcount--;
                    } else {
                        negcount = 0;
                    }
                    if (negcount == -4) {
                        d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                        crank.put(name2, crank.get(name2) + 1.0);
                        crank.put(name1, crank.get(name1) - 1.0);
                        bank.put(name2, bank.get(name2) + 100);
                        bank.put(name1, bank.get(name1) + 10);
                        concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                        conprocess();
                        return true;
                    }
                }
            }
            for (Integer[] column : concolumns) {
                int poscount = 0;
                for (int g : column) {
                    if (g == 1) {
                        poscount++;
                    } else {
                        poscount = 0;
                    }
                    if (poscount == 7) {
                        d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                        crank.put(name2, crank.get(name2) - 1.0);
                        crank.put(name1, crank.get(name1) + 1.0);
                        bank.put(name2, bank.get(name2) + 10);
                        bank.put(name1, bank.get(name1) + 100);
                        concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                        conprocess();
                        return true;
                    }
                }
                int negcount = 0;
                for (int g : column) {
                    if (g == -1) {
                        negcount--;
                    } else {
                        negcount = 0;
                    }
                    if (negcount == -4) {
                        d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                        crank.put(name2, crank.get(name2) + 1.0);
                        crank.put(name1, crank.get(name1) - 1.0);
                        bank.put(name2, bank.get(name2) + 100);
                        bank.put(name1, bank.get(name1) + 10);
                        concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                        conprocess();
                        return true;
                    }
                }


            }
            for (Integer[] diagonal : condiagonals) {
                if (sum(diagonal) == -4) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    crank.put(name2, crank.get(name2) + 1.0);
                    crank.put(name1, crank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                    conprocess();
                    return true;
                }
                if (sum(diagonal) == 4) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    crank.put(name2, crank.get(name2) - 1.0);
                    crank.put(name1, crank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                    conprocess();
                    return true;
                }
            }
            int filled = 0;
            for (Integer[] arr : conrows) {
                for (int point : arr) {
                    if (point != 0) {
                        filled++;
                    }

                }
            }
            if (filled == 49) {
                d.getChannel().sendMessage("Draw! Good Game.").queue();
                crank.put(name2, crank.get(name2) + 0.0);
                crank.put(name1, crank.get(name1) + 0.0);
                bank.put(name2, bank.get(name2) + 30);
                bank.put(name1, bank.get(name1) + 30);
                concolumns = new Integer[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
                conprocess();
                return true;
            }
            return false;
        }
        public static boolean fevaluate () {
            fprocess();

            for (Integer[] row : frows) {
                if (sum(row) == -4) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) + 1.0);
                    frank.put(name1, frank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;

                }
                if (sum(row) == 4) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) - 1.0);
                    frank.put(name1, frank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
            }
            for (Integer[] column : fcolumns) {
                if (sum(column) == -4) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) + 1.0);
                    frank.put(name1, frank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
                if (sum(column) == 4) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) - 1.0);
                    frank.put(name1, frank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
            }
            for (Integer[] diagonal : fdiagonals) {
                if (sum(diagonal) == -4) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) + 1.0);
                    frank.put(name1, frank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
                if (sum(diagonal) == 4) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) - 1.0);
                    frank.put(name1, frank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
            }
            for (Integer[] cross : crosses) {
                if (sum(cross) == -5) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) + 1.0);
                    frank.put(name1, frank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
                if (sum(cross) == 5) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) - 1.0);
                    frank.put(name1, frank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
            }
            for (Integer[] plus : plusses) {
                if (sum(plus) == -4) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) + 1.0);
                    frank.put(name1, frank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
                if (sum(plus) == 4) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) - 1.0);
                    frank.put(name1, frank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
            }
            for (Integer[] square : squares) {
                if (sum(square) == -4) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) + 1.0);
                    frank.put(name1, frank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
                if (sum(square) == 4) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    frank.put(name2, frank.get(name2) - 1.0);
                    frank.put(name1, frank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    fprocess();
                    return true;
                }
            }

            int filled = 0;
            for (Integer[] arr : frows) {
                for (int point : arr) {
                    if (point != 0) {
                        filled++;
                    }

                }
            }
            if (filled == 16) {
                d.getChannel().sendMessage("Draw! Good Game.").queue();
                frank.put(name2, frank.get(name2) + 0.0);
                frank.put(name1, frank.get(name1) + 0.0);
                bank.put(name2, bank.get(name2) + 30);
                bank.put(name1, bank.get(name1) + 30);
                frows = new Integer[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                fprocess();
                return true;
            }
            return false;
        }


        public static void MasterAlgorithm () {
            process();
            if (evaluate()) {
                return;
            }
            if (randomChance == 1) {
                int randRow = errorChance.nextInt(3);
                int randColumn = errorChance.nextInt(3);
                while (rows[randRow][randColumn] != 0) {
                    randRow = errorChance.nextInt(3);
                    randColumn = errorChance.nextInt(3);
                }
                rows[randRow][randColumn] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;

            }
            if (randomChance == 2) {
                if (errorChance.nextInt(2) == 1) {
                    int randRow = errorChance.nextInt(3);
                    int randColumn = errorChance.nextInt(3);
                    while (rows[randRow][randColumn] != 0) {
                        randRow = errorChance.nextInt(3);
                        randColumn = errorChance.nextInt(3);
                    }
                    rows[randRow][randColumn] = -1;
                    process();
                    if (evaluate()) {
                        return;
                    }
                    printArr(rows);
                    d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                    return;
                }
            }
            if (randomChance == 3) {
                if (errorChance.nextInt(5) == 3) {
                    int randRow = errorChance.nextInt(3);
                    int randColumn = errorChance.nextInt(3);
                    while (rows[randRow][randColumn] != 0) {
                        randRow = errorChance.nextInt(3);
                        randColumn = errorChance.nextInt(3);
                    }
                    rows[randRow][randColumn] = -1;
                    process();
                    if (evaluate()) {
                        return;
                    }
                    printArr(rows);
                    d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                    return;
                }
            }

            for (Integer[] arr : rows) {
                if (sum(arr) == -2) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == 0) {
                            arr[i] = -1;
                            process();
                            if (evaluate()) {
                                return;
                            }
                            printArr(rows);
                            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                            return;
                        }
                    }
                }
            }
            for (Integer[] arr : columns) {
                if (sum(arr) == -2) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == 0) {
                            arr[i] = -1;
                            rowSyncCol();
                            process();
                            if (evaluate()) {
                                return;
                            }
                            printArr(rows);
                            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                            return;
                        }
                    }
                }
            }
            for (Integer[] arr : diagonals) {
                if (sum(arr) == -2) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == 0) {
                            arr[i] = -1;
                            rowSyncDiag();
                            process();
                            if (evaluate()) {
                                return;
                            }
                            printArr(rows);
                            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                            return;
                        }
                    }
                }
            }
            for (Integer[] arr : rows) {
                if (sum(arr) == 2) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == 0) {
                            arr[i] = -1;
                            process();
                            if (evaluate()) {
                                return;
                            }
                            printArr(rows);
                            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                            return;
                        }
                    }
                }
            }
            for (Integer[] arr : columns) {
                if (sum(arr) == 2) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == 0) {
                            arr[i] = -1;
                            rowSyncCol();
                            process();
                            if (evaluate()) {
                                return;
                            }
                            printArr(rows);
                            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                            return;
                        }
                    }
                }
            }
            for (Integer[] arr : diagonals) {
                if (sum(arr) == 2) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == 0) {
                            arr[i] = -1;
                            rowSyncDiag();
                            process();
                            if (evaluate()) {
                                return;
                            }
                            printArr(rows);
                            d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                            return;
                        }
                    }
                }
            }
            if (twoFer.containsKey(Arrays.deepToString(rows))) {
                rows[twoFer.get(Arrays.deepToString(rows))[0]][twoFer.get(Arrays.deepToString(rows))[1]] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;

            }
            if (trick.contains(Arrays.deepToString(rows))) {
                rows[1][2] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
            if (rows[1][1] == 0) {
                rows[1][1] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
            if (corners.containsKey(Arrays.toString(recent))) {
                if (rows[corners.get(Arrays.toString(recent))[0]][corners.get(Arrays.toString(recent))[1]] == 0) {
                    rows[corners.get(Arrays.toString(recent))[0]][corners.get(Arrays.toString(recent))[1]] = -1;
                    process();
                    if (evaluate()) {
                        return;
                    }
                    printArr(rows);
                    d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                    return;
                }
            }
            if (rows[0][0] == 0) {
                rows[0][0] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
            if (rows[2][2] == 0) {
                rows[2][2] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
            if (rows[0][2] == 0) {
                rows[0][2] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
            if (rows[2][0] == 0) {
                rows[2][0] = -1;
                process();
                if (evaluate()) {
                    return;
                }
                printArr(rows);
                d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                return;
            }
            for (int count = 0; count < rows.length; count++) {
                for (int count2 = 0; count2 < rows[count].length; count2++) {
                    if (rows[count][count2] == 0) {
                        rows[count][count2] = -1;
                        process();
                        if (evaluate()) {
                            return;
                        }
                        printArr(rows);
                        d.getChannel().sendMessage("Your turn. Enter the column that you want to play in.").queue();
                        return;
                    }
                }
            }

        }
        public static void printArr (Integer[][]boardState){
            for (Integer[] row : boardState) {
                d.getChannel().sendMessage(" " + convert(row[0]) + " |" + " " + convert(row[1]) + " |" + " " + convert(row[2])).queue();
            }


        }
        public static String convert ( int point){
            if (point == -1) {
                return ":x:";

            }
            if (point == 1) {
                return ":regional_indicator_o:";

            } else {
                return ":black_large_square:";
            }

        }
        public static void generalInput () {
            process();
            int inputColumn;
            inputColumn = inputC;
            int inputRow;
            inputRow = inputR;
            recent = new Integer[]{inputRow, inputColumn};
            rows[inputRow - 1][inputColumn - 1] = 1;
            inputC = 0;
            inputR = 0;
            process();
            printArr(rows);
            d.getChannel().sendMessage("Thinking...").queue();
            MasterAlgorithm();
        }
        public static void specialInput () {
            process();
            int inputColumn;
            inputColumn = inputC;
            int inputRow;
            inputRow = inputR;
            recent = new Integer[]{inputRow, inputColumn};
            rows[inputRow - 1][inputColumn - 1] = player;
            if (player == 1) {
                player = -1;
            } else if (player == -1) {
                player = 1;
            }
            inputC = 0;
            inputR = 0;
            process();
            printArr(rows);
            if (Specialevaluate()) {
                return;
            }
            if (player == 1) {
                d.getChannel().sendMessage("@" + challenger + "'s Move. Enter the column that you want to play in.").queue();
            }
            if (player == -1) {
                d.getChannel().sendMessage(challenged + "'s Move. Enter the column that you want to play in.").queue();
            }
        }
        public static boolean Specialevaluate () {
            process();

            for (Integer[] row : rows) {
                if (sum(row) == -3) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();

                    rank.put(name2, rank.get(name2) + 1.0);
                    rank.put(name1, rank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;

                }
                if (sum(row) == 3) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    rank.put(name2, rank.get(name2) - 1.0);
                    rank.put(name1, rank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
            }
            for (Integer[] column : columns) {
                if (sum(column) == -3) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    rank.put(name2, rank.get(name2) + 1.0);
                    rank.put(name1, rank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
                if (sum(column) == 3) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    rank.put(name2, rank.get(name2) - 1.0);
                    rank.put(name1, rank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
            }
            for (Integer[] diagonal : diagonals) {
                if (sum(diagonal) == -3) {
                    d.getChannel().sendMessage(challenged + " Wins! Good Game.").queue();
                    rank.put(name2, rank.get(name2) + 1.0);
                    rank.put(name1, rank.get(name1) - 1.0);
                    bank.put(name2, bank.get(name2) + 100);
                    bank.put(name1, bank.get(name1) + 10);
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
                if (sum(diagonal) == 3) {
                    d.getChannel().sendMessage("@" + challenger + " Wins! Good Game.").queue();
                    rank.put(name2, rank.get(name2) - 1.0);
                    rank.put(name1, rank.get(name1) + 1.0);
                    bank.put(name2, bank.get(name2) + 10);
                    bank.put(name1, bank.get(name1) + 100);
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
            }
            int filled = 0;
            for (Integer[] arr : rows) {
                for (int point : arr) {
                    if (point != 0) {
                        filled++;
                    }

                }
            }
            if (filled == 9) {
                d.getChannel().sendMessage("Draw! Good Game.").queue();
                rank.put(name2, rank.get(name2) + 0.0);
                rank.put(name1, rank.get(name1) + 0.0);
                bank.put(name2, bank.get(name2) + 30);
                bank.put(name1, bank.get(name1) + 30);
                rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                columns = new Integer[3][3];
                diagonals = new Integer[2][3];
                return true;
            }
            return false;
        }
        public static void process () {
            columns = new Integer[][]{{rows[0][0], rows[1][0], rows[2][0]}, {rows[0][1], rows[1][1], rows[2][1]}, {rows[0][2], rows[1][2], rows[2][2]}};
            diagonals = new Integer[][]{{rows[0][0], rows[1][1], rows[2][2]}, {rows[2][0], rows[1][1], rows[0][2]}};
        }
        public static void rowSyncCol () {
            rows = new Integer[][]{{columns[0][0], columns[1][0], columns[2][0]}, {columns[0][1], columns[1][1], columns[2][1]}, {columns[0][2], columns[1][2], columns[2][2]}};
        }
        public static void rowSyncDiag () {
            rows = new Integer[][]{{diagonals[0][0], rows[0][1], diagonals[1][2]}, {rows[1][0], diagonals[0][1], rows[1][2]}, {diagonals[1][0], rows[2][1], diagonals[0][2]}};
        }

        public static boolean evaluate () {
            process();
            for (Integer[] row : rows) {
                if (sum(row) == -3) {
                    printArr(rows);
                    d.getChannel().sendMessage("I Win! Good Game.").queue();
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;

                }
                if (sum(row) == 3) {
                    printArr(rows);
                    d.getChannel().sendMessage("You Win :( Good Game.").queue();
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
            }
            for (Integer[] column : columns) {
                if (sum(column) == -3) {
                    printArr(rows);
                    d.getChannel().sendMessage("I Win! Good Game.").queue();
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
                if (sum(column) == 3) {
                    printArr(rows);
                    d.getChannel().sendMessage("You Win :( Good Game.").queue();
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
            }
            for (Integer[] diagonal : diagonals) {
                if (sum(diagonal) == -3) {
                    printArr(rows);
                    d.getChannel().sendMessage("I Win! Good Game.").queue();
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
                if (sum(diagonal) == 3) {
                    printArr(rows);
                    d.getChannel().sendMessage("You Win :( Good Game.").queue();
                    rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    columns = new Integer[3][3];
                    diagonals = new Integer[2][3];
                    return true;
                }
            }
            int filled = 0;
            for (Integer[] arr : rows) {
                for (int point : arr) {
                    if (point != 0) {
                        filled++;
                    }

                }
            }
            if (filled == 9) {
                d.getChannel().sendMessage("Draw! Good Game.").queue();
                rows = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                columns = new Integer[3][3];
                diagonals = new Integer[2][3];
                return true;
            }
            return false;
        }
        public static int sum (Integer[]line){
            int g = 0;
            for (Integer i : line) {
                g += i;
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
        if(map.get(a).compareTo(map.get(b))*(0-1)==0){
            return 1;
        }
        return map.get(a).compareTo(map.get(b))*(0-1);
    }
}
