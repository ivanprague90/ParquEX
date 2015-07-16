
;;;======================================================
;;;   ParquEX
;;;======================================================

(defmodule MAIN (export ?ALL))

;;*****************
;;* INITIAL STATE *
;;*****************

(deftemplate MAIN::attribute
   (slot name)
   (slot value)
   (slot certainty (default 100.0)))

(defrule MAIN::start
  (declare (salience 10000))
  =>
  (set-fact-duplication TRUE)
  (focus CHOOSE-QUALITIES ESSENCES))

(defrule MAIN::combine-certainties ""
  (declare (salience 100)
           (auto-focus TRUE))
  ?rem1 <- (attribute (name ?rel) (value ?val) (certainty ?per1))
  ?rem2 <- (attribute (name ?rel) (value ?val) (certainty ?per2))
  (test (neq ?rem1 ?rem2))
  =>
  (retract ?rem1)
  (modify ?rem2 (certainty (/ (- (* 100 (+ ?per1 ?per2)) (* ?per1 ?per2)) 100))))
  
 
;;******************
;; The RULES module
;;******************

(defmodule RULES (import MAIN ?ALL) (export ?ALL))

(deftemplate RULES::rule
  (slot certainty (default 100.0))
  (multislot if)
  (multislot then))

(defrule RULES::throw-away-ands-in-antecedent
  ?f <- (rule (if and $?rest))
  =>
  (modify ?f (if ?rest)))

(defrule RULES::throw-away-ands-in-consequent
  ?f <- (rule (then and $?rest))
  =>
  (modify ?f (then ?rest)))

(defrule RULES::remove-is-condition-when-satisfied
  ?f <- (rule (certainty ?c1) 
              (if ?attribute is ?value $?rest))
  (attribute (name ?attribute) 
             (value ?value) 
             (certainty ?c2))
  =>
  (modify ?f (certainty (min ?c1 ?c2)) (if ?rest)))

(defrule RULES::remove-is-not-condition-when-satisfied
  ?f <- (rule (certainty ?c1) 
              (if ?attribute is-not ?value $?rest))
  (attribute (name ?attribute) (value ~?value) (certainty ?c2))
  =>
  (modify ?f (certainty (min ?c1 ?c2)) (if ?rest)))

(defrule RULES::perform-rule-consequent-with-certainty
  ?f <- (rule (certainty ?c1) 
              (if) 
              (then ?attribute is ?value with certainty ?c2 $?rest))
  =>
  (modify ?f (then ?rest))
  (assert (attribute (name ?attribute) 
                     (value ?value)
                     (certainty (/ (* ?c1 ?c2) 100)))))

(defrule RULES::perform-rule-consequent-without-certainty
  ?f <- (rule (certainty ?c1)
              (if)
              (then ?attribute is ?value $?rest))
  (test (or (eq (length$ ?rest) 0)
            (neq (nth 1 ?rest) with)))
  =>
  (modify ?f (then ?rest))
  (assert (attribute (name ?attribute) (value ?value) (certainty ?c1))))

;;*******************************
;;* CHOOSE ESSENCE QUALITIES RULES *
;;*******************************

(defmodule CHOOSE-QUALITIES (import RULES ?ALL)
                            (import MAIN ?ALL))

(defrule CHOOSE-QUALITIES::startit => (focus RULES))

(deffacts the-essence-rules

  ; Rules for picking the best colore
		
  (rule (if sports is si)
        (then best-colore is chiaro with certainty 95 and
			  best-colore is bruno with certainty 35))

  (rule (if sports is no)
        (then best-colore is chiaro with certainty 95 and
			  best-colore is bruno with certainty 75))
			  
  (rule (if sports is nonso)
        (then best-colore is chiaro with certainty 50 and
			  best-colore is bruno with certainty 50 and
			  best-colore is rosso with certainty 50 and
			  best-colore is scuro with certainty 50))
			  
  (rule (if sports is nonsolo)
        (then best-colore is chiaro with certainty 50 and
			  best-colore is bruno with certainty 80 and
			  best-colore is rosso with certainty 50 and
			  best-colore is scuro with certainty 50))

  (rule (if style is classico and 
            walls is chiara)
        (then best-colore is bruno with certainty 95 and
			  best-colore is rosso with certainty 55))
			  
  (rule (if style is classico and 
            walls is mista)
        (then best-colore is chiaro with certainty 95 and
			  best-colore is rosso with certainty 55))
		
  (rule (if style is classico and 
            walls is scura)
        (then best-colore is rosso with certainty 95 and
			  best-colore is bruno with certainty 55))
		
  (rule (if style is moderno and 
            walls is chiara)
        (then best-colore is scuro with certainty 95 and
			  best-colore is bruno with certainty 55))
			  
  (rule (if style is moderno and 
            walls is mista)
        (then best-colore is scuro with certainty 95 and
			  best-colore is bruno with certainty 55))

  (rule (if style is moderno and 
            walls is scura)
        (then best-colore is chiaro with certainty 95 and
			  best-colore is bruno with certainty 35))
		
  (rule (if style is nonso)
        (then best-colore is scuro with certainty 50 and
              best-colore is bruno with certainty 50 and
              best-colore is rosso with certainty 50 and
			  best-colore is chiaro with certainty 50))
			  
(rule (if style is misto)
        (then best-colore is scuro with certainty 50 and
              best-colore is bruno with certainty 70 and
              best-colore is rosso with certainty 70 and
			  best-colore is chiaro with certainty 50))
			  
  (rule (if walls is nonso)
        (then best-colore is scuro with certainty 50 and
              best-colore is bruno with certainty 50 and
              best-colore is rosso with certainty 50 and
			  best-colore is chiaro with certainty 50))

  (rule (if color is chiaro)
        (then best-colore is chiaro with certainty 95 and
              best-colore is rosso with certainty 35 and
			  best-colore is bruno with certainty 15))

  (rule (if color is rosso)
        (then best-colore is rosso with certainty 95 and
              best-colore is chiaro with certainty 55 and
			  best-colore is bruno with certainty 55))
			  
  (rule (if color is intermedio)
        (then best-colore is rosso with certainty 55 and
			  best-colore is bruno with certainty 55))
		
  (rule (if color is bruno)
        (then best-colore is bruno with certainty 95 and
              best-colore is rosso with certainty 35 and
			  best-colore is scuro with certainty 55))
		
  (rule (if color is scuro)
        (then best-colore is scuro with certainty 95 and
              best-colore is rosso with certainty 15 and
			  best-colore is bruno with certainty 35))

  ; Rules for picking the best ossidazione
  
  (rule (if location is esterne)
        (then best-ossidazione is bassa with certainty 95 and
              best-ossidazione is alta with certainty 35))
			  
(rule (if location is entrambe)
        (then best-ossidazione is bassa with certainty 75 and
              best-ossidazione is alta with certainty 45))
  
  (rule (if location is nonso)
        (then best-ossidazione is bassa with certainty 50 and
              best-ossidazione is alta with certainty 50))

  (rule (if light is si)
        (then best-ossidazione is bassa with certainty 95 and
              best-ossidazione is alta with certainty 75))
		
  (rule (if light is no)
        (then best-ossidazione is alta with certainty 95 and
              best-ossidazione is bassa with certainty 75))
			  
  (rule (if light is parzialmente)
        (then best-ossidazione is alta))
  
  (rule (if light is nonso)
        (then best-ossidazione is alta with certainty 50 and
              best-ossidazione is bassa with certainty 50))
  
  ; Rules for picking the best durezza

  (rule (if walkable is molto and
            sports is si)
        (then best-durezza is elevata with certainty 95 and
              best-durezza is media with certainty 55 and
              best-durezza is molto-elevata with certainty 85))
		
  (rule (if walkable is molto and
            sports is no)
        (then best-durezza is media with certainty 95 and
              best-durezza is elevata with certainty 85 and
              best-durezza is molto-elevata with certainty 55))
			  
  (rule (if walkable is poco)
        (then best-durezza is media with certainty 95 and
              best-durezza is elevata with certainty 85 and
              best-durezza is molto-elevata with certainty 75))
		
  (rule (if walkable is nonso)
        (then best-durezza is media with certainty 50 and
              best-durezza is elevata with certainty 50 and
              best-durezza is molto-elevata with certainty 50))
		
  (rule (if sports is nonso)
        (then best-durezza is media with certainty 55 and
              best-durezza is elevata with certainty 55 and
              best-durezza is molto-elevata with certainty 55))
			  
  (rule (if sports is nonsolo)
        (then best-durezza is media with certainty 95 and
              best-durezza is elevata with certainty 75 and
              best-durezza is molto-elevata with certainty 55))
			  
  (rule (if placing is casa)
        (then best-durezza is molto-elevata with certainty 95 and
              best-durezza is elevata with certainty 55 and
              best-durezza is media with certainty 35))
		
  (rule (if placing is nonso)
        (then best-durezza is molto-elevata with certainty 33 and
              best-durezza is elevata with certainty 33 and
              best-durezza is media with certainty 33))
		
  (rule (if pet is si)
        (then best-durezza is media with certainty 35 and
              best-durezza is elevata with certainty 85 and
              best-durezza is molto-elevata with certainty 95))
			  
  (rule (if pet is no)
        (then best-durezza is media with certainty 95 and
              best-durezza is elevata with certainty 85 and
              best-durezza is molto-elevata with certainty 75))

  (rule (if room is cucinaobagno)
        (then best-durezza is molto-elevata with certainty 95))
		
  (rule (if room is lettooaltro)
        (then best-durezza is media with certainty 55 and
              best-durezza is elevata with certainty 55 and
              best-durezza is molto-elevata with certainty 55))
			  
  (rule (if room is nonso)
        (then best-durezza is media with certainty 55 and
              best-durezza is elevata with certainty 55 and
              best-durezza is molto-elevata with certainty 55))
			  
  (rule (if room is misto)
        (then best-durezza is media with certainty 55 and
              best-durezza is elevata with certainty 85 and
              best-durezza is molto-elevata with certainty 55))
			  
  ; Rules for picking the best assorbimento-umidita
  
  (rule (if location is esterne)
        (then best-assorbimento-umidita is basso with certainty 95 and
              best-assorbimento-umidita is medio with certainty 35 and
              best-assorbimento-umidita is alto with certainty 35))
  
  (rule (if location is interne)
        (then best-assorbimento-umidita is medio with certainty 95 and
              best-assorbimento-umidita is basso with certainty 75 and
              best-assorbimento-umidita is alto with certainty 55))
			  
(rule (if location is entrambe)
        (then best-assorbimento-umidita is medio with certainty 95 and
              best-assorbimento-umidita is basso with certainty 45 and
              best-assorbimento-umidita is alto with certainty 75))
		
  (rule (if location is nonso)
        (then best-assorbimento-umidita is basso with certainty 50 and
		      best-assorbimento-umidita is medio with certainty 50 and
              best-assorbimento-umidita is alto with certainty 50))
			  
  (rule (if placing is casa)
        (then best-assorbimento-umidita is basso with certainty 95 and
		      best-assorbimento-umidita is medio with certainty 55 and
              best-assorbimento-umidita is alto with certainty 55))
		
  (rule (if placing is nonso)
        (then best-assorbimento-umidita is basso with certainty 55 and
		      best-assorbimento-umidita is medio with certainty 55 and
              best-assorbimento-umidita is alto with certainty 55))

  (rule (if room is cucinaobagno)
        (then best-assorbimento-umidita is basso with certainty 95 and
		      best-assorbimento-umidita is medio with certainty 55 and
              best-assorbimento-umidita is alto with certainty 55))
		
  (rule (if room is lettooaltro)
        (then best-assorbimento-umidita is basso with certainty 75 and
              best-assorbimento-umidita is medio with certainty 75 and
              best-assorbimento-umidita is alto with certainty 55))
			  
  (rule (if room is nonso)
        (then best-assorbimento-umidita is basso with certainty 55 and
		      best-assorbimento-umidita is medio with certainty 55 and
              best-assorbimento-umidita is alto with certainty 55))
			  
  (rule (if room is misto)
        (then best-assorbimento-umidita is basso with certainty 75 and
		      best-assorbimento-umidita is medio with certainty 85 and
              best-assorbimento-umidita is alto with certainty 55))
  
  ; Rules for picking the best resistenza-escursioni
  
  (rule (if location is esterne)
        (then best-resistenza-escursioni is alta))
		
  (rule (if location is interne)
        (then best-resistenza-escursioni is alta with certainty 55 and
              best-resistenza-escursioni is bassa with certainty 75))
			  
(rule (if location is entrambe)
        (then best-resistenza-escursioni is alta with certainty 75 and
              best-resistenza-escursioni is bassa with certainty 45))
		
  (rule (if location is nonso)
        (then best-resistenza-escursioni is bassa with certainty 50 and
              best-resistenza-escursioni is alta with certainty 50))
			  
  (rule (if heating is si)
        (then best-resistenza-escursioni is alta with certainty 95 and
              best-resistenza-escursioni is bassa with certainty 35))
		
  (rule (if heating is no)
        (then best-resistenza-escursioni is bassa with certainty 75 and
              best-resistenza-escursioni is alta with certainty 75))
		
  (rule (if heating is nonso)
        (then best-resistenza-escursioni is bassa with certainty 55 and
              best-resistenza-escursioni is alta with certainty 75))
			  
  (rule (if heating is nontotalmente)
        (then best-resistenza-escursioni is bassa with certainty 45 and
              best-resistenza-escursioni is alta with certainty 85))
  
  ; Rules for picking the best fibratura
  
  (rule (if grain is dritta)
        (then best-fibratura is dritta with certainty 95 and
              best-fibratura is ondulata with certainty 75))
		
  (rule (if grain is ondulata)
        (then best-fibratura is ondulata with certainty 95 and
              best-fibratura is dritta with certainty 75))
		
  (rule (if grain is nonso)
        (then best-fibratura is dritta with certainty 50 and
              best-fibratura is ondulata with certainty 50))
			  
  (rule (if grain is mista)
        (then best-fibratura is dritta with certainty 50 and
              best-fibratura is ondulata with certainty 80))
  
  ; Rules for picking the best origine
  
  (rule (if budget is si)
        (then best-origine is europea))
		
  (rule (if budget is no)
        (then best-origine is esotica with certainty 95 and
              best-origine is europea with certainty 70))

)


;;************************
;;* ESSENCE SELECTION RULES *
;;************************

(defmodule ESSENCES (import MAIN ?ALL)
                 (export deffunction get-essence-list))

(deffacts any-attributes
  (attribute (name best-colore) (value any))
  (attribute (name best-ossidazione) (value any))
  (attribute (name best-durezza) (value any))
  (attribute (name best-assorbimento-umidita) (value any))
  (attribute (name best-resistenza-escursioni) (value any))
  (attribute (name best-fibratura) (value any))
  (attribute (name best-origine) (value any)))

(deftemplate ESSENCES::essence
  (multislot name (default ?NONE))
  (multislot colore (default any))
  (multislot ossidazione (default any))
  (multislot durezza (default any))
  (multislot assorbimento-umidita (default any))
  (multislot resistenza-escursioni (default any))
  (multislot fibratura (default any))
  (multislot origine (default any)))

(deffacts ESSENCES::the-essence-list 
  (essence (name Rovere) 
		(colore chiaro) 
		(ossidazione bassa) 
		(durezza elevata)
		(assorbimento-umidita alto)
		(resistenza-escursioni bassa)
		(fibratura dritta)
		(origine europea))
  (essence (name Faggio) 
		(colore chiaro) 
		(ossidazione bassa) 
		(durezza elevata)
		(assorbimento-umidita alto)
		(resistenza-escursioni bassa)
		(fibratura dritta)
		(origine europea))
  (essence (name Acero-Canadese) 
		(colore chiaro) 
		(ossidazione bassa)
		(durezza elevata)
		(assorbimento-umidita basso)
		(resistenza-escursioni bassa)
		(fibratura ondulata)
		(origine esotica))
  (essence (name Frassino) 
		(colore chiaro) 
		(ossidazione bassa) 
		(durezza elevata)
		(assorbimento-umidita medio)
		(resistenza-escursioni bassa)
		(fibratura dritta)
		(origine europea))
  (essence (name Noce-Europeo) 
		(colore bruno) 
		(ossidazione bassa) 
		(durezza media)
		(assorbimento-umidita medio)
		(resistenza-escursioni bassa)
		(fibratura ondulata)
		(origine europea))
  (essence (name Doussie) 
		(colore rosso) 
		(ossidazione alta)
		(durezza molto-elevata)		
		(assorbimento-umidita basso)
		(resistenza-escursioni alta)
		(fibratura ondulata)
		(origine esotica))
  (essence (name Iroko) 
		(colore bruno) 
		(ossidazione alta)
		(durezza media)
		(assorbimento-umidita basso)
		(resistenza-escursioni alta)
		(fibratura dritta)
		(origine esotica))
  (essence (name Teak) 
		(colore bruno) 
		(ossidazione alta) 
		(durezza elevata)
		(assorbimento-umidita basso)
		(resistenza-escursioni alta)
		(fibratura dritta)
		(origine esotica))
  (essence (name Ciliegio-Americano) 
		(colore rosso) 
		(ossidazione alta)
		(durezza media)
		(assorbimento-umidita basso)
		(resistenza-escursioni bassa)
		(fibratura dritta)
		(origine esotica))
  (essence (name Jatoba) 
		(colore scuro) 
		(ossidazione alta)
		(durezza molto-elevata)
		(assorbimento-umidita medio)
		(resistenza-escursioni bassa)
		(fibratura dritta)
		(origine esotica))
  (essence (name Wenge) 
		(colore scuro) 
		(ossidazione bassa)
		(durezza molto-elevata)
		(assorbimento-umidita medio)
		(resistenza-escursioni alta)
		(fibratura dritta)
		(origine esotica))
  (essence (name Ipe) 
		(colore bruno) 
		(ossidazione alta) 
		(durezza molto-elevata)
		(assorbimento-umidita basso)
		(resistenza-escursioni bassa)
		(fibratura ondulata)
		(origine esotica))
  (essence (name Afrormosia) 
		(colore scuro) 
		(ossidazione alta))
		(durezza elevata)
		(assorbimento-umidita medio)
		(resistenza-escursioni alta)
		(fibratura dritta)
		(origine esotica))
  
(defrule ESSENCES::generate-essences
  (essence (name ?name)
        (colore ?c)
        (ossidazione ?b)
        (durezza ?d)
		(assorbimento-umidita ?u)
		(resistenza-escursioni ?e)
		(fibratura ?f)
		(origine ?o))
  (attribute (name best-colore) (value ?c) (certainty ?certainty-1))
  (attribute (name best-ossidazione) (value ?b) (certainty ?certainty-2))
  (attribute (name best-durezza) (value ?d) (certainty ?certainty-3))
  (attribute (name best-assorbimento-umidita) (value ?u) (certainty ?certainty-4))
  (attribute (name best-resistenza-escursioni) (value ?e) (certainty ?certainty-5))
  (attribute (name best-fibratura) (value ?f) (certainty ?certainty-6))
  (attribute (name best-origine) (value ?o) (certainty ?certainty-7))
  =>
  (assert (attribute (name essence) (value ?name)
                     (certainty (min ?certainty-1 ?certainty-2 ?certainty-3 ?certainty-4
									 ?certainty-5 ?certainty-6 ?certainty-7)))))

(deffunction ESSENCES::essence-sort (?w1 ?w2)
   (< (fact-slot-value ?w1 certainty)
      (fact-slot-value ?w2 certainty)))
      
(deffunction ESSENCES::get-essence-list ()
  (bind ?facts (find-all-facts ((?f attribute))
                               (and (eq ?f:name essence)
                                    (>= ?f:certainty 20))))
  (sort essence-sort ?facts))
  

