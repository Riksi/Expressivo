package expressivo;

//Notes:
//For the moment, will not consider subtract, divide and power
//Will do nothing for the methods related to power
//Will not consider the possibility of '-' in sum and '/' in product

import java.io.*;

public class Main {
    private static String prompt = ">";
    
    public static void printOut(String output){
        System.out.println(makePromptString(output));
    }
    
    public static String makePromptString(String output){
        if(output.startsWith("(")){
            output = output.substring(1,output.length()-1);
        }
        return prompt+output;
    }
    
    public static void main(String[] args) {
        //System.err.close();
        Expression exp = null;
        try{
            printOut("Welcome to Expressivo! \nEnter your expression below.\n"
                   +"Once you have done that you may start entering your commands with \'!\'.\nPress enter twice to quit");
           BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
           String line;
           int leave = 0;
           while(true){
               System.out.print(prompt);
               line=bufferRead.readLine();
               if(line.length() == 0){
                   leave+=1;
                   if(leave == 2){
                       printOut("Goodbye!");
                       break;
                   }
               }else{
                   
                   if(line.startsWith("!d/d")){
                       
                       if(exp!=null){
                           String var = line.replaceFirst("!d/d", "");
                           try{
                               String diffExp = Commands.differentiate(exp, var);
                               printOut(diffExp);
                           }catch(AssertionError ae){
                               printOut("Your variable is not in the right format.");
                           }
                       }else{
                           printOut("Please enter an expression first");
                       }
                   }else if(line.startsWith("!simplify")){
                       
                       if(exp!=null){
                           try{
                               String vars = line.replaceFirst("!simplify\\s*", "");
                               String simpExp = Commands.simplify(exp, vars);
                               printOut(simpExp);
                           }catch(AssertionError ae){
                               printOut("Your variables and values are not in the right format.");
                           }
                       }else{
                           printOut("Please enter an expression first");
                       }
                   }else{
                       try{
                           exp = Expression.parse(line);
                           printOut(exp.toString());
                       }catch(ParseError pe){
                           printOut(pe.getMessage());
                       }
                   }
               
               }
           }
           
       }catch(IOException ex){
          //ex.printStackTrace();
       }
     }
    
}




