import java.util.*;
interface Media //observer
{
public void update(float interest_rate);
}

interface Bank //Subject
{
public void registerMedia(Media observer); //media will subscribe or register to get updates
public void removeMedia(Media observer); //media can be removed
public void notifyMedias(); //notifications to media
}

class BankLoan implements Bank
{
private ArrayList<Media> observers = new ArrayList<Media>();
private String loanType;
private float interest_rate;
private String name;

public BankLoan(String loanType, float interest_rate, String name)
{
this.loanType = loanType;
this.interest_rate = interest_rate;
this.name = name;
}

public float getInterestRate()
{
return interest_rate;
}

public void setInterestRate(float interest_rate)
{
this.interest_rate = interest_rate;
notifyMedias();
}

public String getName()
{
return this.name;
}

public String getloanType()
{
return this.loanType;
}

@Override
public void registerMedia(Media observer) // adding media instance to the ArrayList
{
observers.add(observer);
}

@Override
public void removeMedia(Media observer) // removing media from the ArrayList
{
observers.remove(observer);
}

@Override
public void notifyMedias()
{
for (Media ob : observers)
{
System.out.println("\nattention! intrest rates are changed !!");
ob.update(this.interest_rate);
}

}

}

class Newspaper implements Media
{
@Override
public void update(float interest_rate)
{
System.out.println("communication Media : Newyorktimes \nMessage:updated intest rate: "+ interest_rate);
System.out.println("\n____________________________________");

}
}

class Television implements Media
{
@Override
public void update(float interest_rate)
{
System.out.println("communication Media Name: Times NOW \nMessage:updated intrest rate: " + interest_rate);
System.out.println("\n____________________________________");
}
}

public class Main //starting point for an application
{
public static void main(String args[]) //main method
{
  
Newspaper n = new Newspaper();
Television t = new Television();

BankLoan bl = new BankLoan("Personal Loan", 10.7f, "Citi");
bl.registerMedia(n);
bl.registerMedia(t);
bl.setInterestRate(5.5f);

}
}

