Random.self_init();;

type face = Sept|Huit|Neuf|Dix|Valet|Dame|Roi|As ;;
type couleur = Pique|Coeur|Carreau|Trefle ;;
type carte = { f : face ; c : couleur };;

let roipique = { f = Roi; c = Pique};;
let ascoeur = { f = As; c = Coeur};;

let valInCol n =
  match n with
  | 0  -> Pique
  | 1  -> Coeur
  | 2  -> Carreau
  | 3   -> Trefle
;;

let faceInVal c =
  match c.f with
  | Sept  -> 7
  | Huit  -> 8
  | Neuf  -> 9
  | Dix   -> 10
  | Valet -> 11
  | Dame  -> 12
  | Roi   -> 13
  | As    -> 14
;;


let compare c1 c2 =
  let val_c1 = faceInVal c1 in
  let val_c2 = faceInVal c2 in
  if val_c1 > val_c2 then true
  else false
;;


let valInFace n =
  match n with
  | 7   -> Sept
  | 8   -> Huit
  | 9   -> Neuf
  | 10  -> Dix
  | 11  -> Valet
  | 12  -> Dame
  | 13  -> Roi
  | 14  -> As
;;

let tire_random() =
  let n1 = (Random.int 8 + 7) in
  let n2 = Random.int 4 in
  {f = valInFace n1 ; c = valInCol n2}
;;

let score_carte c =
match c.f with
  | Sept  -> 0
  | Huit  -> 0
  | Neuf  -> 0
  | Dix   -> 10
  | Valet -> 2
  | Dame  -> 3
  | Roi   -> 4
  | As    -> 11
;;

let rec bataille n pJ1 pJ2 =
  if n == 0 then
    begin
      print_endline("-------------------score final--------------------------");
      print_string("le joueur 1 : ");
      print_int(pJ1);
      print_endline(" pts");
      print_string("le joueur 2 : ");
      print_int(pJ2);
      print_endline(" pts");
      if pJ1 > pJ2 then print_endline("*************** le vainqueur est : le joueur 1 ***********************")
      else if pJ1 < pJ2 then print_endline("le vainqueur est : le joueur 2")
      else print_endline("pas de vainqueur egalité ")
    end
  else
    begin
    print_string("partie en cours ... tirage n° : ");
    print_int(101-n);
    print_endline("\nscore courant : ");
    print_string("joueur 1 : ");
    print_int(pJ1);
    print_string("\njoueur 2 : ");
    print_int(pJ2);
    print_newline();
    let c1 = tire_random() in let c2 = tire_random() in
    let res = score_carte c1 + score_carte c2 in
    print_int(res);
    print_newline();
    if compare c1 c2 then bataille (n-1) (res+pJ1) pJ2
    else bataille (n-1) pJ1 (res+pJ2)
    end
;;

bataille 100 0 0;;







  (*  *)
