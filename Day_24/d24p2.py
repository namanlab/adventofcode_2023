from z3 import *

file = "Day_24/input.txt"

arr = []
with open(file, "r") as f:
    for i, l in enumerate(f):
        cur_line = list(map(lambda x: list(map(lambda y: int(y), x.split(", "))), l.split(" @ ")))
        arr.append(cur_line)

x, y, z, vx, vy, vz = Int('x'), Int('y'), Int('z'), Int('vx'), Int('vy'), Int('vz')
s = Solver()

for i in arr:
    xi, yi, zi = i[0]
    vxi, vyi, vzi = i[1]
    s.add((x - xi)*(vyi - vy) == (y - yi)*(vxi - vx))
    s.add((x - xi)*(vzi - vz) == (z - zi)*(vxi - vx))

print (s.check())
m = s.model()
print(m[x].as_long() + m[y].as_long() + m[z].as_long())


