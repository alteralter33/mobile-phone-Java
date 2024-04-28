/**
    MobileFacilities class 
    @author: 535353 Zhang Zihan & 535359 Wang Yiwen
    @version: 1.0 
    KXO151 Programming & Problem Solving
    purpose: resources for assignment 3, semester 1, 2020
*/

import java.util.Random;
import java.util.Scanner;

public class MobileFacilities 
{
 //contacts (names, gender, home number, mobile number, birthdates)
 private String[] names={"Minnie Mouse", "Homer Simpson", "Eric Cartman",
       "Bob the Builder", "Lightning McQueen", "Dorothy the Dinosaur"};
 private char[] genders={'f', 'm', 'm',
       'm', 'm', 'f'};
 private String[] homeNumbers={"0363654321", "0364192834", "0362262001",
          "", "", "0298341333"};
 private String[] mobileNumbers={"0418123456", "", "",
         "0898356455", "0292773344", ""};
 private String[] birthDates={"19380629", "19700110", "20010802",
         "19830321", "", ""};
 private boolean tracing; // do we want to see trace output, true or false
 private Random generator; // random number generator
 private int currentContact; // index into above arrays, but numbered from 1
 private String msg;   // current text message for SMS
 private int msgLength=0; // number of lines in current text message
 
    /** MobileFacilities
  Constructor
  Initialises instance variables
    */
 public MobileFacilities()
 {
  tracing = false;   // show trace output
  generator=new Random(); // instantiate random number generator
  currentContact=1;  // start with first contact
  msg="";     // current message is empty
  msgLength=0;   // current message is empty
 }
    
    /** setUp
        @param int - indicates whether to synchronise random number generator
        0 synchronises random number generator for predictable execution
        any other number leaves pseudo random number generator alone
    */
 public void setUp(int seed)
 {
  trace("setUp() called");
  if (seed ==0)
  {
   generator.setSeed(1234);
   trace("setUp: rng seeded");
  }
  trace("setUp() left");
 }
 
    /** getNumContacts
        @return int - number of contacts in contact list
        determine number of contacts in contact list
    */
 public int getNumContacts()
 {
  trace("getNumContacts() called and exited with return value of " + names.length);
  return names.length;
 }
 
    /** getCurrentNumber
        @return int - number (index) of current contact
        determine real-world (not array index) number of current contact in contact list
    */
 public int getCurrentNumber()
 {
  trace("getCurrentNumber() called and exited with return value of " + currentContact);
  return currentContact;
 }
 
    /** getCurrentName
        @return String - name of current contact
        determine name of current contact from contact list
    */
 public String getCurrentName()
 {
  trace("getCurrentName() called and exited with return value of " + names[currentContact-1]);
  return names[currentContact-1];
 }
 
    /** getCurrentGender
        @return String - gender of current contact
        determine gender of current contact from contact list
    */
 public char getCurrentGender()
 {
  trace("getCurrentGender() called and exited with return value of " + genders[currentContact-1]);
  return genders[currentContact-1];
 }
 
    /** getCurrentHomeNumber
        @return String - home number of current contact (may be empty)
        determine home number of current contact from contact list
    */
 public String getCurrentHomeNumber()
 {
  trace("getCurrentHomeNumber() called and exited with return value of " + homeNumbers[currentContact-1]);
  return homeNumbers[currentContact-1];
 }
 
     /** getCurrentMobileNumber
        @return String - mobile number of current contact (may be empty)
        determine mobile number of current contact from contact list
    */
 public String getCurrentMobileNumber()
 {
  trace("getCurrentMobileNumber() called and exited with return value of " + mobileNumbers[currentContact-1]);
  return mobileNumbers[currentContact-1];
 }
 
    /** getCurrentBirthdate
        @return String - birthdate of current contact (may be empty)
        determine birthdate of current contact from contact list
    */
 public String getCurrentBirthdate()
 {
  trace("getCurrentBirthdate() called and exited with return value of " + birthDates[currentContact-1]);
  return birthDates[currentContact-1];
 }
 
    /** getPrevious
        retreat to previous contact in contact list, moving back to end if already at start
    */
 public void getPrevious()
 {
  trace("getPrevious() called");
  if (currentContact == 1) // at beginning so go back to end
  {
   trace("getPrevious: rolled back to end");
   currentContact=names.length;
  }
  else // retreat
  {
   trace("getPrevious: contact retreated");
   currentContact--;
  }
  trace("getPrevious() left");
 }
 
    /** getNext
        advance to next contact in contact list, moving forward to start if already at end
    */
 public void getNext()
 {
  trace("getNext() called");
  if (currentContact == names.length) // at end so go on to start
  {
   trace("getNext: rolled back to start");
   currentContact=1;
  }
  else // advance
  {
   trace("getNext: contact advanced");
   currentContact++;
  }
  trace("getNext() left");
 }

    /** call
        @return boolean - whether or not call was connected (true) or diverted to message bank (false)
        attempt to make a phone call with current contact
    */
 public boolean call()
 {
  trace("call() called");
  System.out.print("Calling ");
  if (! homeNumbers[currentContact-1].equals("")) // home number known so call that
  {
   trace("call: displaying home number");
   System.out.println("(H) " + homeNumbers[currentContact-1]+"...");
  }
  else // calling mobile
  {
   trace("call: displaying mobile number");
   System.out.println("(M) " + mobileNumbers[currentContact-1]+"...");
  }
  
  if (generator.nextInt(100)<65) //person unavailable 65% of the time
  {
   trace("call() left with return value: false");
   return false;
  }
  else // person available 35% of the time
  {
   trace("call() left with return value: false");
   return true;
  }
 }
 
    /** converse
        @param Scanner - connection to the user for inward information flow
        talk to current contact after establishing a call
    */
 public void converse(Scanner sc)
 {
  String line;
  
  trace("converse() called");
  System.out.println("Hello?");
  line=sc.nextLine();
  System.out.println("Who's this?");
  line=sc.nextLine();
  System.out.println("How'd you get this number?");
  line=sc.nextLine();
  System.out.println("Don't call me at work!  Bye!");
  trace("converse() left");
 }
 
    /** voiceMail
        @param Scanner - connection to the user for inward information flow
        @return int -  synchronises random number generator for predictable execution
        leave voice message for current contact after establishing a call
    */
 public void voiceMail(Scanner sc)
 {
  String line;
  
  trace("voiceMail() called");
  System.out.println("You have reached the voicemail for " + names[currentContact-1] + ".");
  System.out.println("Please enter your message...");
  line=sc.nextLine();
  System.out.println("Message stored.");
  trace("voiceMail() left");
 }

    /** newMsg
        @return boolean - whether or not message could be sent (mobile number needed)
        try to begin composition of new message to current contact from contact list
    */
 public boolean newMsg()
 {
  trace("newMsg() called");
  // initialise current message
  msg="";
  msgLength=0;
  
  if (mobileNumbers[currentContact-1].equals("")) // don't know their mobile number
  {
   trace("newMsg() left with return value: false");
   return false;
  }
  else // have their mobile number
  {
   trace("newMsg() left with return value: false");
   return true;
  }
 } 
  
    /** addMsgLine
        @param String - line to be added to existing message
        build up existing message to current contact from contact list
    */
 public void addMsgLine(String line)
 {
  int origLength;
  
  trace("addMsgLine() called");
  origLength=msg.length(); // ensure message has been initialised
  msg=msg + "\n" + line;  // add line to existing message
  msgLength++;    // update counter of message length
  trace("addMsgLine() left");
 }
 
    /** sendMsg
        send existing message to current contact from contact list
    */
 public void sendMsg()
 {
  int origLength;
  
  trace("sendMsg() called");
  origLength=msg.length(); // ensure message has been initialised
  System.out.println(msgLength + " line message sent."); // 'send' message
  trace("sendMsg() left");
 }
 
    /** changeName
        @param String - new name for current contact from contact list
        @return boolean - whether or not name was updated (fails if new value is invalid)
        change the name of the current contact from the contact list
    */
 public boolean changeName(String name)
 {
  trace("changeName() called");
  if (name.equals("")) // empty, discard
  {
   trace("changeName() left with return value: false");
   return false;
  }
  else // 'valid' name
  {
   names[currentContact-1]=name;
   trace("changeName() left with return value: true");
   return true;
  }
 }
 
    /** changeGender
        @param char - new gender for current contact from contact list
        @return boolean - whether or not gender was updated (fails if new value is invalid)
        change the gender of the current contact from the contact list
    */
 public boolean changeGender(char gender)
 {
  trace("changeGender() called");
  gender=Character.toLowerCase(gender);
  if ((gender != 'm') && (gender != 'f')) // invalid gender
  {
   trace("changeGender() left with return value: false");
   return false;
  }
  else // valid gender
  {
   genders[currentContact-1]=gender;
   trace("changeGender() left with return value: true");
   return true;
  }
 }
 
    /** checkNumber
        @param String - phone number to check validity (not accuracy) of
        @return boolean - whether given value is likely to be a phone number
        check that the given value is numeric and 10 digits in length
    */
 public boolean checkNumber(String number)
 {
  trace("checkNumber() called");
  if (number.length() != 10) // invalid length
  {
   trace("checkNumber() left with return value: false");
   return false;
  }
  else
  {
   for (int i=0; i<number.length(); i++) // check all characters
   {
    if (! Character.isDigit(number.charAt(i))) // check if not a digit
    {
     trace("checkNumber() left with return value: false");
     return false;
    }
   }
   trace("checkNumber() left with return value: true");
   return true;
  }
 }
 
    /** changeHomeNumber
        @param String - new home number for current contact from contact list
        @return boolean - whether or not home number was updated (fails if new value is invalid)
        change the home number of the current contact from the contact list
    */
 public boolean changeHomeNumber(String number)
 {
  trace("changeHomeNumber() called");
  if (! checkNumber(number)) // not a phone number
  {
   trace("changeHomeNumber() left with return value: false");
   return false;
  }
  else // valid
  {
   homeNumbers[currentContact-1]=number;
   trace("changeHomeNumber() left with return value: true");
   return true;
  }
 }
 
     /** changeMobileNumber
        @param String - new mobile number for current contact from contact list
        @return boolean - whether or not mobile number was updated (fails if new value is invalid)
        change the mobile number of the current contact from the contact list
    */
 public boolean changeMobileNumber(String number)
 {
  trace("changeMobileNumber() called");
  if (! checkNumber(number)) // not a phone number
  {
   trace("changeMobileNumber() left with return value: false");
   return false;
  }
  else // valid
  {
   mobileNumbers[currentContact-1]=number;
   trace("changeMobileNumber() left with return value: true");
   return true;
  }
 }
 
     /** changeBirthDate
        @param String - new birthdate for current contact from contact list
        @return boolean - whether or not birthdate was updated (fails if new value is invalid)
        change the birthdate of the current contact from the contact list
    */
 public boolean changeBirthDate(String date)
 {
  trace("changeBirthDate() called");
  if (! checkDate(date)) // invalid date
  {
   trace("changeBirthDate() left with return value: false");
   return false;
  }
  else // valid
  {
   birthDates[currentContact-1]=date;
   trace("changeBirthDate() left with return value: true");
   return true;
  }
 }
 
    /** checkDate
        @param String - date to be validated in YYYYMMDD format
        @return boolean - whether or not date is valid
        determine if a given YYYYMMDD date is valid
    */
 public boolean checkDate(String date)
 {
  final int[] days={31,28,31,30,31,30,31,31,30,31,30,31}; // number of days in each month
  
  int year; // year component of parameter
  int month; // month component of parameter
  int day; // day component of parameter
  int limit; // upper limit of days in given month
  
  trace("checkDate() called");
  if (date.length() != 8) // invalid format based on length
  {
   trace("checkDate() left with return value: false");
   return false;
  }
  else // check contents
  {
   trace("checkDate: length ok");
   for (int i=0; i<date.length(); i++) // check each character
   {
    if (! Character.isDigit(date.charAt(i))) // found non-digit
    {
     trace("checkDate() left with return value: false");
     return false;
    }
   }
   
   trace("checkDate: numbers only");
   month=Integer.valueOf(date.substring(4,6));
   if ((month == 0) || (month > 12)) //  month is not between 1 and 12
   {
    trace("checkDate() left with return value: false");
    return false;
   }
   else // month ok, check day
   {
    limit=days[month-1];
    day=Integer.valueOf(date.substring(6,8));
    if (month == 2) // month is February, check for leap years
    {
     trace("checkDate: February");
     year=Integer.valueOf(date.substring(0,4));

     if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) // leap year
     {
      trace("checkDate: leap year");
      limit++;
     }
    }
    if ((day == 0) || (day > limit)) // day is outside bounds
    {
     trace("checkDate() left with return value: false");
     return false;
    }
   }
   trace("checkDate() left with return value: true");
   return true; // everything ok
  }
 }
 
 /** setTracing
        @param boolean - indicates state of tracing messages
        true turns them on
        false turns them off        
    */
 public void setTracing(boolean on)
 {
  tracing = on;
 }
    
    /** trace
        @param String - the message
        use to display tracing messages
        shown if the variable tracing is true
    */
 public void trace(String message)
 {
  if (tracing)
  {
   System.out.println("MobileFacilities: " + message);
  }
 }
}

