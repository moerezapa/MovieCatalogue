package com.example.moviecatalogue.model

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite

object DataDummy {
    fun generateDummyMovieList(): List<MovieFavorite> {

        val movies = ArrayList<MovieFavorite>()

        movies.add(MovieFavorite(
            1,
            "1",
            "A Star Is Born",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            0.75,
            2000.00,
            "2018-10-03",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            false))
        movies.add(MovieFavorite(
            2,
            "2",
            "Alita: Battle Angel",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            0.72,
            3000.00,
            "14/02/2019",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
            false))
        movies.add(MovieFavorite(
            3,
            "3",
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            0.69,
            2500.00,
            "21/12/2018",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",false))
        movies.add(MovieFavorite(
            4,
            "4",
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            0.80,
            2800.00,
            "02/11/2018",
            "${R.drawable.poster_bohemian}",false))
        movies.add(MovieFavorite(
            5,
            "5",
            "Creed II",
            "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            0.69,
            200.00,
            "05/10/2018",
            "${R.drawable.poster_creed}",false))
        movies.add(MovieFavorite(
            6,
            "6",
            "Cold Pursuit",
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            0.57,
            2000.00,
            "08/02/2019",
            "${R.drawable.poster_cold_persuit}",false))
        movies.add(MovieFavorite(
            7,
            "7",
            "Fantastic Beasts: The Crimes of Grindelwald",
            "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
            0.69,
            600.00,
            "16/11/2018",
            "${R.drawable.poster_crimes}",false))
        movies.add(MovieFavorite(
            8,
            "8",
            "Glass",
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            0.67,
            2400.00,
            "18/01/2019",
            "${R.drawable.poster_glass}",false))
        movies.add(MovieFavorite(
            9,
            "9",
            "Avengers: Infinity War",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            0.75,
            760.00,
            "26/04/2018",
            "${R.drawable.poster_infinity_war}",false))
        movies.add(MovieFavorite(
            10,
            "10",
            "How to Train Your Dragon: The Hidden World",
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            0.78,
            2000.00,
            "09/01/2019",
            "${R.drawable.poster_how_to_train}",false))
        movies.add(MovieFavorite(
            11,
            "11",
            "Ralph Breaks the Internet",
            "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            0.72,
            2000.00,
            "21/11/2018",
            "${R.drawable.poster_ralph}", false))
        return movies
    }

    fun generateDummyTVList(): List<TVFavorite> {

        val tv = ArrayList<TVFavorite>()

        tv.add(TVFavorite(
            1,
            "1",
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            0.66,
            2000.00,
            "2010-10-10",
            "${R.drawable.poster_arrow}",false))
        tv.add(TVFavorite(
            2,
            "2",
            "Doom Patrol",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            0.76,
            2200.00,
            "2019-02-15",
            "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/drlfSCIlMKrEeMPhi8pqY4xGxj.jpg", false))
        tv.add(TVFavorite(
            3,
            "3",
            "Dragon Ball",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
            0.81,
            2500.00,
            "2019-02-15",
           "${R.drawable.poster_dragon_ball}",false))
        tv.add(TVFavorite(
            4,
            "4",
            "Fairy Tail",
            "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
            0.78,
            20.00,
            "2019-02-15",
           "${R.drawable.poster_fairytail}",false))
        tv.add(TVFavorite(
            5,
            "5",
            "Family Guy",
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            0.7,
            2200.00,
            "2019-02-15",
           "${R.drawable.poster_family_guy}",false))
        tv.add(TVFavorite(
            6,
            "6",
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            0.77,
            2000.00,
            "2019-02-15",
           "${R.drawable.poster_flash}",false))
        tv.add(TVFavorite(
            7,
            "7",
            "Game of Thrones",
            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            0.84,
            120.00,
            "2019-02-15",
           "${R.drawable.poster_god}",false))
        tv.add(TVFavorite(
            8,
            "8",
            "Gotham",
            "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            0.75,
            2000.00,
            "2019-02-15",
           "${R.drawable.poster_gotham}",false))
        tv.add(TVFavorite(
            9,
            "9",
            "Grey's Anatomy",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            0.82,
            500.00,
            "2019-02-15",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/jnsvc7gCKocXnrTXF6p03cICTWb.jpg",false))
        tv.add(TVFavorite(
            10,
            "10",
            "Hanna",
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            0.75,
            2000.00,
            "2019-02-15",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",false))
        tv.add(TVFavorite(
            11,
            "11",
            "Marvel's Iron Fist",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            0.66,
            2000.00,
            "2019-02-15",
           "${R.drawable.poster_iron_fist}",false))
        tv.add(TVFavorite(
            12,
            "12",
            "Naruto Shippūden",
            "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
            0.86,
            700.00,
            "2019-02-15",
           "${R.drawable.poster_naruto_shipudden}",false))
        
        return tv
    }
}