open List;;
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

type score = hauteur list;;

let my_score = [la2;la3;la4;mi3];;
length my_score;;

let affiche_note note =
  print_int(note.o);
  print_newline();
;;
(* affiche_note la3;; *)

let rec applique l f =
    match l with
    | [] -> ()
    | a::lrestant -> f a ;applique lrestant  f
  ;;
applique my_score affiche_note ;;














(*  *)
