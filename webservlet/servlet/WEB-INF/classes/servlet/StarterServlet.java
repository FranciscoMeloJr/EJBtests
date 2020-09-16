package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

public class StarterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig servletConfiguration) throws ServletException {

        super.init(servletConfiguration);

        System.out.println("SERVLET STARTED!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("Test");
        try {
            creatingFile();
            System.out.println("test1");
            creatingFileDirectory();
            System.out.println("test2");
            creatingFilePW();
            System.out.println("test3");
            not_creatingFile();
            System.out.println("test4");
            writing_empty();

        } catch (Exception e) {
            System.out.println("Something went wrong " + e.toString());
        }
    }

    //Creating File:
    public static void creatingFile() throws IOException{
            File file = new File("/home/fdemeloj/Downloads/cases/test1.txt");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(" In My Life ");
            printWriter.close();
    }

    //Creating File:
    public static void creatingFileDirectory() throws IOException{
            File file = new File("/home/fdemeloj/Downloads/cases/test2.txt");
            file.getParentFile().mkdirs();
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write("Strawberry Fields Forever ");
            printWriter.close();
    }

    //Creating file directly with PW
    public static void creatingFilePW() throws IOException{
            String file = "/home/fdemeloj/Downloads/cases/test3.txt";
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write("Lucy in The Sky ");
            printWriter.close();
    }

    //Not creating File ~ throws file not found:
    public static void not_creatingFile() throws IOException{
    File file = new File ("/home/fdemeloj/Downloads/casestest4.txt");
    PrintWriter printWriter = new PrintWriter ("test4.txt");
    printWriter.println ("Yellow Submarine");
    printWriter.close ();
    }

    //Not creating File ~ throws file not found:
    public static void writing_empty() throws IOException{
    PrintWriter printWriter = null;
    printWriter.println ("Across the Universe");
    printWriter.close ();
    }
}