type midi = int;;
type nom = Do|Re|Mi|Fa|Sol|La|Si;;
type alteration = Becarre|Diese;;
type hauteur = {n : nom ; a : alteration ; o : int}

let do4 = {n = Do ; a = Becarre ; o = 4};;
let dodiese4 = {n = Do ; a = Diese ; o = 4};;
let la2 = {n = La ; a = Becarre ; o = 2};;
let la3 = {n = La ; a = Becarre ; o = 3};;
let la4 = {n = La ; a = Becarre ; o = 4};;
let mi3 = {n = Mi ; a = Becarre ; o = 3};;


let note2midi x =
  (* val note2midi : hauteur -> int = <fun> *)
  let degre y =
    match y with
    | Do  -> 0
    | Re  -> 2
    | Mi  -> 4
    | Fa  -> 5
    | Sol -> 7
    | La  -> 9
    | Si  -> 11 in
  let plusun z = if z.a = Diese then 1 else 0 in
  60 + degre(x.n) + (plusun x) + 12 * (x.o - 3)
;;

let midi2freq m =
  int_of_float(440. *. (2. ** ((m -. 69.) /. 12.)))
;;

let note2freq h =
  let m = float_of_int(note2midi h) in
  midi2freq m
;;

let gere_note i =
  print_int(i)
;;

let montant a b c =
  gere_note a;
  print_string(" - ");
  gere_note (a+b);
  print_string(" - ");
  gere_note (a+c);
  print_string(" - ");
;;

let descendant a b c =
  gere_note (a+c);
  print_string(" - ");
  gere_note (a+b);
  print_string(" - ");
  gere_note (a);
  print_string(" - ");
;;

let rec arpegie a b c i oct oct_init sens =
  if(i == 0 && oct == 0 && sens = true) then ()
  else if (i == 0 && oct == 1 && sens == false) then
  begin
  gere_note (a+12);
  print_string(" - ");
  gere_note (a+12);
  print_string(" - ");
  arpegie a b c 2 (oct_init-1) oct_init true
  end
  else if(i == 2 && sens == false) then
  begin
    montant a b c;
    arpegie a b c 1 oct oct_init sens
  end
  else if (i == 1 && sens == false) then arpegie a b c 0 oct oct_init sens
  else if (i == 0 && sens == false) then arpegie (a+12) b c 2 (oct-1) oct_init sens
  else if(i == 2 && sens == true) then
  begin
    descendant a b c;
    arpegie a b c 1 oct oct_init sens
  end
  else if (i == 1 && sens == true) then arpegie a b c 0 oct oct_init sens
  else if (i == 0 && sens == true) then arpegie (a-12) b c 2 (oct-1) oct_init sens

;;

print_endline("48 - 52 - 55 - 60 - 64 - 67 - 72 - 76 - 79 - 84 - 84 - 79 - 76 - 72 - 67 - 64 - 60 - 55 - 52 - 48 \n");;
arpegie 48 4 7 2 2 2 false;;
print_newline();

(*  *)
