# reverse.py by Igor Oleshko (ioleshko@csus.edu) for CSC 135 SPRING 2023


# Persistent list data structure for Sac State CSC 135 by Ted Krovetz
# Adding is done only at front of list, maintaining existing views.

class List135:
    
    # create new list node. Defaults to a node representing the empty list
    def __init__(self, data=None, next=None):
        self._data = data
        self._next = next
    
    # is the list beginning with self empty?
    def empty(self):
        return self._next == None
    
    # return the first element of the list that starts with self
    def first(self):
        return self._data
    
    # return the list that begins after the list that starts with self
    def rest(self):
        return self._next
    
    # return list beginning with data, followed by list beginning with self
    def add(self, data):
        return List135(data, self)
    
    # Helper reverse function
    def reverse(self):
        
        # Create an acc which we will use to hold the reversed List135
        acc = List135()
        
        # Using self we can call the private _reverse function and pass in the acc
        return self._reverse(acc)
     
    # Actual reverse function which reverse the self list
    def _reverse(self, acc):
      
        # Check to see if the self list is empty
        if self.empty():
        
            # Return the acc
            return acc
    
        # Work on the solution if the self list is not empty
        else:
        
            # Capture the first element in the CSC135 list
            curr = self.first()
        
            # Add the element to the acc
            acc = acc.add(curr)
        
            # Call the _reverse function recursively
            return self.rest()._reverse(acc)
            
  
        
    # tail-recursive function accumulates "," + str(cur._data) for each remaining item
    def _str(self, cur, acc):
        if cur._next == None:
            return acc
        else:
            acc = acc + "," + str(cur._data)
            cur = cur._next
            return self._str(cur, acc)
    
    def __str__(self):
        if self._next == None:
            return "[]"
        else:
            acc = str(self._data)   # init accumulator with first item
            cur = self._next        # continue accumulation with next
            return "[" + self._str(cur, acc) + "]"
        
    
            
    

# If you run this file, the indented code below will run. This is a
# suitable place for you to put testing code. When I test your code
# using a separate file, the code below will not be run. to be eligible
# for credit, your code must run the following without sytax or runtime
# errors. Leave your test cases here, indented, so that I can see them.
if __name__ == '__main__':
    a = List135()   # a --> []
    b = a.add(1)    # b --> [1]
    c = b.add(2)    # c --> [2,1]
    d = c.reverse() # d --> [1,2]
    print(c)
    print(d)
    
    
    # [] --> []
    a = List135()
    b = a.reverse()
    print(a)
    print(b)
    
    # [1] --> [1]
    a = List135().add(1)
    b = a.reverse()
    print(a)
    print(b)
    
    # [1,2] --> [2,1]
    a = List135().add(2).add(1)
    b = a.reverse()
    print(a)
    print(b)
    
    # [1,2,3]
    a = List135().add(3).add(2).add(1)
    b = a.reverse()
    print(a)
    print(b)
    

