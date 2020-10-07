package com.mindtree.yoyogift.utility;

public class ReferalCodeUtil {

	public static String Encryption(String str) {
		String Newstr = " ";
		try {

			for (int i = 0; i < str.length(); i++) {
				char ch = Character.toLowerCase(str.charAt(i));
				switch (ch) {
				case 'a':
					Newstr = Newstr + "Z";
					break;
				case 'b':
					Newstr = Newstr + "Y";
					break;
				case 'c':
					Newstr = Newstr + "X";
					break;
				case 'd':
					Newstr = Newstr + "W";
					break;
				case 'e':
					Newstr = Newstr + "V";
					break;
				case 'f':
					Newstr = Newstr + "U";
					break;
				case 'g':
					Newstr = Newstr + "T";
					break;
				case 'h':
					Newstr = Newstr + "S";
					break;
				case 'i':
					Newstr = Newstr + "R";
					break;
				case 'j':
					Newstr = Newstr + "Q";
					break;
				case 'k':
					Newstr = Newstr + "P";
					break;
				case 'l':
					Newstr = Newstr + "O";
					break;
				case 'm':
					Newstr = Newstr + "N";
					break;
				case 'n':
					Newstr = Newstr + "M";
					break;
				case 'o':
					Newstr = Newstr + "L";
					break;
				case 'p':
					Newstr = Newstr + "K";
					break;
				case 'q':
					Newstr = Newstr + "J";
					break;
				case 'r':
					Newstr = Newstr + "I";
					break;
				case 's':
					Newstr = Newstr + "H";
					break;
				case 't':
					Newstr = Newstr + "G";
					break;
				case 'u':
					Newstr = Newstr + "F";
					break;
				case 'v':
					Newstr = Newstr + "E";
					break;
				case 'w':
					Newstr = Newstr + "D";
					break;
				case 'x':
					Newstr = Newstr + "C";
					break;
				case 'y':
					Newstr = Newstr + "B";
					break;
				case 'z':
					Newstr = Newstr + "A";
					break;
				case ' ':
					Newstr = Newstr + " ";
					break;
				case '.':
					Newstr = Newstr + '3';
					break;
				case ',':
					Newstr = Newstr + "1";
					break;
				case '(':
					Newstr = Newstr + '4';
					break;
				case '\"':
					Newstr = Newstr + '5';
					break;
				case ')':
					Newstr = Newstr + "7";
					break;
				case '?':
					Newstr = Newstr + "2";
					break;
				case '!':
					Newstr = Newstr + "8";
					break;
				case '-':
					Newstr = Newstr + "6";
					break;
				case '%':
					Newstr = Newstr + "9";
					break;
				case '1':
					Newstr = Newstr + "a";
					break;
				case '2':
					Newstr = Newstr + "b";
					break;
				case '3':
					Newstr = Newstr + "c";
					break;
				case '4':
					Newstr = Newstr + "d";
					break;
				case '5':
					Newstr = Newstr + "e";
					break;
				case '6':
					Newstr = Newstr + "f";
					break;
				case '7':
					Newstr = Newstr + "g";
					break;
				case '8':
					Newstr = Newstr + "h";
					break;
				case '9':
					Newstr = Newstr + "i";
					break;
				case '0':
					Newstr = Newstr + "j";
					break;
				default:
					Newstr = Newstr + "0";
					break;
				}
			}
		} catch (Exception ioe) {
			System.out.println(ioe.getMessage());
		}
		return Newstr;
	}

	public static String Encoder(String str) {
		String Newstr = " ";
		try {

			for (int i = 0; i < str.length(); i++) {
				char ch = Character.toLowerCase(str.charAt(i));
				switch (ch) {
				case 'Z':
					Newstr = Newstr + "a";
					break;
				case 'Y':
					Newstr = Newstr + "b";
					break;
				case 'X':
					Newstr = Newstr + "c";
					break;
				case 'W':
					Newstr = Newstr + "d";
					break;
				case 'V':
					Newstr = Newstr + "e";
					break;
				case 'U':
					Newstr = Newstr + "f";
					break;
				case 'T':
					Newstr = Newstr + "g";
					break;
				case 'S':
					Newstr = Newstr + "h";
					break;
				case 'R':
					Newstr = Newstr + "i";
					break;
				case 'Q':
					Newstr = Newstr + "j";
					break;
				case 'P':
					Newstr = Newstr + "k";
					break;
				case 'O':
					Newstr = Newstr + "l";
					break;
				case 'N':
					Newstr = Newstr + "m";
					break;
				case 'M':
					Newstr = Newstr + "n";
					break;
				case 'L':
					Newstr = Newstr + "o";
					break;
				case 'K':
					Newstr = Newstr + "p";
					break;
				case 'J':
					Newstr = Newstr + "q";
					break;
				case 'I':
					Newstr = Newstr + "r";
					break;
				case 'H':
					Newstr = Newstr + "s";
					break;
				case 'G':
					Newstr = Newstr + "t";
					break;
				case 'F':
					Newstr = Newstr + "u";
					break;
				case 'E':
					Newstr = Newstr + "v";
					break;
				case 'D':
					Newstr = Newstr + "w";
					break;
				case 'C':
					Newstr = Newstr + "x";
					break;
				case 'B':
					Newstr = Newstr + "y";
					break;
				case 'A':
					Newstr = Newstr + "z";
					break;
				case 'a':
					Newstr = Newstr + "1";
					break;
				case 'b':
					Newstr = Newstr + "2";
					break;
				case 'c':
					Newstr = Newstr + "3";
					break;
				case 'd':
					Newstr = Newstr + "4";
					break;
				case 'e':
					Newstr = Newstr + "5";
					break;
				case 'f':
					Newstr = Newstr + "6";
					break;
				case 'g':
					Newstr = Newstr + "7";
					break;
				case 'h':
					Newstr = Newstr + "8";
					break;
				case 'i':
					Newstr = Newstr + "9";
					break;
				case 'j':
					Newstr = Newstr + "0";
					break;
				default:
					Newstr = Newstr + "0";
					break;
				}
			}
		} catch (Exception ioe) {
			System.out.println(ioe.getMessage());
		}
		return Newstr;
	}
}
