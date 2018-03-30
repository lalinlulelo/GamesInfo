package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DBConstructor implements InitializingBean {
	/*
	private List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png",
			"https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png",
			"https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png",
			"https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg",
			"https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");	
	
	// repositorio de la tabla usuarios 
	@Autowired
	private UserRepository userRepository;	
	
	// repositorio de la tabla compañias
	@Autowired
	private CompanyRepository companyRepository;
	
	// repositorio de la tabla eventos
	@Autowired
	private EventRepository eventRepository;
	
	// repositorio de la tabla juegos
	@Autowired
	private GameRepository gameRepository;
	
	// repositorio de la tabla comentarios
	@Autowired
	private CommentRepository commentRepository;
	
	// repositorio de la tabla anuncios
	@Autowired
	private ArticleRepository articleRepository;
	
	//repositorio de la tabla listas
	@Autowired
	private MyListRepository mylistRepository;	
	*/
	@Override
	@Transactional()
	public void afterPropertiesSet() throws Exception {
		/*
		// usuarios
		User Juan = new User("Juan", "123", "20/11/85", "Juan@gmail.com", "ROLE_USER");
		Juan.setIcon(icons.get(3));
		userRepository.save(Juan);
		User Pedro = new User("Pedro", "456", "15/6/92", "Pedro@hotmail.com", "ROLE_USER");;
		Pedro.setIcon(icons.get((int)Math.random()*6));
		userRepository.save(Pedro);
		User Guille = new User("Guille", "789", "25/2/96", "guillemelmor@gmail.com", "ROLE_USER");;
		Guille.setIcon(icons.get((int)Math.random()*6));
		userRepository.save(Guille);
		User Sergio = new User("Sergio", "1011", "4/2/95", "Sergio@hotmail.com", "ROLE_USER");;
		Sergio.setIcon(icons.get((int)Math.random()*6));
		userRepository.save(Sergio);
		User Agus = new User("Agus", "1213", "14/10/96", "Agus@hotmail.com", "ROLE_USER");;
		Agus.setIcon(icons.get((int)Math.random()*6));
		userRepository.save(Agus);
		User Admin=new User("Admin", "admin", "29/2/96", "Admin@hotmail.com", "ROLE_USER","ROLE_ADMIN");
		userRepository.save(Admin);
		
		// compañias
		Company Naughty_Dog = new Company("Naughty Dog", "EEUU", "PlayStation fisrt party",1984,"https://ih1.redbubble.net/image.37514287.0124/sticker,220x200-bg,ffffff-pad,220x200,ffffff.u2.jpg","https://www.naughtydog.com");
		companyRepository.save(Naughty_Dog);		
		Company Nintendo = new Company("Nintendo", "Japan", "Nintendo Company", 1889, "https://www.nintendo.com/images/social/fb-400x400.jpg", "https://www.nintendo.es/");
		companyRepository.save(Nintendo);
		Company Activision = new Company("Activision", "EEUU", "AAA Company", 1979, "https://static.blog.playstation.com/wp-content/uploads/avatars//tvj-D5G4_400x400.jpg?m=1475608799", "https://www.activision.com/es/home");
		companyRepository.save(Activision);
		Company Platinum_Games = new Company("Platinum Games", "Japan", "Witches Company", 2007, "https://pbs.twimg.com/profile_images/474105828337676288/IhP1K1eG_400x400.png", "https://www.platinumgames.com/");
		companyRepository.save(Platinum_Games);
		Company Square_Enix = new Company("Square Enix", "Japan", "Only make FF", 1975, "https://na.square-enix.com/sites/default/files/imagecache/post-image/image_gallery/587/6477ee99579631b75080a480f63952e8.jpg", "http://www.square-enix.com/");
		companyRepository.save(Square_Enix);
		Company Santa_Monica = new Company("Santa Monica", "EEUU", "Only God of War", 1999, "https://static.giantbomb.com/uploads/original/0/1992/1292491-sce_santa_monica_logo.png", "http://sms.playstation.com/");
		companyRepository.save(Santa_Monica);
		Company Epic_Games = new Company("Epic Games", "EEUU", "Caotic", 2003, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Epic_Games_logo.svg/1200px-Epic_Games_logo.svg.png", "https://es.wikipedia.org/wiki/Epic_Games");
		companyRepository.save(Epic_Games);
		
		// juegos
		Game TLOU = new Game("The last of us", Naughty_Dog, "survival horror", "Good game", 2013, 9.5, "https://media.vandal.net/m/23887/the-last-of-us-remastered-201449185610_1.jpg","http://www.thelastofus.playstation.com/");
		gameRepository.save(TLOU);		
		Game LOZBTW = new Game("Legend of Zelda Breath of Wild", Nintendo, "adventure", "Game of the Year 2017", 2017, 10, "http://polvar.ir/wp-content/uploads/2018/02/Nintendo_Switch_ESRB_cover.jpg", "https://es.wikipedia.org/wiki/The_Legend_of_Zelda:_Breath_of_the_Wild");
		gameRepository.save(LOZBTW);
		Game SMO = new Game("Super Mario Odyssey", Nintendo, "platform", "Great Mario Game", 2017, 9.5,	"https://www.virginmegastore.ae/medias/sys_master/root/h9f/h76/9104301326366/Super-Mario-Odyssey-375469-Detail.png", "https://www.nintendo.com/games/detail/super-mario-odyssey-switch");
		gameRepository.save(SMO);
		Game CB = new Game("Crash Bandicoot N Sane Trilogy", Activision, "platform", "Hard Game", 2017, 8, "http://www.eliteguias.com/img/caratulas/_og_/playstation4/crash-bandicoot-n-sane-trilogy.jpg", "https://es.wikipedia.org/wiki/Crash_Bandicoot");	
		gameRepository.save(CB);
		Game BYNT = new Game("Bayonetta 2", Platinum_Games, "Hack n Slash", "witch hunting angels", 2017, 8.5, "https://gocdkeys.com/images/games/bayonetta-2-nintendo-switch.jpg", "https://www.nintendo.es/Juegos/Nintendo-Switch/Bayonetta-2-1313750.html");
		gameRepository.save(BYNT);
		Game TMBR = new Game("Rise of the Tomb Raider", Square_Enix, "adventure", "Tomb Raider Aniversary", 2016, 8, "https://images-na.ssl-images-amazon.com/images/I/51Hyk3IIfwL.jpg", "https://es.wikipedia.org/wiki/Rise_of_the_Tomb_Raider");
		gameRepository.save(TMBR);
		Game TLOU2 = new Game("The last of us 2", Naughty_Dog, "survival horror", "Good game", 2018, 9.5, "https://images-na.ssl-images-amazon.com/images/I/511VhhJg%2BbL.jpg","https://www.playstation.com/es-es/games/the-last-of-us-part-ii-ps4/");
		gameRepository.save(TLOU2);
		Game GOW = new Game("God of War", Santa_Monica, "adventure", "Human vs Gods", 2018, 9.5, "https://i11d.3djuegos.com/juegos/11552/god_of_war_ps4__nombre_temporal_/fotos/ficha/god_of_war_ps4__nombre_temporal_-3754795.jpg", "https://www.playstation.com/es-es/games/god-of-war-ps4/");
		gameRepository.save(GOW);
		Game GOWX = new Game("Gears of War", Epic_Games, "adventure", "apocalipsis", 2016, 7, "https://i11c.3djuegos.com/juegos/1444/gears_of_war/fotos/ficha/gears_of_war-1681066.jpg", "https://es.wikipedia.org/wiki/Gears_of_War");
		gameRepository.save(GOWX);
		
		// eventos
		Event E3 = new Event("E3", "Los Angeles", 2018, 6, 15, 286, "muy chachi", "http://media.comicbook.com/2018/02/e3-2018-1080845.jpeg" );
		eventRepository.save(E3);
		Event GameGen = new Event("GameGen", "Madrid", 2018, 2, 22, 0, "a jugar", "https://upload.ticketing.events/event-logo/event-logo-2373.png");
		eventRepository.save(GameGen);
		Event GDC = new Event("GDC", "Berlin", 2018, 4, 15, 100, "ja!", "http://www.gdconf.com/img/fb.png");
		eventRepository.save(GDC);
		Event FS = new Event("Fun&Serious", "Bilbao", 2018, 11, 21, 30, "txangurro", "https://www.republica.com/deportes-electronicos/wp-content/uploads/sites/48/2016/11/logo-funserious-fondo-transparente.png");
		eventRepository.save(FS);
		Event PGW = new Event("PGW", "Paris", 2018, 1, 30, 18, "croisant", "http://www.nintenderos.com/wp-content/uploads/2016/09/Paris-Games-Week.jpg");
		eventRepository.save(PGW);
		
		// listas de juegos
		MyList mylist = new MyList("played");
		mylist.addGame(TLOU);
		TLOU.addMyList(mylist);
		mylist.addGame(CB);
		CB.addMyList(mylist);
		Juan.addList(mylist);
		mylist.addUser(Juan);
		mylistRepository.save(mylist);
		userRepository.save(Juan);
		gameRepository.save(TLOU);
		gameRepository.save(CB);
		mylist = new MyList("waiting for");
		mylist.addGame(SMO);
		SMO.addMyList(mylist);
		mylist.addGame(GOW);
		GOW.addMyList(mylist);
		Juan.addList(mylist);
		mylist.addUser(Juan);
		mylistRepository.save(mylist);
		userRepository.save(Juan);
		gameRepository.save(GOW);
		gameRepository.save(SMO);
		
		// articulos
		Article article = new Article (userRepository.findByName("Agus").get(0), "Nintendo Labo", "Nintendo saca un nuevo producto!", "Nintendo Labo es una nueva gama de experiencias interactivas con las que crear, jugar y descubrir, para inspirar a las mentes más creativas y los corazones más inquietos.", "https://statics.vrutal.com/m/7193/7193c0a1bd77df5d5aa766727a187b77.jpg");
		articleRepository.save(article);
		article = new Article(userRepository.findByName("Guille").get(0), "The Seven Deadly Sins: Knights of Britannia", "Nuevo juego de PS4 a la vista", "nos encontramos ante un juego de lucha tridimensional en el que nos podemos mover con total libertad por los escenarios de forma muy parecida a lo que vimos en J-Stars Victory VS.", "http://www.vostory.com/wp-content/uploads/2018/01/maxresdefault.jpg");
		articleRepository.save(article);
		article = new Article(userRepository.findByName("Sergio").get(0), "Kingdom Hearts 3", "¡Nuevas imágenes de Kingdom Hearts 3!", "El pasado fin de semana Square Enix presentó el mundo de Monstruos S.A para Kingdom Hearts III. Ahora nos llega una nueva galería de imágenes y renders que nos ponen los dientes largos esperando conocer más detalles.", "https://statics.vrutal.com/m/9168/9168def48ee8a753b36bde6312659da5.jpg");
		articleRepository.save(article);

		article = new Article(userRepository.findByName("Guille").get(0),
				"Sea of Thieves", "requisitos mínimos y recomendados en PC", 
				"En la imagen podemos ver hasta seis configuraciones diferentes, las cuales van desde una resolución de 540p y 30 frames hasta los 4K y 60 frames. No cabe duda de que el estudio quiere abarcar una amplia gama de equipos.", 
				"https://media.redadn.es/imagenes/sea-of-thieves-pc_317030.jpg");
		articleRepository.save(article);
		article = new Article(userRepository.findByName("Agus").get(0),
				"Fear Effect Sedna", "llegará a PC, PS4, Xbox One y Switch el 6 de marzo", 
				"Esta entrega, cuyos hechos se ambientan unos años después del primer título de la serie, estará disponible en próximo 6 de marzo en los sistemas PC, PlayStation 4, Xbox One y Nintendo Switch a través de las tiendas digitales de cada una de ellas. ", 
				"https://media.redadn.es/imagenes/fear-effect-sedna-pc-ps4-xbox-one_313695.jpg");
		articleRepository.save(article);
		*/
	}	
}
