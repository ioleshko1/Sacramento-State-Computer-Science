# Programming test 2, Part 2, CSC 135 Spring 2023
# Name:  Igor Oleshko
# Sac State username:  ioleshko

# When you are done, write your name and username above, save, and
# submit this file to
#
#     https://fileinbox.com/csc135/xxyxx
#
# with xxyxx replaced by your Sac State username.

# Complete the functions below.
#
# The first should return how many data items in the trie make f True.
#
# The second function should return a trie containing f(x) for each x in the
# original trie. So, if the original trie contains { 1, 2, 3 }, then the
# returned trie should contain { f(1), f(2), f(3) }. Recall, however, that a
# set won't have any repetition and t.add(x) returns None if x is already in t.
#
# You should not alter any other code in the hamt class, except you may
# add helper functions if you wish.
#
# To be eligible for credit your code must have no syntax errors and the
# functions you write should not print anything.

class hamt:

    # 3 points
    # f returns boolean
    def howmany(self, f, acc=0):
        
        # Check to see if the current item is not empty
        if self._item != None:
            
            # If so, then we check to see if the result of the function is returning true
            if f(self._item):
                
                # If so, then we can increment the acc by 1
                acc = acc + 1
        
        # Loop through each child in the current node
        for child in self._children:
            
            # If the child is not empty
            if child != None:
                
                # Call the howmany method on it to see how many true it has (and store the result in the acc)
                acc = child.howmany(f, acc)
        
        # Return the total count which is stored in the acc
        return acc
    
    
    
    
    # 4.5 points
    # f returns boolean
    def _hamtmap(self, f, acc):
        
        # Check to see if the current item is not empty
        if self._item != None:
            
            # Get the result of the function
            result = f(self._item)
            
            # Use a temporary hamt to store the result of the add
            tmp = acc.add(result)
            
            # Check to see if the tmp is None (node already exists in the hamt)
            if tmp != None:
                
                # If not then we can set the acc equal to tmp
                acc = tmp
        
        # Loop through each child in the current node
        for child in self._children:
            
            # If the child is not empty
            if child != None:
                
                # Call the hamtmap method on it to see resulting set of the results from the function
                acc = child._hamtmap(f, acc)
        
        # Return the total count which is stored in the acc
        return acc
    
    
    # Helper function
    def hamtmap(self, f):
        
        # Create a new hamt to store the accumulated values
        acc = hamt()
        
        # Call the _hamtmap method and return the result
        return self._hamtmap(f, acc)
    
    




    DEG  = 4     # Children per node (can be set to any power of 2)
    BITS = 2     # Should be log2(DEG), bits needed to select child
    MASK = 0b11  # Should be BITS one-bits
    
    def __init__(self, item = None, children = None):
        self._item = item
        self._len = 0
        if children == None:
            self._children = [None] * hamt.DEG  # List of DEG Nones
        else:
            self._children = children
    
    # returns copy of self node with child i set to node x
    def _updatechild(self, i, x):
        updatedchildlist = list(self._children)    # copy self's child list
        updatedchildlist[i] = x                    # update child i
        tmp = hamt(self._item, updatedchildlist)  # build and return new node
        return tmp
        
    # Returns reference to new root if change made, else None
    def _add(self, item, hashbits):
        if self._item == item:
            # This node matches item. Return None to indicate no change.
            return None
        else:
            # Continue search using hashbits to pick direction
            child_num = hashbits & hamt.MASK
            if self._children[child_num] == None:
                # item not in trie. Add it as new leaf node.
                return self._updatechild(child_num, hamt(item))
            else:
                # Ask appropriate child to add item, receive updated reference
                shiftedhashbits = hashbits >> hamt.BITS
                newchild = self._children[child_num]._add(item, shiftedhashbits)
                if newchild == None:
                    return None
                else:
                    return self._updatechild(child_num, newchild)
    
    def add(self, item):
        # Pass item and hashbits to private recursive helper
        return self._add(item, hash(item))

    # Adds self's item string to acc list, then ask each child to do the same
    def _str(self, acc):
        if self._item != None:           # Don't do anything in fake root node
            acc.append(str(self._item))
        for child in self._children:
            if child != None:
                child._str(acc)
    
    # Build list of all items in trie, then join them with ", " in between
    def __str__(self):
        acc = []
        self._str(acc)
        return "{" + ", ".join(acc) + "}"
            
# The following is a trick to make this testing code be ignored
# when this file is being imported, but run when run directly
# https://codefather.tech/blog/if-name-main-python/
if __name__ == '__main__':
    a = hamt()
    b = a.add(2)
    c = b.add(3)
    d = c.add(4)
    e = d.add(5)
    f = e.add(6)
    
    # Test the howmany method
    print(f.howmany(lambda x: x%2==0))
    print(f.howmany(lambda x: x%1==0))
    print(f.howmany(lambda x: x==3))
    
    # Test the hamtmap method
    print(f.hamtmap(lambda x: x%2==0))
    print(f.hamtmap(lambda x: x%10 == 0))
    print(f.hamtmap(lambda x: x%1 == 0))
    print(f.hamtmap(lambda x: x * 2))
    print(f.hamtmap(lambda x: x / 2))
    print(f.hamtmap(lambda x: x % 2))
    print(f.hamtmap(lambda x: x % 3))
