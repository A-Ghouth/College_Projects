
public class MonoCipher extends Cipher
{

    private char[] cipher;

    public MonoCipher(String keyword)
    {
        cipher = new char[26];
        super.generateAlphabets();
////////////////////////////////////////////////////////////////////////////////    
        for (int i = 0; i < keyword.length(); i++)
        {
            cipher[i] = keyword.charAt(i);
        }
////////////////////////////////////////////////////////////////////////////////
        boolean flag;
        char letter = 'Z';

        for (int i = keyword.length(); i < 26; i++)
        {
            flag = true;
            for (int j = 0; j < keyword.length(); j++)
            {
                if (letter == cipher[j])
                {
                    flag = false;
                }
            }
            if (flag == true)             // TO GET TO NEXT PLACE IN ARRAY 
            {
                cipher[i] = letter;
                i = i + 1;
            }
            i = i - 1;                        // TO DON'T CHANGE THE PLACE
            letter = (char) (letter - 1);
        }
////////////////////////////////////////////////////////////////////////////////
/* System.out.print("MonoCipher = ");
for (int i = 0; i < 26; i++) {
System.out.print( cipher[i]);
}
System.out.print("\n"+"Alphabet = ");
for (int i = 0; i < 26; i++) {
System.out.print( alphabet[i]);
}*/
    }
////////////////////////////////////////////////////////////////////////////////        

    @Override
    public char encode(char ch)
    {
        int NO = ch;
        for (int j = 0; j < 26; j++)
        {
            if (ch == alphabet[j])
            {
                NO = cipher[j];
                break;
            }
        }
        return (char) NO;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public char decode(char ch)
    {
        int Number = ch;
        for (int j = 0; j < 26; j++)
        {
            if (ch == cipher[j])
            {
                Number = alphabet[j];
                break;
            }
        }
        return (char) Number;
    }
}
