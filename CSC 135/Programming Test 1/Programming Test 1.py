from List135 import List135
from functools import reduce  # Allows reduce to be called directly
import functools              # Allows functools.reduce to work too

# Programming test 1, CSC 135 Spring 2023
# Name:  Igor Oleshko
# Sac State username:  ioleshko

# When you are done, write your name and username above, save, and
# submit this file to
#
#     https://fileinbox.com/csc135/xxyxx
#
# with xxyxx replaced by your Sac State username.

# Complete each of the following functions. Each problem will be scored
# separately, but the file as a whole must contain no syntax or runtime
# errors. To eliminate an error, you can comment the erroneous code and
# place the "pass" keyword in its place.


# Problem 1 (2.5 Pts): Write a function using some combination of map, filter,
# reduce and lambda that takes a python list of integers as a parameter and
# returns a list that contains the absolute values of all the even numbers
# in the list. For example, if the parameter is [1,2,-3,-4] then your
# function should return [2,4]. You may not call any other built-in functions
# except you may call "abs".
def abs_even(xs):
    
    # Map the list to the absolute values of the numbers
    absolute_values = map(lambda x: abs(x), xs)
    
    # Filter all of the abs(values) to see which ones are evens
    return_evens = filter(lambda x: x % 2 == 0, absolute_values)
    
    # Return a list of the evens
    return list(return_evens)


# Problem 2 (2.5 Pts): Write a function using some combination of map, filter,
# reduce and lambda that takes a python list of strings as a parameter and
# returns their concatenation. For example, if the parameter is ["bow","wow",
# "wow""] then your function should return "bowwowwow". You may not call any
# other built-in functions including "join".
def concat_all(xs):
    
    # Reduce all of the elements of the list into one string
    combine = reduce(lambda acc, x: acc + x, xs, "")
    
    # Return the reduced value
    return combine


# Problem 3 (2.5 Pts): Write a function any way you want that takes a unary
# boolean function f, a positive integer n and a value x. Your function should
# return the n-th iteration of f on x. For example nth_iter(f, 3, 100) should
# output the result of f(f(f(100))) because that is the result of applying f
# 3 times beginning with 100. 
def nth_iter(f, n, x):
    
    # Initializes the acc to the passed in variable x
    acc = x
    
    # We can assume that n is positive but this while loop is to just go through all of the n's
    while n != 0:
        
        # If the n is valid then we take the function of the value of whatever is inside the accumulator
        acc = f(acc)
        
        # Reduce the n by 1 to get to the next function rotation
        n -= 1
        
    # Return the acc which has the value after all the function calls
    return acc
    
    
    


# NOTE: The following are client functions of List135. They must not
# access the _data or _next fields. Only the first(), rest(), add(),
# and empty() methods and List135() constructor are allowed.

# Problem 4 (4 Pts): Write a recursive function "longer" that takes two List135
# parameters and returns True if the first is longer than the second and False
# otherwise. For example longer([1,2,3,4], [2,3,4]) should be True.
# Hint: You'll want to stop recursing is either xs or ys is empty.
def longer(xs, ys):
    
    # Checks to see if either of the lists are empty
    if xs.empty() or ys.empty():
        
        # This return statement checks to see if the ys is empty and if the xs is NOT empty
        # This basically returns True if ys is empty and the xs is NOT empty (True and !False -> True and True -> True)
        # However, if they are both empty then this results in False (True and !True -> True and False -> False)
        # Also, if ys is not empty but xs is empty then it also returns False (True and !True -> True and False -> False)
        return ys.empty() and not(xs.empty())
    
    else:
        
        # Recursively call the longer function and shorten each of the lists by one element
        return longer(xs.rest(), ys.rest())
        


# Problem 5 (3.5 Pts): Write a tail-recursive function common_prefix_len that
# takes two List135 parameters and returns the length of their longest common
# prefix. For example common_prefix_len([1,2,3], [2,3,4]) should return
# 0 because they have no common prefix, whereas common_prefix_len([1,2,4],
# [1,2,3,4]) should return 2 because both lists begin [1,2]. You may add
# a helper function or new parameter(s) with default value(s) if you want.
def common_prefix_len(xs, ys, acc=0):
    
    # Check to see if xs or ys is empty
    if xs.empty() or ys.empty():
        
        # Return the acc if one of them are empty
        return acc
    
    else:
        
        # Check to see if the first elements of the lists match up
        if xs.first() == ys.first():
            
            # If they do we increment the acc by 1
            acc += 1
            
            # And we recursively call the common_prefix_len() function, shorten the lists, and pass in the current acc
            return(common_prefix_len(xs.rest(), ys.rest(), acc))
        
        # If the first elements do not match up
        else:
            
            # Then we return the current acc value that we have
            return acc
        
        
        


# My testing will ignore the following indented code. You test here.
if __name__ == '__main__':
    a = List135()
    b = a.add(0)
    c = b.add(1)
    d = c.add(1)
    e = d.add(0)
    f = e.add(1)
    g = f.add(1)
    
    print("True indicates successful tets(s) for the problem number indicated.")
    print(1, abs_even([1,2,-3,-4,5]) == [2,4])
    print(1, abs_even([-1,3]) == [])
    
    # My tests for 1
    print(1.1, abs_even([-4, -3, -2, -1, 0, 1, 2, 3, 4, 5]))
    print(1.2, abs_even([-3, -1, 1, 3]))
    print(1.3, abs_even([5, 2, -1, 3, 2]))
    
    print(2, concat_all(["bow","wow","wow"]) == "bowwowwow")
    print(2, concat_all([]) == "")
    
    # My tests for 2
    print(2.1, concat_all(['hi', 'my', 'name', 'is', ' Igor']))
    print(2.2, concat_all([]))
    print(2.3, concat_all(['hi']))
    print(2.4, concat_all(['hi', 'bye']))
    
    print(3, nth_iter(lambda x: x + x, 4, 100) == 1600)
    print(3, nth_iter(lambda x: x + x, 3, "a") == "aaaaaaaa")
    
    # My tests for 3
    print(3.1, nth_iter(lambda x: x * x, 1, 2))
    print(3.2, nth_iter(lambda x: x + x, 3, "a"))
    print(3.3, nth_iter(lambda x: x + x, 4, 100))
    
    print(4, not longer(a, b) and longer(b, a) and not longer(a, a))
    print(4, not longer(c, e) and longer(e, c) and not longer(e, e))
    
    # My tests for 4
    print(4.1, longer(b, a))
    print(4.2, longer(a, b))
    print(4.3, longer(a, a))
    print(4.4, longer(c, e))
    
    print(5, common_prefix_len(d, g) == 3 and common_prefix_len(d, f) == 1)
    print(5, common_prefix_len(e, d) == 0 and common_prefix_len(d, d) == 3)
    
    print(e)
    print(d)
    
    # My tests for 5
    print(common_prefix_len(d, g))
    print(common_prefix_len(d, f))
    print(common_prefix_len(List135().add(1).add(0).add(1), List135().add(1).add(2).add(1)))

