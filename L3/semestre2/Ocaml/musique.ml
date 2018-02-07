type midi = int;;
type nom = Do|Re|Mi|Fa|Sol|La|Si;;
type alteration = Becarre|Diese;;
type hauteur = {n : nom ; a : alteration ; o : int}

let do4 = {n = Do ; a = Becarre ; o = 4};;
let la2 = {n = La ; a = Becarre ; o = 2};;
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

(* print_int(note2midi la2);;
print_newline();; *)

let midi2freq i = 400 * puiss 2 ((i-69)/12);;

print_int(midi2freq 60);;
print_newline();;

let rec puiss x y =
  if y = 0 then 1
  else x * puiss x (y-1)
;;
