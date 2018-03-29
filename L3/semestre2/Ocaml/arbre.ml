open List;;
Random.self_init();;
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

let unarbre = Noeud (1, [Noeud (2, [Feuille 3; Noeud (4, [Feuille 5])]); Feuille 6; Noeud (7,[Feuille 8])]);;

let l = [Noeud (2, [Feuille 3; Noeud (4, [Feuille 5])]); Feuille 6; Noeud (7,[Feuille 8])];;

let echange_aux l a b =
  let rec echange_aux_aux l ind_a ind_b ll ind =
    match l with
    | [] -> ll
    | first::l_restant ->
        if(ind = ind_a) then echange_aux_aux l_restant a b (first::ll) (ind+1)
        else if(ind = ind_b) then echange_aux_aux l_restant a b (first::ll) (ind+1)
        else echange_aux_aux l_restant a b ll (ind+1)
  in echange_aux_aux l a b [] 0
;;

echange_aux l 0 1;;

let echange l a b =
  let elts = echange_aux l a b in
  let rec echange_rec l ind_a ind_b ll ind lll =
    match l with
    | [] -> ll
    | first::l_restant ->
        if(ind = ind_a) then (hd lll)::echange_rec l_restant a b ll (ind+1) (tl lll)
        else if(ind = ind_b) then (hd lll)::echange_rec l_restant a b ll (ind+1) lll
        else first::echange_rec l_restant a b ll (ind+1) lll
  in echange_rec l a b [] 0 elts
;;
echange l 0 1;;


let rec melange l n =
  match n with
  | 0 -> l
  | _ -> melange (echange l 0 (Random.int (length l) + 1)) (n-1)
;;

melange l 5;;


let rec parcoursRandom a =
  match a with
  | Feuille x -> [x]
  | Noeud (x,l1) -> let l1 = melange l1 7 in
  x::List.fold_left (fun a b -> a@(parcoursprof b)) [] l1
;;

profondeur unarbre;;
largeur unarbre;;
parcoursprof unarbre;;
parcoursRandom unarbre;;
