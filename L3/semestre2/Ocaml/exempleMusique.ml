(*********SYSTEME DE SON *********)
(* Transformation d'une hauteur MIDI en fréquence *)
let midi2freq m =
    440.*.(2. ** (float_of_int(m-69)/. 12.));;

(* Joue une note midi pendans une durée duration avec le paquet SOX *)
(* let joueNote midi duration =
    Sys.command ("play -n synth " ^ string_of_float(duration ) ^ " sin " ^ string_of_float (midi2freq midi) );; *)




(*************** EXEMPLE DE GENERATEUR DE MORCEAUX A PARTIR D'UNE GRILLE D'ACCORDS *****************)

let rec gere_note l =
  match l with
  | [] -> 0
  | e::ll -> Sys.command ("play -n synth " ^ string_of_float(0.5) ^ " sin " ^ string_of_float (midi2freq e) ) ;gere_note ll
;;

let rec gere_note l =
Sys.command ("play -n synth " ^ string_of_float(0.5) ^ " sin " ^ string_of_float (midi2freq e) ) ;gere_note ll
;;


(* let montant a b c =
  gere_note a;
  (* print_string(" - "); *)
  gere_note (a+b);
  (* print_string(" - "); *)
  gere_note (a+c);
  (* print_string(" - "); *)
;;

let descendant a b c =
  gere_note (a+c);
  (* print_string(" - "); *)
  gere_note (a+b);
  (* print_string(" - "); *)
  gere_note (a);
  (* print_string(" - "); *)
;;

let sommet a =
  gere_note (a);
  gere_note (a);
;; *)

(* let rec arpegie a b c oct oct_init sens =
  if(oct == 0 && sens = true) then ()
  else if (oct == 0 && sens == false) then
    begin
    sommet a;
    arpegie (a-12) b c (oct_init) oct_init true
    end
  else if(sens == false) then
    begin
      montant a b c;geren
      arpegie (a+12) b c (oct-1) oct_init sens
    end(* let montant a b c =
  gere_note a;
  (* print_string(" - "); *)
  gere_note (a+b);
  (* print_string(" - "); *)
  gere_note (a+c);
  (* print_string(" - "); *)
;;

let descendant a b c =
  gere_note (a+c);
  (* print_string(" - "); *)
  gere_note (a+b);
  (* print_string(" - "); *)
  gere_note (a);
  (* print_string(" - "); *)
;;

let sommet a =
  gere_note (a);
  gere_note (a);
;; *)
  else if(sens == true) then
    begin
      descendant a b c;
      arpegie (a-12) b c (oct-1) oct_init sens
    end
;;

arpegie 48 4 7 3 3 false;; *)


let listMidi = [48;52;55;60;64;67;72;76;79;84;84;79;76;72;67;64;60;55;52;48];;

gere_note listMidi;;
