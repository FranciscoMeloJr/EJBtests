import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
 import javax.naming.Context;

public class EJBTester {
 
   BufferedReader brConsoleReader = null; 
   Properties props;
   Context ctx;
   {
      props = new Properties();
      try {
         props.load(new FileInputStream("jndi.properties"));
      } catch (IOException ex) {
         ex.printStackTrace();
      }
      try {
         ctx = getInitialContext();            
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      brConsoleReader = 
      new BufferedReader(new InputStreamReader(System.in));
   }
   
   public static void main(String[] args) {
 
      EJBTester ejbTester = new EJBTester();
 
      ejbTester.testStatelessEjb();
   }
   
   private void showGUI() {
      System.out.println("**********************");
      System.out.println("Welcome to Book Store");
      System.out.println("**********************");
      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
   }

      //getInitialContext:
   public static Context getInitialContext() throws NamingException, IOException{
      Properties props = new Properties();
      props.load(new FileInputStream("jndi.properties"));
      final Context context = new InitialContext(props);
      return context;
   }

   
   private void testStatelessEjb() {
 
      try {
         int choice = 1; 
 
         LibraryStatefulSessionBeanRemote libraryBean = (LibraryStatefulSessionBeanRemote)ctx.lookup("ejb:/EjbStateful/LibraryStatefulSessionBean!LibraryStatefulSessionBeanRemote?stateful");
 
         while (choice != 2) {
            String bookName;
            showGUI();
            String strChoice = brConsoleReader.readLine();
            choice = Integer.parseInt(strChoice);
            if (choice == 1) {
               System.out.print("Enter book name: ");
               bookName = brConsoleReader.readLine();
               Book book = new Book();
               book.setName(bookName);
               libraryBean.addBook(book.getName());          
            } else if (choice == 2) {
               break;
            }
         }
 
         List<String> booksList = libraryBean.getBooks();
 
         System.out.println("Book(s) entered so far: " + booksList.size());
         int i = 0;
         for (String book:booksList) {
            System.out.println((i+1)+". " + book);
            i++;
         }       
         LibraryStatefulSessionBeanRemote libraryBean1 = 
            (LibraryStatefulSessionBeanRemote)ctx.lookup("ejb:/EjbStateful/LibraryStatefulSessionBean!LibraryStatefulSessionBeanRemote?stateful");
         List<String> booksList1 = libraryBean1.getBooks();
         System.out.println(
            "***Using second lookup to get library stateful object***");
         System.out.println(
            "Book(s) entered so far: " + booksList1.size());
         for (int j = 0; j < booksList1.size(); ++j) {
            System.out.println((i+1)+". " + booksList1.get(i));
         }	
         	 
      } catch (Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }finally {
         try {
            if(brConsoleReader !=null) {
               brConsoleReader.close();
            }
         } catch (IOException ex) {
            System.out.println(ex.getMessage());
         }
      }
   }
}

class Book {
   
   String name;

   public void setName(String bookName){
      this.name = bookName;
   }
   public String getName(){
      return name;
   }
}