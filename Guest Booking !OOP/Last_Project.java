
package last_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
public class Last_Project { 
////////////////////////////////////////////////////////////////////////////////
private static void mainMenu (String [] guests) throws IOException{
    System.out.println("  | Welcome |");
    System.out.println("1 - User");
    System.out.println("2 - Admin");
    Scanner input = new Scanner(System.in);
        int number =  input.nextInt() ;
    switch(number)
    {
        case 1 :
            user(guests);
            break;
        case 2 :  
             passWord(guests);
                  break ;
    }
} 
////////////////////////////////////////////////////////////////////////////////
private static void user(String [] guests) throws IOException
{
        System.out.println("\n--------------------");
        System.out.println("  | Menu |");        
        System.out.println("1 - Informtion About Events");
        System.out.println("2 - Register Now");  
        Scanner input = new Scanner(System.in);
        int number =  input.nextInt() ;
    switch(number)
    {
        case 1 :
            informtion(guests);
            break;
        case 2 :  
              registerNow(guests);
             break;           
    }
}
////////////////////////////////////////////////////////////////////////////////
    private static void registerNow(String[] guests) throws IOException{
        System.out.println("-----------------------\n");
        boolean x = true ;
        if (!((listLong()-1)>(howManyGuests()))) {
            System.out.println("Sorry, there are no places to register");
            x = false;
        }
       if(x==true){
           System.out.println("Enter The Name..?");
        Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                for (int i = 0; i < guests.length; i++)
                {
                    if(guests[i]==null)
                    {
                        guests[i]= name ;
                          break;         
                    }  
                }
       }
        exit(guests);   
    }
////////////////////////////////////////////////////////////////////////////////                    
    private static void informtion (String [] guests) throws IOException
    {
        System.out.println("-----------------------\n");
        System.out.println("  | Events |");        
        System.out.println("1 - CookBook");
        System.out.println("2 - Fireworks Show"); 
        System.out.println("3 - Walking or Hiking");
        System.out.println("4 - Exit");
        Scanner input = new Scanner(System.in);
        int number =  input.nextInt() ;
    switch(number)
    {
        case 1 :
            try
            {
               FileReader x1 = new FileReader("CookBook.txt") ;
               int c ;
               while((c=x1.read())!=-1)
                    System.out.print((char)c);
            }
            catch(IOException e)
            {                
            }
            System.out.println("\n-----------------------\n");
            System.out.println("1-Register Now \n2-Back");
                    int x = input.nextInt();
                    if(x==1)
                        registerNow(guests);
                    if(x==2)
                        informtion(guests);   
                    break;
        case 2 :  
            try
            {
               FileReader x1 = new FileReader("Fireworks Show.txt") ;
               int c ;
               while((c=x1.read())!=-1)
                    System.out.print((char)c);
            }
            catch(IOException e)
            {                
            }
            
            System.out.println("\n-----------------------\n");
            System.out.println("1-Register Now \n2-Back");
                    x = input.nextInt();
                    if(x==1)
                        registerNow(guests);
                    if(x==2)
                        informtion(guests);    
            
                  break ;
         case 3 :  
            try
            {
               FileReader x1 = new FileReader("Walking or Hiking.txt") ;
               int c ;
               while((c=x1.read())!=-1)
                    System.out.print((char)c);
            }
            catch(IOException e)
            {                
            }
            
            System.out.println("\n-----------------------\n");
            System.out.println("1-Register Now \n2-Back");
                    x = input.nextInt();
                    if(x==1)
                        registerNow(guests);
                    if(x==2)
                        informtion(guests);                                
                  break ;    

         default :
                  exit(guests);
             
    }
    }
////////////////////////////////////////////////////////////////////////////////    
        private static void displayMenu(String[] guests) throws IOException {
        System.out.println("-----------------------\n");
        System.out.println("  | Menu |");        
        System.out.println("1 - Display Guest");
        System.out.println("2 - Add Guest");
        System.out.println("3 - Rename Guest");
        System.out.println("4 - Remove Guest");
        System.out.println("6 - Exit");
        System.out.println("5 - Edit the list");
        
        Scanner input = new Scanner(System.in);
        int number =  input.nextInt() ;   
        switch(number)
        {
            case 1 :
                    displayGuests(guests);
                  break ;
            case 2 :
                   addGuest(guests);
                   break ;
            case 3 :
                  renameGuest(guests);
                  break; 
            case 4 :
                 removeGuest(guests);
                 break;
            case 6 :
                  exit(guests);
            case 5 :
                  exit(guests);
        }              
    }
////////////////////////////////////////////////////////////////////////////////  
    private static void displayGuests(String [] guests) throws IOException{
        System.out.println("-----------------------\n");
        System.out.println("  | guests |");
        sort(guests);
    for (String guest : guests) {
        if (guest != null) {
            System.out.println(" - " + guest);            
        }
    }
                        displayMenu(guests);
    }            
////////////////////////////////////////////////////////////////////////////////
            private static void addGuest(String [] guests) throws IOException {
                System.out.println("-----------------------");
                 boolean x = true ;
                 if (!((listLong())>(howManyGuests()))) {
                 System.out.println("Sorry, there are no places to add guest");
                 displayMenu(guests);
                 x =false ;
                }
                if (x == true) {
                    System.out.println("Enter The Name..?");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                for (int i = 0; i < guests.length; i++) {
                    if(guests[i]==null)
                    {
                        guests[i]=name ;
                          break;         
                    }         
            }
                filing(guests);
                System.out.println("Add Another Name..?\n1-Yes\n2-No")  ;                          
                int yn = input.nextInt();
                if (yn==1)
                    addGuest(guests);
                else
                displayMenu(guests);
                }
        }    
////////////////////////////////////////////////////////////////////////////////            
            private  static void renameGuest(String [] guests) throws IOException {
                System.out.println("-----------------------\n");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter The Name... ");
        String check = input.nextLine(); 
                boolean f = true ;
                for (int i = 0; i < guests.length; i++) {
                     if(check.equalsIgnoreCase(guests[i])==true){
                             System.out.println("Enter The New Name..");
                             guests[i]= input.nextLine();
                             System.out.println("The Name is Changed to "+guests[i] );
                             f=false;               
                }
                }
                if (f==true){
                     System.out.println("This Name is Not Found in The List");
                    }
                    filing(guests);
                    System.out.print("-----------------------\n");
                    System.out.println("1-Back To Menu \n2-Exit");
                    int x = input.nextInt();
                    if(x==1)
                        displayMenu(guests);
                    else
                        exit(guests);
                         
            }        
////////////////////////////////////////////////////////////////////////////////            
     private static void removeGuest(String [] guests) throws IOException {
         System.out.println("-----------------------\n");
         Scanner input = new Scanner(System.in);
         System.out.println("Enter The Name... ");
            String check = input.nextLine(); 
            boolean f = true ;
            int g = (guests.length)-1 ;
                for (int k = 0; k <guests.length; k++) {
                    if(guests[k]==null){
                         g=k-1;
                        break;
                    }           
         }
                    for (int i = 0; i < guests.length; i++){ 
                    if(check.equalsIgnoreCase(guests[i])==true){
                        guests[i]= guests[g];
                        guests[g]= null ;
                        System.out.println("Deleted"); 
                        f=false;
                        }
                    }                   
                    if(f==true){
                        System.out.println("This Name is Not Found in The List");
                    } 
                    
                    System.out.println("--------------------\n");
                    filing(guests);
                    System.out.println("1-Back To Menu \n2-Exit");
                    int x = input.nextInt();
                    if(x==1)
                        displayMenu(guests);
                    else
                        exit(guests);        
    }
////////////////////////////////////////////////////////////////////////////////
       private static void editList(String [] guests) throws IOException{
           System.out.println("your list are "+listLong());
           System.out.println("you have "+(listLong()-howManyGuests())+" place");
           System.out.println("1-To add places \n 2- To remove places");
           
       }
               
////////////////////////////////////////////////////////////////////////////////
private static void exit(String [] guests) throws IOException
{
    System.out.println("-----------------------\n");
    
    System.out.println("Thanks..");
    filing(guests);
}        
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
private static void inform(String [] guests) throws IOException{
    System.out.println("The long of your list is "+ listLong() );
    System.out.println("There are "+howManyGuests()+ "  guests" );
    if(listLong()==howManyGuests())
        System.out.println("NOTE :  There is no place to add guest");
    
    displayMenu(guests);
    
}
////////////////////////////////////////////////////////////////////////////////
private static void addPlace(String [] guests) throws IOException{
    
}
////////////////////////////////////////////////////////////////////////////////        
private static int howManyGuests() throws FileNotFoundException, IOException{
          String[] guests = new String[listLong()];
          Reader inner = new FileReader("guestList.txt"); 
          BufferedReader one = new BufferedReader(inner);
           int a = 0;
           for (int i = 0; i < guests.length; i++) {
            String line = one.readLine();
            if(line == null)
                break;
             a = i+1 ; 
           }
    return a;         
 }
////////////////////////////////////////////////////////////////////////////////
 private static int listLong() throws FileNotFoundException, IOException{
           Reader inner = new FileReader("howManyGuests.txt");
           BufferedReader one = new BufferedReader(inner);
           String line = one.readLine();
           int a = Integer.parseInt(line);
    return a;         
 }
////////////////////////////////////////////////////////////////////////////////
 private static void bringGuests(String [] guests) throws FileNotFoundException, IOException{
           Reader inner = new FileReader("guestList.txt"); 
           BufferedReader one = new BufferedReader(inner);
           for (int i = 0; i < guests.length; i++) {
            String line = one.readLine();
            if(line == null)
                break;
            guests[i]=line;            
        }
 }
////////////////////////////////////////////////////////////////////////////////
        private static void filing(String [] guests) throws IOException{
    try (FileWriter guestList = new FileWriter("guestList.txt")) {
        for (int i = 0; i <guests.length ; i++) {
            guestList.write(guests[i]+"\n");
            if(guests[i+1]==null)
                break;
        }
    }
        }
////////////////////////////////////////////////////////////////////////////////
  private static void passWord(String []guests) throws IOException
  {
      System.out.println("Enter in The PassWord");
      Scanner input = new Scanner(System.in);
       int x = input.nextInt();
       int p = 1412 ;
        if(x == p ){
            inform(guests);
        }
        else
        {
            System.out.println("PassWord is Wrong");
            passWord(guests);
        }
  }
////////////////////////////////////////////////////////////////////////////////
            private static void sort(String [] guests ) {
                for (int i = 0; i <guests.length; i++) {
                 String F = guests[i];
                 int check = i ;
            for (int j=i+1; j <guests.length; j++) {
                if(guests[j]==null)
                    
                    break;
                if((F.compareToIgnoreCase(guests[j]))>0)
                {
                    F = guests[j];
                    check = j ;
                }
            }
                if(check != i )
                {
                    String t = guests[check];
                    guests[check]= guests[i];
                    guests[i]=t ;
                }   
                }   
            }
////////////////////////////////////////////////////////////////////////////////
            private static void start() throws IOException{
             String[] guests = new String[listLong()+1];
             bringGuests(guests);
             mainMenu(guests);  
            }
           
public static void main(String[] args) throws IOException {
                   start();
    }
}

    
    

