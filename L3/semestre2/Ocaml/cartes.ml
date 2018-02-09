Random.self_init();;

type face = Sept|Huit|Neuf|Dix|Valet|Dame|Roi|As ;;
type couleur = Pique|Coeur|Carreau|Trefle ;;
type carte = { f : face ; c : couleur };;

let roipique = { f = Roi; c = Pique};;
let ascoeur = { f = As; c = Coeur};;

let nbInCol n =
  match n with
  | 0  -> Pique
  | 1  -> Coeur
  | 2  -> Carreau
  | 3   -> Trefle
;;

let cardInVal c =
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
  let val_c1 = cardInVal c1 in
  let val_c2 = cardInVal c2 in
  if val_c1 > val_c2 then true
  else false
;;

let nbAlea()=
  Random.int 8 + 7
;;

let valInCard n =
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
  let n1 = nbAlea() in
  let n2 = Random.int 4 in
  {f = valInCard n1 ; c = nbInCol n2}
;;

let score_carte c =
match c with
  | Sept  -> 0
  | Huit  -> 0
  | Neuf  -> 0
  | Dix   -> 10
  | Valet -> 2
  | Dame  -> 3
  | Roi   -> 4
  | As    -> 11
;;
