from pt2_hamt import hamt

def checkexpect_howmany(t, f, r, points):
    try:
        tmp = t.howmany(f)
    except Exception:
        return 0
    else:
        if type(tmp) == type(r) and tmp == r:
            return points
        else:
            return 0

a = hamt()
b = a.add(2)
c = b.add(3)
d = c.add(4)
e = d.add(5)
f = e.add(6)
g = f.add(22)

pts = 0.0
pts += checkexpect_howmany(f, lambda x: x%2==0, 3, 1)
pts += checkexpect_howmany(g, lambda x: x > 5, 2, 2)

s1 = str(f)
s2 = str(g)
try:
    t = f.hamtmap(lambda x: x%2)
    s = str(t)
    if str(f)==s1 and s in ("{0, 1}", "{1, 0}"):
        pts += 1
except:
    pass

try:
    t = f.hamtmap(lambda x: x//2-1)
    s = str(t)
    if str(f)==s1 and s in ("{0, 1, 2}", "{0, 2, 1}", "{1, 0, 2}", "{1, 2, 0}", "{2, 0, 1}", "{2, 1, 0}"):
        pts += 2
except:
    pass

try:
    t = g.hamtmap(lambda x: 0 if x < 22 else 1)
    s = str(t)
    if str(g)==s2 and s in ("{0, 1}", "{1, 0}"):
        pts += 1.5
except:
    pass

print(pts)
