/** A class to hold and manage a student record's data
 *
 *  Note that this class does not have any direct appearance on the screen,
 *  and "understands" no events - that is the responsibility of the associated
 *  main program GUI class.
 *
 *  SBJ April 2014
 */

public class StudentRecord 
{
    // Personal details
    private String name;
    private String address;                        // Will initially be blank; modifiable
    private String registrationNo;
    private String degreeProgram;

    // Academic details
    private int creditsObtained;                   // Will start at 0; incrementable

    // This constructor is called to set up the student record with essential information
    public StudentRecord(String theName, String theRegistrationNo, String theDegreeProgram) 
    {
        name = theName;
        address = "";                                // Initially unknown
        registrationNo = theRegistrationNo;
        creditsObtained = 0;                         // None obtained at start
        degreeProgram = theDegreeProgram;
    }

    // This method is called to obtain the name of the student
    public String getName() 
    {
        return name;
    }

    // This method is called to change the address held to that given as parameter
    public void setAddress(String theAddress) 
    {
        address = theAddress;
    }

    // This method is called to obtain the current address of the student
    public String getAddress() 
    {
        return address;
    }

    // This method is called to obtain the registration number of the student
    public String getRegistrationNo() 
    {
        return registrationNo;
    }

    // This method is called to add one more credit to the student's record
    public void addACredit() 
    {
        creditsObtained++;
    }

    // This method is called to obtain the current number of credits held by the student
    public int getCreditsObtained() 
    {
        return creditsObtained;
    }
    
    public void  multipleCredit(int theCreditsObtained)
    {
       creditsObtained = creditsObtained + theCreditsObtained;
    }
    // This method returns a single string summarising the student's details.
    public String toString()
    {
        return "Student: " + name + ", " + registrationNo + ", " + creditsObtained + " credits" +", " + degreeProgram;
    }

}