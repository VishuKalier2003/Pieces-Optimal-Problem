/* You are given two strings start and target, both of length n... Each string consists only of the characters 'L', 'R', and '_' where:
* The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right...
* The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces...
Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times... Otherwise, return false...
  * Eg 1: Input = _L__R_R_      Output = L_____RR       Transformation = True
  * Eg 2: Input = _R_L          Output = __LR           Transformation = False 
  * Eg 3: Input = _R            Output = R_             Transformation = False
  * Eg 4: Input = L_            Output = R_             Transformation = False           */
import java.util.*;
public class Pieces
{
    public boolean ConvertString(String str, String st)
    {
        int leftIndex = 0, rightIndex = 0, rightCount = 0, leftCount = 0;
        for(int i = 0, j = str.length()-1; i < str.length(); i++, j--)
        {
            if(str.charAt(i) == 'L')    // Finding the Left most 'L'...
                leftIndex = i;
            else if(str.charAt(j) == 'R')  // Finding the Rightmost 'R'...
                rightIndex = j;
        }
        int temp = leftIndex;
        while(temp != -1)
        {
            if(str.charAt(temp) == 'R')
                rightCount++;     // Finding number of 'R' left to the first 'L'...
            temp--;
        }
        temp = rightIndex;
        while(temp != str.length())
        {
            if(str.charAt(temp) == 'L')
                leftCount++;     // Finding number of 'L' right to the first 'R'...
            temp++;
        }
        int checkLeft = 0, checkRight = 0, r1Count = 0, l1Count = 0;
        for(int i = 0, j = str.length()-1; i < str.length(); i++, j--)
        {
            if(st.charAt(i) == 'L')
                checkLeft = i;       // Similarly we check for the transformed String...
            else if(st.charAt(j) == 'R')
                checkRight = j;      // Evaluating the position of 'L' and 'R'...
        }
        temp = checkLeft;
        while(temp != -1)
        {
            if(st.charAt(temp) == 'R')
                r1Count++;   // Finding number of 'R' left to first 'L' in the transformed String...
            temp--;
        }
        temp = checkRight;
        while(temp != st.length())
        {
            if(st.charAt(temp) == 'L')
                l1Count++;  // Finding number of 'L' right to first 'R' in the transformed String...
            temp++;
        }
        if((checkLeft > leftIndex) || (checkRight < rightIndex))
            return false;    // If there is a controversy in indices...
        if((leftCount > l1Count) || (rightCount > r1Count))
            return false;    // If the number of 'L' or 'R' on left and right are different in the new String...
        return true;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String str, st;
        System.out.print("Enter the Input String : ");
        str = sc.next();
        System.out.print("Enter the Output String : ");
        st = sc.next();
        Pieces pieces = new Pieces();     // Object creation...
        System.out.println("String Conversion possible : "+pieces.ConvertString(str, st));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(1) space...

/* DEDUCTIONS :- 
 * 1. Since L can move Left and R can move Right, if L moves Right or R moves Left, the sequence will becomes invalid...
 * 2. If the number of R before the first L are higher than that of the changed string, the string formation is not possible... Converse is also True...
 * 3. Also, if the last position of the L found in the input string is lower than that of the Output String then the sequence is invalid... Converse is also True...
 * 4. The blank spaces are just to deceive, assume solving without blank spaces as if there is no movement of L and R possible because we do not care about how it will be transformed, we only care about whether it is possible to transform or not...
*/