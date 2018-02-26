open List;;
(*
let rec create_string n s c =
  match n with
  | 0 -> s
  | _ -> create_string (n-1) (c^s) c
;;

(* print_string(create_string 5 "" "* ");; *)

let rec carre n c =
  let s = create_string 5 "" c in
  match n with
  | 0 -> ()
  | _ -> print_string(s^"\n"); carre(n-1) c
;;

carre 5 "7 ";; *)

let l = [1;2;3;4;5;6;7;8;9];;

(* let occ l e =
  let rec occ_aux l n =
    match l with
    | [] -> n
    | e1::ll -> if(e1 = e) then occ_aux ll (n+1) else occ_aux ll n
    in occ_aux l 0
;;
occ l 1;; *)


let inv l =
  let rec inv_aux l lres =
    match l with
    | [] -> lres
    | e1::ll -> e1::(inv_aux ll lres)
    in inv_aux l []
;;

inv l;;

let suppr l e =
  let suppr_aux ll el lres =
    match ll with
    | [] -> lres
    | e1::l_restant -> if(e1 = el) then suppr_aux l_restant el lres
      else e1::(suppr_aux l_restant el lres)
      in suppr_aux l e []
;;



(*  *)
