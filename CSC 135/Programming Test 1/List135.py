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

# The following is a trick to make this testing code be ignored
# when this file is being imported, but run when run directly
# https://codefather.tech/blog/if-name-main-python/
if __name__ == '__main__':
    a = List135()  # a --> []
    b = a.add(1)   # b --> [1]
    c = b.add(2)   # c --> [2,1]
    print(a)
    print(b)
    print(c)
