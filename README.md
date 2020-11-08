Hi Nick! 
Hier is mijn opdracht tot nu toe.
Er missen nog wat onderdelen, dat komt omdat ik een aantal problemen niet goed heb ingeschat en dat daardoor de planning verkeerd liep.
Ik heb ook wat te veel tijd aan de front-end besteed, maar nu gaat de backend echt eerst voor.

Ik heb een hele specifieke feedbackvraag. Ik heb ervoor gekozen om een nummer dat wordt geüpload op te slaan op de lokale drive van een gebruiker. 
Dit voelt niet heel logisch omdat het nummer natuurlijk al op de drive stond, anders kon hij ook niet worden geüpload. 
Het probleem is dat ik geen grote server heb waar ik de files kan opslaan. 
Ik zie ook dat afgeraden wordt om nummers op te slaan in een database of in bijvoorbeeld de resources folder.
Ik sla wel de metadata van een nummer op in de database, waaronder de locatie op de drive waar dit nummer te vinden is.
Hoe zou jij dit aanpakken? Lokaal opslaan voelt vreemd, maar ik zie zo snel niet echt een andere goede optie.

Het is nu misschien wat lastig om de applicatie te testen, bij de volledige oplevering op 18 november zorg ik ervoor dat alles met testdata precies goed klaar staat.
Mocht je het wel willen proberen:

Om het uploaden van nummers te testen in postman:
- endpoint = POST http://localhost:8080/songs/?artist={artiestennaam}&name={songnaam}
- in de body kan je onder de key 'song' een file toevoegen (mp3, max grootte = 10mb).
- let op: het nummer komt momenteel dus in je home directory terecht (op windows 10 is dit standaard C:\Gebruikers\{jouwnaam}

Het Downloaden van nummers is te testen via:
- endpoint GET http://localhost:8080/songs?artist={artiestennaam}&name={songnaam}

Er is dus nog wat werk aan de winkel, maar ik ga er voor zorgen dat het gaat lukken!
Dank voor je tijd!
