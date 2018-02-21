 open List;;
(*
let f1 g l =
  if (g(hd l) = (hd l))
    then hd l
    else hd l
;;


let f2 g h =
  if(hd g = 238) then g::h
  else g::h
;;

let f3 l e =
  e::l
;; *)


(* let retourne l =
  let rec retourneaux l1 l2 =
    match l1 with
    | [] -> l2
    | e::ll -> retourneaux ll (e::l2)
    in retourneaux l [];;

retourne [1;2;3;4;5];; *)


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

type score = | Ajoute of hauteur*score;;

let my_score = la3;;

myscore = la2*myscore;;



(*  *)
