import java.util.*;
import java.io.*;
import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner fileIn; //Input file connection
        PrintWriter fileOut; //HTML file connection
        String filenameIn; //Original file's name
        String filenameOut; //New html file's name
        int dotIndex; //Position of . in filename
        String line = null; //A line from the input file

        System.out.println("Enter file name or path");
        filenameIn = scanner.nextLine();

        try{
            System.out.println("Choose a website name");
            String pageName = scanner.nextLine();
            fileIn = new Scanner(new FileReader(filenameIn));
            dotIndex = filenameIn.lastIndexOf(".");
            if(dotIndex == -1){
                filenameOut = filenameIn + ".html";
            }else{
                filenameOut = filenameIn.substring(0,dotIndex) + ".html";
            }
            fileOut = new PrintWriter(filenameOut);

            try{
                line = fileIn.nextLine();
                if(line == null){
                    System.out.println("This file is empty");
                }else{
                    fileOut.println("<html>");
                    fileOut.println("<head>");
                    fileOut.println("<title>" + pageName + "</title>");
                    fileOut.println("</head>");
                    fileOut.println("<body>");
                    fileOut.println("<p>" + line + "</p>");
                    while(fileIn.hasNextLine()){
                        line = fileIn.nextLine();
                        if(line.isEmpty()){
                            fileOut.println("<br>");
                        }else{
                            fileOut.println("<p>" + line + "</p>");
                        }
                    }
                    fileOut.println("<p>This website was made with KC's html generator</p>");
                    fileOut.println("</body>");
                    fileOut.println("</html>");
                    System.out.println("HTML file is processed");
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    fileIn.close();
                    fileOut.close();
                }
            }
            catch(NoSuchElementException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}