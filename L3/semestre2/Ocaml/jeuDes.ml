let lancer_de () =
Random.self_init();
Random.int 6 + 1 ;;



let rec jouer() =
let x = lancer_de() in
if (x>=1 && x<=4) then
  begin
    print_int(x);
    print_newline();
    print_string("perdu ");
    print_newline ();
    print_string("score : ");
    x
  end
else
begin
  print_string("on relance");
  print_newline ();
  print_string("score : ");
  print_int(x);
  print_newline();
  x + jouer()
end;;


(* let x = lancer_de();;
print_int(x);;
print_newline();; *)


let res = jouer();;
print_int(res);
print_newline();;


(*let x = lancer_de 6;;
print_int(x);;
print_newline();;*)
