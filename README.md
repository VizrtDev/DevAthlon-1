# First DevAthlon repository
(proof of concept)

Spielidee:
Am Anfang spawnen die Spieler in einer random generierten Welt, jeder Spieler hat seinen Kessel im Inventar.
Die Spieler können während einer 4 Minütigen Friedensphase Zutaten suchen und sich damit verschiedeste Dinge "brauen".
Diese Items haben magische Fähigkeiten. Es gibt eine 15%ige Wahrscheinlichkeit, dass nichts dabei herauskommt.
Es können auch items "verzaubert" werden.

Datenbank: MongoDB(syncDriver mit ThreadPool)

Lobby: 60 Sek
Pregame: 10 Sec
Peace: 5 Min
Ingame: 12 Min
Deathmatch: 2 Min
Restarting/Endgame: 15 Sek

Startequip:
Armor: volle Leder
Holz x10
Kessel
Wooden Sword

Itemwertbrechnung:
max insg: 99
max 3 items in cauldron
also 33 max Wert pro item