package simple_cinema_reservation;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StoredFiles {
    private String stored_file;

    private List<Account> memory = new ArrayList<>();
    List<Account> list = new ArrayList<>(memory);

    public StoredFiles() {
    }

    public StoredFiles(String stored_file) {
        this.stored_file = stored_file;
    }

    
        public void read(){
            try {
                Gson gson = new Gson();
                
                Reader reader = Files.newBufferedReader(Paths.get("data.json"));
                
                memory = Arrays.asList(gson.fromJson(reader, Account[].class));
            
                for (Account account : memory) {
                    System.out.println(account);
                }
            
                reader.close();
            
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }    

    public void write() {
        //dạng file json in ra đẹp hơn
        GsonBuilder gsonBuilder = new GsonBuilder(); 
        Gson gson = gsonBuilder.create();
        
        try(Writer writer = Files.newBufferedWriter(Paths.get("data.json"))){
            gsonBuilder.setPrettyPrinting().create().toJson(list, writer);
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        //  ghi file của thầy
        // Gson gson = new Gson();
        // try(FileWriter fileWriter = new FileWriter("data.json")) {
        //     gson.toJson(list, fileWriter);
        //     fileWriter.close();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    public void update() {
        for(Account a : memory){
            list.add(a);
        }
    }

    public void get_all() {
        // list.getClass();
        System.out.println(list);
    }    
     
    public void search() {
        Account account1 = null;
        Account account3 = null;
        System.out.println("Lựa chọn phương thức tìm:");
        System.out.println("1. Theo username    2. Theo Gmail");
        int choose = UITerminal.sc.nextInt();
        switch(choose){
            case 1:
            UITerminal.sc.nextLine();
            System.out.print("Nhập username  account bạn cần tìm: ");
            String searched = UITerminal.sc.nextLine();
            System.out.println("Account tìm được: ");
            for(Account account2 :list){
                if(account2.getUsername().equalsIgnoreCase(searched) ){
                    account1 = account2;
                    System.out.println(account1);
                }
            } break;
            case 2: 
            UITerminal.sc.nextLine();
            System.out.print("Nhập gmail  account bạn cần tìm: ");
            String gmail = UITerminal.sc.nextLine();
            for(Account account4 :list){
                if(account4.getEmail().equalsIgnoreCase(gmail) ){
                    account3 = account4;
                    System.out.println(account3);
                }
             
               }   break; 
            default: System.out.println("Vui lòng nhập lại");
            
        
       
        
            
              
            
        }
    }
}