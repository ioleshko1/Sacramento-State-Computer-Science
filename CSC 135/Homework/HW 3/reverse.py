# reverse.py by Igor Oleshko (ioleshko@csus.edu) for CSC 135 SPRING 2023



from List135 import List135

# Returns a List135 that is the same as xs but in reverse order
# Function is tail-recursive to enable compiler optimization
# [] --> []; [1] --> [1]; [1,2] --> [2,1]; [1,2,3] --> [3,2,1]; etc.
def _reverse(xs, acc):
    # Check to see if the xs list is empty
    if xs.empty():
        
        # Return the acc
        return acc
    
    # Work on the solution if the xs list is not empty
    else:
        
        # Capture the first element in the CSC135 list
        curr = xs.first()
        
        # Add the element to the acc
        acc = acc.add(curr)
        
        # Call the _reverse function recursively
        return _reverse(xs.rest(), acc)
    

def reverse(xs):
    acc = List135()
    return _reverse(xs, acc)  # Begin accumulation with an empty list

# If you run this file, the indented code below will run. This is a
# suitable place for you to put testing code. When I test your code
# using a separate file, the code below will not be run. to be eligible
# for credit, your code must run the following without sytax or runtime
# errors. Leave your test cases here, indented, so that I can see them.
if __name__ == '__main__':
    a = List135().add(3).add(1).add(5).add(2).add(4)
    b = reverse(a)
    print(a)
    print(b)
    
    # [] --> []
    a = List135()
    b = reverse(a)
    print(a)
    print(b)
    
    # [1] --> [1]
    a = List135().add(1)
    b = reverse(a)
    print(a)
    print(b)
    
    # [1,2] --> [2,1]
    a = List135().add(2).add(1)
    b = reverse(a)
    print(a)
    print(b)
    
    # [1,2,3]
    a = List135().add(3).add(2).add(1)
    b = reverse(a)
    print(a)
    print(b)
    