let est_pair n = if(n mod 2 = 0) then 1 else 0;; (* "1" -> pair "0" -> !pair *)

let x = est_pair 5;;
print_int(x);;


let rec syracuse m k =
  print_int(k);
  if( k = 0) then m
  else if (est_pair(k) = 1) then syracuse m (k/2)
  else syracuse m (3*k+1)
;;


syracuse 1 2;;
