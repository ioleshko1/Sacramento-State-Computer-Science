# Set implemented as Hash Array Mapped Trie - Used in CSC 135, Sacramento State
# Written by Ted Krovetz, tdk@csus.edu, 2022-23
#
# hamt_hw.py by Igor Oleshko (ioleshko@csus.edu), CSC 135 SPRING 2023, 2023-03-01
#
# This implementation requires objects in the set be hashable.
# Resulting trie will have expected log height if hash is random.
# In this implementaiton the root node always has _item == None


from functools import reduce

class hamt:
    
    DEG  = 4     # Children per node (can be set to any power of 2)
    BITS = 2     # Should be log2(DEG), bits needed to select child
    MASK = 0b11  # Should be BITS one-bits
    
    def __init__(self, item = None, children = None):
        self._item = item
        self._length = 0
        
        if children == None:
            self._children = [None] * hamt.DEG  # List of DEG Nones
        else:
            self._children = children
    
    # returns copy of self node with child i set to node x
    def _updatechild(self, i, x):
        updatedchildlist = list(self._children)    # copy self's child list
        updatedchildlist[i] = x                    # update child i
        
        return hamt(self._item, updatedchildlist)  # build and return new node
    
    
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
    
    
    
    
    

    # Returns True if trie has no items, else False. Rutime O(1).
    def empty(self):
        
        # Checks to see if the root item is None and if the children are None as well
        if self._item == None and self._children == [None, None, None, None]:
            
            # If so then we return that the trie has no items
            return True
        
        # Otherwise we have some items inside of the trie
        else:
            
            # And we return False
            return False
    
    
    # Helper function for contains()
    def _contains(self, item, hashbits):
        
        # Checks to see if the current item matches up with the item that we are looking for
        if self._item == item:
            
            # Return True when it is
            return True
        
        # Otherwise, let us go through using the hash value
        else:
            
            # Continue search using hashbits to pick direction
            child_num = hashbits & hamt.MASK
            
            # Check to see if the current child_num is equal to None
            if self._children[child_num] == None:
                
                # Return False when it is
                return False
            
            # Otherwise, let us go through the children of the current item
            else:
                
                # Ask appropriate child to add item, receive updated reference
                shiftedhashbits = hashbits >> hamt.BITS
                
                # Capture if the item is found
                is_found = self._children[child_num]._contains(item, shiftedhashbits)
                
                # Return the result
                return is_found
        
    
    # Returns True if item in trie, else False. Expected rutime O(log n).
    def contains(self, item):
        
        # Pass item and hashbits to private _contains() recursive helper
        return self._contains(item, hash(item))
    
    
    
    
    # Helper function for __len__()
    def _len(self, acc):
        
        # Return the length
        return acc
        
        
    # Returns number of items in trie. Runtime O(n).
    def __len__(self):
        
        # Set the acc to 0
        #acc = 0
        
        # Return the value (which will be the acc) that we get using the _len() function
        return self._length
        
        
        
        
    
    # Returns HAMT that is union of self and other. Expected rutime O(n log n).
    def union(self, other):
        
        # Loop through all of the children
        for child in other._children:

            # Check to see if the child is not None
            if child != None:
                
                # Call the union function recursively to add the child
                self = self.union(child)
             
        # Check to see if the self does not contain the item
        if not self.contains(other._item):
            
            # Then we add it to self
            self = self.add(other._item)
        
        # Return self which is the union of the two HAMTs
        return self


    # Intersection: Returns HAMT that is the intersection of self and other
    def _intersection(self, other, acc):
                 
        for child in self._children:
            
            if child != None:
                
                acc = child._intersection(other, acc)
            
        if self._item != None and other.contains(self._item):
            
            acc = acc.add(self._item)
            
        return acc


    def intersection(self, other):
        acc = hamt()
        
        return self._intersection(other, acc)







    def difference(self, other):
        acc = other
        acc = self._difference(other, acc)
        return acc

    def _difference(self, other, acc):
        if self._item is not None:
            if other.contains(self._item) == False:
                acc = acc.add(self._item)
            elif other.contains(self._item) == True:
                acc = acc.remove(self._item)
                
        for child in self._children:
            if child is not None:
                acc = child._difference(other, acc)
        return acc

    


    











    # Remove: Returns HAMT that has an element removed
    def _remove(self, item, acc):
        tmp = hamt()
        if self._item not in (item, None):
            acc = acc.add(self._item)
        for child in self._children:
            if child != None:
                acc = child._remove(item, acc)
        return acc


    # Remove: Returns HAMT that has an element removed
    def remove(self, item):
        acc = hamt()
        
        return self._remove(item, acc)


    def hamtHash(self):
        return hash(str(self))
    
    
    
    
    



    
    








    # In order traversal
    def traverse(self):
        if self._item != None:
            print(self._item,end="")
            
        for child in self._children:
            if child != None:
                child.traverse()












    # Intersection: Returns HAMT that is the intersection of self and other
    def subset(self, other):
        
        if self._item != None and other.contains(self._item) == False:
            
            return False
            
        
        for child in self._children:
            if child != None:
                if child.subset(other) == False:
                    return False
            
        return True
            

    
    def hamtreduce(self, f, acc):
        if self._item != None:           # Don't do anything in fake root node
            acc = f(acc, self._item)
            
        for child in self._children:
            if child != None:
                acc = child.hamtreduce(f, acc)
    
        return acc




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
    b = a.add("B")
    c = b.add("C")
    d = c.add("D")
    e = d.add("E")
    f = e.add("F").add('G').add('I').add('J').add('K')
    g = f.add("F")
    
    
    l = hamt().add('1').add('3').add("B")
    print(b)
    print(c)
    print(d)
    print(e)
    print(f)
    print(g)
    print()
    
    
    print('--------------------------------')
    print('-------- EMPTY METHOD ----------')
    print('--------------------------------')
    print("Test:  empty a", a.empty() == True)
    print("Test:  empty b", b.empty() == False)
    print("Test:  empty d", d.empty() == False)
    print()
    
    
    print('--------------------------------')
    print('------- CONTAINS METHOD --------')
    print('--------------------------------')
    print("Test:  contains B in a", a.contains("B") == False)
    print("Test:  contains B in b", b.contains("B") == True)
    print("Test:  contains B in d", d.contains("B") == True)
    print("Test:  contains F in c", c.contains("F") == False)
    print("Test:  contains C in d", d.contains("C") == True)
    print()
    
    
    print('--------------------------------')
    print('-------- LENGTH METHOD ---------')
    print('--------------------------------')
    print("Test:  length a", len(a))# == 0)
    print("Test:  length b", len(b))# == 1)
    print("Test:  length d", len(d))# == 3)
    print()
    
    
    print('--------------------------------')
    print('--------- UNION METHOD ---------')
    print('--------------------------------')
    print("Test:  ", d.union(e))
    print("Test:  ", l.union(d))
    print("Test:  ", f.union(a))
    
    
    
    print('---------------------------------------')
    print('--------- INTERSECTION METHOD ---------')
    print('---------------------------------------')
    print("Intersection:  ", d.intersection(e))
    print("Intersection:  ", l.intersection(d))
    print("Intersection:  ", f.intersection(a))
    
    
    print('-------------------------------------')
    print('--------- DIFFERENCE METHOD ---------')
    print('-------------------------------------')
    print("Difference:  ", d.difference(e))
    print("Difference:  ", l.difference(d))
    print("Difference:  ", f.difference(a))
    
    
    print('---------------------------------')
    print('--------- REMOVE METHOD ---------')
    print('---------------------------------')
    print("Remove:  ", d.remove('B'))
    print("Remove:  ", l.remove('1'))
    print("Remove:  ", f.remove('F').remove("J"))
    
    
    
    print('---------------------------------')
    print('--------- SUBSET METHOD ---------')
    print('---------------------------------')
    print("Subset:  ", d.subset(e))
    print("Subset:  ", e.subset(d))




    print('---------------------------------')
    print("Acc:  ", f.hamtreduce(lambda x, y: x + len(y), 0))
    print("Hash:  ", f.hamtHash())
    d.traverse()
