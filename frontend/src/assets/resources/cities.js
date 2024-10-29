const cities = 
["Aleksandrów Łódzki",
"Alwernia",
"Andrychów",
"Annopol",
"Babimost",
"Baborów",
"Baranów Sandomierski",
"Barcin",
"Barczewo",
"Bardo",
"Barlinek",
"Barwice",
"Bełżyce",
"Biała",
"Biała Piska",
"Biała Podlaska",
"Biała Rawska",
"Białaczów",
"Białobrzegi",
"Biały Bór",
"Białystok",
"Biecz",
"Bielsko-Biała",
"Bierutów",
"Bieżuń",
"Bircza",
"Biskupiec",
"Bisztynek",
"Blachownia",
"Błaszki",
"Błażowa",
"Błonie",
"Bobolice",
"Bobowa",
"Bobrowniki",
"Bodzanów",
"Bodzentyn",
"Bogatynia",
"Bogoria",
"Boguchwała",
"Bojanowo",
"Bolesławiec",
"Bolimów",
"Bolków",
"Borek Wielkopolski",
"Borne Sulinowo",
"Brody",
"Brok",
"Brusy",
"Brwinów",
"Brzeg Dolny",
"Brzesko",
"Brzeszcze",
"Brześć Kujawski",
"Brzostek",
"Brzozów",
"Budzyń",
"Buk",
"Busko-Zdrój",
"Bychawa",
"Byczyna",
"Bydgoszcz",
"Bystrzyca Kłodzka",
"Bytom",
"Bytom Odrzański",
"Bytów",
"Cedynia",
"Cegłów",
"Chełm",
"Chełmek",
"Chęciny",
"Chmielnik",
"Chocianów",
"Chociwel",
"Chocz",
"Chodecz",
"Chojna",
"Choroszcz",
"Chorzele",
"Chorzów",
"Choszczno",
"Chrzanów",
"Ciechanowiec",
"Ciepielów",
"Cieszanów",
"Ciężkowice",
"Cybinka",
"Czaplinek",
"Czarna Białostocka",
"Czarna Woda",
"Czarne",
"Czarny Dunajec",
"Czchów",
"Czechowice-Dziedzice",
"Czemierniki",
"Czempiń",
"Czerniejewo",
"Czersk",
"Czerwieńsk",
"Czerwińsk nad Wisłą",
"Czerwionka-Leszczyny",
"Częstochowa",
"Człopa",
"Czyżew",
"Ćmielów",
"Daleszyce",
"Dąbie",
"Dąbrowa Białostocka",
"Dąbrowa Górnicza",
"Dąbrowa Tarnowska",
"Dąbrowice",
"Debrzno",
"Dębno",
"Dobczyce",
"Dobiegniew",
"Dobra",
"Dobra",
"Dobre",
"Dobre Miasto",
"Dobrodzień",
"Dobrzany",
"Dobrzyca",
"Dobrzyń nad Wisłą",
"Dolsk",
"Drawno",
"Drawsko Pomorskie",
"Drezdenko",
"Drobin",
"Drohiczyn",
"Drzewica",
"Dubiecko",
"Dukla",
"Działoszyce",
"Działoszyn",
"Dzierzgoń",
"Dziwnów",
"Elbląg",
"Frampol",
"Frombork",
"Gąbin",
"Gąsawa",
"Gdańsk",
"Gdynia",
"Gielniów",
"Glinojeck",
"Gliwice",
"Głogów Małopolski",
"Głogówek",
"Głowaczów",
"Głubczyce",
"Głuchołazy",
"Głuszyca",
"Gniew",
"Gniewkowo",
"Gogolin",
"Golczewo",
"Goleniów",
"Golina",
"Gołańcz",
"Gołdap",
"Goniądz",
"Goraj",
"Gorzów Śląski",
"Gorzów Wielkopolski",
"Gostyń",
"Gościno",
"Gowarczów",
"Góra",
"Góra Kalwaria",
"Górzno",
"Grabów",
"Grabów nad Prosną",
"Grodków",
"Grodzisk Mazowiecki",
"Grodzisk Wielkopolski",
"Grójec",
"Grudziądz",
"Gryfice",
"Gryfino",
"Gryfów Śląski",
"Halinów",
"Iłowa",
"Iłża",
"Inowłódz",
"Ińsko",
"Iwaniska",
"Iwonicz-Zdrój",
"Izbica",
"Izbica Kujawska",
"Jabłonowo Pomorskie",
"Jadów",
"Janikowo",
"Janowiec Wielkopolski",
"Janów Lubelski",
"Jaraczewo",
"Jarocin",
"Jasień",
"Jastarnia",
"Jastrowie",
"Jastrząb",
"Jastrzębie-Zdrój",
"Jawornik Polski",
"Jaworzno",
"Jaworzyna Śląska",
"Jedlicze",
"Jedlnia-Letnisko",
"Jedwabne",
"Jelcz-Laskowice",
"Jelenia Góra",
"Jeziorany",
"Jeżów",
"Jędrzejów",
"Józefów",
"Józefów nad Wisłą",
"Jutrosin",
"Kaczory",
"Kalisz",
"Kalisz Pomorski",
"Kalwaria Zebrzydowska",
"Kałuszyn",
"Kamieniec Ząbkowicki",
"Kamień Krajeński",
"Kamień Pomorski",
"Kamieńsk",
"Kamionka",
"Kańczuga",
"Karczew",
"Kargowa",
"Karlino",
"Kartuzy",
"Katowice",
"Kazimierz Dolny",
"Kazimierza Wielka",
"Kąty Wrocławskie",
"Kcynia",
"Kępice",
"Kępno",
"Kęty",
"Kielce",
"Kiernozia",
"Kietrz",
"Kikół",
"Kisielice",
"Kleczew",
"Kleszczele",
"Klimontów",
"Kluczbork",
"Kłecko",
"Kłobuck",
"Kłodawa",
"Knyszyn",
"Kobylin",
"Kock",
"Kolbuszowa",
"Kolonowskie",
"Koluszki",
"Kołaczyce",
"Koniecpol",
"Konin",
"Konstancin-Jeziorna",
"Końskie",
"Koprzywnica",
"Korfantów",
"Koronowo",
"Korsze",
"Kosów Lacki",
"Kostrzyn",
"Koszalin",
"Koszyce",
"Kowalewo Pomorskie",
"Koziegłowy",
"Kozienice",
"Koźmin Wielkopolski",
"Koźminek",
"Kożuchów",
"Kórnik",
"Krajenka",
"Kraków",
"Krapkowice",
"Krasnobród",
"Krobia",
"Krosno",
"Krosno Odrzańskie",
"Krośniewice",
"Krotoszyn",
"Kruszwica",
"Krynica-Zdrój",
"Krynki",
"Krzanowice",
"Krzepice",
"Krzeszowice",
"Krzywiń",
"Krzyż Wielkopolski",
"Książ Wielki",
"Książ Wielkopolski",
"Kunów",
"Kuźnia Raciborska",
"Latowicz",
"Lądek-Zdrój",
"Legnica",
"Lesko",
"Leszno",
"Leśna",
"Leśnica",
"Lewin Brzeski",
"Libiąż",
"Lidzbark",
"Lipiany",
"Lipsk",
"Lipsko",
"Lubawka",
"Lubień Kujawski",
"Lublin",
"Lubniewice",
"Lubomierz",
"Lubowidz",
"Lubraniec",
"Lubsko",
"Lubycza Królewska",
"Lutomiersk",
"Lututów",
"Lwówek",
"Lwówek Śląski",
"Łabiszyn",
"Łagów",
"Łapy",
"Łasin",
"Łask",
"Łaszczów",
"Łazy",
"Łęczna",
"Łobez",
"Łobżenica",
"Łochów",
"Łomianki",
"Łomża",
"Łopuszno",
"Łosice",
"Łódź",
"Maciejowice",
"Magnuszew",
"Maków Podhalański",
"Małogoszcz",
"Małomice",
"Margonin",
"Maszewo",
"Miasteczko Krajeńskie",
"Miastko",
"Michałowo",
"Miechów",
"Miejska Górka",
"Mielno",
"Mieroszów",
"Mieszkowice",
"Mieścisko",
"Międzybórz",
"Międzychód",
"Międzylesie",
"Międzyrzecz",
"Międzyzdroje",
"Miękinia",
"Mikołajki",
"Mikstat",
"Milicz",
"Miłakowo",
"Miłomłyn",
"Miłosław",
"Mirosławiec",
"Mirsk",
"Młynary",
"Modliborzyce",
"Mogielnica",
"Mogilno",
"Mońki",
"Morawica",
"Morąg",
"Mordy",
"Moryń",
"Mosina",
"Mrocza",
"Mrozy",
"Mszczonów",
"Murowana Goślina",
"Muszyna",
"Mysłowice",
"Myszyniec",
"Myślenice",
"Myślibórz",
"Nakło nad Notecią",
"Nałęczów",
"Namysłów",
"Narol",
"Nasielsk",
"Nekla",
"Nidzica",
"Niemcza",
"Niemodlin",
"Niepołomice",
"Nisko",
"Nowa Dęba",
"Nowa Sarzyna",
"Nowa Słupia",
"Nowe",
"Nowe Brzesko",
"Nowe Miasteczko",
"Nowe Miasto",
"Nowe Miasto nad Pilicą",
"Nowe Skalmierzyce",
"Nowe Warpno",
"Nowogard",
"Nowogrodziec",
"Nowogród",
"Nowogród Bobrzański",
"Nowy Dwór Gdański",
"Nowy Korczyn",
"Nowy Sącz",
"Nowy Staw",
"Nowy Tomyśl",
"Nowy Wiśnicz",
"Nysa",
"Oborniki",
"Oborniki Śląskie",
"Odolanów",
"Odrzywół",
"Ogrodzieniec",
"Okonek",
"Olecko",
"Olesno",
"Oleszyce",
"Oleśnica",
"Olkusz",
"Olsztyn",
"Olsztyn",
"Olsztynek",
"Olszyna",
"Opalenica",
"Opatowiec",
"Opatów",
"Opatówek",
"Opoczno",
"Opole",
"Opole Lubelskie",
"Orneta",
"Orzysz",
"Osieck",
"Osieczna",
"Osiek",
"Osjaków",
"Ostrołęka",
"Ostroróg",
"Ostrów Lubelski",
"Ostrzeszów",
"Ośno Lubuskie",
"Otmuchów",
"Otyń",
"Ozimek",
"Ożarów",
"Ożarów Mazowiecki",
"Pacanów",
"Paczków",
"Pajęczno",
"Pakość",
"Parczew",
"Parzęczew",
"Pasłęk",
"Pasym",
"Pelplin",
"Pełczyce",
"Piaseczno",
"Piaski",
"Piątek",
"Piekary Śląskie",
"Piekoszów",
"Pieniężno",
"Pieńsk",
"Pierzchnica",
"Pieszyce",
"Pilawa",
"Pilica",
"Pilzno",
"Pińczów",
"Piotrków Kujawski",
"Piotrków Trybunalski",
"Pisz",
"Piszczac",
"Piwniczna-Zdrój",
"Pleszew",
"Płock",
"Płoty",
"Pniewy",
"Pobiedziska",
"Poddębice",
"Pogorzela",
"Polanów",
"Police",
"Polkowice",
"Połaniec",
"Połczyn-Zdrój",
"Poniatowa",
"Poniec",
"Poznań",
"Prabuty",
"Praszka",
"Prochowice",
"Proszowice",
"Prószków",
"Pruchnik",
"Prudnik",
"Prusice",
"Pruszcz",
"Przecław",
"Przedbórz",
"Przedecz",
"Przemków",
"Przemyśl",
"Przyrów",
"Przysucha",
"Przytyk",
"Pszczyna",
"Pułtusk",
"Pyrzyce",
"Pyzdry",
"Rabka-Zdrój",
"Radków",
"Radłów",
"Radom",
"Radomyśl Wielki",
"Radoszyce",
"Radzymin",
"Radzyń Chełmiński",
"Rajgród",
"Rakoniewice",
"Raszków",
"Rawicz",
"Recz",
"Rejowiec",
"Resko",
"Reszel",
"Rogoźno",
"Ropczyce",
"Rozprza",
"Różan",
"Ruciane-Nida",
"Ruda Śląska",
"Rudnik nad Sanem",
"Rybnik",
"Rychtal",
"Rychwał",
"Rydzyna",
"Ryglice",
"Ryki",
"Rymanów",
"Ryn",
"Rzepin",
"Rzeszów",
"Rzgów",
"Sanniki",
"Serock",
"Sędziszów",
"Sędziszów Małopolski",
"Sępopol",
"Sępólno Krajeńskie",
"Sianów",
"Siechnice",
"Siedlce",
"Siedliszcze",
"Siemianowice Śląskie",
"Sieniawa",
"Siennica",
"Sienno",
"Sieraków",
"Siewierz",
"Skalbmierz",
"Skała",
"Skarszewy",
"Skaryszew",
"Skawina",
"Skępe",
"Skierniewice",
"Skoczów",
"Skoki",
"Skwierzyna",
"Sława",
"Słomniki",
"Słubice",
"Słupsk",
"Sobótka",
"Sochocin",
"Sokołów Małopolski",
"Sokółka",
"Solec Kujawski",
"Solec nad Wisłą",
"Sompolno",
"Sopot",
"Sosnowiec",
"Sośnicowice",
"Stary Sącz",
"Staszów",
"Stawiski",
"Stawiszyn",
"Stąporków",
"Stepnica",
"Stęszew",
"Stopnica",
"Stronie Śląskie",
"Strumień",
"Stryków",
"Strzegom",
"Strzelce Krajeńskie",
"Strzelce Opolskie",
"Strzeleczki",
"Strzelin",
"Strzelno",
"Strzyżów",
"Suchań",
"Suchedniów",
"Suchowola",
"Sulechów",
"Sulejów",
"Sulęcin",
"Sułkowice",
"Supraśl",
"Suraż",
"Susz",
"Suwałki",
"Swarzędz",
"Syców",
"Szadek",
"Szamocin",
"Szamotuły",
"Szczawnica",
"Szczebrzeszyn",
"Szczecin",
"Szczekociny",
"Szczucin",
"Szczuczyn",
"Szczytna",
"Szepietowo",
"Szlichtyngowa",
"Szprotawa",
"Sztum",
"Szubin",
"Szydłowiec",
"Szydłów",
"Ścinawa",
"Ślesin",
"Śmigiel",
"Śrem",
"Środa Śląska",
"Środa Wielkopolska",
"Świątniki Górne",
"Świebodzin",
"Świecie",
"Świerzawa",
"Świętochłowice",
"Świnoujście",
"Tarczyn",
"Tarnobrzeg",
"Tarnogród",
"Tarnów",
"Tłuszcz",
"Tolkmicko",
"Toruń",
"Torzym",
"Toszek",
"Trzcianka",
"Trzciel",
"Trzcińsko-Zdrój",
"Trzebiatów",
"Trzebinia",
"Trzebnica",
"Trzemeszno",
"Tuchola",
"Tuchów",
"Tuczno",
"Tuliszków",
"Tułowice",
"Turobin",
"Tuszyn",
"Twardogóra",
"Tychowo",
"Tychy",
"Tyczyn",
"Tykocin",
"Tyszowce",
"Ujazd",
"Ujazd",
"Ujście",
"Ulanów",
"Uniejów",
"Urzędów",
"Ustrzyki Dolne",
"Wadowice",
"Wałbrzych",
"Warka",
"Warszawa",
"Warta",
"Wasilków",
"Wąchock",
"Wąsosz",
"Węgliniec",
"Węgorzewo",
"Węgorzyno",
"Wiązów",
"Wielbark",
"Wieleń",
"Wielichowo",
"Wieliczka",
"Wieluń",
"Wieruszów",
"Więcbork",
"Wilamowice",
"Wiskitki",
"Wiślica",
"Witkowo",
"Witnica",
"Wleń",
"Władysławowo",
"Włocławek",
"Włodowice",
"Włoszczowa",
"Wodzisław",
"Wojnicz",
"Wolbórz",
"Wolbrom",
"Wolin",
"Wolsztyn",
"Wołczyn",
"Wołomin",
"Wołów",
"Woźniki",
"Wrocław",
"Wronki",
"Września",
"Wschowa",
"Wyrzysk",
"Wysoka",
"Wyszków",
"Wyszogród",
"Wyśmierzyce",
"Zabłudów",
"Zabrze",
"Zagórów",
"Zagórz",
"Zakliczyn",
"Zaklików",
"Zakroczym",
"Zalewo",
"Zamość",
"Zator",
"Zawadzkie",
"Zawichost",
"Ząbkowice Śląskie",
"Zbąszynek",
"Zbąszyń",
"Zduny",
"Zdzieszowice",
"Zelów",
"Zielona Góra",
"Ziębice",
"Złocieniec",
"Złoczew",
"Złoty Stok",
"Zwierzyniec",
"Zwoleń",
"Żabno",
"Żarki",
"Żarnów",
"Żarów",
"Żelechów",
"Żerków",
"Żmigród",
"Żnin",
"Żory",
"Żukowo",
"Żuromin",
"Żychlin"]
