# Lekce 10 – výjimky

Aplikace představuje jednoduchou kalkulačku ovládanou z terminálu.
Jednotlivé operace jsou realizovány jako samostatné třídy implementující rozhraní `Operace` (návrhový vzor [Command](https://cs.wikipedia.org/wiki/Command)).

Kalkulačka má displej, který umí zobrazit pouze 8 číslic.

Kalkulačka hlídá a neumožní provést neplatné operace – např. dělení nulou nebo výsledek výpočtu, který nelze zobrazit na displeji. 
