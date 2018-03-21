(* open Graphics;;
Graphics.open_graph"";;
wait_next_event[Button_down];; *)
(* lineto 10 100;;
lineto 50 200;; *)
(* let marge = 30;; *)

type 'a arbre = Noeud of 'a * 'a arbre list | Feuille of 'a;;

let rec profondeur a = match a with
  | Feuille _ -> 1
  | Noeud (_,l) -> 1+List.fold_left (fun x l -> max x (profondeur l)) 0 l
;;

let rec largeur a = match a with
  | Feuille _ -> 1
  | Noeud (_,l) -> List.fold_left (fun x l -> x + (largeur l)) 0 l
;;

let rec parcoursprof a =
  match a with
  | Feuille x -> [x]
  | Noeud (x,l1) ->
  x::List.fold_left (fun a b -> a@(parcoursprof b)) [] l1
;;

let unarbre = Noeud (1, [Noeud (2, [Feuille 3; Noeud (4, [Feuille 5])]); Feuille 6; Noeud (7,[Feuille 8])])
;;

profondeur unarbre;;
largeur unarbre;;
parcoursprof unarbre;;
