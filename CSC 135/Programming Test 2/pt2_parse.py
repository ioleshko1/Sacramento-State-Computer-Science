# Programming test 2, Part 1, CSC 135 Spring 2023
# Name:  Igor Oleshko
# Sac State username:  ioleshko

# When you are done, write your name and username above, save, and
# submit this file to
#
#     https://fileinbox.com/csc135/xxyxx
#
# with xxyxx replaced by your Sac State username.

# Complete the function below following the PDA method
# seen in class for
#      S  -->  aSbS
#      S  -->  lambda
# The function currently has the example code from the online notes, but
# you will need to change it for this problem. Don't forget to add $
# Some simple tests are given below, but you may want to expand on them.
#
# To be eligible for credit your code must have no syntax errors and the
# parse function you write must not print anything.

class scanner:
    # toks[i] must evaluate to the i-th token in the token stream.
    # Assumes toks does not change during parsing
    def __init__(self,toks):
        self._toks = toks
        self._i = 0
    
    # If no more tokens exist or current token isn't s, raise exception.
    # Otherwise pass over the current one and move to the next.
    def match(self,s):
        if (self._i < len(self._toks)) and (self._toks[self._i] == s):
            self._i += 1
        else:
            raise Exception
            
    # If any tokens remain return the current one. If no more, return None.
    def next(self):
        if self._i < len(self._toks):
            return self._toks[self._i]
        else:
            return None

# Input can be any type where len(input) is defined and input[i] yields a
# string (ie, string, list, etc). Raises Exception on error.
# 7.5 points
def parse(input):
    
    # Grab the token string using the scanner class
    toks = scanner(input)
    
    # Add S to the initial stack
    stack = ['S']
    
    # Loop through this while loop while the length of our stack is greater than 0
    while len(stack) > 0:
        
        # Remove the top character from the stack and store it in a variable
        top = stack.pop()
        
        # Get the next character from the toks string
        tok = toks.next()
        
        # Check to see if the top of the stack is either of our terminals
        if top in ('a', 'b'):
            
            # If so then we consume the token from the token string
            toks.match(top)
        
        # Check to see if the top of the stack is S and if the next tok is a
        elif top == 'S' and tok == 'a':
            
            # If so then we append aSbS from right to left (S, b, S, a)
            stack.append('S')
            stack.append('b')
            stack.append('S')
            stack.append('a')
            
        # Check to see if the top of the stack is S and if the next tok is either b or None
        elif top == 'S' and tok in ('b', None):
            
            # If so then we just pass because this is our lambda
            pass
        
        # Check to see if none of the tok or top matched
        else:
            
            # If so, we raise an exception
            raise Exception
        
    # After consuming the entire stack, check to see if we still have toks left in the string
    if toks.next() != None:
        
        # If we do, we raise an exception
        raise Exception




if __name__ == "__main__":
    
    # Here are strings that should work correctly. Nothing should print.
    parse("")
    parse("ab")
    parse("aabb")
    parse("aaabbbab")
    parse("abababab")
    
    # Here are strings that should not work correctly. Nothing should print.
    try:
        parse("c")
    except:
        pass     # We expect this, so do nothing
    else:
        print("Error")
        
    try:
        parse("aa")
    except:
        pass     # We expect this, so do nothing
    else:
        print("Error")
        
    try:
        parse("abb")
    except:
        pass     # We expect this, so do nothing
    else:
        print("Error")
    
