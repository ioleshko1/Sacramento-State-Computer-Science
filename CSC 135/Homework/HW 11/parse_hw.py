#
# parse_hw.py by Igor Oleshko (ioleshko@csus.edu), CSC 135 SPRING 2023, 2023-04-19
#


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
# string (ie, string, list, etc). Raises Exception on a parse error.
# S → <AB
# A → aAb | b
# B → bB | >
def parse1(input):
    
    # Store the input which has been processed by the scanner into toks
    toks = scanner(input)
    
    # Set the initial stack to just have the character S
    stack = ['S']
    
    # Repeat this loop while the stack is not empty
    while len(stack) > 0:
        
        # Pop the top character of the stack and store it in top
        top = stack.pop()
        
        # Get the next character from the toks input and store it in tok
        tok = toks.next()
        
        # Check to see if the top of the stack matches one of the listed characters
        if top in ('<', 'a', 'b', '>'):
            
            # If so, remove the top character from the toks input using match
            toks.match(top)
            
        # Check to see if top is equal to S and the next input (tok) is <
        elif top == 'S' and tok == '<':
            
            # Append the characters from right to left B, A, < to the stack
            stack.append('B')
            stack.append('A')
            stack.append('<')
            
        # Check to see if the top is equal to A and the next input (tok) is a
        elif top == 'A' and tok == 'a':
            
            # Append the characters from right to left b, A, a to the stack
            stack.append('b')
            stack.append('A')
            stack.append('a')
            
        # Check to see if the top is equal to A and the next input (tok) is b
        elif top == 'A' and tok == 'b':
            
            # Append the character b to the stack
            stack.append('b')
            
        # Check to see if the top is equal to B and the next input (tok) is b
        elif top == 'B' and tok == 'b':
            
            # Append the characters from right to left B, b to the stack
            stack.append('B')
            stack.append('b')
            
        # Check to see if the top is equal to B and the next input (tok) is >
        elif top == 'B' and tok == '>':
            
            # Append the character > to the stack
            stack.append('>')
            
        # If none of the expressions match, raise an error message
        else:
            
            # Unrecognized top/tok combination
            raise Exception
        
    # Check to make sure that the toks string is empty
    if toks.next() != None:
        
        # If not we throw an error
        raise Exception





# Input can be any type where len(input) is defined and input[i] yields a
# string (ie, string, list, etc). Raises Exception on a parse error.
# S → BA
# A → +BA | -BA | λ
# B → DC
# C → *DC | /DC | λ
# D → a | (S)
def parse2(input):
    
    # Store the input which has been processed by the scanner into toks
    toks = scanner(input)
    
    # Set the initial stack to just have the character S
    stack = ['S']
    
    # Repeat this loop while the stack is not empty
    while len(stack) > 0:
        
        # Pop the top character of the stack and store it in top
        top = stack.pop()
        
        # Get the next character from the toks input and store it in tok
        tok = toks.next()
        
        # Check to see if the top of the stack matches one of the listed characters
        if top in ('a', '+', '-', '*', '/', '(', ')'):
            
            # If so, remove the top character from the toks input using match
            toks.match(top)
        
        # Check to see if top is equal to S and the next input (tok) is one of the following:  a, (
        elif top == 'S' and tok in ('a', '('):
            
            # Append the characters from right to left A, B to the stack
            stack.append('A')
            stack.append('B')
        
        # Check to see if top is equal to A and the next input (tok) is one of the following:  +
        elif top == 'A' and tok == '+':
            
            # Append the characters from right to left A, B, + to the stack
            stack.append('A')
            stack.append('B')
            stack.append('+')
        
        # Check to see if top is equal to A and the next input (tok) is one of the following:  -
        elif top == 'A' and tok == '-':
            
            # Append the characters from right to left A, B, - to the stack
            stack.append('A')
            stack.append('B')
            stack.append('-')
        
        # Check to see if top is equal to A and the next input (tok) is one of the following:  ), None
        elif top == 'A' and tok in (')', None):
            
            # Pass which will act as our lambda
            pass
        
        # Check to see if top is equal to B and the next input (tok) is one of the following:  a, (
        elif top == 'B' and tok in ('a', '('):
            
            # Append the characters from right to left C, D to the stack
            stack.append('C')
            stack.append('D')
        
        # Check to see if top is equal to C and the next input (tok) is one of the following:  *
        elif top == 'C' and tok == '*':
            
            # Append the characters from right to left C, D, * to the stack
            stack.append('C')
            stack.append('D')
            stack.append('*')
        
        # Check to see if top is equal to C and the next input (tok) is one of the following:  /
        elif top == 'C' and tok == '/':
            
            # Append the characters from right to left C, D, / to the stack
            stack.append('C')
            stack.append('D')
            stack.append('/')
            
        # Check to see if top is equal to C and the next input (tok) is one of the following:  +, -, ), None
        elif top == 'C' and tok in ('+', '-', ')', None):
            
            # Pass which will act as our lambda
            pass
        
        # Check to see if top is equal to D and the next input (tok) is one of the following:  a
        elif top == 'D' and tok == 'a':
            
            # Append the character a
            stack.append('a')
            
        # Check to see if top is equal to D and the next input (tok) is one of the following:  (
        elif top == 'D' and tok == '(':
            
            # Append the characters from right to left ), S, ( to the stack
            stack.append(')')
            stack.append('S')
            stack.append('(')
        
        # If none of the expressions match, raise an error message
        else:
            
            # Unrecognized top/tok combination
            raise Exception
        
    # Check to make sure that the toks string is empty
    if toks.next() != None:
        
        # If not we throw an error
        raise Exception


# The following is a trick to make this testing code be ignored
# when this file is being imported, but run when run directly
# https://codefather.tech/blog/if-name-main-python/
if __name__ == '__main__':
    
    # Check a string where <a^n b^m> where n < m (should accept)
    try:
        parse1("<abb>")
    except:
        print("Reject")
    else:
        print("Accept")
        
    # Check a string where <a^n b^m> where n > m (should reject)
    try:
        parse1("<aaabb>")
    except:
        print("Reject")
    else:
        print("Accept")
        
    # Check a string without the <> (should reject)
    try:
        parse1("abb")
    except:
        print("Reject")
    else:
        print("Accept")





    # Check a string where the string is just a (should accept)
    try:
        parse2("a")
    except:
        print("Reject")
    else:
        print("Accept")
    
    # Check a string where it has several arithmetic operations (should accept)
    try:
        parse2("a-a/a+(a+a)")
    except:
        print("Reject")
    else:
        print("Accept")
        
    # Check a string where it has (a) (should accept)
    try:
        parse2("(a)")
    except:
        print("Reject")
    else:
        print("Accept")
        
    # Check a string where it has several a's (should reject)
    try:
        parse2("aaa")
    except:
        print("Reject")
    else:
        print("Accept")
        
    # Check a string where it has () (should reject)
    try:
        parse2("()")
    except:
        print("Reject")
    else:
        print("Accept")


