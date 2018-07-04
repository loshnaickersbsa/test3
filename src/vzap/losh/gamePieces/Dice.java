package vzap.losh.gamePieces;

public class Dice
{

   private int faceValue; //
   
   public Dice()
   {
      faceValue = (int) (Math.random() * 6 + 1);
   }
   public void rollDice()
   {
      faceValue = (int) (Math.random() * 6 + 1);
   } 
   public int getFaceValue()
   {
      return faceValue;
   }
   
   public boolean equals(Dice incomingDice)
   {
      //keyword
      return this.getFaceValue() == incomingDice.getFaceValue();// method used because face value is private
      
   }
   
   public String toString()
   {

      return  "Face value:  " + faceValue;
   
   }
   
 }
   