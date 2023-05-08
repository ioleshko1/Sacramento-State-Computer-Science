from pt2_parse import parse

def checkexpect(string, accept, points):
    try:
        parse(string)
    except RuntimeError:
        #print(string + " runtime error")
        return 0
    except Exception as e:
        if accept:
            #print(string + " rejected")
            return 0
        else:
            return points
    else:
        if accept:
            return points
        else:
            #print(string + " accepted")
            return 0

pts = checkexpect("", True, 1)
pts += checkexpect("ab", True, 1)
pts += checkexpect("abab", True, 1)
pts += checkexpect("aabb", True, 1)
pts += checkexpect("aababb", True, 1)
pts += checkexpect("aaabbaabbb", True, 1)
pts += checkexpect("x", False, 2)
pts += checkexpect("aa", False, 1)
pts += checkexpect("b", False, 1)
pts += checkexpect("ba", False, 1)
pts += checkexpect("abba", False, 1)
pts += checkexpect("abb", False, 2)
pts += checkexpect("aby", False, 1)
pts -= 7.5
print(pts if pts > 0 else 0)
