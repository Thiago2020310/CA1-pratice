/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca1.pratice;

import java.io.FileReader; //Import Java FileReader package to read the file
import java.util.Arrays; //Import Arrays package that will be used to store names
import java.util.Scanner; //Import Scanner package 

/*
Pattern to define a pattern that will be used by Matcher to validate the data. (w3schools)
*/
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author tgsouzadev
 */
public class CA1Pratice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create a try and catch to verify if theres any possible error and send an error message to the user with the proper syntax.
        
        try{
            /* This line follows three steps:
                First creates a Scanner variable called MyReader
                Initiate a new Scanner
                Initiate FileReader
                Follow the absolute path to the document to be read
             */
            Scanner myReader = new Scanner(new FileReader("/Users/tgsouzadev/Desktop/PROGRAMMING AND MATHS FUNDAMENTALS/Java Applications/CA1-pratice/customers.txt"));
            /*
                Create a condition to verify if the document is empty
                If the document is empty print to the console that there's nothing on the file
                Else proceeds to read the first line on the document.
             */
            if(!myReader.hasNext()){
                System.out.println("Theres nothing on the file");
            }
            else{
                 /*
                Variables to store the lines during the loop that will follow. 
                fullName, name and surname will be used to manipulate string and validate the inputs as per the following rules:
                a) the first name must be leUers only; b) The second name can be leUers and/or numbers and must be separated from the first name by a single space;
                theClass, price and year will also be used to validate the inputs as per following rules:
                c) the value of purchase of classes must be double; d) the Class must be a integer between 1 to 3. e) Last purchase must be a valid year.s
                */
                String fullName;
                String firstName;
                String surname;
                int theClass;
                double price;
                int year;
                
                
                
                /*
                A while loop that will run while there is a next line to be read. It stops once it gets to a blank line. So it can be used on this way for this exercise, but on a more complex document would require a more complex validation(guard) since this could easly cause trouble.
                */
                while(myReader.hasNext()){
                    //variable to read the next line on the file
                    var line = myReader.nextLine();
                    
                    /*
                    Matcher class assigning a pattern to and comparing the line read with the validation
                    boolean to hold if the line read is valid or not
                    */
                    Pattern namePattern = Pattern.compile("^[A-Za-z0-9]+\\s[A-Za-z0-9]+$");
                    Matcher lineMatcher = namePattern.matcher(line);
                    boolean nameValidation = lineMatcher.find();
                    
                    //Pattern for special characters the pattern is unicode found on https://stackoverflow.com/questions/18057962/regex-pattern-including-all-special-characters
                    Pattern specialCh = Pattern.compile("[^\\\\p{L}\\\\d\\\\s_]");
                    Matcher specialChMatcher = specialCh.matcher(line);
                    boolean specialChValidation = specialChMatcher.find();
                    /*
                    An if condition to check which data are we looking at:
                    Starting from the names manipulation, using a Regex, took from Regex-generator website, first we take the full name input, if the line matches with the Regex we understand as the name and assign to the fullName variable..
                    */
                    if(nameValidation){
                        fullName = line;
                        
                        String[] names = fullName.split(" ");
                        firstName = names[0];
                        surname = names[1];
                        Pattern fnPattern = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
                        Matcher fnMatcher = fnPattern.matcher(firstName);
                        boolean fnValidation = fnMatcher.find();
                        if(fnValidation){
                            System.out.println(firstName);
                        }
                        else{
                            System.out.println("The first name must contain only letters.");
                        }
                        
                        System.out.println(firstName + " " + surname);
                        
                    }
                    else if(line.matches("^[0-9]*\\.[0-9]+$")){
                        price = Double.parseDouble(line);
                        System.out.println(price);
                    }
                    else if (line.matches("^\\d{1}$")){
                        theClass = Integer.parseInt(line);
                        System.out.println(theClass);
                    }
                    else if(line.matches("\\d{4}$")){
                        year = Integer.parseInt(line);
                        System.out.println(year);
                    }else if(specialChValidation){
                        System.out.println("No special character are accepted.");
                    }
                    
                }
             
            }
            

        }catch(Exception e){
            System.out.println("This is a test");
        }
             
                
    }
    
}
