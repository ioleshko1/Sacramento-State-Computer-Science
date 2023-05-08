# reverse.py by Igor Oleshko (ioleshko@csus.edu) for CSC 135 SPRING 2023



from List135 import List135

# Returns how many times x is found inside List135 xs (uses == to test equality)
# Function does NOT use tail-recursion or accumulators.
# [], 1 --> 0; [1], 1 --> 1; [1,2], 1 --> 1; [1,2,1], 1 --> 2; etc.
def count(xs, x):
    # Check to see if the 135 list is empty
    if (xs.empty()):
        
        # Return 0 of the list is empty
        return 0
    
    # Work on the solution if the list is not empty
    else:
        
        # Capture the first element in the 135 list
        curr = xs.first()

        # Check to see if the first element is equal to the passed in x
        if (curr == x):
            
            # Add 1 and call the function recursively 
            return 1 + count(xs.rest(), x)
        
        # If it does not match
        else:
            
            # Add 0 and call the function recursively
            return 0 + count(xs.rest(), x)       

        

# If you run this file, the indented code below will run. This is a
# suitable place for you to put testing code. When I test your code
# using a separate file, the code below will not be run. to be eligible
# for credit, your code must run the following without sytax or runtime
# errors. Leave your test cases here, indented, so that I can see them.
if __name__ == '__main__':
    a = List135().add(1).add(2).add(1)
    print(count(a, 1) == 2)
    
    # Check the empty list
    b = List135()
    print(count(b, 1))
    
    # Check a one element list that matches
    c = List135().add(1)
    print(count(c, 1))
    
    # Check a one element list that doesn't match
    d = List135().add(1)
    print(count(d, 2))
    
    # Check a two element list that doesn't match
    e = List135().add(1).add(3)
    print(count(e, 2))
    
    # Check a two element list that does match
    f = List135().add(2).add(2)
    print(count(f, 2))
    
    # Check a two element list that only has one match
    g = List135().add(2).add(3)
    print(count(g, 2))
