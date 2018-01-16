# AltitudeRoutes

![travis](https://travis-ci.org/mikkokotola/AltitudeRoutes.svg?branch=master)
[![Code Coverage](https://img.shields.io/codecov/c/github/mikkokotola/AltitudeRoutes/master.svg)](https://codecov.io/github/mikkokotola/AltitudeRoutes/)

AltitudeRoutes on sovellus lyhimmän reitin etsimiseen maaston korkeusmallissa. Sovellus käyttää reitinhakuun A*- ja Dijkstran algoritmeja ja vertailee niiden suorituskykyä reitinhaussa.

Sovellus käyttää lähdeaineistona Maanmittauslaitoksen avoimena datana tarjoamia 2 metrin resoluutiolla toteutettuja korkeusmalleja, jotka sisältävä 3000 x 3000 -resoluutioisen korkeusmallin 6 km x 6 km -maastoalueesta (Maanmittauslaitoksen Maastotietokannan 12/2017 aineistoa). Sovellus tukee myös MML:n 10 m -korkeusmallitiedostoja.

## Käyttöohje



### Karttatiedostojen hakeminen

Sovellukseen voi hakea lähdeaineistoksi Maanmittauslaitoksen 2 m tai 10 m korkeusmallitiedostoja (.asc):
* Mene <a href="https://tiedostopalvelu.maanmittauslaitos.fi/tp/kartta">MML:n avoimien aineistojen tiedostopalveluun</a>
* Etsi kartalta haluamasi alue
* Klikkaa vasemmasta valikosta Korkeusmalli -> Korkeusmalli 2 m tai 10 m
* Klikkaa haluamaasi korkeusmalliruutua kartalta
* Tilaa ja lataa tiedosto ilmaiseksi ohjeiden mukaan (edellyttää nimen ja s-postiosoitteen antamisen)

Toteutan sovelluksen HY:n tietojenkäsittelytieteen aineopintojen tietorakenteet ja algoritmit -harjoitustyönä 12/2017-1/2018.

## Dokumentit
<ul>
  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Maarittelydokumentti_Tiralabra_Kotola.pdf">Määrittelydokumentti</a></li>
  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Toteutusdokumentti_Tiralabra_Kotola.pdf">Toteutusdokumentti (työversio)</a></li>
  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Testausdokumentti_Tiralabra_Kotola.pdf">Testausdokumentti (työversio)</a></li>
</ul>

## Viikkoraportit
<ul>
  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Vkoraportti1_Tiralabra_Kotola.pdf">Viikkoraportti 1</a></li>

  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Vkoraportti2_Tiralabra_Kotola.pdf">Viikkoraportti 2</a></li>
  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Vkoraportti3_Tiralabra_Kotola.pdf">Viikkoraportti 3</a></li>
  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Vkoraportti4_Tiralabra_Kotola.pdf">Viikkoraportti 4</a></li>
  <li><a href="https://github.com/mikkokotola/AltitudeRoutes/blob/master/documentation/Vkoraportti5_Tiralabra_Kotola.pdf">Viikkoraportti 5</a></li>
</ul>

