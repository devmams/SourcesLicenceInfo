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

<<<<<<< HEAD:L3/semestre2/Ocaml/tp_list.ml
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

=======
>>>>>>> 3643c0ee917f88f7c0be1dcebe97bb5410d4329d:L3/semestre2/Ocaml/tp_info.ml
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
<<<<<<< HEAD:L3/semestre2/Ocaml/tp_list.ml
    | a::lrestant -> f a ; applique lrestant f
  ;;

let transpose note intervalle =
  (note2midi note) + intervalle
;;

let rec applique_liste l f i =
  match l with
  | [] -> ()
  | a::lrestant -> print_int(f a i);print_newline() ; applique_liste lrestant f i
;;

(* applique_liste my_score transpose 0;;
applique_liste my_score transpose (1);;
applique_liste my_score transpose (-1);; *)



(*---------------------- Exo4 --------------------------*)
Random.self_init();;

let genere_list n =
  let rec genere_list_aux n l =
    match n with
    | 0 -> l
    | _ -> genere_list_aux (n-1) (n::l)
  in genere_list_aux n []
;;

let l = genere_list 10;;

let retient_premiers l p =
  let rec retient_premiers_aux l p ll=
    match p with
    | 0 -> ll
    | _ -> (hd l)::retient_premiers_aux (tl l) (p-1) (ll)
  in retient_premiers_aux l p []
;;

retient_premiers l 5;;

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

echange_aux l 0 9;;

let echange l a b =
  let elts = echange_aux l a b in
  let rec echange_rec l ind_a ind_b ll ind lll =
    print_int(length lll);
    match l with
    | [] -> ll
    | first::l_restant ->
        if(ind = ind_a) then (hd lll)::echange_rec l_restant a b ll (ind+1) (tl lll)
        else if(ind = ind_b) then (hd lll)::echange_rec l_restant a b ll (ind+1) lll
        else first::echange_rec l_restant a b ll (ind+1) lll
  in echange_rec l a b [] 0 elts
;;
echange l 3 8;;


let rec melange l n =
  match n with
  | 0 -> l
  | _ -> melange (echange l 0 (Random.int 10)) (n-1)
;;

melange l 5;;
=======
    | a::lrestant -> f a ;applique lrestant  f
  ;;
applique my_score affiche_note ;;









>>>>>>> 3643c0ee917f88f7c0be1dcebe97bb5410d4329d:L3/semestre2/Ocaml/tp_info.ml





(*  *)
