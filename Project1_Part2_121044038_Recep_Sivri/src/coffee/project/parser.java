//project1_Part2_121044038
//   made by recep sivri on 22/12/2015
// Description:
//  in this part of project we implemented shift reduce parser.
//

package coffee.project;
import coffee.TokenList;
import coffee.datatypes.Token;
import java.util.ArrayList;
import java.util.Stack;

public class parser {//parser class.
    ArrayList par = new ArrayList();// creates arraylist for parsers.
    public ArrayList TakeTokenAndList()
    {
        TokenList tokens = TokenList.getInstance();//takes tokens from lexer which I wrote in part1 of  project.
        String str;
        int i;
        // determines tokens for parsing.
        for(Token token : tokens.getAllTokens()) {//iterates all tokens from lexer.

            //determinates tokentypes.
            if(token.getTokenType().toString().compareTo("OPERATOR")==0)
            {
                if(token.toString().compareTo("Operator_(")==0)// if token is operator_( adds to arraylist.
                    par.add("Op(");//adding arraylist.
                else
                if(token.toString().compareTo("Operator_)")==0)// if token is operator_) adds to arraylist.
                    par.add("Op)");//adding arraylist.
                else
                if(token.toString().compareTo("Operator_-")==0)// if token is operator_- adds to arraylist.
                    par.add("Op-");//adding arraylist.
                else
                if(token.toString().compareTo("Operator_+")==0)// if token is operator_+ adds to arraylist.
                    par.add("Op+");//adding arraylist.
                else
                if(token.toString().compareTo("Operator_*")==0)// if token is operator_* adds to arraylist.
                    par.add("Op*");//adding arraylist.
                else
                if(token.toString().compareTo("Operator_/")==0)// if token is operator_/ adds to arraylist.
                    par.add("Op/");//adding arraylist.

            }
            else
            if(token.getTokenType().toString().compareTo("IDENTIFIER")==0)// if token is IDENTIFIER adds to arraylist.
            {
                par.add("id");//adding arraylist.
            }
            else
            if(token.getTokenType().toString().compareTo("INTEGER_VALUE")==0)// if token is INTEGER_VALUE adds to arraylist.
            {
                par.add("IntValue");//adding arraylist.
            }
            else
            if(token.getTokenType().toString().compareTo("KEYWORD")==0) {// if token is KEYWORD adds to arraylist.
                if(token.toString().compareTo("Keyword_and")==0)// if token is Keyword_and adds to arraylist.
                    par.add("and");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_or")==0)// if token is Keyword_or adds to arraylist.
                    par.add("or");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_equal")==0)// if token is Keyword_equal adds to arraylist.
                    par.add("equal");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_deffun")==0)// if token is Keyword_deffun adds to arraylist.
                    par.add("deffun");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_concat")==0)// if token is Keyword_concat adds to arraylist.
                    par.add("concat");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_append")==0)// if token is Keyword_append adds to arraylist.
                    par.add("append");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_else")==0)// if token is Keyword_else adds to arraylist.
                    par.add("else");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_for")==0)// if token is Keyword_for adds to arraylist.
                    par.add("for");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_if")==0)// if token is Keyword_if adds to arraylist.
                    par.add("if");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_set")==0)// if token is Keyword_set adds to arraylist.
                    par.add("set");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_then")==0)// if token is Keyword_then adds to arraylist.
                    par.add("then");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_not")==0)// if token is Keyword_not adds to arraylist.
                    par.add("not");//adding arraylist.
                else
                if(token.toString().compareTo("Keyword_while")==0)// if token is Keyword_while adds to arraylist.
                    par.add("while");//adding arraylist.
            }
            if(token.getTokenType().toString().compareTo("BINARY_VALUE")==0)// if token is BINARY_VALUE adds to arraylist.
            {
                par.add("Bvalue");//adding arraylist.
            }
        }
        return par;//return arraylist.
    };

    public void DetermineTokensAndDerivative() {// determine type of tokens end derivates them with shift reduce parser rule.

        ArrayList par2 = new ArrayList();//new arraylist object for shift reduce parser rule
        Stack stCArray = new Stack();//stack object for derivate in shift reduce parser and print result.
        String str="\0";//str in using for elements of shift reduce parser.

        par = TakeTokenAndList();//  takes tokens from TakeTokenAndList.

        // variables:
        int i, j,count=0;
        i = 0;
        j = 0;

        //
        while (par.size() > i) {
           // select operators ,keywords ,identifiers , integer values and boolean values.
            //adds those to arraylist.
            if (par.get(i).toString().compareTo("Op(") == 0) {// if expression has a left pharantesis.
                while (par.get(i).toString().compareTo("Op)") != 0) {// until  expression has a left pharantesis.
                    if(par.get(i).toString().compareTo("Op(") == 0)
                        par2.add("(");//adds to array list.
                    else
                    if(par.get(i).toString().compareTo("Op+") == 0)
                        par2.add("+");//adds to array list.
                    else
                    if(par.get(i).toString().compareTo("Op-") == 0)
                        par2.add("-");//adds to array list.
                    else
                    if(par.get(i).toString().compareTo("Op*") == 0)
                        par2.add("*");//adds to array list.
                    else
                    if(par.get(i).toString().compareTo("Op/") == 0)
                        par2.add("/");//adds to array list.
                    else

                        par2.add(par.get(i).toString());//adds to array list.
                    ++i;
                }
                if (par.get(i).toString().compareTo("Op)") == 0)
                    par2.add(")");//adds to array list.
                else
                    par2.add(par.get(i).toString());

            }
            ++i;
        }

        // in this loop program combines elements of array list and pushes to stack.
        for(i=0;i<par2.size();++i)
            str= str + par2.get(i).toString()+" ";
        stCArray.push(str);
        str="\0";// reseting  str.
        count=0;//reseting counter.


        // if first element of expression is a int value or idetifier program implements this:
        if(par2.get(1).toString().compareTo("IntValue")==0&&par2.size()==3||par2.get(1).toString().compareTo("id")==0&&par2.size()==3)
        {
            par2.set(1, "EXPI");
            for(i=0;i<par2.size();++i)//checks each element of expression.
                str= str + par2.get(i).toString()+" ";
            if (par2.get(par2.size() - 2).toString().compareTo("EXPI") == 0) {// if last element of expression is EXPI then
                                                                                // push EXPI to stack and push  EXP tt stack.
                stCArray.push("EXPI");
                stCArray.push("EXP");
            }
        }
        else//if first element of expression is a operator program implements this:
        if(par2.get(1).toString().compareTo("+")==0|| par2.get(1).toString().compareTo("-")==0|| par2.get(1).toString().compareTo("*")==0
                || par2.get(1).toString().compareTo("/")==0)
        {
            for (i = 0; i < par2.size(); ++i) {
                if ((par2.get(i).toString().compareTo("IntValue") == 0 && count == 0) || (par2.get(i).toString().compareTo("id") == 0 && count == 0)) {
                    par2.set(i, "EXPI");
                    for (j = 0; j < par2.size(); ++j)//checks each element of expression.
                        str = str + par2.get(j).toString() + " ";
                    stCArray.push(str);
                    str = "\0";
                }
            }
            if (par2.get(par2.size() - 2).toString().compareTo("EXPI") == 0) {
                stCArray.push("EXPI");
                stCArray.push("EXP");
            }
        }//if first element of expression is a keyword program implements this:
        if(par2.get(1).toString().compareTo("equal")==0|| par2.get(1).toString().compareTo("concat")==0 ||
                par2.get(1).toString().compareTo("if")==0 || par2.get(1).toString().compareTo("and")==0 ||
                par2.get(1).toString().compareTo("or")==0 || par2.get(1).toString().compareTo("not")==0 ||
                par2.get(1).toString().compareTo("append")==0 || par2.get(1).toString().compareTo("else")==0 ||
                par2.get(1).toString().compareTo("set")==0 || par2.get(1).toString().compareTo("while")==0 ||
                par2.get(1).toString().compareTo("deffun")==0 || par2.get(1).toString().compareTo("for")==0
                || par2.get(1).toString().compareTo("then")==0)
        {
            if(par2.get(1).toString().compareTo("equal")==0)// if first element of expression is equal implements this.
            {
                for(i=0;i<5;++i)// loop for  expressions.
                {

                    //if elements of expression are int value or boolean value implements this.
                    if ((par2.get(i).toString().compareTo("IntValue") == 0 ) || (par2.get(i).toString().compareTo("Bvalue") == 0 )) {
                        par2.set(i, "EXPB");
                        for (j = 0; j < par2.size(); ++j)//combines elements of array list for push expression to stack.
                            str = str + par2.get(j).toString() + " ";
                        stCArray.push(str);
                        str = "\0";
                    }
                }
                if (par2.get(par2.size() - 2).toString().compareTo("EXPB") == 0) {//if last element is boolean pushes elements in below to stack.
                    stCArray.push("EXPB");
                    stCArray.push("EXP");
                }
            }
            else
            if(par2.get(1).toString().compareTo("and")==0 ||par2.get(1).toString().compareTo("or")==0)// checks elements of array list for operation
                                                                                                    // or ,and.
            {
                for(i=0;i<5;++i)
                {

                    if (par2.get(i).toString().compareTo("Bvalue") == 0) {//checks elements of expression if is Binary value.
                        par2.set(i, "EXPB");
                        for (j = 0; j < par2.size(); ++j)
                            str = str + par2.get(j).toString() + " ";//combines elements of expression
                        stCArray.push(str);//push combined expiression to stack.
                        str = "\0";
                    }
                }
                if (par2.get(par2.size() - 2).toString().compareTo("EXPB") == 0) {// if last element of expression is EXPB
                                                                                    // push elements in below to stack.
                    stCArray.push("EXPB");
                    stCArray.push("EXP");
                }
            }
            else
            if(par2.get(1).toString().compareTo("not")==0&&par2.size()<5)// if first element of expression is not implements in below.
            {
                for(i=0;i<4;++i)
                {
                    if (par2.get(i).toString().compareTo("Bvalue") == 0) {// if second element of expression is binary value implements in below.
                        par2.set(i, "EXPB");
                        for (j = 0; j < par2.size(); ++j)//combines changed elements in expression
                            str = str + par2.get(j).toString() + " ";
                        stCArray.push(str);//pushes new expression to stack.
                        str = "\0";
                    }
                }
                if (par2.get(par2.size() - 2).toString().compareTo("EXPB") == 0) {//if last element is changed push elements in below to stack.
                    stCArray.push("EXPB");
                    stCArray.push("EXP");
                }
            }
        }
        else
        if(par2.get(1).toString().compareTo("Bvalue")==0&&par2.size()<4)//checks if expression has only binary value.
        {
            for(i=0;i<3;++i)
            {
                if (par2.get(i).toString().compareTo("Bvalue") == 0) {
                    par2.set(i, "EXPB");
                    for (j = 0; j < par2.size(); ++j)
                        str = str + par2.get(j).toString() + " ";
                    stCArray.push(str);
                    str = "\0";
                }
            }
            if (par2.get(1).toString().compareTo("EXPB") == 0) {//if second element is changed pushes elements in below to stack.
                stCArray.push("EXPB");
                stCArray.push("EXP");
            }
        }
        // prints parse tree.
        System.out.println("START -> INPUT");

        while(0<stCArray.size())
        {
            System.out.print("      -> ");
            System.out.println(stCArray.pop());
        }
    }
}