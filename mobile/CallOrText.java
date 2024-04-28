/**
    CallOrText
    @author: 535353 Zhang Zihan
             535359 Wang Yiwen
    @version: 1 June 2020
    purpose: KXO151 assignment 3, semester 1, 2020
*/

import java.util.Scanner;

public class CallOrText 
{
    //******************************************************************************** 
    // Declare required instance variables.
    //********************************************************************************     
    private final int SIZE = 50; //limit the length of list

    private MobileFacilities mobilefacilities;  //declare the object of MobileFacilities
    private boolean tracing; //to swich the on and off function of tracing
    private char choose,newGender; //define the variables 
    private String messages,unlock,action,select,newName,newBirthdate,newHome,newMobile;  
    
    private String[] list; //define an array to show the list of contacts
    private int count; //count the contacts
        
    Scanner scan = new Scanner(System.in); //define a new scan to print in


    public CallOrText() // constructor method
    {
     mobilefacilities = new MobileFacilities();
     mobilefacilities.setTracing(false);
     setTracing(false); 
     count = 0; // initialize the number of the interacted contacts 
     list = new String[SIZE];       
     mobilefacilities.setUp(0);
     intro();
    }

    //*************************************************************************    
    // Initial introduction reporting the number of contacts 
    //*************************************************************************
    public void intro()
    {    
         trace("open the phone");
         
         //to introduce the function of this program 
         System.out.println("You have "+ mobilefacilities.getNumContacts() +" contacts.");
         
         //ask the user whether or not to unlock the phone
         System.out.println("\nDo you wish to unlock your phone?");
         System.out.print("Enter choice (Y,N): ");
         unlock = scan.next();
         if (unlock.equalsIgnoreCase("y")) //ensure the user deicide to unlock phone
             use();
         else
             return;
     } 

    //*************************************************************************
    // Start to use the simulated mobile phone.
    //*************************************************************************     
    public void use()
    {
        trace("use phone to find target contact");
        
        //show the information of the contact
        System.out.println("\nContact #"+mobilefacilities.getCurrentNumber());
        System.out.println("Name: "+mobilefacilities.getCurrentName());
        System.out.println("Gender: "+mobilefacilities.getCurrentGender());            
        System.out.println("Home: #"+mobilefacilities.getCurrentHomeNumber());
        System.out.println("Mobile: #"+mobilefacilities.getCurrentMobileNumber());
        System.out.println("Birthdate: "+mobilefacilities.getCurrentBirthdate());
        action();
    }


    //*************************************************************************
    // Browse the contact list, select someone or turn off the phone.
    //*************************************************************************    
    private void action()
    {    
        System.out.print("\nPlease enter your choice (P,N,S,Q): ");
        action = scan.next();         
        if (action.equalsIgnoreCase("p"))
        {
            mobilefacilities.getPrevious();
            use();
        }
        
        else if (action.equalsIgnoreCase("n"))
        {
            mobilefacilities.getNext();
            use();
        }
            
        else if (action.equalsIgnoreCase("s"))
            select();
  
        else if (action.equalsIgnoreCase("q"))
        {
            System.out.print("Do you wish to turn off your phone? (Y/N): ");
            unlock = scan.next();
            if (unlock.equalsIgnoreCase("y"))  
                list();  //show the list of contacts before exit             
            else                 
                action();
        }        
            
         else
         {   
             System.out.println("Please enter the correct choice.");
             action();
         }
    }  

    //*************************************************************************
    // Choose what to do with the selected contact.
    //*************************************************************************    
    private void select()
    {         
        trace("select contact to call or text");
        
        System.out.println("\nWhat do you want to do with this contact?");
        System.out.print("Please enter your choice (E,C,T,R): ");
        select = scan.next();
        if (select.equalsIgnoreCase("e"))
            edit();
            
        else if (select.equalsIgnoreCase("c"))                    
            calling();

        else if (select.equalsIgnoreCase("t"))    
            text();

        else if (select.equalsIgnoreCase("r"))    
            use();
                
        else
        {       
            System.out.println("Please enter the correct choice.");
            select();               
        }
    }  

    //*************************************************************************
    // Call the contact or give a voice mail if the contact is not available.
    //*************************************************************************  
    private void calling()
    {
        trace("calling the contact " +mobilefacilities.getCurrentName());  
        if (mobilefacilities.call())
        {
            scan.nextLine(); 
            mobilefacilities.converse(scan); //the contact is available
            trace("calling the contact: true"); 
        }
             
        else
        {
            scan.nextLine();    
            mobilefacilities.voiceMail(scan);//the contact is not available and voicemail is used 
            trace("calling the contact: false");         
        }
        
        list[count] = "CALL " + mobilefacilities.getCurrentName();
        count++; //trace the contact that the user called
        
        select();
    }


    //*************************************************************************
    // Write and send message if the contact has mobile phone number.
    //************************************************************************* 
    private void text()
    {
        if (mobilefacilities.newMsg())
        {
            trace("text the contact" +mobilefacilities.getCurrentName());
           
            System.out.println("\nEnter message one line at a time, type SEND to stop and send...");
            scan.nextLine();
            messages = scan.nextLine();
            while((!messages.equalsIgnoreCase("SEND")))
            {
                mobilefacilities.addMsgLine(messages);
                messages = scan.nextLine();                
            }
            mobilefacilities.sendMsg(); //after receive the send signal send the message
            
            list[count] = "TEXT "+mobilefacilities.getCurrentName();
            count++; //trace the contact that the user texted
            select();
            
        }
        else
        {
            System.out.println("No mobile phone number. Message can not be send.");
            select();
        }
    }
    
    //*************************************************************************
    // Choose to edit contact information.
    //*************************************************************************    
    private void edit()
    {    
        trace("choose the certain function to edit contact information");
        
        System.out.println("\n0. Return without changing contact details");
        System.out.println("1. Edit name");
        System.out.println("2. Edit gender");
        System.out.println("3. Edit home number");
        System.out.println("4. Edit mobile number");
        System.out.println("5. Edit birthdate");
        System.out.print("Please enter your choice (0,1,2,3,4,5): ");
        
        choose = scan.next().charAt(0);
        switch (choose)
        {
            case '0':
                select();
                break;
               
            case '1':
                System.out.println("Enter new name:");
                scan.nextLine();               
                newName = scan.nextLine();
                mobilefacilities.changeName(newName);
                System.out.println("Name was successfully changed!");
                trace("change name: true");
                edit();
                break;
               
            case '2':
                System.out.println("Enter new gender:");
                scan.nextLine();
                newGender = scan.next().charAt(0);
                if (mobilefacilities.changeGender(newGender))
                    System.out.println("Gender was successfully changed!");
                else
                    System.out.println("Failed to edit. Please check the format (m/f).");
                edit();  
                break;
            
            case '3': 
                System.out.println("Enter new home number:");
                scan.nextLine();
                newHome = scan.nextLine();
                if (mobilefacilities.changeHomeNumber(newHome))
                    System.out.println("Home number was successfully changed!");
                else
                    System.out.println("Failed to edit. Please check the format (10 digits).");
                edit(); 
                break;
            
            case '4': 
                System.out.println("Enter new mobile phone number:");
                scan.nextLine();
                newMobile = scan.nextLine();
                if (mobilefacilities.changeMobileNumber(newMobile))
                    System.out.println("Mobile phone number was successfully changed!");
                else
                    System.out.println("Failed to edit. Please check the format (10 digits).");
                edit(); 
                break;
            
            case '5': 
                System.out.println("Enter new birthdate:");
                scan.nextLine();
                newBirthdate = scan.nextLine();
                if (mobilefacilities.changeBirthDate(newBirthdate))
                    System.out.println("Birthdate was successfully changed!");
                else
                    System.out.println("Failed to edit. Please check the format (date legality).");
                edit();                
                break;
                
            default:
                System.out.println("Please enter the correct choice.");
                edit();

        }             
    }
    
    //*************************************************************************
    // Show the contacts called and texted in order before turn off the phone.
    //*************************************************************************
    private void list()
    {
        trace("show contacts list");
        for (String element:list)
        {
        if(element != null) //valid element 
        System.out.println(element); 
        }   
        System.exit(0);    
    } 
   
    //*************************************************************************
    // Manage trace messages.
    //*************************************************************************
    public void setTracing(boolean on)
    {
        tracing = on;    
    }
    
    public void trace(String message)
    {
        if (tracing)
        {
            
            System.out.println("CallOrText: " + message);
        }
    }
}

