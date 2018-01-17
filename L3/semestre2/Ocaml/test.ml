let rec puissance x =
  if x>10 then ()
  else
  begin
    print_int(x*2);
    print_newline();
    puissance(x*2);
  end
;;

print_string("entrez un entier : ");;
let x = read_int();;
print_string("resutat");;
print_newline();;
puissance x;;

(*--------------------------------------------------------------

let rec ackermann m n =
  if m = 0 then n+1
  else if (n=0 && m>0) then ackermann (m-1) 1
  else ackermann (m-1) (ackermann m (n-1))
;;

print_string("entrez un premier entier svp :");;
let x = read_int();;
print_string("entrez un second entier svp :");;
let y = read_int();;
let x = ackermann x y;;
print_string("resultat : ");;
print_int(x);;
print_newline();;*)
