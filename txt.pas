int a, b, r, d, x, y;
a = 1;
b = 4;
r = 10;
d = b * b - 4 * a * r;
if(d >= 0) {
    if(d <> 0) {
        x = (-b + sqrt(d)) / 2 * a;
       	y = (-b - sqrt(d)) / 2 * a;
  		writeln(x);
  		writeln(y);
    } else {
        x = -(b / 2 * a);
        writeln(x)
    }
} else {
    writeln(false);
}