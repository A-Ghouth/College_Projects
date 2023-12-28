/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**

 @author xDoni
 */
abstract class Cipher
{

    protected final int SIZE = 26;

    protected char[] alphabet;

    public void generateAlphabets()
    {
        alphabet = new char[SIZE];
        char Letter = 'A';
        for (int i = 0; i < 26; i++)
        {
            alphabet[i] = Letter;
            Letter = (char) (Letter + 1);
            //  System.out.println(x);
        }
    }

    public abstract char encode(char ch);

    public abstract char decode(char ch);

}
