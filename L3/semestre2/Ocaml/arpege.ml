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


let rec arpegie a b c i oct sens =
  if(i == 0 && oct == 0 && sens = true) then ()
  else if (i == 0 && oct == 1 && sens == false) then arpegie a b c 2 oct true
  else if(i == 2) then
  begin
    gere_note a;
    print_string(" - ");
    (* gere_note (a+4);
    print_string(" - ");
    gere_note (a+7);
    print_string(" - "); *)
    arpegie b c (a+12) 1 oct sens
  end
  else if (i == 1) then
      arpegie c (a+12) (b+12) 0 oct sens
  else
      arpegie (a+12) (b+12) (c+12) 2 (oct-1) sens
;;



arpegie 48 4 7 2 1 false;;
print_newline();

(*  *)
