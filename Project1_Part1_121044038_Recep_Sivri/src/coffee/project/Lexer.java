package coffee.project;

import coffee.IdentifierList;
import coffee.REPL;
import coffee.TokenList;
import coffee.datatypes.*;
import coffee.syntax.Keywords;
import coffee.syntax.Operators;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.StringTokenizer;

/**
 * Created by ft on 10/14/15.
 */
//HW01_121044038_Recep_Sivri
// project1-part1
//in this project part1 made by Recep Sivri on 26/10/2015
//     Description:
//  in this part we write a program that lexer part and this program tokens valid words.
public class Lexer implements REPL.LineInputCallback {

    public static int controlKeywords(String str){  //checks keywords from line input if valid return 1 else return 0.

        int result=0;
        TokenList tokenList= TokenList.getInstance();
        IdentifierList identifierList=IdentifierList.getInstance();
        if(str.equals(Keywords.DEFFUN)) {
            tokenList.addToken(new Keyword(Keywords.DEFFUN));
            ++result;
        }
        else
        if(str.equals(Keywords.AND)) {
            tokenList.addToken(new Keyword(Keywords.AND));
            ++result;
        }
        else
        if(str.equals(Keywords.APPEND)) {
            tokenList.addToken(new Keyword(Keywords.APPEND));
            ++result;
        }
        else
        if(str.equals(Keywords.CONCAT)) {
            tokenList.addToken(new Keyword(Keywords.CONCAT));
            ++result;
        }
        else
        if(str.equals(Keywords.ELSE)) {
            tokenList.addToken(new Keyword(Keywords.ELSE));
            ++result;
        }
        else
        if(str.equals(Keywords.EQUAL)) {
            tokenList.addToken(new Keyword(Keywords.EQUAL));
            ++result;
        }
        else
        if(str.equals(Keywords.FOR)) {
            tokenList.addToken(new Keyword(Keywords.FOR));
            ++result;
        }
        else
        if(str.equals(Keywords.IF)) {
            tokenList.addToken(new Keyword(Keywords.IF));
            ++result;
        }
        else
        if(str.equals(Keywords.NOT)) {
            tokenList.addToken(new Keyword(Keywords.NOT));
            ++result;
        }
        else
        if(str.equals(Keywords.OR)) {
            tokenList.addToken(new Keyword(Keywords.OR));
            ++result;
        }
        else
        if(str.equals(Keywords.SET)) {
            tokenList.addToken(new Keyword(Keywords.SET));
            ++result;
        }
        else
        if(str.equals(Keywords.THEN)) {
            tokenList.addToken(new Keyword(Keywords.THEN));
            ++result;
        }
        else
        if(str.equals(Keywords.WHILE)) {
            tokenList.addToken(new Keyword(Keywords.WHILE));
            ++result;
        }
        return result;
    }
    public static int controlOperators(String str){   //checks operators from line input if valid return 1 else return 0.

        TokenList tokenList= TokenList.getInstance();
        IdentifierList identifierList=IdentifierList.getInstance();
        int result=0;
        if(str.equals(Operators.ASTERISK)) {
            tokenList.addToken(new Operator (Operators.ASTERISK));
            ++result;
        }
        else
        if(str.equals(Operators.MINUS)) {
            tokenList.addToken(new Operator(Operators.MINUS));
            ++result;
        }
        else
        if(str.equals(Operators.PLUS)) {
            tokenList.addToken(new Operator(Operators.PLUS));
            ++result;
        }
        else
        if(str.equals(Operators.RIGHT_PARENTHESIS)) {
            tokenList.addToken(new Operator(Operators.RIGHT_PARENTHESIS));
            ++result;
        }
        else
        if(str.equals(Operators.SLASH)) {
            tokenList.addToken(new Operator(Operators.SLASH));
            ++result;
        }
        else
        if(str.equals(Operators.LEFT_PARENTHESIS)) {
            tokenList.addToken(new Operator(Operators.LEFT_PARENTHESIS));
            ++result;
        }
    return result;
    }
    public static int controlIdentifiers(String str){ //checks identifiers from line input if valid return 0 else return -1.

        int i=0;
        for(i=0;i<str.length();++i)
        {
            if(!(('a'<=str.charAt(i)&&'z'>=str.charAt(i)) || ('A'<=str.charAt(i)&&'Z'>=str.charAt(i))))
            return -1;
        }
        if(str.length()==0)
            return -1;
        return 0;
    }
    public static int controlIntVariable(String str) { //checks integers  from line input if valid return 0 else return -1.

        int num = 0, neg = 0, number=0;
        int i = 0;

            if(str.length()==0)
                return -1;
            if (str.length()>1&&str.charAt(0) == '-') {
                if(str.charAt(1)=='0')
                    return -1;
                for (i = 1; i < str.length(); ++i) {
                    if (!('0' <= str.charAt(i) && '9' >= str.charAt(i)))
                        return -1;
                }
                    if(str.length()>1) {
                        number = Integer.parseInt(str.substring(1, str.length()));
                        number = number * -1;
                    }
                }
                else
                {
                    if(str.charAt(0)=='0'&&str.length()>1)
                        return -1;
                for (i = 0; i < str.length(); ++i) {

                    if (!('0' <= str.charAt(i) && '9' >= str.charAt(i)))
                        return -1;
                    }
                    number = Integer.parseInt(str);
                }
                TokenList tokenList= TokenList.getInstance();
                tokenList.addToken(new ValueInt(number));
                return 1;
    }

    public static int controlBoolVariable(String str) {//checks Boolean variable from line input if valid return 0 else return -1.

        TokenList tokenList = TokenList.getInstance();
        String str2;
        str2=str.toLowerCase();
        if(str2.equals("true"))
        {
            tokenList.addToken(new ValueBool(true));
            return 0;
        }
        else
        if(str2.equals("false"))
        {
            tokenList.addToken(new ValueBool(false));
            return 0;
        }
        return -1;
    }
    @Override
    public String lineInput(String line) throws Exception {

        int statusK=0,statusO=0,statusI=0,i=0,j=0,l=0,m=0,n=0,statusIv=0,statusB=0;
        String str2,str3,str4;
        StringTokenizer arg = new StringTokenizer(line);
        IdentifierList identifierList= IdentifierList.getInstance();
        TokenList tokenList= TokenList.getInstance();
                while(arg.hasMoreElements()) // string tokenizing  until string will be empty.
                {
                    str2=arg.nextToken();// string token.
                    i=0;
                    l=0;
                    while(i<str2.length()&&str2.charAt(i)=='(')//controlling if string contains '('.
                    {
                        controlOperators("(");
                        ++i;
                    }
                    while(l<str2.length()&&str2.charAt(l)==')')//controlling if string contains ')'.
                    {
                        controlOperators(")");
                        ++l;
                    }
                    if(i!=0) {//if  start of string contains '(' make this part.

                        while (i < str2.length()) {
                            m = i;
                            while(m<str2.length()&&!(str2.charAt(m) == ')' || str2.charAt(m) == '('))//counting '(' or ')' in start of string.
                                ++m;
                            j=m;
                            str3 = str2.substring(i, j);// take substring from i to j.
                            str4 = str3.toLowerCase();// to lower case all of chars in string.
                            statusK = controlKeywords(str4);//control if lower case string is a Keyworld.
                            statusO = controlOperators(str3);//control if  string is a operator.
                            statusB = controlBoolVariable(str3);//control if string is a Boolean variable.
                            if (statusK == 0 && statusO == 0&&statusB==-1) {//if string is not a operator ,keyword or Boolean variable maybe  it is identifier
                                statusI = controlIdentifiers(str3);// controls if string is identifier.
                                if (statusI != -1){//if string is Identifier adds identifierList and token List
                                    tokenList.addToken(new Identifier(str3));
                                    identifierList.addIdentifier(str3);
                                }
                            }
                            statusIv=controlIntVariable(str3);//controls integer variable.
                            if(str3.length()>0&&statusO==0&&statusK==0&&statusB==-1&&statusI==-1&&statusIv==-1)// if string is nothing throwing exception
                            {
                                throw new Exception(str3+" is not valid word!\n");
                            }
                            if(j<str2.length()) {//checks if j is less then length of string
                                n = j;
                                if (str2.charAt(n) == '(') {//checks if end of string contains '('.
                                    while (n < str2.length() && str2.charAt(n) == '(') {
                                        controlOperators("(");
                                        ++n;
                                    }
                                    i = n;//assign n to i because i is in loop.
                                } else if (str2.charAt(n) == ')') {//checks if end of string contains ')'.
                                    while (n < str2.length() && str2.charAt(n) == ')') {
                                        controlOperators(")");
                                        ++n;
                                    }
                                    i = n;//assign n to i because i is in loop.
                                }
                            }
                            else
                                i=j;// if not end of string contains '(' or ')' assign j to i.
                        }
                    }
                    if(l!=0) {//if start of string contains ')' make this part.
                        while (l < str2.length()) {
                            m = l;
                            while(m<str2.length()&&!(str2.charAt(m) == ')' || str2.charAt(m) == '('))//counting '(' or ')' in start of string.
                                ++m;
                            j=m;
                            str3 = str2.substring(l, j);// take substring from l to j.
                            str4 = str3.toLowerCase();// to lower case all of chars in string.
                            statusK = controlKeywords(str4);//control if lower case string is a Keyworld.
                            statusO = controlOperators(str3);//control if  string is a operator.
                            statusB = controlBoolVariable(str3);//control if  string is a Boolean variable.
                            if (statusK == 0 && statusO == 0&&statusB==-1) {//if string is not a operator ,keyword or Boolean variable maybe it is identifier
                                statusI = controlIdentifiers(str3);// controls if string is identifier.
                                if (statusI != -1){//if string is Identifier adds identifierList and token List
                                    tokenList.addToken(new Identifier(str3));
                                    identifierList.addIdentifier(str3);
                                }
                            }
                            statusIv=controlIntVariable(str3);//controls integer variable.
                            if(str3.length()>0&&statusO==0&&statusK==0&&statusB==-1&&statusI==-1&&statusIv==-1)// if string is nothing throwing exception
                            {
                                throw new Exception(str3+" is not valid word!\n");
                            }
                            if(j<str2.length()) {//checks if j is less then length of string
                                n = j;
                                if (str2.charAt(n) == '(') {//checks if end of string contains '('.
                                    while (n < str2.length() && str2.charAt(n) == '(') {
                                        controlOperators("(");
                                        ++n;
                                    }
                                } else if (str2.charAt(n) == ')') {//checks if end of string contains ')'.
                                    while (n < str2.length() && str2.charAt(n) == ')') {
                                        controlOperators(")");
                                        ++n;
                                    }
                                }
                                l = n;//assign n to l because l is in loop.
                            }
                            else
                                l=j;// if not end of string contains '(' or ')' assign j to l.
                        }
                    }
                    if(l==0&&i==0) {//if start of string does not contain ')' and '(' make this part.
                        while (l < str2.length()) {
                            m = l;
                            while(m<str2.length()&&!(str2.charAt(m) == ')' || str2.charAt(m) == '('))//counting '(' or ')' in start of string.
                                ++m;
                            j=m;
                            str3 = str2.substring(l, j);// take substring from l to j.
                            str4 = str3.toLowerCase();// to lower case all of chars in string.
                            statusK = controlKeywords(str4);//control if lower case string is a Keyworld.
                            statusO = controlOperators(str3);//control if  string is a operator.
                            statusB = controlBoolVariable(str3);//control if  string is a Boolean variable.
                            if (statusK == 0 && statusO == 0&&statusB==-1) {//if string is not a operator ,keyword or Boolean variable maybe it is identifier
                                statusI = controlIdentifiers(str3);// controls if string is identifier.
                                if (statusI != -1){//if string is Identifier adds identifierList and token List
                                    tokenList.addToken(new Identifier(str3));
                                    identifierList.addIdentifier(str3);
                                }
                            }
                            statusIv=controlIntVariable(str3);//controls integer variable.
                            if(str3.length()>0&&statusO==0&&statusK==0&&statusB==-1&&statusI==-1&&statusIv==-1)// if string is nothing throwing exception
                            {
                                throw new Exception(str3+" is not valid word!\n");
                            }
                            if(j<str2.length()) {//checks if j is less then length of string
                                n = j;
                                if (str2.charAt(n) == '(') {//checks if end of string contains '('.
                                    while (n < str2.length() && str2.charAt(n) == '(') {
                                        controlOperators("(");
                                        ++n;
                                    }
                                } else if (str2.charAt(n) == ')') {//checks if end of string contains ')'.
                                    while (n < str2.length() && str2.charAt(n) == ')') {
                                        controlOperators(")");
                                        ++n;
                                    }
                                }
                                l = n;//assign n to l because l is in loop.
                            }
                            else
                                l=j;// if not end of string contains '(' or ')' assign j to l.
                        }
                    }
                    m=0;//reset (assign 0 or -1) the loop variables.
                    n=0;
                    statusK=0;
                    statusO=0;
                    statusI=-1;
                    statusB=-1;
                    statusIv=-1;
                }
        return null;
    }
}
