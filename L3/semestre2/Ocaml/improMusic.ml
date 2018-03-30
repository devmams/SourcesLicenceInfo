open List;;
Random.self_init();; (* Initialisatio du module random *)

(* Conversion d'une hauteur MIDI en fréquence Hz *)
let midi2freq m =
  440.*.(2. ** (float_of_int(m-69)/. 12.));;

(* Joue une note (midi,duration) avec le paquet SOX *)
let joueNote note = match note with
  (midi, duration) ->
  Sys.command ("play -n synth " ^ string_of_float(duration) ^ " sin " ^ string_of_float (midi2freq midi) );;

(* Joue une liste de notes (midi,duration) via le paquet SOX (commande pré-générée) *)
let play l =
  let rec concat x = match x with
    | [(midi,duration)] -> "play -n synth " ^ string_of_float(duration) ^ " sin " ^ string_of_float (midi2freq (midi))
    | (midi,duration)::tl -> (concat tl)^" : synth " ^ string_of_float(duration) ^ " sin " ^ string_of_float (midi2freq (midi))
    | [] -> ""
  in Sys.command (concat (List.rev l));;


(* Définition du type arbre *)
type 'a arbre = Feuille of 'a | Noeud of 'a * 'a arbre list;;

(*
  Crée un arbre aléatoire à partir d'une liste [l]
  Le parcours en profondeur de l'arbre correspond à [l]
*)
let listToTree l =
  (* Ajoute e dans l'arbre t aléatoirement en extension droite *)
  let rec addToTree t e =
    match t with
    | Feuille x   -> Noeud (x,[Feuille e]) (*cas d'une feuille, on ajoute à la suite*)
    | Noeud (x,l) -> begin
      if (Random.int 100) <= 10 then
        (* 10% de chance d'ajouter [e] aux fils *)
        Noeud (x, l@[Feuille(e)])
      else
        (* Sinon recursion sur le dernier fils *)
        Noeud (x, (List.rev (List.tl(List.rev l)))@[addToTree (List.hd (List.rev l)) e])
      end

    in match l with
    | [x]    -> Feuille x
    | hd::tl -> List.fold_left addToTree (Feuille hd) tl (* ajout séquentiel des notes *)
    | []     -> failwith "Liste vide"
  ;;

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

let rec melange l n =
  match n with
  | 0 -> l
  | _ -> melange (echange l 0 (Random.int (length l) + 1)) (n-1)
;;

let rec parcoursprof a =
  match a with
  | Feuille x -> [x]
  | Noeud (x,l1) ->
  x::List.fold_left (fun a b -> a@(parcoursprof b)) [] l1
;;

let rec parcoursProfBiasee a =
  match a with
  | Feuille x -> [x]
  | Noeud (x,l1) -> let l1 = melange l1 7 in
  x::List.fold_left (fun a b -> a@(parcoursProfBiasee b)) [] l1
;;

let elementList l =
  let nb = Random.int (length l) in
  let rec elementListaux l ind =
  if (nb = ind) then hd l
  else elementListaux (tl l) (ind+1)
  in elementListaux l 0
;;

let rec sousArbre a l =
  match a with
  | Feuille x -> x::l
  | Noeud (x,l1) -> let l1 = (elementList l1) in x::(sousArbre l1 l)
;;

let parcoursRacine a n =
  let rec parcoursRacineaux a n l =
    match n with
    | 0 -> l
    | _ -> (sousArbre a [])@(parcoursRacineaux a (n-1) l)
    in parcoursRacineaux a n []
;;


let rec parcoursPlusOuMoins12 a =
  let nb = (Random.int 100) in
  match a with
  | Feuille (midi,duration) -> if(nb < 10) then [midi-12,duration] else [midi+12,duration]
  | Noeud ((midi,duration),l1) ->
  if(nb < 10) then (midi-12,duration)::List.fold_left (fun a b -> a@(parcoursPlusOuMoins12 b)) [] l1
  else (midi+12,duration)::List.fold_left (fun a b -> a@(parcoursPlusOuMoins12 b)) [] l1
;;

(* Arbre aléatoire à partir de la partition *)

(* Une partition longue (midi,duree)*)
let partition = [
  (67,0.5); (60,0.5); (63,0.25); (65,0.25) ;(67,0.5); (60,0.5); (63,0.25); (65,0.25) ;(67,0.5); (60,0.5); (63,0.25); (65,0.25)
  ;(67,0.5); (60,0.5); (63,0.25); (65,0.25) ;(67,0.5); (60,0.5); (64,0.25); (65,0.25) ;(67,0.5); (60,0.5); (64,0.25); (65,0.25)
  ;(67,0.5); (60,0.5); (64,0.25); (65,0.25)  ;(67,0.5); (60,0.5); (64,0.25); (65,0.25) ;(67,1.5); (60,1.5); (63,0.25); (65,0.25)
  ;(67,1.0001); (60,1.0001); (63,0.25); (65,0.25) ;(62,0.5); (55,0.5); (58,0.25); (60,0.25) ;(62,0.5); (55,0.5); (58,0.25); (60,0.25)
  ;(62,0.5); (55,0.5); (58,0.25); (60,0.25) ;(62,0.5); (55,0.5); (58,0.5) ;(65,1.5); (58,1.5); (63,0.25); (62,0.25)
  ;(65,1.0001); (58,1.0001); (63,0.25); (62,0.25) ;(60,1.5)
];;

let arbre = listToTree partition;;

let l = parcoursprof arbre;;

let lalea = parcoursProfBiasee arbre;;

let alea12 = parcoursPlusOuMoins12 arbre;;

let e = elementList [1;2;3;4;5;6];;

let a = sousArbre arbre [];;

let parcRac = parcoursRacine arbre 10;;

play l;;


(* Partie élève *)
