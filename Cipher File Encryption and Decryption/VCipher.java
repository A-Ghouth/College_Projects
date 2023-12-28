/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**

 @author xDoni
 */
public class VCipher extends Cipher
{

    char[][] cipher;
    int Counter = 0;       // FOR ENCODE THE NEXT ARRAY
    String keyword;

    public VCipher(String keyword)
    {
        this.keyword = keyword;
        super.generateAlphabets();
        cipher = new char[keyword.length()][26];
////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < keyword.length(); i++)
        {
            cipher[i][0] = keyword.charAt(i);
            for (int j = 1; j < 26; j++)
            {
                generateArray(cipher, keyword.charAt(i), i);
            }
        }
////////////////////////////////////////////////////////////////////////////////
/* System.out.println("VCipher");
for (int i = 0; i < keyword.length(); i++) {
System.out.print("| "+(i+1)+" | ");
for (int j = 0; j < 26; j++) {
System.out.print( cipher[i][j]);
}
System.out.println("");
}*/
////////////////////////////////////////////////////////////////////////////////           
/*  System.out.print("\n"+"Alphabet = ");
for (int i = 0; i < 26; i++) {
System.out.print( alphabet[i]);
}*/
    }
////////////////////////////////////////////////////////////////////////////////        

    @Override
    public char encode(char Letter)
    {
        for (int j = 0; j < 26; j++)
        {

            if (Letter == alphabet[j])
            {
                /// MOVE THIS
                Letter = cipher[Counter][j];
                Counter++;
                if (Counter == keyword.length())
                {
                    Counter = 0;
                }
                break;
            }
        }
        return Letter;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public char decode(char Letter)
    {
        for (int j = 0; j < 26; j++)
        {
            if (Letter == cipher[Counter][j])
            {
                Counter++;
                Letter = alphabet[j];
                if (Counter == keyword.length())
                {
                    Counter = 0;
                }
                break;
            }
        }
        return Letter;
    }
////////////////////////////////////////////////////////////////////////////////

    public void generateArray(char[][] cipher, char KEYLetter, int ALPHLetter)
    {
        for (int i = 0; i < 26; i++)
        {
            cipher[ALPHLetter][i] = KEYLetter;
            if (KEYLetter == 'Z')
            {
                KEYLetter = 64; // A -1 
            }
            KEYLetter = (char) (KEYLetter + 1);
        }
    }
}
